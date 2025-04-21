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
		<input type="text" id="itemCode" placeholder="휴가 코드"> <input
			type="text" id="itemName" placeholder="휴가 이름"> <input
			type="text" id="itemDescription" placeholder="휴가 설명">
		<button onclick="saveItem()">저장</button>
	</div>

	<div class="box-container">
		<div class="box" id="itemList"></div>
		<div class="box" id="selectedList"></div>
	</div>

	<div class="table-wrapper">
		<div class="table-container">
			<h3>비선택 휴가</h3>
			<table>
				<thead>
					<tr>
						<th>휴가 코드</th>
						<th>휴가 이름</th>
						<th>휴가 설명</th>
					</tr>
				</thead>
				<tbody id="itemTableBody"></tbody>
			</table>
		</div>

		<div class="table-container">
			<h3>선택된 휴가</h3>
			<table>
				<thead>
					<tr>
						<th>휴가 코드</th>
						<th>휴가 이름</th>
						<th>휴가 설명</th>
					</tr>
				</thead>
				<tbody id="itemselectBody"></tbody>
			</table>
		</div>
	</div>
</div>

<script>
let annualMangeList = [];
<c:forEach items="${annualMangeList}" var="x">
    annualMangeList.push({
        annualCode: "${x.annualCode}",
        annualName: "${x.annualName}",
        annualInfo: "${x.annualInfo}",
        annualStatus: "${x.annualStatus}"
    });
</c:forEach>;

document.addEventListener("DOMContentLoaded", function () {
    let itemList = document.getElementById("itemList");
    let selectedList = document.getElementById("selectedList");
    let itemTableBody = document.getElementById("itemTableBody");
    let itemSelectBody = document.getElementById("itemselectBody");

    annualMangeList.forEach(item => {
        let newItem = createItemElement(item.annualCode, item.annualName, item.annualInfo, item.annualStatus);
        let newRow = createTableRow(item.annualCode, item.annualName, item.annualInfo);

        if (item.annualStatus === "N") {
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
    let code = document.getElementById("itemCode").value.trim();
    let name = document.getElementById("itemName").value.trim();
    let description = document.getElementById("itemDescription").value.trim();

    if (!code || !name || !description) {
        alert("모든 항목을 입력해주세요.");
        return;
    }

    // 항목 객체 생성
    let newItem = createItemElement(code, name, description, "N");
    let newRow = createTableRow(code, name, description);

    // 왼쪽 박스(비선택 항목)에 추가
    document.getElementById("itemList").appendChild(newItem);
    document.getElementById("itemTableBody").appendChild(newRow);
    
    // 서버에 항목 저장 요청
    fetch('/annual/Insert', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ annualCode: code, annualName: name, annualInfo: description, annualStatus: "N" })
    })
    .then(response => response.json())
    .then(data => console.log('Item saved:', data))
    .catch(error => console.error('Error:', error));
    
    // 입력 필드 초기화
    document.getElementById("itemCode").value = "";
    document.getElementById("itemName").value = "";
    document.getElementById("itemDescription").value = "";
}

//✅ 항목 추가 함수 (+, - 버튼 추가)
function createItemElement(code, name, description, status) {
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
            addItem(this, code, name, description);
        } else {
            removeItem(this, code, name, description);
        }
    };

    let deleteButton = document.createElement("button");
    deleteButton.textContent = "🗑"; // 삭제 버튼 (이모지 사용)
    deleteButton.onclick = function () {
        deleteItem(this, code);
    };

    actionContainer.appendChild(toggleButton);
    actionContainer.appendChild(deleteButton);

    itemHeader.appendChild(nameSpan);
    itemHeader.appendChild(actionContainer);
    item.appendChild(itemHeader);

    return item;
}

// ✅ 테이블 행 생성 함수 (code 추가)
function createTableRow(code, name, description) {
    let newRow = document.createElement("tr");
    newRow.innerHTML = `<td>\${code}</td><td>\${name}</td><td>\${description}</td>`;
    return newRow;
}

// ✅ 항목 선택 리스트로 이동 함수 (왼쪽 > 오른쪽)
function addItem(button, code, name, description) {
    let item = button.parentNode.parentNode;
    let selectedList = document.getElementById("selectedList");
    let itemSelectBody = document.getElementById("itemselectBody");
    let itemTableBody = document.getElementById("itemTableBody");

    let newItem = createItemElement(code, name, description, "Y");
    selectedList.appendChild(newItem);

    let newRow = createTableRow(code, name, description);
    itemSelectBody.appendChild(newRow);
	
    // 서버에 상태 업데이트 요청
    fetch('/annual/Update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ annualCode: code, annualName: name, annualInfo: description, annualStatus: "Y" })
    })
    .then(response => response.json())
    .then(data => console.log('Item status updated:', data))
    .catch(error => console.error('Error:', error));

    
    removeTableRow(itemTableBody, code);
    item.remove();
}

// ✅ 항목 비선택으로 이동 함수 ( 오른쪽 > 왼쪽 )
function removeItem(button, code, name, description) {
    let item = button.parentNode.parentNode;
    let itemList = document.getElementById("itemList");
    let itemTableBody = document.getElementById("itemTableBody");
    let itemSelectBody = document.getElementById("itemselectBody");

    let newItem = createItemElement(code, name, description, "N");
    itemList.appendChild(newItem);

    let newRow = createTableRow(code, name, description);
    itemTableBody.appendChild(newRow);
	
 // 서버에 상태 업데이트 요청
    fetch('/annual/Update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ annualCode: code, annualName: name, annualInfo: description, annualStatus: "N" })
    })
    .then(response => response.json())
    .then(data => console.log('Item status updated:', data))
    .catch(error => console.error('Error:', error));
    
    removeTableRow(itemSelectBody, code);
    item.remove();
}

// ✅ 테이블에서 특정 행 이동 함수 (code 기준으로 삭제)
function removeTableRow(tableBody, code) {
    let rows = tableBody.getElementsByTagName("tr");
    for (let i = 0; i < rows.length; i++) {
        if (rows[i].getElementsByTagName("td")[0].textContent === code) {
            tableBody.removeChild(rows[i]);
            break;
        }
    }
}
//✅ 항목 삭제 함수 (완전히 삭제)
function deleteItem(button, code) {
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
    removeTableRow(itemTableBody, code);

    // 🔹 부모 요소가 존재하는지 확인 후 삭제
    if (itemList.contains(item)) {
        itemList.removeChild(item);
    } else {
        console.warn("삭제할 항목이 itemList에 존재하지 않습니다.");
    }
    // 서버에서도 삭제 요청
    fetch('/annual/Delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ annualCode: code })
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

