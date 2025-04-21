function loadChatRoomList() {
	const currentEmpId = sessionStorage.getItem("currentUserId");
	if (!currentEmpId) {
		alert("로그인 정보가 없습니다.");
		return;
	}

	$.ajax({
		url: "/messenger/chatRoom",
		type: "GET",
		data: { empId: currentEmpId },
		dataType: "json",
		success: function (chatRooms) {
			console.log("💬 채팅방 목록 불러오기 성공", chatRooms);

			let html = `
				<div id="chatRoomWrapper">
					<h4>내 채팅방 목록</h4>
					<ul id="chatRoomUl">
			`;

			chatRooms.forEach(room => {
				html += `
					<li class="chat-room-item"
						style="margin-bottom: 10px; border-bottom: 1px solid #ddd; padding-bottom: 5px;">
						<div onclick="enterChatRoom(${room.roomId})" style="cursor: pointer;">
							<strong>${room.displayRoomName || room.roomName || "채팅방"}</strong><br>
							<small>유형: ${room.roomType}</small>
						</div>
						<button onclick="event.stopPropagation(); leaveRoom(${room.roomId})"
							style="margin-top: 5px; font-size: 12px; background-color: #ff5c5c; color: white; border: none; padding: 3px 8px; border-radius: 3px;">
							나가기
						</button>
					</li>
				`;
			});

			html += `
					</ul>
				</div>

				<!-- Floating 버튼 -->
				<button id="openCreateChatModalBtn"
					style="position: fixed; bottom: 30px; right: 30px; background-color: #007bff; color: white; border: none; border-radius: 50px; padding: 15px 20px; font-size: 14px; cursor: pointer; z-index: 999;">
					채팅방 생성
				</button>

				<!-- 모달 -->
				<div id="createChatModal" style="display:none; position:fixed; top:50%; left:50%; transform:translate(-50%,-50%); background:white; padding:30px; border:1px solid #ccc; border-radius:10px; z-index:1000; width:500px; max-height:600px; overflow:auto;">
					<h3>그룹 채팅 생성</h3>
					<input type="text" id="groupRoomName" placeholder="채팅방 이름 입력" style="width:100%; padding:5px; margin-bottom:10px;" />
					<div id="empTreeContainer" style="max-height:400px; overflow:auto;"></div>
					<br/>
					<button id="createGroupChatBtn" style="margin-top:10px;">그룹 채팅방 만들기</button>
					<button id="closeChatModalBtn" style="margin-left:10px;">닫기</button>
				</div>
			`;

			$("#contentArea").html(html);
			loadEmployeesAsTree();

			$("#openCreateChatModalBtn").on("click", () => {
				$("#createChatModal").show();
			});
			$("#closeChatModalBtn").on("click", () => {
				$("#createChatModal").hide();
			});

			$("#createGroupChatBtn").on("click", function () {
				const roomName = $("#groupRoomName").val();
				const checkedEmpIds = [];

				$("input[name='groupMember']:checked").each(function () {
					checkedEmpIds.push($(this).val());
				});

				const currentUserId = sessionStorage.getItem("currentUserId");
				if (!checkedEmpIds.includes(currentUserId)) {
					checkedEmpIds.push(currentUserId);
				}

				if (!roomName) {
					alert("채팅방 이름을 입력해주세요.");
					return;
				}

				if (checkedEmpIds.length < 2) {
					alert("참여자는 최소 2명 이상이어야 합니다.");
					return;
				}

				const groupRoom = {
					roomName: roomName,
					empIdList: checkedEmpIds
				};

				$.ajax({
					url: "/messenger/insertGroupChat",
					type: "POST",
					contentType: "application/json",
					data: JSON.stringify(groupRoom),
					success: function (roomId) {
						alert("✅ 그룹 채팅방 생성 성공!");
						const popupName = `chatRoom_${roomId}`;
						const popupOptions = "width=600,height=500,left=300,top=100,resizable=yes,scrollbars=yes";

						window.open(`/messenger/room?roomId=${roomId}`, popupName, popupOptions);
						$("#createChatModal").hide();
						loadChatRoomList();
					},
					error: function () {
						alert("❌ 채팅방 생성 실패");
					}
				});
			});
		},
		error: function () {
			console.error("❌ 채팅방 목록 불러오기 실패");
			alert("채팅방 목록을 불러오는 중 오류 발생");
		}
	});
}

function enterChatRoom(roomId) {
	const popupName = `chatRoom_${roomId}`;
	const popupOptions = "width=600,height=500,left=300,top=100,resizable=yes,scrollbars=yes";
	window.open(`/messenger/room?roomId=${roomId}`, popupName, popupOptions);
}

function leaveRoom(roomId) {
	const empId = sessionStorage.getItem("currentUserId");
	if (!confirm("채팅방에서 나가겠습니까?")) return;

	$.ajax({
		url: "/messenger/deleteChatMember",
		type: "POST",
		data: { empId, roomId },
		success: function () {
			alert("채팅방을 나갔습니다.");
			loadChatRoomList();
		},
		error: function () {
			alert("채팅방 나가기 실패");
		}
	});
}

// 부서 > 팀 > 직원 구조로 출력
function loadEmployeesAsTree() {
	$.ajax({
		url: "/messenger/empList",
		type: "GET",
		success: function (data) {
			const tree = {};

			data.forEach(emp => {
				const dept = emp.department?.departmentName || '기타부서';
				const team = emp.teamName || '무소속';

				if (!tree[dept]) tree[dept] = {};
				if (!tree[dept][team]) tree[dept][team] = [];

				tree[dept][team].push(emp);
			});

			let html = `
				<div style="margin-bottom: 10px;">
					<input type="text" id="treeSearchInput" placeholder="이름, 팀, 부서 검색" style="padding:5px; width:200px;">
					<button id="treeSearchBtn">검색</button>
					<button id="collapseTreeBtn" style="margin-left:5px;">전체 닫기</button>
				</div>
				<ul id='orgTree'>
			`;

			for (const dept in tree) {
				html += `<li><span class='toggle'><i class="fas fa-building" style="margin-right:5px;"></i>${dept}</span><ul style='display:none;'>`;
				for (const team in tree[dept]) {
					html += `<li><span class='toggle'><i class="fas fa-users" style="margin-right:5px;"></i>${team}</span><ul style='display:none;'>`;
					tree[dept][team].forEach(emp => {
						const rank = emp.rankName || "직급없음";
						const status = emp.status === "온라인" ? "🟢 온라인" : "⚪ 오프라인";

						html += `<li class="tree-emp-item"
							data-name="${emp.empName}"
							data-dept="${dept}"
							data-team="${team}">
							<label>
								<i class="fas fa-user" style="margin-right:5px;"></i>
								<input type="checkbox" name="groupMember" value="${emp.empId}"> 
								${emp.empName} (${rank}) - ${status}
							</label>
						</li>`;
					});
					html += `</ul></li>`;
				}
				html += `</ul></li>`;
			}
			html += "</ul>";

			$("#empTreeContainer").html(html);

			// 토글 애니메이션
			$(document).off("click", ".toggle").on("click", ".toggle", function () {
				$(this).toggleClass("open");
				$(this).next("ul").stop(true, true).slideToggle(150);
			});

			// 검색 이벤트
			$("#treeSearchBtn").on("click", () => {
				const keyword = $("#treeSearchInput").val().trim();
				if (keyword) searchTree(keyword);
			});
			$("#treeSearchInput").on("keypress", function (e) {
				if (e.which === 13) $("#treeSearchBtn").click();
			});

			// 전체 닫기
			$("#collapseTreeBtn").on("click", () => {
				$(".toggle").removeClass("open");
				$("#orgTree ul").slideUp(150);
			});
		},
		error: function () {
			alert("직원 트리 로딩 실패");
		}
	});
}

function searchTree(keyword) {
	let found = false;
	$(".tree-emp-item").each(function () {
		const $empItem = $(this);
		const name = $empItem.data("name");
		const dept = $empItem.data("dept");
		const team = $empItem.data("team");

		const isMatch = name.includes(keyword) || dept.includes(keyword) || team.includes(keyword);

		if (isMatch) {
			found = true;

			// 트리 열기
			const teamLi = $empItem.closest("ul").closest("li");
			const teamToggle = teamLi.children(".toggle");
			const teamUl = teamToggle.next("ul");
			teamToggle.addClass("open");
			teamUl.stop(true, true).slideDown(150);

			const deptLi = teamLi.closest("ul").closest("li");
			const deptToggle = deptLi.children(".toggle");
			const deptUl = deptToggle.next("ul");
			deptToggle.addClass("open");
			deptUl.stop(true, true).slideDown(150);

			$(".tree-emp-item").css("background", "");
			$empItem.css("background", "#ffff99");

			$empItem[0].scrollIntoView({ behavior: "smooth", block: "center" });
		}
	});
	if (!found) alert("검색 결과가 없습니다.");
}

$(document).ready(function () {
	$("#chatRoomBtn").on("click", function (e) {
		e.preventDefault();
		loadChatRoomList();
	});
});
