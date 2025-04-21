<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title><tiles:getAsString name="title" /></title>
 <!-- ✅ 이 줄을 추가 -->
 
 <!-- ✅ 여기에서 favicon 경로를 출력 -->
    <link rel="icon" href="${pageContext.request.contextPath}<tiles:getAsString name='favicon' />" type="image/png" />

<style>
body {
    background-color: #F2F6FA    !important; /* !important 추가 */
    caret-color: transparent;
}
</style>

<tiles:insertAttribute name="preScript" />

<script src="${pageContext.request.contextPath}/resources/template/dist/assets/static/js/initTheme.js"></script>
		
</head>
<body>

	<div id="app">
		<tiles:insertAttribute name="sidebar" />
		<tiles:insertAttribute name="header" />
		<div id="main">
			<tiles:insertAttribute name="content" />
			<tiles:insertAttribute name="footer" />
		</div>
	</div>

<tiles:insertAttribute name="postScript" />

<script defer="defer">
	var contextPath = "${pageContext.request.contextPath}";

	document.getElementById("messengerBtn")
			.addEventListener(
					"click",
					function() {

						const url = contextPath + "/messenger/Main";
						const popupOptions = "width=800,height=600,left=300,top=100,resizable=yes,scrollbars=yes";

						window.open(url, "MessengerPopup", popupOptions);
					});
	
</script>

</body>
</html>