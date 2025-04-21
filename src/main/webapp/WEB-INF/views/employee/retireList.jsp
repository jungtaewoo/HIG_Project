<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<head>
    <title>퇴사자/휴직자 목록</title>
   <style>
    body {
        font-family: 'Noto Sans KR', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f7f9fc;
    }

    .container {
        width: 100%;
        padding: 30px 40px;
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.05);
        box-sizing: border-box;
    }

    h2 {
        margin-bottom: 20px;
        color: #2c3e50;
        border-bottom: 2px solid #2c3e50;
        padding-bottom: 10px;
    }

    .top-bar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .register-btn {
        background-color: #1f5aaa;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 6px;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.2s;
    }

    .register-btn:hover {
        background-color: #164c93;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }

    thead {
        background-color: #ecf0f1;
    }

    th, td {
        padding: 12px 10px;
        border-bottom: 1px solid #ddd;
        text-align: center;
    }

    tbody tr:hover {
        background-color: #f2f6fc;
    }

    .empty-msg {
        text-align: center;
        padding: 30px;
        color: #777;
    }
</style>
</head>

<div class="page-container container-fluid" style="font-size: 18px;">
	<div class="d-flex justify-content-between align-items-center mb-2">

		<!-- 좌측: 버튼 그룹 -->
		<div>
			<button type="button" class="btn btn-outline-secondary"
				onclick="history.back();">
				<i class="fas fa-arrow-left"></i>
			</button>
		</div>

		<!-- 우측: Breadcrumb -->
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb mb-0">
				<li class="breadcrumb-item fw-bold text-primary"><a
					href="${pageContext.request.contextPath }/account/login/home">📌Main</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/employee/empList">직원관리</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/employee/appointList">인사발령</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/account/read">계정관리</a></li>
<%-- 				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/employee/empUpdate/retireList">퇴사자관리</a></li> --%>
				<li class="breadcrumb-item active" aria-current="page">퇴사자관리</li>
			</ol>
		</nav>

	</div>
</div>
<br />


<body>
    <div class="container">
        <div class="top-bar">
            <h2>퇴사자 / 휴직자 목록</h2>
            <button class="register-btn"
                onclick="location.href='${pageContext.request.contextPath}/employee/empUpdate/retireLeaveForm'">
                + 퇴사자 / 휴직자 등록
            </button>
        </div>

        <table>
            <thead>
                <tr>
                    <th>사번</th>
                    <th>이름</th>
                    <th>상태</th>
                    <th>퇴사일</th>
                    <th>휴직기간</th>
                    <th>사유</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty retireLeaveList}">
                        <c:forEach var="emp" items="${retireLeaveList}">
                            <tr>
                                <td>${emp.empId}</td>
                                <td>${emp.name}</td>
                                <td>${empty emp.empStatus ? '-' : emp.empStatus}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty emp.retireDate}">
                                            ${fn:substring(emp.retireDate, 0, 10)}
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty emp.leaveStartDate}">
                                            ${fn:substring(emp.leaveStartDate, 0, 10)} ~ ${fn:substring(emp.leaveEndDate, 0, 10)}
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${emp.empStatus eq '퇴사'}">
                                            ${empty emp.retireReason ? '-' : emp.retireReason}
                                        </c:when>
                                        <c:otherwise>
                                            ${empty emp.leaveReason ? '-' : emp.leaveReason}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="empty-msg">등록된 퇴사자/휴직자가 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>
