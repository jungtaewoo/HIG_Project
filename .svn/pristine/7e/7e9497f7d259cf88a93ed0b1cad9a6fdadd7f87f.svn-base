<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 13.     	 KHS           최초 생성
 *  2025. 3. 21.     	 KHS           테이블 변경으로 코드수정
    2025. 3. 22.         KHS           데이터 테이블 적용
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal" var="principal" />


<style>
#table1 tbody td {
    font-size: 15px !important;
}
</style>

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
        <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath }/employee/organization">조직도</a></li>
        <li class="breadcrumb-item active" aria-current="page">팀 목록</li>
      </ol>
    </nav>
  </div>

</div>


	<div class="page-title">
	   <h3>조직관리</h3>
    </div>

    <section class="section">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">팀 목록</h5>
            </div>
            <script>
                function navigateToDetail(postId) {
                    console.log("Navigating to detail with noticeId:", postId); // 로그 추가
                    window.location.href = "${pageContext.request.contextPath}/team/detail?teamId=" + postId;
                }
            </script>
            <!-- 예: 부서 목록 JSP 상단에 Flash Attribute를 체크 -->
			<c:if test="${not empty updateError}">
			    <script>
			        document.addEventListener("DOMContentLoaded", function() {
			            Swal.fire({
			                title: "업데이트 실패",
			                text: "${updateError}",
			                icon: "error",
			                confirmButtonText: "확인"
			            });
			        });
			    </script>
			</c:if>

			<c:if test="${not empty updateSuccess}">
			    <script>
			        document.addEventListener("DOMContentLoaded", function() {
			            Swal.fire({
			                title: "업데이트 성공",
			                text: "${updateSuccess}",
			                icon: "success",
			                confirmButtonText: "확인"
			            });
			        });
			    </script>
			</c:if>
            <div class="card-body">
                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                    <div class="dataTable-container">
                        <table id="table1" class="table table-striped datatable">
                            <thead>
                                <tr>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">삭제</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">팀ID</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">소속부서명</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">팀명</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">팀장명</a></th>
<!--                                     <th data-sortable="" style="width: 16.3695%;"><a href="#" class="dataTable-sorter">팀장/팀원 여부</a></th> -->
                                </tr>
                            </thead>
                            <tbody>

                                <c:choose>

                                    <c:when test="${not empty teamManageList}">
									    <c:forEach items="${teamManageList}" var="team">
										    <tr>
										   		<td><input type="checkbox" class="selectDept" value="${team.teamId}"></td>
										        <td>${team.teamId}</td>
										        <td>${team.department.departmentName}</td>
										        <td>
										            <a href="javascript:void(0);" onclick="navigateToDetail('${team.teamId}')">${team.teamName}</a>
										        </td>
										        <c:set var="leaderName" value="-" />
										        <!-- 내부 반복에서 변수 이름을 충돌 없이 사용 -->
										        <c:if test="${not empty team.employees}">
										            <c:forEach items="${team.employees}" var="employee">
										                <!-- 팀장인 경우 이름 저장 -->
										                    <c:set var="leaderName" value="${employee.name}" />
										            </c:forEach>
										        </c:if>
										        <td>${leaderName}</td>
										    </tr>
										</c:forEach>
									</c:when>

                                    <c:otherwise>
                                        <tr>
                                            <td colspan="6">팀 없음.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>

						<security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
							<!-- 버튼을 오른쪽에 정렬하기 위한 스타일 -->
							<div class="button-container" style="display: flex; justify-content: flex-end; gap: 10px;">
		                        <a href="${pageContext.request.contextPath}/team/register" class="btn btn-primary">팀추가</a>
		                        <button class="btn btn-danger" id="deleteSelected">팀삭제</button>
	                    	</div>
                    	</security:authorize>
	                </div>
	            </div>
	        </div>
        </div>
    </section>

	<!-- 데이터테이블 처리 스크립트 일단 적용안함-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/approval/approvalDelete.js"></script>

	<script src="${pageContext.request.contextPath}/resources/template/dist/assets/extensions/simple-datatables/umd/simple-datatables.js"></script>
	<script src="${pageContext.request.contextPath}/resources/template/dist/assets/static/js/pages/simple-datatables.js"></script>


	<script>


   	document.addEventListener("DOMContentLoaded", function() {
   	    console.log("DOM이 완전히 로드되었습니다.");
   	    const deleteSelectedBtn = document.getElementById("deleteSelected");
   	    console.log("deleteSelected 버튼:", deleteSelectedBtn);
   	    if (!deleteSelectedBtn) {
   	        console.error("deleteSelected 버튼을 찾을 수 없습니다.");
   	        return;
   	    }

   	    deleteSelectedBtn.addEventListener("click", function () {
   	        console.log("deleteSelected 버튼 클릭됨");
   	        let selectedIds = [];
   	        document.querySelectorAll(".selectDept:checked").forEach((checkbox) => {
   	            selectedIds.push(checkbox.value);
   	        });
   	        console.log("선택된 팀 ID:", selectedIds);
   	        if (selectedIds.length === 0) {
   	            Swal.fire("삭제할 팀을 선택하세요.");
   	            return;
   	        }

   	        Swal.fire({
   	        	html: '<h2 style="font-size: 24px;">삭제한 팀은 되돌릴 수 없습니다<br>선택한 팀을 삭제하시겠습니까?</h2>',
   	            icon: "warning",
   	            showCancelButton: true,
   	            confirmButtonColor: "#435ebe",
   	            cancelButtonColor: "#d33",
   	            confirmButtonText: "삭제",
   	            cancelButtonText: "취소"
   	        }).then((result) => {
   	            console.log("Swal 결과:", result);
   	            if (result.isConfirmed) {
   	                console.log("fetch 호출 전");
   	                fetch("${pageContext.request.contextPath}/team/delete", {
   	                    method: "POST",
   	                    headers: {
   	                        "Content-Type": "application/json",
   	                    },
   	                    body: JSON.stringify(selectedIds),
   	                })
   	                .then((response) => response.json())
   	                .then((data) => {
   	                    console.log("fetch 응답:", data);
   	                    if (data.success) {
   	                        Swal.fire({
   	                            title: "삭제가 완료되었습니다.",
   	                            icon: "success",
   	                            confirmButtonText: "확인"
   	                        }).then(() => {
   	                            location.reload();
   	                        });
   	                    } else {
   	                        Swal.fire({
   	                            title: "삭제 실패",
   	                            text:  "팀원이 있는 팀을 삭제할 수 없습니다.",
   	                            icon: "error",
   	                            confirmButtonText: "확인"
   	                        });
   	                    }
   	                })
   	                .catch((error) => {
   	                    console.error("fetch 에러:", error);
   	                    Swal.fire({
   	                        title: "삭제 실패",
   	                        text: "서버와의 통신 중 문제가 발생했습니다.",
   	                        icon: "error",
   	                        confirmButtonText: "확인"
   	                    });
   	                });
   	            }
   	        });
   	    });
   	});


//    	document.getElementById("deleteSelected").addEventListener("click", function () {
//    	    let selectedIds = [];
//    	    document.querySelectorAll(".selectDept:checked").forEach((checkbox) => {
//    	        selectedIds.push(checkbox.value);
//    	    });

//    	    if (selectedIds.length === 0) {
//    	        alert("삭제할 팀을 선택하세요.");
//    	        return;
//    	    }

//    	    if (!confirm("해당팀에 팀원이 없는경우만 삭제가능합니다. \n선택한 팀을 삭제하시겠습니까?")) {
//    	        return;
//    	    }

//    	    fetch("${pageContext.request.contextPath}/team/delete", {
//    	        method: "POST",
//    	        headers: {
//    	            "Content-Type": "application/json",
//    	        },
//    	        body: JSON.stringify(selectedIds), // 배열로 전송
//    	    })
//    	    .then((response) => response.json())
//    	    .then((data) => {
//    	        if (data.success) {
//    	            alert("삭제가 완료되었습니다.");
//    	            location.reload(); // 페이지 새로고침하여 반영
//    	        } else {
//    	            alert("삭제 중 오류가 발생했습니다.");
//    	        }
//    	    })
//    	    .catch((error) => console.error("Error:", error));
//    	});


   	</script>


