<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/member/memberDelete.do" method="post">
		<ul>
			<li>
				비밀번호 : <input type="password" name="memPass">
				<button type="submit">탈퇴</button>
			</li>
		</ul>
	</form>
</body>
</html>