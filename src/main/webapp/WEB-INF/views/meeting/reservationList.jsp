<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>🗓 회의실 예약 현황</h2>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>예약자</th>
      <th>시작 시간</th>
      <th>종료 시간</th>
      <th>목적</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="res" items="${reservations}">
      <tr>
        <td>${res.empName}</td>
        <td><fmt:formatDate value="${res.startTime}" pattern="yyyy-MM-dd HH:mm" /></td>
        <td><fmt:formatDate value="${res.endTime}" pattern="yyyy-MM-dd HH:mm" /></td>
        <td>${res.purpose}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
