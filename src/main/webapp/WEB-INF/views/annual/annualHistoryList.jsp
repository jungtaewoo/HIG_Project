<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 25.     	정태우            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="card">
	<div class="card-header">
		<h3>
			<a id="toggleButton1">전직원 연차 사용내역
				<h5>(접기)</h5>
			</a>
		</h3>
	</div>
	<div id="dropdown1" class="dropdown-content">

		<div class="card-body">
			<table class="table table-striped table1" id="2t">
				<thead>
					<tr>
						<th>사번</th>
						<th>부서</th>
						<th>팀</th>
						<th>이름</th>
						<th>직급</th>
						<th>연차종류</th>
						<th>사용일수</th>
						<th>시작일</th>
						<th>종료일</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty historyList}">
						<c:forEach items="${historyList}" var="history">
							<tr>
								<td>${history.empId}</td>
								<td>${history.employee.department.departmentName}</td>
								<td>${history.employee.team.teamName}</td>
								<td>${history.employee.name}</td>
								<td>${history.employee.rank.rankName}</td>
								<td>${history.annualType.annualName}</td>
								<td>${history.leaveDay}</td>
								<td>${history.leaveDate.substring(0, 10)}</td>
								<td>${history.leaveEndDate.substring(0, 10)}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty historyList}">
						<tr>
							<td colspan="9">연차 사용 내역 없음</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
	document.getElementById("toggleButton1").addEventListener(
			"click",
			function() {
				let dropdown = document.getElementById("dropdown1");
				if (dropdown.style.display === "none"
						|| dropdown.style.display === "") {
					dropdown.style.display = "block"; // 보이게 변경
				} else {
					dropdown.style.display = "none"; // 숨기기
				}
			});
</script>