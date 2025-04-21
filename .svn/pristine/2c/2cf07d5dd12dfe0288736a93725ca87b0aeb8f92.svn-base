<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 14.     	young           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<style>
.summary-dashboard {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  margin-bottom: 2rem;
}
.summary-card {
  flex: 1;
  min-width: 180px;
  background: #fff;
  border-radius: 0.5rem;
  padding: 1rem;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  text-align: center;
}
.summary-card h6 {
  color: #6c757d;
  font-size: 0.95rem;
  margin-bottom: 0.5rem;
}
.summary-card .value {
  font-size: 1.5rem;
  font-weight: bold;
}
#table1 th, #table1 td {
  text-align: center;
  vertical-align: middle;
}
.summary-card.confirmed .value { color: #198754; }     /* green */
.summary-card.pending .value { color: #dc3545; }       /* red */
.summary-card.total .value { color: #0d6efd; }         /* blue */
.summary-card.request .value { color: #fd7e14; }       /* orange */
</style>

<div class="summary-dashboard">
  <div class="summary-card total">
    <h6>귀속년월</h6>
    <div class="value" id="selectedDate"></div>
  </div>
  <div class="summary-card total">
    <h6>전체 직원 수</h6>
    <div class="value" id="totalemp"></div>
  </div>
  <div class="summary-card total">
    <h6>급여등록</h6>
    <div class="value"id="totalSalaryCount"></div>
  </div>
  <div class="summary-card confirmed">
    <h6>확정 인원</h6>
    <div class="value" id="finalList"></div>
  </div>
  <div class="summary-card request">
    <h6>이체 요청</h6>
    <form action="/salary/transfer/request" style="display: flex; flex-direction: column; gap: 0.3rem;">
      <div class="value" id="transferReauest"></div>
      <button type="submit" class="btn btn-sm btn-outline-primary">요청내역 조회</button>
    </form>
  </div>
  <div class="summary-card pending">
    <h6>요청 대기</h6>
    <div class="value"></div>
  </div>
</div>

	<table class="table table-bordered" id="table1">
	<thead class="table-light">
		<th>귀속연월</th>
		<th>급여확정일</th>
		<th>사원번호</th>
		<th>사원명</th>
		<th>부서</th>
		<th>팀</th>
		<th>직급</th>
		<th>확정지급액</th>
		<th>이체요청</th>
	</thead>
	    <tbody>
        <c:forEach items="${finalList }" var="salaryVO">
        <tr>

            <td>${salaryVO.payYear }년${salaryVO.payMonth }월</td>
            <td>${fn:substring(salaryVO.confirmDate, 0, 10)}</td>
            <td data-salaryid="${salaryVO.salaryId }">${salaryVO.empId }</td>
            <c:forEach items="${salaryVO.employeeList}" var="employeeVO">
						<td>${employeeVO.name}</td>
						<td>${employeeVO.department.departmentName}</td>
						<td>${employeeVO.team.teamName}</td>
						<td>${employeeVO.rank.rankName}</td>
					</c:forEach>
            <td>${salaryVO.netSalary}</td>
            <td>
            	<button type="button" class="btn btn-primary" onclick="finalStatus(this)">
            		${salaryVO.paymentRequest }
            	</button>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<%-- <script src="${pageContext.request.contextPath}/resources/js/salary/salaryFinalList.js"></script> --%>