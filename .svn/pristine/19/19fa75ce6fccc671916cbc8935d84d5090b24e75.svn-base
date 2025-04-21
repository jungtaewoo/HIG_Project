<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<security:authentication property="principal" var="principal" />
	<security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
		<jsp:include page="content1.jsp"></jsp:include>
	</security:authorize>
<jsp:include page="content.jsp"></jsp:include>

