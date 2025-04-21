<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 11.     	 KHS           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal" var="principal" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>부서 관리</title>
    <style>
		    #table1 tbody td {
		    font-size: 15px !important;
		}
        .button-container {
            display: flex;
            gap: 10px;
        }

        /* 페이지네이션 전체 컨테이너 정렬 */
		.dataTable-pagination {
		    display: flex !important;
		    justify-content: center;
		    align-items: center;
		    margin-top: 15px;
		    gap: 5px; /* 버튼 간격 조정 */
		}

		/* 페이지네이션 리스트 (ul 요소) */
		.dataTable-pagination-list {
		    display: flex !important;
		    list-style: none;
		    padding: 0;
		    margin: 0;
		}

		/* 페이지네이션 버튼 기본 스타일 */
		.dataTable-pagination-list li {
		    display: inline-block;
		}

		/* 페이지네이션 버튼 디자인 */
		.dataTable-pagination-list li a {
		    display: flex;
		    align-items: center;
		    justify-content: center;
		    padding: 8px 12px;
		    font-size: 14px;
		    border-radius: 5px;
		    background-color: #007bff;
		    color: white;
		    text-decoration: none;
		    transition: 0.3s;
		}

		/* 현재 선택된 페이지 */
		.dataTable-pagination-list li.active a {
		    background-color: #0056b3;
		}

		/* hover 효과 */
		.dataTable-pagination-list li a:hover {
		    background-color: #0056b3;
		}

		/* 비활성 버튼 */
		.dataTable-pagination-list li.pager a {
		    background-color: #ccc;
		    cursor: not-allowed;
		}

    </style>


<!-- <div style="position: absolute;top:10px;left: 600px;"> -->
<%-- 	${pageContext.request.requestURL}<br/> --%>
<%-- 	${pageContext.request}<br/> --%>
<%-- 	${pageContext.request.contextPath}<br/> --%>
<!-- </div> -->


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
        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/employee/organization">조직도</a></li>
        <li class="breadcrumb-item active">부서 목록</li>
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
                <h5 class="card-title">부서 목록</h5>
            </div>
            <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
	            <script>
	                function navigateToDetail(postId) {
	                    console.log("Navigating to detail with noticeId:", postId); // 로그 추가
	                    window.location.href = "${pageContext.request.contextPath}/department/update?departmentId=" + postId;
	                }
	            </script>
            </security:authorize>
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
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">부서ID</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">부서 이름</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">부서 위치</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">부서장</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">전화번호</a></th>
                                    <th data-sortable=""><a href="#" class="dataTable-sorter">팩스번호</a></th>
<!--                                     <th data-sortable="" style="width: 11.1426%;"><a href="#" class="dataTable-sorter">번호 타입</a></th> -->
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty departmentList}">
                                        <c:forEach items="${departmentList}" var="dept">
                                            <tr>
                                            	<td><input type="checkbox" class="selectDept" value="${dept.departmentId}"></td>
                                                <td>${dept.departmentId}</td>
                                                <td>
                                                    <a href="javascript:void(0);" onclick="navigateToDetail('${dept.departmentId}')">${dept.departmentName}</a>
                                                </td>
                                                <td>${dept.departmentLocation}</td>
                                                <td>
								                    <!-- 팀과 그 안의 직원들을 각각 반복하여 이름을 출력 -->
								                    <c:if test="${not empty dept.teams}">
								                        <c:forEach items="${dept.teams}" var="team">
								                            <c:if test="${not empty team.employees}">
								                                <c:forEach items="${team.employees}" var="emp">
								                                    ${dept.deptHeadName}  <!-- 부서장 이름 출력 -->
								                                </c:forEach>
								                            </c:if>
								                        </c:forEach>
								                    </c:if>
								                </td>
                                                <td>${dept.departmentPhonenumber}</td>
                                                <td>${dept.departmentFaxnumber}</td>
<%--                                                 <td>${dept.numberType}</td> --%>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="6">부서 없음.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                        <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
	                        <!-- 버튼을 오른쪽에 정렬하기 위한 스타일 -->
							<div class="button-container" style="display: flex; justify-content: flex-end; gap: 10px;">
							    <a href="${pageContext.request.contextPath}/department/register" class="btn btn-primary">부서추가</a>
							    <button class="btn btn-danger" id="deleteSelected">부서삭제</button>
							</div>
						</security:authorize>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- 데이터테이블 처리 스크립트 일단 적용안함-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/approval/approvalDelete.js"></script>


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
   	        console.log("선택된 부서 ID:", selectedIds);
   	        if (selectedIds.length === 0) {
   	            Swal.fire("삭제할 부서를 선택하세요.");
   	            return;
   	        }

   	        Swal.fire({
   	        	html: '<h2 style="font-size: 24px;">삭제한 부서는 되돌릴 수 없습니다<br>선택한 부서를 삭제하시겠습니까?</h2>',
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
   	                fetch("${pageContext.request.contextPath}/department/delete", {
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
   	                            text:  "부서원이 있는 부서는 삭제할 수 없습니다.",
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



//    	document.addEventListener("DOMContentLoaded", function() {
//    	    const deleteSelectedBtn = document.getElementById("deleteSelected");
//    	    if (!deleteSelectedBtn) {
//    	        console.error("deleteSelected 버튼이 존재하지 않습니다.");
//    	        return;
//    	    }

//    	    deleteSelectedBtn.addEventListener("click", function () {
//    	        let selectedIds = [];
//    	        document.querySelectorAll(".selectDept:checked").forEach((checkbox) => {
//    	            selectedIds.push(checkbox.value);
//    	        });

//    	        if (selectedIds.length === 0) {
//    	            Swal.fire("삭제할 부서를 선택하세요.");
//    	            return;
//    	        }

//    	        Swal.fire({
//    	            title: "선택한 부서를 삭제하시겠습니까?",
//    	            icon: "warning",
//    	            showCancelButton: true,
//    	            confirmButtonColor: "#3085d6",
//    	            cancelButtonColor: "#d33",
//    	            confirmButtonText: "삭제",
//    	            cancelButtonText: "취소"
//    	        }).then((result) => {
//    	            if (result.isConfirmed) {
//    	                fetch("${pageContext.request.contextPath}/department/delete", {
//    	                    method: "POST",
//    	                    headers: {
//    	                        "Content-Type": "application/json",
//    	                    },
//    	                    body: JSON.stringify(selectedIds),
//    	                })
//    	                .then((response) => response.json())
//    	                .then((data) => {
//    	                    if (data.success) {
//    	                        Swal.fire({
//    	                            title: "삭제가 완료되었습니다.",
//    	                            icon: "success",
//    	                            confirmButtonText: "확인"
//    	                        }).then(() => {
//    	                            location.reload(); // 확인 버튼 클릭 후 페이지 새로고침
//    	                        });
//    	                    } else {
//    	                        Swal.fire({
//    	                            title: "삭제 실패",
//    	                            text: data.message || "삭제 중 오류가 발생했습니다.",
//    	                            icon: "error",
//    	                            confirmButtonText: "확인"
//    	                        });
//    	                    }
//    	                })
//    	                .catch((error) => {
//    	                    Swal.fire({
//    	                        title: "삭제 실패",
//    	                        text: "서버와의 통신 중 문제가 발생했습니다.",
//    	                        icon: "error",
//    	                        confirmButtonText: "확인"
//    	                    });
//    	                    console.error("Error:", error);
//    	                });
//    	            }
//    	        });
//    	    });
//    	});



   	</script>
