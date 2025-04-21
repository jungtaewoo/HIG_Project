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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

    h6 {
        font-size: 18px;
        font-weight: bold;
        color: #333;
        margin-bottom: 10px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }

    th, td {
        padding: 12px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }

    th {
        background: #007bff;
        color: #ffffff;
        font-weight: bold;
    }

    td {
        color: #555;
    }

</style>
<c:if test="${not empty annualDetail}">
    <c:set var="firstHistory" value="${annualDetail[0]}" />
    <h6>사번: ${firstHistory.employee.empId}</h6>
    <h6>이름: ${firstHistory.employee.name}</h6>
</c:if>
<table id="table1">
    <tr>
        <th>연차 종류</th>
        <th>시작 일시</th>
        <th>종료 일시</th>
        <th>사용일</th>
        <th>승인여부</th>
        <th>연차 사유</th>
    </tr>
    <c:if test="${not empty annualDetail}">
    <c:forEach items="${annualDetail}" var="history">
        <tr>
            <td>${history.annualType.annualName}</td> 
            <td>${history.leaveDate.substring(0, 10)}</td> 
            <td>${history.leaveEndDate.substring(0, 10)}</td> 
            <td>${history.leaveDay}일</td> 
            <td>${history.status}</td> 
            <td>${history.reason}</td> 
        </tr>
    </c:forEach>
    </c:if>
    <c:if test="${empty annualDetail}">
    	<tr>
    		<td colspan="7">연차 사용 내역 없음</td>
    	</tr>
    </c:if>
</table>
