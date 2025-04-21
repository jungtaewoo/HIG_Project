<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서명 리스트</title>
</head>
<body>
	<h2>등록된 모든 서명</h2>

	<table border="1">
		<tr>
			<th>직원 ID</th>
			<th>서명 이미지</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="sign" items="${signatures}">
			<tr>
				<td>${sign.empId}</td>
				<td>
					<img src="${pageContext.request.contextPath}${sign.signImagePath}" alt="서명 이미지">
				</td>
				<td>${sign.createdAt}</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a href="${pageContext.request.contextPath}/">메인으로</a>
</body>
</html>
