<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h2>퇴사 직원 리스트</h2>
<a href="/employee/resignFormUIController">퇴사자 등록</a>
<div class="card">
	<table class="table-bordered">
		<thead>
			<tr>
				<th>퇴사ID</th>
				<th>사번</th>
				<th>이름</th>
				<th>부서</th>
				<th>팀</th>
				<th>직무</th>
				<th>직급</th>
				<th>직책</th>
				<th>퇴사일자</th>
				<th>계정 상태 (Y:N)</th>
				<th>사유</th>
				<th>유형</th>
				<th>인터뷰 여부</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${resignList }" var="x">
				<tr>
					<td>
						<c:url value="/employee/resignDetail" var="resignDetailUrl">
							<c:param name="resign" value="${x.resignId }"/>
						</c:url>
						<a href="${resignDetailUrl }">${x.resignId }</a>
					</td>
					<td>${x.empId         }</td>
					<td>${x.employee.name }</td>
					<td>${x.department.departmentName }</td>
					<td>${x.team.teamName }</td>
					<td>${x.job.jobName   }</td>
					<td>${x.rank.rankName }</td>
					<td>${x.position.positionName     }</td>
					<td>
					    <fmt:parseDate value="${x.resignDate}" pattern="yyyy-MM-dd HH:mm:ss" var="z" />
						<fmt:formatDate value="${z}" pattern="yyyy / MM / dd" />
					</td>
					<td>${x.idStatus      }</td>
					<td>${x.resignReason  }</td>
					<td>${x.resignType    }</td>
					<td>${x.resignInterview           }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>