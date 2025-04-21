<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>내 할일 리스트</title>
    <!-- 부트스트랩 CSS -->
    <style>
        .todo-item {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }
        .todo-title {
            font-size: 1.2rem;
            font-weight: bold;
        }
        .todo-date {
            color: #777;
            font-size: 0.9rem;
        }
        .todo-context {
            margin-top: 5px;
        }
    </style>
</head>

<div class="col-12 col-lg-4">
    <div class="card-content pb-4">
        <c:if test="${not empty scheduleList}">
            <div class="card widget-todo">
                <div class="list-group">
                    <h5>이번 주 내 할 일</h5>
                    <c:forEach var="schedule" items="${scheduleList}">
                        <!-- 날짜 문자열을 파싱하여 Date 객체로 변환 -->
                        <fmt:parseDate var="parsedStartDate" value="${schedule.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:parseDate var="parsedEndDate" value="${schedule.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <div class="todo-item list-group-item">
                            <div class="todo-title">${schedule.scheduleTitle}</div>
                            <div class="todo-date">
                                시작: <fmt:formatDate value="${parsedStartDate}" pattern="yyyy-MM-dd"/>
                                &nbsp;~&nbsp;
                                종료: <fmt:formatDate value="${parsedEndDate}" pattern="yyyy-MM-dd"/>
                            </div>
                            <div class="todo-context mt-2">${schedule.scheduleContext}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <c:if test="${empty scheduleList}">
            <p>이번주와 다음주 일정이 없습니다.</p>
        </c:if>
    </div>
</div>

<!--     <div class="container mt-4"> -->
<!--         <h1>내 할일 리스트</h1> -->

<%--         <c:if test="${not empty scheduleList}"> --%>
<!--             <div class="list-group"> -->
<%--                 <c:forEach var="schedule" items="${scheduleList}"> --%>
<!--                     <div class="todo-item list-group-item"> -->
<%--                         <div class="todo-title">${schedule.scheduleTitle}</div> --%>
<!--                         <div class="todo-date"> -->
<%--                             시작: ${schedule.startDate} &nbsp;&nbsp; 종료: ${schedule.endDate} --%>
<!--                         </div> -->
<%--                         <div class="todo-context mt-2">${schedule.scheduleContext}</div> --%>
<!--                     </div> -->
<%--                 </c:forEach> --%>
<!--             </div> -->
<%--         </c:if> --%>

<%--         <c:if test="${empty scheduleList}"> --%>
<!--             <p>등록된 일정이 없습니다.</p> -->
<%--         </c:if> --%>
<!--     </div> -->




<!-- <div class="col-12 col-lg-4"> -->
<!--     <div class="card-content pb-4"> -->
<%--         <c:if test="${not empty scheduleList}"> --%>
<!--             <div class="card widget-todo"> -->
<!--             	<div class="list-group"> -->
<!-- 	        		<h5>내 할일 리스트</h5> -->
<%-- 	                <c:forEach var="schedule" items="${scheduleList}"> --%>
<!-- 	                    <div class="todo-item list-group-item"> -->
<%-- 	                        <div class="todo-title">${schedule.scheduleTitle}</div> --%>
<!-- 	                        <div class="todo-date"> -->
<%-- 	                            시작: ${schedule.startDate} &nbsp;&nbsp; 종료: ${schedule.endDate} --%>
<!-- 	                        </div> -->
<%-- 	                        <div class="todo-context mt-2">${schedule.scheduleContext}</div> --%>
<!-- 	                    </div> -->
<%-- 	                </c:forEach> --%>
<!-- 				</div> -->
<!--             </div> -->
<%--         </c:if> --%>

<%--         <c:if test="${empty scheduleList}"> --%>
<!--             <p>등록된 일정이 없습니다.</p> -->
<%--         </c:if> --%>
<!--     </div> -->
<!-- </div> -->