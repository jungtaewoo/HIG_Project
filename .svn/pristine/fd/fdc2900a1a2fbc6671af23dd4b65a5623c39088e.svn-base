<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 12.     	LJW            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

인사기록 카드 리스트
${empCardList}

<a href="/employee/empCardFormUI">인사기록 등록</a>

<div class="card">
	<table class="table-bordered">
		<thead>
			<tr>
				<th>인사기록카드ID</th>
				<th>사번</th>
				<th>이름</th>
				<th>소속부서</th>
				<th>소속팀</th>
				<th>열람</th>
			</tr>
		</thead>

		<tbody>
			<c:if test="${not empty empCardList }">
				<c:forEach items="${empCardList }" var="x">
				<tr>
					<td>${x.cardId }</td>

					<td>${x.employee.empId }</td>
					<td>${x.employee.name }</td>
					<td>${x.department.departmentName }</td>
					<td>${x.team.teamName }</td>
					<td>
						<c:url value="/employee/empCardDetail" var="empCardDetailUrl">
							<c:param name="empcard" value="${x.cardId}" />
						</c:url> <a href="${empCardDetailUrl }">선택</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>