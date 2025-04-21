<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <div class="page-title">
            <h3 class="my-4">근로계약서 목록</h3>
        </div>
	<div class="card">
		<div class="card-body">
			<table class="table table-striped" id="table1">
				<thead>
					<tr>
						<th>직원 ID</th>
						<th>직원명</th>
						<th>계약 유형</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>상태</th>
						<th>상세보기</th>
					</tr>
				</thead>
					<tbody>
						<c:forEach var="contract" items="${contractList}">
							<tr>
								<td>${contract.empId}</td>
								<td>${contract.employee.name}</td>
								<td>${contract.contractType}</td>
								<td>${contract.startDate}</td>
								<td>${contract.endDate}</td>
								<td>${contract.contractStatus}</td>
								<td><a
									href="${pageContext.request.contextPath}/contract/detail?empId=${contract.empId}">보기</a></td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
    </div>
