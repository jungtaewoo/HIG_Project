document.addEventListener("DOMContentLoaded", function() {
	// PDF 다운로드
	function exportPDF() {
		const element = document.getElementById("table1");

		const opt = {
			margin: 1,
			filename: '출퇴현황.pdf',
			html2canvas: { scale: 2 },
			jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
		};

		html2pdf().from(element).set(opt).save();
	}

	// Excel 다운로드
	function downloadExcel() {
		var table = document.getElementById("table1");
		var wb = XLSX.utils.table_to_book(table, { sheet: "출퇴현황" });
		XLSX.writeFile(wb, "출퇴현황.xlsx");
	}

	// 현재 날짜를 YYYY-MM-DD 형식으로 설정
	var today = new Date();
	var day = ("0" + today.getDate()).slice(-2);
	var month = ("0" + (today.getMonth() + 1)).slice(-2);
	var year = today.getFullYear();

	document.getElementById('date').value = year + "-" + month + "-" + day;

	var departmentList = [
		<c:forEach items="${departmentList}" var="x">
			{
				"departmentName": "${x.departmentName}",
			"departmentId": "${x.departmentId}",
			"employeeCount": "${x.departmentEmployeeCount}" // 부서별 인원수
            } <c:if test="${not empty x}">,</c:if>
		</c:forEach>
	];
	var departmentAliveCount = [
		<c:forEach items="${todayAlive}" var="x">
			{"departmentName" : "${x.departmentName}",
			"AliveemployeeCount": "${x.departmentArrivedEmployeeCount}" //부서별 출근한 인원수
        } <c:if test="${not empty x}">,</c:if>
		</c:forEach>
	];

	var dpList = departmentList; //부서의 리스트와 총 인원수 표시
	var dac = departmentAliveCount; // 날짜별 근태가 있는 부서의 리스트와 근태찍은 인원 수 


	// 부서에 하위 팀만 보여주는 함수
	function filterTeams() {
		var selectedDept = document.getElementById("department").value;
		var teamSelect = document.getElementById("team");  // 팀 셀렉트 박스
		var teamOptions = document.querySelectorAll(".team-option");

		// 팀 셀렉트를 "전체"로 리셋
		teamSelect.value = "";
		teamOptions.forEach(function(option) {
			if (selectedDept === "" || option.dataset.departmentid === selectedDept) {
				option.style.display = "block";
			} else {
				option.style.display = "none";
			}
		});

		fetchFilteredData(); // 부서가 변경되었을 때 필터링된 데이터 가져오기
	}

	// 팀 변경 시 필터링된 데이터를 가져오는 함수
	$("#team").change(function() {
		fetchFilteredData(); // 팀 선택 시 필터링된 데이터 새로 불러오기
	});

	// 날짜 변경 시 필터링된 데이터를 가져오는 함수
	$("#date").change(function() {
		fetchFilteredData(); // 날짜 변경 시 데이터 새로 불러오기
	});

	// 필터링된 데이터를 가져오는 함수
	function fetchFilteredData() {
		var departmentId = $("#department").val();
		var teamId = $("#team").val();
		var date = $("#date").val();

		$.ajax({
			url: "/filter",
			type: "GET",
			data: {
				departmentId: departmentId,
				teamId: teamId,
				date: date
			},
			dataType: "json",
			success: function(data) {
				updateTable(data.attendanceList);
				updateChart(data);  // 차트 업데이트 추가
			},
			error: function() {
				alert("데이터를 불러오는 중 오류가 발생했습니다.");
			}
		});
	}

	// 테이블 업데이트
	function updateTable(data) {
		var tableBody = $("#table1 tbody");
		tableBody.empty(); // 기존 데이터 삭제
		if (data.length === 0) {
			tableBody.append("<tr><td class='text-center' colspan='9'>데이터가 없습니다.</td></tr>");
			return;
		}

		$.each(data, function(index, x) {
			var row =
				"<tr>" +
				"<td>" + (x.employee.department && x.employee.department.departmentName ? x.employee.department.departmentName : "-") + "</td>" +
				"<td>" + (x.employee.team && x.employee.team.teamName ? x.employee.team.teamName : "-") + "</td>" +
				"<td><a href='/check/Detail?empId=" + x.empId + "'>" + x.employee.name + "</a></td>" +
				"<td>" + (x.workStartTime ? x.workStartTime : "") + "</td>" +
				"<td>" + (x.workEndTime ? x.workEndTime : "") + "</td>" +
				"<td>" +
				(x.workingHours && x.workingMinutes ? x.workingHours + "시간 " + x.workingMinutes + "분" :
					(x.workingHours ? x.workingHours + "시간 0분" : "0시간 0분")) +
				"</td>" +
				"<td>" +
				(x.overtimeHours && x.overtimeHours !== "" ? x.overtimeHours + "시간 " + x.overtimeMinutes + "분" : "") +
				"</td>" +
				"<td>" +
				(x.nightWorkHours && x.nightWorkHours !== "" ? x.nightWorkHours + "시간 " + x.nightWorkMinutes + "분" : "") +
				"</td>" +
				"<td>" + (x.attendanceStatus ? x.attendanceStatus : "") + "</td>" +
				"</tr>";

			tableBody.append(row);  // 테이블 본문에 추가
		});

	}
	var optionsProfileVisit = {
		annotations: {
			position: "back",
		},
		dataLabels: {
			enabled: true,
			formatter: function(val, opt) {
				// 각 부서의 인원수에 맞게 값을 반환하도록 수정
				const department = dac[opt.dataPointIndex]; // 각 데이터 포인트에 맞는 부서 정보
				return `출근 인원: \${department.AliveemployeeCount}명`; // "출근 인원"으로 텍스트 변경

			},
			style: {
				fontSize: '12px',
				fontWeight: 'bold',
				colors: ['#000000'],
			},
			offsetY: -10, // 글자가 막대 위로 올라오도록 설정
		},
		chart: {
			type: "bar",
			height: 300,
		},
		fill: {
			opacity: 1,
		},
		series: [
			{
				name: "출근",
				data: dac.map(function(todayAlive) {
					return todayAlive.AliveemployeeCount + "명"; // 각 부서의 출근 인원 (이 예시에서는 부서별 인원수로 설정)
				}),
			},
		],
		colors: ["#435ebe"],
		xaxis: {
			categories: dpList.map(function(department) {
				return `\${department.departmentName} / 총 \${department.employeeCount}명`;
				// 부서 이름 옆에 총 인원수를 추가
			}),
		},
		yaxis: {
			min: 0,
			max: 60, // 총 인원보다 큰 최대값 설정
		},
	};

	// ApexCharts 차트 생성
	var chartProfileVisit = new ApexCharts(
		document.querySelector("#chart-profile-visit"),
		optionsProfileVisit
	);

	// 차트 렌더링
	chartProfileVisit.render();

	// 차트 업데이트 함수
	function updateChart(data) {
		var departmentAliveCount1 = (data.TodayAlive || []).map(function(x) {
			return {
				departmentName: x.departmentName,
				AliveemployeeCount: x.departmentArrivedEmployeeCount
			};
		});
		// 출근 인원 데이터 업데이트
		dac = departmentAliveCount1;

		// 각 부서에 해당하는 출근 인원 수를 업데이트
		var updatedDepartmentList = dpList.map(function(department) {
			var aliveCount = dac.find(d => d.departmentName === department.departmentName);
			return {
				departmentName: department.departmentName,
				employeeCount: department.employeeCount,
				AliveemployeeCount: aliveCount ? aliveCount.AliveemployeeCount : 0
			};
		});

		console.log("updatedDepartmentList", updatedDepartmentList)

		var updatedAliveCount = updatedDepartmentList.map(function(department) {
			return {
				name: department.departmentName,  // 부서명
				y: department.AliveemployeeCount  // 출근 인원 수
			};
		});

		console.log("updatedAliveCount", updatedAliveCount)
		// 차트 데이터 업데이트
		chartProfileVisit.updateOptions({
			xaxis: {
				categories: updatedDepartmentList.map(department =>
					`${department.departmentName} / 총 ${department.employeeCount}명`
				)
			}
		});
		console.log("여긴찍히나1")
		// 출근 인원 데이터 업데이트 (기존 차트 유지)
		chartProfileVisit.updateSeries([{
			name: "출근",
			data: updatedDepartmentList.map(department => department.AliveemployeeCount)
		}]);

		console.log("여긴찍히나2")
	}
}); 