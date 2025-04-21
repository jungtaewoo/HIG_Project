<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
h4 {
	text-align: center; /* h3 태그를 가운데 정렬 */
}
/* 카드 스타일 */
.card {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	padding: 20px;
}

.header-tab {
	display: flex;
	justify-content: space-between; /* 좌우로 배치 */
	align-items: center;
	padding: 10px 20px;
	background-color: #f0f0f0;
	border-bottom: 2px solid #ddd;
	font-weight: bold;
	color: #333;
}

.header-tab .right-items {
	margin-left: auto; /* 오른쪽 끝으로 배치 */
	display: flex; /* '빼기'와 '삭제'를 나란히 배치 */
	gap: 10px; /* 요소 사이에 간격 추가 */
}

.header-tab span {
	font-size: 16px;
}

/* 버튼 */
button {
	cursor: pointer;
	padding: 8px 16px;
	font-size: 19px;
	color: #fff;
	background-color: #0d6efd;
	border: none;
	border-radius: 5px;
	margin-right: 10px;
	transition: background-color 0.3s ease;
}

/* 입력 폼 */
.input-container {
	margin-top: 20px;
	text-align: center;
}

.input-wrapper {
	display: flex;
	flex-direction: column;
	gap: 12px;
	max-width: 350px;
	margin: 0 auto;
}

.input-container input, .input-container textarea {
	width: 100%;
	padding: 12px;
	font-size: 14px;
	border-radius: 6px;
	border: 1px solid #ccc;
	box-sizing: border-box;
	margin-bottom: 15px;
}

.input-container input:focus, .input-container textarea:focus {
	border-color: #007bff;
}

/* 박스(container) 스타일 */
.box-container {
	display: flex;
	justify-content: space-between;
	gap: 30px;
	margin-top: 20px;
}

.box {
	width: 48%; /* 리스트와 박스의 너비를 맞추기 위해 동일한 너비 설정 */
	min-height: 200px;
	border: 2px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa;
}

/* 리스트와 박스의 너비 맞추기 */
#itemList, #selectedList {
	width: 48%; /* 박스와 동일한 너비 */
}

.table-wrapper {
	display: flex;
	justify-content: space-between;
	gap: 20px;
	margin-top: 20px;
}

.table-container {
	width: 48%;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
	background-color: #fff;
}

th, td {
	padding: 12px;
	text-align: center;
	border: 1px solid #ddd;
}

th {
	background-color: #f0f0f0;
	font-weight: bold;
	white-space: nowrap;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f1f1f1;
}

/* 추가적인 스타일링 */
.item-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 12px;
}

.item-header span {
	font-weight: bold;
	color: #333;
	margin-left: 17px;
}

.item-header button {
	padding: 5px 10px;
	font-size: 14px;
	background-color: #f1f1f1;
	color: #000;
	border-radius: 5px;
	border: 1px solid #ddd;
}

.item-header button:hover {
	background-color: #007bff;
	color: white;
}

/* 업무 이름과 설명의 높이를 맞추기 */
.input-container {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	height: 100%;
}

.input-wrapper {
	display: flex;
	flex-direction: column;
	background-color: #ffffff;
	border-radius: 12px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.08);
	width: 100%;
	max-width: 600px;
	margin: auto;
}

.input-wrapper label {
	font-weight: 600;
	font-size: 14px;
	color: #000;
	margin-bottom: 4px;
}

.input-wrapper input[type="text"], .input-wrapper textarea {
	padding: 12px 14px;
	border: 1px solid #000;
	border-radius: 8px;
	background-color: #f9f9f9;
	transition: border-color 0.3s ease;
	font-size: 14px;
	width: 100%;
	box-sizing: border-box;
}

.input-wrapper input[type="text"]:focus, .input-wrapper textarea:focus {
	border-color: #0d6efd;
	background-color: #fff;
	outline: none;
}

.input-wrapper textarea {
	resize: vertical;
	min-height: 100px;
}

.input-container input, .input-container textarea {
	padding: 31px;
	font-size: 19px;
	border-radius: 8px;
	border: 2px solid #000;
	box-sizing: border-box;
	margin-bottom: 28px;
}

.input-container textarea {
	height: 150px; /* 원하는 높이로 조정 (예: 150px) */
}
</style>

<div class="page-container container-fluid">
  <div class="d-flex justify-content-between align-items-center mb-2">
    
    <!-- 좌측: 버튼 그룹 -->
    <div>
      <button type="button" class="btn btn-outline-secondary" onclick="history.back();">
        <i class="fas fa-arrow-left"></i>
      </button>
    </div>

    <!-- 우측: Breadcrumb -->
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb mb-0">
        <li class="breadcrumb-item fw-bold text-primary"><a href="${pageContext.request.contextPath }/account/login/home">📌Main</a></li>
        <li class="breadcrumb-item">
          <a href="${pageContext.request.contextPath }/workstauts/list">업무 항목 관리</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">휴가 항목 관리</li>
      </ol>
    </nav>

  </div>
</div>

<div class="card">
<h3>휴가 항목 관리</h3>
	<!-- 비활성 휴가와 활성된 휴가 테이블 -->
	<div class="table-wrapper">
		<div class="table-container">
			<h4>미사용 휴가</h4>
			<table>
				<thead>
					<tr>
						<th>휴가 코드</th>
						<th>휴가</th>
						<th>휴가 설명</th>
					</tr>
				</thead>
				<tbody id="itemTableBody"></tbody>
			</table>
		</div>

		<div class="table-container">
			<h4>사용 휴가</h4>
			<table>
				<thead>
					<tr>
						<th>휴가 코드</th>
						<th>휴가</th>
						<th>휴가 설명</th>
					</tr>
				</thead>
				<tbody id="itemselectBody"></tbody>
			</table>
		</div>
	</div>
</div>
<div class="card">
	<!-- 입력 부분: 왼쪽, 오른쪽 박스 사이에 입력란 배치 -->
	<div class="box-container">
		<div class="box" id="itemList"></div>

		<!-- 업무 이름과 설명을 세로로 배치 -->
		<div class="input-container">
			<div class="input-wrapper">
				<input type="hidden" id="itemCode" placeholder="휴가 코드">

				<div>
					<label for="itemName">휴가 이름</label> <input type="text"
						id="itemName" placeholder="ex) 연차">
				</div>
				<div>
					<label for="itemDescription">설명</label>
					<textarea id="itemDescription" placeholder="ex) 직원들의 기본적인 휴가"></textarea>
				</div>
			</div>
			<button onclick="saveItem()">휴가 추가</button>
		</div>

		<div class="box" id="selectedList"></div>
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

let headerTab1 = document.createElement("div");
headerTab1.id = "itemListHeader1";
headerTab1.className = "header-tab";
headerTab1.innerHTML = `
    <h5>미사용휴가</h5>
    <div class="right-items">
    <span>추가</span>
    <span>삭제</span>
    </div>
`;
let headerTab2 = document.createElement("div");
headerTab2.id = "itemListHeader2";
headerTab2.className = "header-tab";
headerTab2.innerHTML = `
    <h5>사용휴가</h5>
    <div class="right-items">
    <span>빼기</span>
    <span>삭제</span>
    </div>
`;
itemList.appendChild(headerTab1); // itemList 박스 안에 삽입
selectedList.appendChild(headerTab2); // selectedList 박스 안에 삽입
    
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

    if ( !name || !description) {
        alert("모든 항목을 입력해주세요.");
        return;
    }

    
    // 서버에 항목 저장 요청
    fetch('/annual/Insert', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ annualCode: code, annualName: name, annualInfo: description, annualStatus: "N" })
    })
    .then(response => response.json())
    .then(data =>  window.location.href="/annual/list"	)
    .catch(error => console.error('Error:', error));
    
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
    toggleButton.textContent = status === "N" ? "+" : "ㅡ";
    toggleButton.onclick = function () {
        if (status === "N") {
            addItem(this, code, name, description);
        } else {
            removeItem(this, code, name, description);
        }
    };

    let deleteButton = document.createElement("button");
    deleteButton.textContent = "X"; // 삭제 버튼 (이모지 사용)
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
    newRow.innerHTML = `<td>\${code}</td><td style="white-space: nowrap;">\${name}</td><td style="text-align: left;">\${description}</td>`;
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
        window.location.href="/annual/list"	
    })
    .catch(error => console.error('Error:', error));
}



</script>

