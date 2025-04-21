<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 23.     	young           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<table class="table text-center align-middle mb-0">
  <thead>
    <tr class="text-secondary small border-0">
      <th class="py-2">📌 대상 인원</th>
      <th class="py-2">💰 총 급여</th>
      <th class="py-2">➕ 총 수당</th>
      <th class="py-2">➖ 총 공제</th>
      <th class="py-2">✅ 예상실 지급액</th>
    </tr>
  </thead>
  <tbody class="fw-semibold fs-6">
    <tr>
      <td class="text-dark">${salaryEx.totalemp} 명</td>
      <td class="text-dark">
        <fmt:formatNumber value="${salaryEx.totalPaySum}" pattern="#,##0" /> 원
      </td>
      <td class="text-success">
        <fmt:formatNumber value="${salaryEx.totalAllowanceSum}" pattern="#,##0" /> 원
      </td>
      <td class="text-danger">
        <fmt:formatNumber value="${salaryEx.totalDeductionSum}" pattern="#,##0" /> 원
      </td>
      <td class="text-primary fs-5">
        <fmt:formatNumber value="${salaryEx.totalNetSalary}" pattern="#,##0" /> 원
      </td>
    </tr>
  </tbody>
</table>




<!-- 	<tbody> -->
<!-- 		<tr> -->
<%-- 			<td>${salaryEx.payYear }년 ${salaryEx.payMonth }월</td> --%>
<%-- 			<td>${salaryEx.totalemp }</td> --%>
<%-- 			<td><fmt:formatNumber value="${salaryEx.totalPaySum }" pattern="#,###" /></td> --%>
<%-- 			<td><fmt:formatNumber value="${salaryEx.totalAllowanceSum }" pattern="#,###" /></td> --%>
<%-- 			<td><fmt:formatNumber value="${salaryEx.totalDeductionSum }" pattern="#,###" /></td> --%>
<%-- 			<td><fmt:formatNumber value="${salaryEx.totalNetSalary }" pattern="#,###" /></td> --%>
<!-- 		</tr> -->
<!-- 	</tbody> -->
</table>

