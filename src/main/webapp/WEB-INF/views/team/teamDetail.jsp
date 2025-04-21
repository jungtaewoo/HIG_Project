<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal" var="principal" />
<head>
    <meta charset="UTF-8">
    <title>팀 상세보기</title>
    <!-- Bootstrap CSS CDN (버전 5) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUa5Ne1/koGNGlp/+plk/SyZLQREvH/6Kimage" crossorigin="anonymous">
    <style>
        .card {
            margin-top: 20px;
        }
        .card-header {
            background-color: navy;
            color: #fff;
        }
    </style>
</head>

<div class="page-container container-fluid">
  <div class="d-flex justify-content-between align-items-center mb-2">

    <!-- 좌측: 버튼 그룹 -->
    <div>
      <button type="button" class="btn btn-outline-secondary" onclick="history.back();">
        <i class="fas fa-arrow-left"></i>
      </button>
    </div>

    <!-- 우측: Breadcrumb -->
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb mb-0">
        <li class="breadcrumb-item fw-bold text-primary"><a href="${pageContext.request.contextPath }/account/login/home"> 📌 Main</a></li>
        <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath }/employee/organization">조직도</a></li>
        <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath }/team/list">팀 목록</a></li>
        <li class="breadcrumb-item active" aria-current="page">팀 수정</li>
      </ol>
    </nav>
  </div>

</div>


<div class="container">
	<div class="card shadow-sm">
<!-- 	<div class="card shadow-sm" style="margin-left: 500px; margin-right: 500px;"> -->
        <div class="card-header">
            <h5 class="card-title mb-0">팀 상세보기</h5>
        </div>
        <div class="card-body" >
            <table class="table table-borderless">
                <tr>
                    <th scope="row" class="w-25">팀번호:</th>
                    <td>${team.teamId}</td>
                </tr>
                <tr>
                    <th scope="row">팀명:</th>
                    <td>${team.teamName}</td>
                </tr>
                <tr>
                    <th scope="row">팀 전화번호:</th>
                    <td>${team.teamPhonenumber}</td>
                </tr>
                <tr>
                    <th scope="row">팀 팩스번호:</th>
                    <td>${team.teamFaxnumber}</td>
                </tr>
				<tr>
				    <th scope="row">팀원명:</th>
				    <td>
				        <c:choose>
				            <c:when test="${not empty team.employees}">
				                <c:forEach items="${team.employees}" var="employee">
				                    <div>${employee.job.jobName} - ${employee.name}</div>
				                </c:forEach>
				            </c:when>
				            <c:otherwise>
				                팀원 없음
				            </c:otherwise>
				        </c:choose>
				    </td>
				</tr>
            </table>
            <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
	            <div class="d-flex justify-content-between mt-4">
	                <a href="${pageContext.request.contextPath}/team/list" class="btn btn-secondary">목록으로</a>
	                <a href="${pageContext.request.contextPath}/team/update?teamId=${team.teamId}" class="btn btn-primary">수정</a>
	            </div>
	        </security:authorize>
        </div>
    </div>
</div>
<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+3lqkGkVmExobotpvzjF5Lp8qIpz6"
        crossorigin="anonymous"></script>
