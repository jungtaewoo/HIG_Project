<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 13.     	young           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
<table class="table">
	<thead>
		<th>사원번호1</th>
		<th>성명</th>
		<th>입사일</th>
		<th>부서</th>
		<th>직급</th>
		<th>E-mail</th>
	</thead>
	<tbody>
	<c:choose>
    <c:when test="${not empty unAccount}">
        <c:forEach items="${unAccount}" var="unA">
            <tr>
                <td><a href="/unAccount/unNew/${unA.empId}">${unA.empId }</a></td>
                <td>${unA.employee.name }</td>
                <td>${unA.employee.hireDate }</td>
                <td>${unA.department.departmentName }</td> 
                <td>${unA.rank.rankName }</td>
                <td>${unA.employee.email }</td>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="6" style="text-align:center;">조회된 데이터가 없습니다.</td>
        </tr>
    </c:otherwise>
</c:choose>

	</tbody>
</table>
