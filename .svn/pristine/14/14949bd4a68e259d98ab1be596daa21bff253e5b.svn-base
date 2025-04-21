<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일               수정자           수정내용
 *  ============      ============== =======================
 *  2025. 3. 14.        young           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
 rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<table class="table">

<c:set var="allowanceMap" value="<%= new java.util.HashMap() %>" scope="page"/>
<c:set var="deductionMap" value="<%= new java.util.HashMap() %>" scope="page"/>

<c:forEach items="${salarySelected.allowanceList }" var="allowanceVO">
   <c:set target="${allowanceMap }" property="${allowanceVO.allowanceCode }" value="${allowanceVO.allowanceAmount }"/>
</c:forEach>

<c:forEach items="${salarySelected.deductionList}" var="deductionVO">
    <c:set target="${deductionMap}" property="${deductionVO.deductionCode}" value="${deductionVO.deductionAmount}"/>
</c:forEach>

<style>
    .salary-detail-title {
        font-size: 1.4rem;
        font-weight: bold;
        color: #333;
        margin-bottom: 1.5rem;
        text-align: center;
    }

    .section-title {
        background-color: #f8f9fa;
        font-weight: bold;
        padding: 0.5rem 1rem;
        border: 1px solid #dee2e6;
    }

    .salary-table th,
    .salary-table td {
        text-align: center;
        vertical-align: middle;
        border: 1px solid #dee2e6;
        padding: 0.5rem;
    }

    .salary-table {
        margin-bottom: 2rem;
    }

    .highlight {
        background-color: #e9f5ff;
        font-weight: bold;
    }

    .summary-box {
        background-color: #fefefe;
        border: 1px solid #dee2e6;
        border-radius: 0.5rem;
        padding: 1rem;
        margin-top: 2rem;
    }
</style>

<div class="container my-5 p-4 border rounded bg-white shadow-sm" style="max-width: 850px;">
<!--   <h2 class="text-center fw-bold text-primary mb-4">급여 상세 명세서</h2> -->
<h2 class="text-center fw-bold text-primary mb-4 salary-detail-title">급여 상세 명세서</h2>

  <div class="row mb-3">
    <div class="col"><strong>사원명:</strong> ${salarySelected.employeeList[0].name}</div>
    <div class="col text-end"><strong>지급연월:</strong> ${salarySelected.payYear}년 ${salarySelected.payMonth}월</div>
  </div>
  <div class="row mb-4">
    <div class="col"><strong>소속 부서:</strong> ${salarySelected.employeeList[0].department.departmentName} / ${salarySelected.employeeList[0].team.teamName}</div>
    <div class="col text-end"><strong>작성일:</strong> <fmt:formatDate value="<%= java.util.Date.from(java.time.LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()) %>" pattern="yyyy-MM-dd"/></div>
  </div>

<table class="table table-bordered text-center mb-4">
  <thead class="table-light">
    <tr>
      <th>기본급</th>
      <th>수당</th>
      <th>지급</th>
      <th>공제</th>
      <th class="text-primary">실지급액</th>
    </tr>
  </thead>
  <tbody>
    <tr class="fw-bold">
      <td class="text-end"><fmt:formatNumber value="${salarySelected.baseSalary}" pattern="#,###"/> 원</td>
      <td class="text-end"><fmt:formatNumber value="${salarySelected.totalAllowance}" pattern="#,###"/> 원</td>
      <td class="text-end"><fmt:formatNumber value="${salarySelected.totalPay}" pattern="#,###" /> 원</td>
      <td class="text-end"><fmt:formatNumber value="${salarySelected.totalDeduction}" pattern="#,###"/> 원</td>
      <td class="text-end text-primary"><fmt:formatNumber value="${salarySelected.netSalary}" pattern="#,###"/> 원</td>
    </tr>
  </tbody>
</table>


  <h5 class="fw-bold mt-4">상세 지급/공제 내역</h5>
  <table class="table table-bordered text-center">
    <thead class="table-light">
      <tr>
        <th colspan="2">지급 항목</th>
        <th colspan="2">공제 항목</th>
      </tr>
    </thead>
    
  <tbody>
  <tr>
    <td>직책수당</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['101']}" pattern="#,###"/></td>
    <td>소득세</td><td class="text-end"><fmt:formatNumber value="${deductionMap['200']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>야근수당</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['102']}" pattern="#,###"/></td>
    <td>지방소득세</td><td class="text-end"><fmt:formatNumber value="${deductionMap['201']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>특별수당</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['103']}" pattern="#,###"/></td>
    <td>국민연금</td><td class="text-end"><fmt:formatNumber value="${deductionMap['202']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>식대</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['104']}" pattern="#,###"/></td>
    <td>건강보험</td><td class="text-end"><fmt:formatNumber value="${deductionMap['203']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>교통비</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['105']}" pattern="#,###"/></td>
    <td>장기요양보험</td><td class="text-end"><fmt:formatNumber value="${deductionMap['204']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>출장비</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['106']}" pattern="#,###"/></td>
    <td>고용보험</td><td class="text-end"><fmt:formatNumber value="${deductionMap['205']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>휴일근무수당</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['107']}" pattern="#,###"/></td>
    <td>기타공제</td><td class="text-end"><fmt:formatNumber value="${deductionMap['210']}" pattern="#,###"/></td>
  </tr>
  <tr>
    <td>위험수당</td><td class="text-end"><fmt:formatNumber value="${allowanceMap['109']}" pattern="#,###"/></td>
    <td colspan="2" class="bg-light"></td>
  </tr>
</tbody>

  </table>
  
      <table class="table table-bordered calculation-table">
        <thead class="bg-light-gray">
        <tr>
            <th colspan="3" class="text-center">공제 항목별 계산 방법</th>
        </tr>
        <tr>
            <th>항목</th>
            <th>산출식</th>
            <th>비율</th>
        </tr>
        </thead>
        <tbody>
        <tr><td>소득세</td><td>총지급액 × 0.05</td><td>5%</td></tr>
        <tr><td>지방소득세</td><td>소득세 × 0.1</td><td>10%</td></tr>
        <tr><td>국민연금</td><td>기본급 × 0.045</td><td>4.5%</td></tr>
        <tr><td>건강보험</td><td>기본급 × 0.04</td><td>4%</td></tr>
        <tr><td>장기요양보험</td><td>건강보험액 × 0.1</td><td>10%</td></tr>
        <tr><td>고용보험</td><td>기본급 × 0.008</td><td>0.8%</td></tr>
        </tbody>
    </table>

  <div class="text-end mt-4 text-muted small">
    주식회사 대덕우리전자<br>
    ※ 본 명세서는 전자문서로 발급되었으며, 무단 수정 및 재배포를 금합니다.
  </div>
</div>

<script>
  window.renderReady = true;  // html2canvas 처리 완료를 위한 신호
</script>
