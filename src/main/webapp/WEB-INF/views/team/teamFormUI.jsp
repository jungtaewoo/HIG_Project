<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 13.     	 KHS           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal" var="principal" />
<head>
    <meta charset="UTF-8">
    <title>팀 등록</title>
    <style>
	.wrapper {
	  margin-left: 200px;
	  margin-right: 200px;
	}
    </style>
</head>
<div class="wrapper">
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
	        <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath }/team/list">팀 목록</a></li>
	        <li class="breadcrumb-item active" aria-current="page">팀 등록</a></li>
	      </ol>
	    </nav>

	  </div>
	</div>

    <div class="container">
	    	<div class="card">
				<div class="card-body">


    <h2>팀 등록</h2>
    <form id="teamForm" action="${pageContext.request.contextPath}/team/register/commit" method="post">

    	<table class="table">
	 	    <!-- 부서 정보: 드롭다운으로 모든 부서 목록을 출력 -->
	        <tr>
	            <th>부서:</th>
	            <td>
					<select name="departmentId" class="form-select" required>
					    <c:forEach var="department" items="${departmentList}">
					        <option value="${department.departmentId}">
					            ${department.departmentName}
					        </option>
					    </c:forEach>
					</select>
				</td>
	        </tr>
	        <!-- 사용자가 직접 팀 ID를 입력 -->
	<!--         <div> -->
	<!--             <label for="teamId">팀 ID:</label> -->
	<!--             <input type="text" id="teamId" name="teamId" required/> -->
	<!--         </div> -->

	        <tr>
	            <th for="teamName">팀 이름:</th>
	            <td>
	            	<input type="text" id="teamName" name="teamName" class="form-control" required/>
	            </td>
	        </tr>

	        <tr>
	            <th for="teamName">팀 전화번호:</th>
	            <td>
		            <input type="text" id="teamName" name="teamPhonenumber" class="form-control" required/>
	            </td>
	        </tr>

	        <tr>
	            <th for="teamName">팀 팩스번호:</th>
	            <td>
		            <input type="text" id="teamName" name="teamFaxnumber" class="form-control"/>
	            </td>
	        </tr>


			<tr>
                <td colspan="2" class="text-end">
                    <button id="success" class="btn btn-primary">팀 등록</button>
                    <a href="${pageContext.request.contextPath}/employee/organization" class="btn btn-danger">취소</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</div>
</div>
    <script>
	    document.getElementById("success").addEventListener("click", function(event) {
	        event.preventDefault();  // 기본 제출 막기

	        // SweetAlert으로 확인 메시지 띄우기
	        Swal.fire({
	            title: "팀을 추가하시겠습니까?",
	            text: "입력한 내용으로 팀을 추가합니다.",
	            icon: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#435ebe",
	            cancelButtonColor: "#d33",
	            confirmButtonText: "등록",
	            cancelButtonText: "취소"
	        }).then((result) => {
	            if (result.isConfirmed) {
	                // 폼 데이터를 AJAX로 제출하기
	                var formData = new FormData(document.getElementById("teamForm"));

	                $.ajax({
	                    url: "${pageContext.request.contextPath}/team/register/commit",
	                    method: "POST",
	                    data: formData,
	                    processData: false,
	                    contentType: false,
	                    success: function(response) {
	                        // 성공 메시지 표시 후 확인 버튼 누르면 다음 화면으로 이동
	                        Swal.fire({
	                            title: "팀 추가에 성공했습니다.",
	                            icon: "success",
	                            confirmButtonText: "확인"
	                        }).then(() => {
	                            // 예를 들어 부서 목록 페이지로 이동
	                            window.location.href = "${pageContext.request.contextPath}/employee/organization";
	                        });
	                    },
	                    error: function(xhr, status, error) {
	                        Swal.fire({
	                            title: "오류 발생",
	                            text: "등록 중 오류가 발생했습니다.",
	                            icon: "error",
	                            confirmButtonText: "확인"
	                        });
	                    }
	                });
	            }
	        });
	    });
    </script>
