<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<h5 class="mb-3">🔐 비밀번호 재설정</h5>
	<form action="${pageContext.request.contextPath}/password/sendLink"
		method="post">
		<div class="mb-3">
			<label for="accountId" class="form-label">사번을 입력해주세요</label> <input
				type="text" id="accountId" name="accountId" class="form-control"
				required />
		</div>
		<button type="submit" class="btn btn-primary">재설정 링크 전송</button>
	</form>

	<c:if test="${not empty msg}">
		<script>
			alert('${msg}');
			window.location.href = '${pageContext.request.contextPath}/';
		</script>
	</c:if>
</div>
