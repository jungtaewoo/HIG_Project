<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>📅 회의실 예약</h2>

<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/meeting/reserve" method="post">
  <input type="hidden" name="roomId" value="${roomId}" />
  <input type="hidden" name="empId" value="${sessionScope.loginUser.empId}" />

  <div class="mb-3">
    <label for="startTime">시작 시간</label>
    <input type="datetime-local" id="startTime" name="startTime" required class="form-control" />
  </div>

  <div class="mb-3">
    <label for="endTime">종료 시간</label>
    <input type="datetime-local" id="endTime" name="endTime" required class="form-control" />
  </div>

  <div class="mb-3">
    <label for="purpose">회의 목적</label>
    <textarea id="purpose" name="purpose" class="form-control" rows="3"></textarea>
  </div>

  <button type="submit" class="btn btn-success">예약 등록</button>
  <a href="${pageContext.request.contextPath}/meeting/roomList" class="btn btn-secondary">뒤로가기</a>
</form>
