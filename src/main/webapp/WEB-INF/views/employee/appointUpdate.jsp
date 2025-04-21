<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="page-container container-fluid">
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
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/employee/appointList">인사발령</a></li>
				<li class="breadcrumb-item active" aria-current="page">인사발령 수정</li>
			</ol>
		</nav>

	</div>
</div>
<br/>


<h2>인사발령 수정</h2>
<%-- ${teamList} --%>
<form:form method="post" modelAttribute="appointment">
	<table class="table table-bordered">
		<thead class="table-light">
			<tr>
				<th>항목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>인사발령고유번호</td>
				<td><input type="number" class="form-control" name="appId"
					value="${appointment.appId}" readonly> <form:errors
						path="appId" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>사번</td>
				<td><input type="text" class="form-control" name="empId"
					value="${appointment.empId}" readonly> <form:errors
						path="empId" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" class="form-control" name="name"
					value="${appointment.employee.name}" readonly> <form:errors
						path="empId" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>발령일자</td>
				<td><input type="date" class="form-control" name="appDate" id="appDate"
					value="${appointment.appDate}" > <form:errors
						path="appDate" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>발령구분</td>
				<td><input type="text" class="form-control" name="appType"
					value="${appointment.appType}"> <form:errors path="appType"
						class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>발령사유</td>
				<td><input type="text" class="form-control" name="appReason"
					value="${appointment.appReason}"> <form:errors
						path="appReason" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>소속팀</td>
				<td><input type="text" class="form-control" name="prevTeamId"
					value="${appointment.prevTeamName}" readonly> <form:errors
						path="prevTeamId" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>직책</td>
				<td><input type="text" class="form-control" name="prevJobId"
					value="${appointment.prevJobName}" readonly> <form:errors
						path="prevJobId" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>직급</td>
				<td><input type="text" class="form-control" name="prevRankId"
					value="${appointment.prevRankName}" readonly> <form:errors
						path="prevRankId" class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>부서</td>
				<td><input type="text" class="form-control"
					name="prevDepartmAentId" value="${appointment.prevDepartmentName}"
					readonly> <form:errors path="prevDepartmentId"
						class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>직무</td>
				<td><input type="text" class="form-control"
					name="prevPositionId" value="${appointment.prevPositionName}"
					readonly> <form:errors path="prevPositionId"
						class="text-danger" element="span" /></td>
			</tr>
			<tr>
				<td>발령 팀</td>
				<td><select name="newTeamId" id="newTeamId"
					class="form-control">
						<option value="">팀을 선택하세요</option>
						<c:forEach items="${teamList}" var="x">
							<option value="${x.teamId}">${x.teamName}</option>
						</c:forEach>
				</select> <form:errors path="newTeamId" class="text-danger" element="span" />
				</td>
			</tr>
			<tr>
				<td>발령 직책</td>
				<td><select name="newJobId" id="newJobId" class="form-control">
						<option value="">직책을 선택하세요</option>
						<c:forEach items="${jobList}" var="x">
							<option value="${x.jobId}">${x.jobName}</option>
						</c:forEach>
				</select> <form:errors path="newJobId" class="text-danger" element="span" />
				</td>
			</tr>
			<tr>
				<td>발령 직급</td>
				<td><select name="newRankId" id="newRankId"
					class="form-control">
						<option value="">직급을 선택하세요</option>
						<c:forEach items="${rankList}" var="x">
							<option value="${x.rankId}">${x.rankName}</option>
						</c:forEach>
				</select> <form:errors path="newRankId" class="text-danger" element="span" />
				</td>
			</tr>
			<tr>
				<td>발령 부서</td>
				<td><select name="newDepartmentId" id="newDepartmentId"
					class="form-control">
						<option value="">부서를 선택하세요</option>
						<c:forEach items="${departmentList}" var="x">
							<option value="${x.departmentId}">${x.departmentName}</option>
						</c:forEach>
				</select> <form:errors path="newDepartmentId" class="text-danger"
						element="span" /></td>
			</tr>
			<tr>
				<td>발령 직무</td>
				<td><select name="newPositionId" id="newPositionId"
					class="form-control">
						<option value="">직무를 선택하세요</option>
						<c:forEach items="${positionList}" var="x">
							<option value="${x.positionId}">${x.positionName}</option>
						</c:forEach>
				</select></td>
			</tr>
		</tbody>
	</table>
	<tr>
		<td colspan="2">
			<button type="submit" class="btn btn-primary">전송</button>
		</td>
	</tr>
</form:form>




<script>
    window.onload = function() {
        var today = new Date().toISOString().split('T')[0];  // 오늘 날짜를 yyyy-mm-dd 형식으로 가져옴
        document.getElementById('appDate').value = today;  // hireDate 필드에 값 설정
    };
</script>