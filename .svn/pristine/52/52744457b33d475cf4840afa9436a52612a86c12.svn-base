<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>📋 회의실 목록</h2>

<div class="room-list">
	<c:forEach var="room" items="${rooms}">
		<div class="room-card"
			style="border: 1px solid #ccc; padding: 20px; margin-bottom: 20px;">
			<h4>${room.roomName}</h4>
			<p>위치: ${room.location}</p>
			<p>수용 인원: ${room.capacity}명</p>
			<p>장비: ${room.equipment}</p>

			<a
				href="${pageContext.request.contextPath}/meeting/reserveForm?roomId=${room.roomId}"
				class="btn btn-primary">예약하기</a>
		</div>
	</c:forEach>
</div>
