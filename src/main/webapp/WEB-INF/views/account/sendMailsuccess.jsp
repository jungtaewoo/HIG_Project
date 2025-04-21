<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 17.     	정태우            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
${msg}
	<c:if test="${not empty msg}">
		<script>
			alert('\${msg}');
// 			window.location.href = '${pageContext.request.contextPath}/';
		</script>
	</c:if>