<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
<security:authentication property="principal" var="principal" />

<style>
#table1 tbody td {
    font-size: 15px !important;
}
</style>

	<div class="container-fluid">

<!--      좌측: 버튼 그룹 -->
<!--     <div> -->
<!--       <button type="button" class="btn btn-outline-secondary" onclick="history.back();"> -->
<!--         <i class="fas fa-arrow-left"></i> -->
<!--       </button> -->
<!--     </div> -->


    <!-- 우측: Breadcrumb -->
<!--     <nav aria-label="breadcrumb"> -->
<!--       <ol class="breadcrumb mb-0"> -->
<!--         <li class="breadcrumb-item fw-bold text-primary">📌 Quick Menu</li> -->
<!--         <li class="breadcrumb-item active" aria-current="page">문서작성</a></li> -->
<!--         <li class="breadcrumb-item"><a href="/approval/mydrafts">제출문서</a></li> -->
<!--         <li class="breadcrumb-item"><a href="/approval/approverDrafts">결재현황</a></li> -->
<%--         <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')"> --%>
<!--         	<li class="breadcrumb-item"><a href="/approval/list">결재양식</a></li> -->
<%--         </security:authorize> --%>
<!--       </ol> -->
<!--     </nav> -->

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
        <li class="breadcrumb-item active" aria-current="page">공지사항</li>
      </ol>
    </nav>

  </div>
</div>


<div class="page-title">

        </div>

		<%-- Flash Attribute 확인 후 Toastify 실행 --%>
		<c:if test="${not empty noticeSuccess}">
		    <script>
		        window.addEventListener("load", function() {
		            Toastify({
		                text: "${noticeSuccess}",
		                duration: 3000,
		                gravity: "top",
		                position: "center",
		                backgroundColor: "#4fbe87",
		                stopOnFocus: true
		            }).showToast();
		        });
		    </script>
		</c:if>

		<c:if test="${not empty noticeDelete}">
		    <script>
		        window.addEventListener("load", function() {
		            Toastify({
		                text: "${noticeDelete}",
		                duration: 3000,
		                gravity: "top",
		                position: "center",
		                backgroundColor: "#f25a5a",
		                stopOnFocus: true
		            }).showToast();
		        });
		    </script>
		</c:if>


        <section class="section">

            <div class="card">
<!--                 <div class="card-header"> -->
<!--                     <h5 class="card-title">공지목록</h5> -->
<!--                 </div> -->
                <div class="card-body">
                  <h3 >공지사항</h3>
                    <!-- 중요 공지 섹션 -->
                    <c:if test="${not empty importanceBoardList}">
                        <div class="fixed-notice-section">
                            <h5 class="text-primary mb-3"><i class="fas fa-exclamation-circle"></i> 중요 공지</h5>
                            <table class="table table-hover fixed-notice-table">
                                <tbody>
                                    <c:forEach items="${importanceBoardList}" var="impboard">
                                        <tr data-importance="Y">
                                            <td width="15%">
                                                <i class="fa fa-thumbtack text-primary"
                                                   onclick="navigateToPin('${impboard.noticeId}', 'Y')"></i>
                                            </td>
                                            <td width="20%">${impboard.category.categoryName}</td>
                                            <td>
                                                <a href="javascript:void(0);"
                                                   onclick="navigateToDetail('${impboard.noticeId}')">
                                                   ${impboard.title}
                                                </a>
                                            </td>
<%--                                             <td width="15%">${impboard.role.roleName}</td> --%>
											<td>${impboard.emp.name} ${impboard.role.roleName}</td>
                                            <td width="15%"><fmt:formatDate value="${impboard.createdAt}" pattern="yyyy-MM-dd"/></td>
                                            <td width="10%">${impboard.viewCount}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="separator"></div>
                    </c:if>

                    <!-- 일반 공지 섹션 -->
                    <div class="normal-notice-section">
                        <table class="table table-striped" id="table1">
                            <thead>
                                <tr>
                                    <th width="15%">글번호</th>
                                    <th width="20%">카테고리</th>
                                    <th>제목</th>
                                    <th width="15%">작성자</th>
                                    <th width="15%">작성일</th>
                                    <th width="10%">조회수</th>
                                </tr>
                            </thead>
                            <tbody>

<%-- 								<c:if test="${board.emp == null}"> --%>
<!-- 								  <td>emp가 없습니다!</td> -->
<%-- 								</c:if> --%>

                                <c:forEach items="${boardList}" var="board">
                                    <tr>
                                        <td>
                                            <a href="javascript:void(0);" onclick="navigateToPin('${board.noticeId}')">
                                               <i class="fa fa-thumbtack" style="color: #ccc;"></i> ${board.noticeId}

                                            </a>
                                        </td>
                                        <td>${board.category.categoryName}</td>
                                        <td>
                                            <a href="javascript:void(0);" onclick="navigateToDetail('${board.noticeId}')">
                                               ${board.title}
                                            </a>
                                        </td>
                                        <td>${board.emp.name} ${board.role.roleName}</td>
                                        <td><fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd"/></td>
                                        <td>${board.viewCount}</td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty boardList and empty importanceBoardList}">
                                    <tr><td colspan="6">등록된 공지가 없습니다</td></tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
					<security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
	                    <div class="text-end mt-4">
	                        <a href="${pageContext.request.contextPath}/board/register" class="btn btn-primary">
	                           <i class="fas fa-pen"></i> 공지등록 </a>
	<!--                            <button id="top-center" class="btn btn-success">토스티파이 테스트</button> -->
	<!--                            <button id="success" class="btn btn-success ">스윗알러트 테스트</button> -->
	                    </div>
                    </security:authorize>
                </div>
            </div>
        </section>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.0.3/dist/umd/simple-datatables.min.js"></script>


    <script>
    function navigateToDetail(noticeId) {
        window.location.href = "${pageContext.request.contextPath}/board/detail?noticeId=" + noticeId;
    }
    </script>

<security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
    <script>

 // Toastify 실행 함수 버튼으로 실행
	document.getElementById("top-center").addEventListener("click", () => {
	  Toastify({
	    text: "공지사항이 삭제되었습니다!",
	    duration: 3000,
	    close: true,
	    gravity: "top",
	    position: "center",
	    backgroundColor: "#f25a5a",
// 	    backgroundColor: "#4fbe87",
	  }).showToast()
	});

    document.getElementById("success").addEventListener("click", function() {
        Swal.fire({
            title: "등록 성공!",
            text: "데이터가 성공적으로 등록되었습니다.",
            icon: "success",
            confirmButtonText: "확인"
        });
    });

        // 중요 공지 테이블 설정 (정렬/검색 비활성화)
        new simpleDatatables.DataTable(".fixed-notice-table", {
            searchable: false,
            sortable: false,
            paging: false,
            info: false
        });




        function navigateToPin(noticeId, currentImportance) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = "${pageContext.request.contextPath}/board/updatePin";

            const noticeIdInput = document.createElement('input');
            noticeIdInput.type = 'hidden';
            noticeIdInput.name = 'noticeId';
            noticeIdInput.value = noticeId;

            const importanceInput = document.createElement('input');
            importanceInput.type = 'hidden';
            importanceInput.name = 'importance';
            importanceInput.value = currentImportance === 'Y' ? 'N' : 'Y';

            form.appendChild(noticeIdInput);
            form.appendChild(importanceInput);
            document.body.appendChild(form);
            form.submit();
        }

    </script>
</security:authorize>