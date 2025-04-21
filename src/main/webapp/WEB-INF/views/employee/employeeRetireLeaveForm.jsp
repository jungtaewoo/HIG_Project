<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f7f9fc;
	margin: 0;
	padding: 40px;
}

.container {
	width: 100%;
	padding: 30px 40px;
	background: #fff;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
	box-sizing: border-box;
}

h2 {
	margin-bottom: 30px;
	color: #2c3e50;
	border-bottom: 2px solid #2c3e50;
	padding-bottom: 10px;
}

form {
	margin-bottom: 50px;
}

h3 {
	color: #34495e;
	margin-top: 20px;
	margin-bottom: 20px;
}

label {
	display: inline-block;
	width: 130px;
	margin-bottom: 10px;
	font-weight: bold;
}

input[type="text"], input[type="date"] {
	padding: 8px;
	width: 250px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin-bottom: 10px;
}

button {
	background-color: #1f5aaa;
	color: white;
	border: none;
	padding: 8px 16px;
	margin-top: 10px;
	border-radius: 6px;
	cursor: pointer;
}

button:hover {
	background-color: #164c93;
}

hr {
	margin: 40px 0;
	border: none;
	border-top: 1px solid #ddd;
}

.message {
	color: green;
	font-weight: bold;
	margin-top: 15px;
}

/* 직원 검색 모달 */
#empModal {
	position: fixed;
	top: 15%;
	left: 50%;
	transform: translateX(-50%);
	width: 800px;
	height: 500px;
	background-color: #fff;
	border: 2px solid #333;
	border-radius: 10px;
	z-index: 1000;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.4);
	display: none;
	flex-direction: column;
	overflow: hidden;
}

#modalHeader {
	background-color: #f5f5f5;
	padding: 15px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#modalHeader h3 {
	margin: 0;
	font-size: 18px;
}

#modalHeader input {
	flex: 1;
	margin-left: 20px;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

#modalCloseBtn {
	background-color: #333;
	color: white;
	border: none;
	padding: 6px 12px;
	cursor: pointer;
	border-radius: 4px;
}

#modalCloseBtn:hover {
	background-color: #222;
}

#empTableContainer {
	flex: 1;
	overflow-y: auto;
	padding: 10px 20px;
}

#empTableContainer table {
	width: 100%;
	border-collapse: collapse;
	font-size: 14px;
}

#empTableContainer th, #empTableContainer td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: center;
}

#empTableContainer tbody tr:hover {
	background-color: #f0f0f0;
	cursor: pointer;
}
</style>
	<div class="container">
		<h2>퇴사 / 휴직 등록</h2>

		<!-- 퇴사 폼 -->
		<form
			action="${pageContext.request.contextPath}/employee/empUpdate/retire"
			method="post">
			<h3>퇴사 처리</h3>
			<div>
				<label>직원 선택:</label> <input type="text" id="retireEmpId"
					name="empId" readonly required>
				<button type="button" onclick="openEmpModal('retire')">직원
					검색</button>
			</div>

			<div>
				<label>퇴사일:</label> <input type="date" name="retireDate" required>
			</div>

			<div>
				<label>퇴사 사유:</label> <input type="text" name="retireReason"
					required>
			</div>

			<button type="submit">퇴사 등록</button>
		</form>

		<hr>

		<!-- 휴직 폼 -->
		<form
			action="${pageContext.request.contextPath}/employee/empUpdate/leave"
			method="post">
			<h3>휴직 처리</h3>
			<div>
				<label>직원 선택:</label> <input type="text" id="leaveEmpId"
					name="empId" readonly required>
				<button type="button" onclick="openEmpModal('leave')">직원 검색</button>
			</div>

			<div>
				<label>휴직 시작일:</label> <input type="date" name="leaveStartDate"
					required>
			</div>

			<div>
				<label>휴직 종료일:</label> <input type="date" name="leaveEndDate"
					required>
			</div>

			<div>
				<label>휴직 사유:</label> <input type="text" name="leaveReason" required>
			</div>

			<button type="submit">휴직 등록</button>
		</form>

		<c:if test="${not empty message}">
			<p class="message">${message}</p>
		</c:if>
	</div>

	<!-- 직원 검색 모달 -->
	<div id="empModal">
		<div id="modalHeader">
			<h3>직원 검색</h3>
			<input type="text" id="empSearchInput" placeholder="이름으로 검색"
				onkeyup="filterEmpList()">
			<button id="modalCloseBtn" onclick="closeEmpModal()">닫기</button>
		</div>
		<div id="empTableContainer">
			<table>
				<thead>
					<tr>
						<th>사번</th>
						<th>이름</th>
						<th>부서</th>
						<th>팀</th>
					</tr>
				</thead>
				<tbody id="empTableBody">
					<c:forEach var="emp" items="${empList}">
						<tr onclick="selectEmp('${emp.empId}', '${emp.name}')">
							<td>${emp.empId}</td>
							<td>${emp.name}</td>
							<td>${emp.departmentName}</td>
							<td>${emp.teamName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- JS -->
	<script>
        let selectedForm = null;

        function openEmpModal(target) {
            selectedForm = target;
            document.getElementById("empModal").style.display = "flex";
        }

        function closeEmpModal() {
            document.getElementById("empModal").style.display = "none";
        }

        function selectEmp(empId, name) {
            if (selectedForm === 'retire') {
                document.getElementById("retireEmpId").value = empId;
            } else if (selectedForm === 'leave') {
                document.getElementById("leaveEmpId").value = empId;
            }
            closeEmpModal();
        }

        function filterEmpList() {
            let input = document.getElementById("empSearchInput").value.toLowerCase();
            let rows = document.querySelectorAll("#empTableBody tr");
            rows.forEach(row => {
                let name = row.cells[1].innerText.toLowerCase();
                row.style.display = name.includes(input) ? "" : "none";
            });
        }
    </script>
