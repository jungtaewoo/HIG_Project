<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h4>웰컴 페이지</h4>
<c:choose>
	<c:when test="${not empty authMember }">
		<h4>현재 사용자 : 
		
			<a href="<c:url value='/mypage'/>">${authMember.memName}</a>
		
		</h4>	
		<a href="<c:url value='/Login/Logout'/>">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href='<c:url value='/login/loginForm.jsp'/>'>로그인</a>	
		<a href='<c:url value='/member/memberInsert.do'/>'>회원가입</a>	
	</c:otherwise>
</c:choose>
