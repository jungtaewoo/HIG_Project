<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 15.     	KHS            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	font-family: Arial, sans-serif;
}

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 30px;
}

.input-container {
	margin-bottom: 20px;
}

.input-container input {
	margin-right: 10px;
	padding: 5px;
}

.box-container {
	display: flex;
}

.box {
	width: 250px;
	height: 300px;
	border: 1px solid #ddd;
	overflow-y: auto;
	padding: 10px;
	margin: 0 10px;
}

.item {
	display: flex;
	flex-direction: column;
	padding: 5px;
	border-bottom: 1px solid #ddd;
}

.item-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

button {
	cursor: pointer;
	padding: 3px 8px;
}

.table-wrapper {
	display: flex;
	justify-content: space-between;
	width: 80%;
	margin-top: 20px;
}

.table-container {
	width: 48%;
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}
</style>

<div class="container">
	<div class="input-container">
		<input type="text" id="itemDeptId" placeholder="부서id" />
		<input type="text" id="itemName" placeholder="팀명" />
		<input type="text" id="itemLeader" placeholder="팀장명" />
		<input type="text" id="itemLeaderStatus" placeholder="팀장/팀원 여부" />
		<button onclick="saveItem()">저장</button>
	</div>

	<div class="box-container">
		<div class="box" id="itemList"></div>
		<div class="box" id="selectedList"></div>
	</div>

	<div class="table-wrapper">
		<div class="table-container">
			<h3>비선택 팀 상태</h3>
			<table>
				<thead>
					<tr>
						<th>부서명</th>
						<th>팀명</th>
						<th>팀장명</th>
						<th>팀장/팀원 여부</th>
					</tr>
				</thead>
				<tbody id="itemTableBody"></tbody>
			</table>
		</div>

		<div class="table-container">
			<h3>선택된 팀 상태</h3>
			<table>
				<thead>
					<tr>
						<th>부서명</th>
						<th>팀명</th>
						<th>팀장명</th>
						<th>팀장/팀원여부</th>
					</tr>
				</thead>
				<tbody id="itemselectBody"></tbody>
			</table>
		</div>
	</div>
</div>

<script>
let workStatusManageList = [];
<c:forEach items="${teamManageList}" var="team">
	workStatusManageList.push({
		dpartmentName: "${team.departmentName}",
		teamName: "${team.teamName}",
		teamEmp: "${team.empName}",
		teamHr: "${team.tmHr}",
		teamStatus: "${team.teamStatus}"
    });
</c:forEach>;

document.addEventListener("DOMContentLoaded", function () {
    let itemList = document.getElementById("itemList");
    let selectedList = document.getElementById("selectedList");
    let itemTableBody = document.getElementById("itemTableBody");
    let itemSelectBody = document.getElementById("itemselectBody");

    workStatusManageList.forEach(item => {
        let newItem = createItemElement(item.departmentName, item.teamName, item.empName, item.tmHr, item.teamStatus);
        let newRow = createTableRow(item.departmentName, item.teamName, item.empName, item.tmHr);

        if (item.teamStatus === "N") {
            itemList.appendChild(newItem);
            itemTableBody.appendChild(newRow);
        } else {
            selectedList.appendChild(newItem);
            itemSelectBody.appendChild(newRow);
        }
    });
});
//✅ 항목 저장 함수
function saveItem() {
	console.log("dfdf  1");

	let obj_deptid	        = document.getElementById("itemDeptId");
	let obj_name			= document.getElementById("itemName");
	let obj_leader			= document.getElementById("itemLeader");
	let obj_leader_status	= document.getElementById("itemLeaderStatus");


    let deptid  		= obj_deptid.value;
    let name			= obj_name.value;
    let leader			= obj_leader.value;
    let leaderstatus	= obj_leader_status.value;

    console.log("deptid",deptid);
    console.log("name",name);
    console.log("leader",leader);
    console.log("leaderstatus",leaderstatus);




    if (!deptid || !name || !leader || !leaderstatus) {
        alert("모든 항목을 입력해주세요.");
        return;
    }

    console.log("dfdf  2");

    // 항목 객체 생성
    let newItem = createItemElement(deptid, name, leader, leaderstatus, "N");
    let newRow  = createTableRow(deptid, name, leader, leaderstatus);

    // 왼쪽 박스(비선택 항목)에 추가
    document.getElementById("itemList").appendChild(newItem);
    document.getElementById("itemTableBody").appendChild(newRow);


    console.log("dfdf  3");


    // 서버에 항목 저장 요청
    fetch('/team/Insert', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({departmentId: deptid, teamName: name, teamEmp: leader, teamHr: leaderstatus, teamStatus: "N" })
    })
    .then(response => response.json())
    .then(data => console.log('Item saved:', data))
    .catch(error => console.error('Error:', error));


    console.log("dfdf  4");
    // 입력 필드 초기화
    document.getElementById("itemDeptId").value = "";
    document.getElementById("itemName").value = "";
    document.getElementById("itemLeader").value = "";
    document.getElementById("itemLeaderStatus").value = "";
}

//✅ 항목 추가 함수 (+, - 버튼 추가)
function createItemElement(deptid, name, leader, leaderstatus, teamStatus) {
    let item = document.createElement("div");
    item.className = "item";

    let itemHeader = document.createElement("div");
    itemHeader.className = "item-header";

    let nameSpan = document.createElement("span");
    nameSpan.textContent = name;

    let actionContainer = document.createElement("div"); // 버튼을 감쌀 컨테이너
    actionContainer.style.display = "flex";
    actionContainer.style.gap = "5px"; // 버튼 간격 조정

    let toggleButton = document.createElement("button");
    toggleButton.textContent = status === "N" ? "+" : "-";
    toggleButton.onclick = function () {
        if (status === "N") {
            addItem(this, deptid, name, leader, leaderstatus);
        } else {
            removeItem(this, deptid, name, leader, leaderstatus);
        }
    };

    let deleteButton = document.createElement("button");
    deleteButton.textContent = "🗑"; // 삭제 버튼 (이모지 사용)
    deleteButton.onclick = function () {
        deleteItem(this, name);
    };

    actionContainer.appendChild(toggleButton);
    actionContainer.appendChild(deleteButton);

    itemHeader.appendChild(nameSpan);
    itemHeader.appendChild(actionContainer);
    item.appendChild(itemHeader);

    return item;
}

// ✅ 테이블 행 생성 함수 (code 추가)
function createTableRow(deptid, name, leader, leaderstatus) {
    let newRow = document.createElement("tr");
    newRow.innerHTML = `<td>\${deptid}</td><td>\${name}</td><td>\${leader}</td><td>\${leaderstatus}</td>`;
    return newRow;
}

// ✅ 항목 선택 리스트로 이동 함수 (왼쪽 > 오른쪽)
function addItem(button, deptid, name, leader, leaderstatus) {
    let item = button.parentNode.parentNode;
    let selectedList = document.getElementById("selectedList");
    let itemSelectBody = document.getElementById("itemselectBody");
    let itemTableBody = document.getElementById("itemTableBody");

    let newItem = createItemElement(deptid, name, leader, leaderstatus, "Y");
    selectedList.appendChild(newItem);

    let newRow = createTableRow(deptid, name, leader, leaderstatus);
    itemSelectBody.appendChild(newRow);

    // 서버에 상태 업데이트 요청
    fetch('/team/Update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({departmentId: deptid, teamName: name, teamEmp: leader, teamHr: leaderstatus, teamStatus: "Y" })
    })
    .then(response => response.json())
    .then(data => console.log('Item status updated:', data))
    .catch(error => console.error('Error:', error));


    removeTableRow(itemTableBody, name);
    item.remove();
}

// ✅ 항목 비선택으로 이동 함수 ( 오른쪽 > 왼쪽 )
function removeItem(button, deptid, name, leader, leaderstatus) {
    let item = button.parentNode.parentNode;
    let itemList = document.getElementById("itemList");
    let itemTableBody = document.getElementById("itemTableBody");
    let itemSelectBody = document.getElementById("itemselectBody");

    let newItem = createItemElement(deptid, name, leader, leaderstatus, "N");
    itemList.appendChild(newItem);

    let newRow = createTableRow(deptid, name, leader, leaderstatus);
    itemTableBody.appendChild(newRow);

 // 서버에 상태 업데이트 요청
    fetch('/team/Update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({departmentId: deptid, teamName: name, teamEmp: leader, teamHr: leaderstatus, teamStatus: "N" })
    })
    .then(response => response.json())
    .then(data => console.log('Item status updated:', data))
    .catch(error => console.error('Error:', error));

    removeTableRow(itemSelectBody, name);
    item.remove();
}

// ✅ 테이블에서 특정 행 이동 함수 (name 기준으로 삭제)
function removeTableRow(tableBody, name) {
    let rows = tableBody.getElementsByTagName("tr");
    for (let i = 0; i < rows.length; i++) {
        if (rows[i].getElementsByTagName("td")[0].textContent === name) {
            tableBody.removeChild(rows[i]);
            break;
        }
    }
}
//✅ 항목 삭제 함수 (완전히 삭제)
function deleteItem(button, name) {
    let item = button.closest(".item");  // 🔹 가장 가까운 .item 요소 찾기
    let itemList = document.getElementById("itemList");
    let itemTableBody = document.getElementById("itemTableBody");

    console.log("삭제할 항목:", item);
    console.log("삭제 전 부모 요소:", item ? item.parentNode : "없음");

    if (!item) {
        console.warn("삭제할 항목을 찾을 수 없습니다.");
        return;
    }

    // 테이블에서도 삭제
    removeTableRow(itemTableBody, name);

    // 🔹 부모 요소가 존재하는지 확인 후 삭제
    if (itemList.contains(item)) {
        itemList.removeChild(item);
    } else {
        console.warn("삭제할 항목이 itemList에 존재하지 않습니다.");
    }
    // 서버에서도 삭제 요청
    fetch('/team/Delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ teamName: name })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`서버 오류: \${response.status}`);
        }
        return response.ok;  // 응답을 텍스트로 받음
    })
    .then(text => {
        if (!text) {
            console.log("서버에서 빈 응답을 받음.");
            return;
        }
        console.log('서버 응답:', text);  // 서버에서 응답받은 텍스트 로그 출력
    })
    .catch(error => console.error('Error:', error));
}



</script>

