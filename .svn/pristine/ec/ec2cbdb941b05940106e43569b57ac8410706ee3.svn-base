<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>


<title>📋 평가 대상자 조회</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
body {
    background: linear-gradient(to right, #f8f9fa, #e9ecef);
    font-family: 'Segoe UI', sans-serif;
}

.card {
	background: #fff;
	border-radius: 10px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
	padding: 30px;
	margin-bottom: 20px;
}

.message.success {
	background-color: #e8f5e9;
	color: #2e7d32;
	border-left: 4px solid #66bb6a;
	padding: 10px;
	margin-bottom: 12px;
}

.message.error {
	background-color: #ffebee;
	color: #c62828;
	border-left: 4px solid #ef5350;
	padding: 10px;
	margin-bottom: 12px;
}

.search-row, .bulk-input-row {
	display: flex;
	gap: 10px;
	align-items: center;
	flex-wrap: wrap;
	margin-bottom: 5px;
}

.bulk-input-row {
	border: 1px solid #ddd;
	border-radius: 6px;
	padding: 10px;
	background-color: #fff;
}

input[type="text"], select {
	font-size: 16px;
	padding: 1px 1px;
	border: 1px solid #ccc;
	border-radius: 4px;
	height: 35px;
}

button {
	background-color: #4caf50;
	color: white;
	padding: 6px 12px;
	border: none;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background-color: #43a047;
}

table {
	width: 100%;
	border-collapse: collapse;
	font-size: 16px;
	margin-top: 10px;
}

th, td {
	border: 1px solid #ddd;
	padding: 6px 8px;
	text-align: center;
}

th {
	background-color: #f4f6f8;
	font-weight: bold;
}

tbody tr:hover {
	background-color: #f1faff;
}
b, strong {
    font-weight: bolder;
    font-size: 25px;
    margin-bottom: 100px;
}
</style>

<div class="page-container container-fluid" style="font-size: 18px;">
	<div class="d-flex justify-content-between align-items-center mb-2">
		<!-- 좌측: 버튼 그룹 -->
		<div>
			<button type="button" class="btn btn-outline-secondary"
				onclick="history.back();">
				<i class="fas fa-arrow-left"></i>
			</button>
		</div>

		<!-- 우측: Breadcrumb -->
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb mb-0">
				<li class="breadcrumb-item fw-bold text-primary"><a
					href="${pageContext.request.contextPath }/account/login/home">📌Main</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/evaluation/evaluationDashboard">평가 현황판</a></li>
				<li class="breadcrumb-item active" aria-current="page">평가 대상자 등록</li>
<%-- 				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/evaluation/evaluationCandidateList">평가 대상자 등록</a></li> --%>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/evaluation/evaluationTypeList">평가 기준 설정</a></li>
			</ol>
		</nav>
	</div>
</div>
<br />

<body>

<div class="card">
	<div class="message-box">
		<strong>📢 현재는 <c:out value="${currentYear}" />년 <c:choose>
				<c:when test="${currentHalf == '1'}">상반기</c:when>
				<c:when test="${currentHalf == '2'}">하반기</c:when>
				<c:otherwise>-</c:otherwise>
			</c:choose> 평가 대상자 등록 기간입니다. 
		</strong>
		<br/>
		<h5><a href="/evaluation/evaluationCandidateList?year=${currentYear}&half=${currentHalf}">[현재 등록기간 바로가기]</a></h5>
		<hr/>
	</div>

	<c:if test="${not empty success}">
		<div class="message success">✅ ${success}</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="message error">⚠ ${error}</div>
	</c:if>

	<form method="get" action="/evaluation/evaluationCandidateList">
		<div class="search-row">
		
			 <label>📅 년도</label>
			 <select name="year">
			 	<c:forEach var="y" begin="2020" end="2026">
					<option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
				</c:forEach>
			 </select>
			 
			 <label>🌓 구분</label>
			 <select name="half">
				<option value="1" <c:if test="${half == '1'}">selected</c:if>>상반기</option>
				<option value="2" <c:if test="${half == '2'}">selected</c:if>>하반기</option>
			 </select>
			 
			 <label>📌 직급</label>
			 <select name="rank">
				<option value="">전체</option>
				<c:forEach var="r" items="${rankList}">
					<option value="${r.rankId}"
						<c:if test="${r.rankId == rank}">selected</c:if>>${r.rankName}</option>
				</c:forEach>
			 </select>

			<button type="submit" style="background-color: #2196f3;">🔍 조회</button>
			<button type="button" style="background-color: #9e9e9e;" onclick="location.href='/evaluation/evaluationCandidateList?year=${currentYear}&half=${currentHalf}'">🔄
				초기화</button>
		</div>
	</form>
</div>


<c:if test="${isCurrent}">
	<div class="bulk-input-row">
		<label>⚙️ 일괄 적용 옵션:</label>
<!-- 		 <input type="text" id="bulkYear" placeholder="년도" />  -->
		 <select id="bulkYear">
		 	<c:forEach var="y" begin="2020" end="2026">
				<option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
			</c:forEach>
		 </select>
		 <select id="bulkHalf">
			<option value="">구분</option>
			<option value="1">상반기</option>
			<option value="2">하반기</option>
		</select> <select id="bulkTarget">
			<option value="">대상여부</option>
			<option value="Y">Y</option>
			<option value="N">N</option>
		</select>
		<button type="button" id="applyBulk" style="background-color: #ff9800;">⚙ 일괄 적용</button>

		<div style="display: flex; justify-content: flex-end; margin: 10px 0;">
			<button type="submit" form="evaluationForm" style="background-color: #4caf50;">✅ 선택 대상자 등록</button>
		</div>
	</div>

	<form:form method="post" action="/evaluation/evaluationCandidateUpdate"
		modelAttribute="wrapper" id="evaluationForm"
		onsubmit="return validateForm()">

		<table>
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll" /></th>
					<th>사번</th>
					<th>이름</th>
					<th>부서</th>
					<th>팀</th>
					<th>직급</th>
					<th>년도</th>
					<th>구분</th>
					<th>평가대상</th>
					<th>평가자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="candidate" items="${wrapper.candidateList}"
					varStatus="status">
					<tr>
						<td><input type="checkbox" class="rowCheck"
							name="candidateList[${status.index}].selected"
							data-index="${status.index}" /> <form:hidden
								path="candidateList[${status.index}].empId" /> <form:hidden
								path="candidateList[${status.index}].jobId" /> <form:hidden
								path="candidateList[${status.index}].teamId" /> <form:hidden
								path="candidateList[${status.index}].departmentId" /> <c:if
								test="${not empty candidate.evaluatorId}">
								<form:hidden path="candidateList[${status.index}].evaluatorId"
									value="${candidate.evaluatorId}" />
							</c:if></td>
						<td><c:out value="${candidate.empId}" /></td>
						<td><c:out value="${candidate.employee.name}" /></td>
						<td><c:out value="${candidate.department.departmentName}" /></td>
						<td><c:out value="${candidate.team.teamName}" /></td>
						<td><c:out value="${candidate.rank.rankName}" /></td>
						<td><form:input
								path="candidateList[${status.index}].evaluationYear"
								cssClass="evalYear" /></td>
						<td><form:select
								path="candidateList[${status.index}].halfYear"
								cssClass="halfSelect">
								<form:option value="">선택하세요</form:option>
								<form:option value="1">상반기</form:option>
								<form:option value="2">하반기</form:option>
							</form:select></td>
						<td><form:select
								path="candidateList[${status.index}].isTarget"
								cssClass="targetSelect">
								<form:option value="">선택하세요</form:option>
								<form:option value="Y">Y</form:option>
								<form:option value="N">N</form:option>
							</form:select></td>
						<td><c:choose>
								<c:when test="${not empty candidate.evaluatorName}">
									<c:out value="${candidate.evaluatorName}" />
								</c:when>
								<c:otherwise>
									<span style="color: #999;">-</span>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form:form>
</c:if>

<div class="card-body">
	<c:if test="${!isCurrent}">
		<div class="card" style="margin-top: 20px;">
			<div
				style="font-size: 1.2rem; font-weight: bold; margin-bottom: 10px; color: #444;">🕓
				과거 평가 내역</div>
			<table>
				<thead>
					<tr>
						<th>사번</th>
						<th>이름</th>
						<th>부서</th>
						<th>팀</th>
						<th>직급</th>
						<th>년도</th>
						<th>구분</th>
						<th>평가대상</th>
						<th>평가여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="candidate" items="${wrapper.candidateList}">
						<tr>
							<td><c:out value="${candidate.empId}" /></td>
							<td><c:out value="${candidate.employee.name}" /></td>
							<td><c:out value="${candidate.department.departmentName}" /></td>
							<td><c:out value="${candidate.team.teamName}" /></td>
							<td><c:out value="${candidate.rank.rankName}" /></td>
							<td><c:out value="${candidate.evaluationYear}" /></td>
							<td><c:choose>
									<c:when test="${candidate.halfYear == '1'}">상반기</c:when>
									<c:when test="${candidate.halfYear == '2'}">하반기</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose></td>
							<td><c:out value="${candidate.isTarget}" /></td>
							<td><c:out value="${candidate.evaluationStatus}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
</div>
</body>
<script>
        $(function () {
            $('#checkAll').on('click', function () {
                $('.rowCheck').prop('checked', this.checked);
            });

            $('#applyBulk').on('click', function () {
                const year = $('#bulkYear').val();
                const half = $('#bulkHalf').val();
                const isTarget = $('#bulkTarget').val();
				
                // 체크된 항목이 있는지 확인
                if ( $('.rowCheck:checked').length === 0 ) {
                    alert('직원을 선택해 주세요.');
                    return;
                }
                
                if (!year || !half || !isTarget) {
                    alert('일괄 적용 옵션을 설정해주세요.');
                    return;
                }

                $('.rowCheck:checked').each(function () {
                    const idx = $(this).data('index');
                    $('.evalYear').eq(idx).val(year);
                    $('.halfSelect').eq(idx).val(half);
                    $('.targetSelect').eq(idx).val(isTarget);
                });

                alert("일괄 적용 완료!");
            });
        });

        function validateForm() {
            let valid = true;
            let hasSelection = false;

            $('.rowCheck:checked').each(function () {
                hasSelection = true;
                const row = $(this).closest('tr');

                const year = row.find('.evalYear').val()?.trim();
                const half = row.find('.halfSelect').val();
                const target = row.find('.targetSelect').val();

                if (!year || !half || !target) {
                    alert("입력값 누락: 선택한 대상자 중 일부 항목이 비어 있습니다.\n년도/구분/대상 여부를 확인해주세요.");
                    valid = false;
                    return false;
                }
            });

            if (!hasSelection) {
                alert("선택된 대상자가 없습니다.");
                return false;
            }

            return valid;
        }
    </script>