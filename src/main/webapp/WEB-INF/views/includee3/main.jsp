<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 4.     	KHS            최초 생성
 *
-->
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
	<tiles:insertAttribute name="preScript" />
</head>

	<body class="index-page">

			<tiles:insertAttribute name="header" />

			<div id="main">
				<tiles:insertAttribute name="content" />
				<tiles:insertAttribute name="footer" />
			</div>

		  <!-- Scroll Top -->
		  <a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

		  <!-- Preloader -->
		  <div id="preloader"></div>

		  <tiles:insertAttribute name="postScript" />

	</body>
</html>
