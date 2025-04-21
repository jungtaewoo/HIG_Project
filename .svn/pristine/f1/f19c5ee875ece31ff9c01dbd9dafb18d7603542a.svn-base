<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h2>퇴사 면담 등록 일단은 JSP만 불러옴</h2>
<table class="col-10 table-bordered">
	<thead>
		<tr>
			<th>퇴사ID</th>
			<td>${x.resignId }</td>
		</tr>
		<tr>
			<th>사번</th>
			<td>${x.empId         }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${x.employee.name }</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>${x.department.departmentName }</td>
		</tr>
		<tr>
			<th>팀</th>
			<td>${x.team.teamName }</td>
		</tr>
		<tr>
			<th>직무</th>
			<td>${x.job.jobName   }</td>
		</tr>
		<tr>
			<th>직급</th>
			<td>${x.rank.rankName }</td>
		</tr>
		<tr>
			<th>직책</th>
			<td>${x.position.positionName }</td>
		</tr>
		<tr>
			<th>퇴사일자</th>
			<td>
			 	<fmt:parseDate value="${x.resignDate}" pattern="yyyy-MM-dd HH:mm:ss" var="z" />
				<fmt:formatDate value="${z}" pattern="yyyy / MM / dd" />
			</td>
		</tr>
		<tr>
			<th>계정 상태</th>
			<td>${x.idStatus      }</td>
		</tr>
		<tr>
			<th>사유</th>
			<td>${x.resignReason  }</td>
		</tr>
		<tr>
			<th>유형</th>
			<td>${x.resignType    }</td>
		</tr>
		<tr>
			<th>인터뷰 여부</th>
			<td>${x.resignInterview }</td>
		</tr>
	</thead>
</table>
<!-- <table> -->
<!-- <tr> -->
<!-- 	<td colspan="2"> -->
<!-- 		<button onclick="history.go(-1);">뒤로가기</button> -->
<%-- 			<c:url value="/employee/퇴사면담jsp로 보내야하는데.." var="퇴사면담어쩌고저쩌고 " > --%>
<%-- 				<c:param name="resign" value="${resignation.resignId}"/> --%>
<%-- 			</c:url> --%>
<%-- 		<a class="btn btn-primary" href="${퇴사면담어쩌고저쩌고}">회원정보 수정</a> --%>
<!-- 	</td> -->
<!-- </tr> -->
<!-- </table> -->
<script>
    window.onload = function() {
        var hireDateElement = document.getElementById('hireDate');
        
        if (hireDateElement && hireDateElement.innerText.trim()) {
            var hireDateStr = hireDateElement.innerText.trim(); // "2025-03-20 14:23:56"
            var dateOnly = hireDateStr.split(' ')[0]; // "2025-03-20" 만 남김
            var parts = dateOnly.split('-'); // ["2025", "03", "20"]
            
            if (parts.length === 3) {
                var formattedDate = parts[0] + "년 " + parts[1] + "월 " + parts[2] + "일";
                hireDateElement.innerText = formattedDate;
            }
        }
    };
</script>