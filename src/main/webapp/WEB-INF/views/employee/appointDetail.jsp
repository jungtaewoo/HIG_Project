<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/employee/appointList">인사발령 리스트</a></li>
				<li class="breadcrumb-item active" aria-current="page">인사발령 상세</li>
				
			</ol>
		</nav>

	</div>
</div>

<h2>인사발령 상세조회</h2>
<table class="table-bordered">

	<thead>
		<tr>
			<th>발령호수</th>
			<td>${appointment.appId}</td>
		</tr>
		<tr>
			<th>사번</th>
			<td>${appointment.empId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${appointment.employee.name}</td>
		</tr>
		<tr>
			<th>발령일자</th>
			<td id="appDate">${appointment.appDate }</td>
		</tr>
		<tr>
			<th>발령구분</th>
			<td>${appointment.appType }</td>
		</tr>
		<tr>
			<th>발령사유</th>
			<td>${appointment.appReason }</td>
		</tr>
		<tr>
			<th>발령 전 직책</th>
			<td>${appointment.prevPositionName }</td>
		</tr>
		<tr>
			<th>발령 전 직급</th>
			<td>${appointment.prevRankName }</td>
		</tr>
		<tr>
			<th>발령 전 부서</th>
			<td>${appointment.prevDepartmentName }</td>
		</tr>
		<tr>
			<th>발령 전 직무</th>
			<td>${appointment.prevJobName }</td>
		</tr>
		<tr>
			<th>발령 전 팀</th>
			<td>${appointment.prevTeamName }</td>
		</tr>
		<tr>
			<th>발령 후 직책</th>
			<td>${appointment.newPositionName }</td>
		</tr>
		<tr>
			<th>발령 후 직급</th>
			<td>${appointment.newRankName }</td>
		</tr>
		<tr>
			<th>발령 후 부서</th>
			<td>${appointment.newDepartmentName }</td>
		</tr>
		<tr>
			<th>발령 후 직무</th>
			<td>${appointment.newJobName }</td>
		</tr>
		<tr>
			<th>발령 후 팀</th>
			<td>${appointment.newTeamName }</td>
		</tr>
	</thead>
</table>
<c:url value="/employee/appointUpdate" var="appUpdate">
	<c:param name="appwho" value="${appointment.appId}" />
</c:url>
<a class="btn btn-primary" href="${appUpdate }">인사발령 정보 수정</a>

<script>
    window.onload = function() {
        var appDateElement = document.getElementById('appDate');
        
        if (appDateElement && appDateElement.innerText.trim()) {
            var appDateStr = appDateElement.innerText.trim(); // "2025-03-20 14:23:56"
            var dateOnly = appDateStr.split(' ')[0]; // "2025-03-20" 만 남김
            var parts = dateOnly.split('-'); // ["2025", "03", "20"]
            
            if (parts.length === 3) {
                var formattedDate = parts[0] + "년 " + parts[1] + "월 " + parts[2] + "일";
                appDateElement.innerText = formattedDate;
            }
        }
    };
</script>