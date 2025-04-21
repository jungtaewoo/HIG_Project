<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<style>
/* 퀵메뉴바 스타일 - 기존 유지 */
.top-bar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    margin-bottom: 20px;
    background-color: transparent;
    box-shadow: none;
    border: none;
}
/* 테이블 셀 사이에 여백 주기 (예: 1rem = 16px 정도) */
table.table th,
table.table td {
  padding: 1rem !important;  /* 수치 조정 가능 */
  vertical-align: top;       /* 내용이 위쪽 정렬되도록(가독성) */
}

/* 카드 푸터의 기본 패딩과 경계선 제거 */
.card-footer.bg-transparent {
    background-color: transparent !important;
    border-top: none;
    padding-top: 0; /* 위쪽 패딩 제거 */
}

</style>
<div class="top-bar">
    <div>
        <button type="button" class="btn btn-outline-secondary" onclick="history.back();">
            <i class="fas fa-arrow-left"></i>
        </button>
    </div>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb mb-0">
            <li class="breadcrumb-item fw-bold text-primary"><a href="${pageContext.request.contextPath}/account/login/home">📌 Main</a> </li>
            <li class="breadcrumb-item"><a href="/approval/templateList">문서작성</a></li>
            <li class="breadcrumb-item"><a href="/approval/mydrafts">제출문서</a></li>
            <li class="breadcrumb-item"><a href="/approval/approverDrafts">결재현황</a></li>
            <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
                <li class="breadcrumb-item active" aria-current="page">결재양식</li>
            </security:authorize>
        </ol>
    </nav>
</div>



<section class="section">
    <div class="card">
        <div class="card-header">
            <h2><i class="fas fa-file-alt me-2"></i>결재 양식 등록</h2>
        </div>

        <form action="${pageContext.request.contextPath}/approval/ApprovalFormProcess" method="post" enctype="multipart/form-data">
            <div class="card-body">
                <div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
                    <div class="dataTable-container">
                        <table class="table">
                            <tr>
                                <th></th>
                                <td>
                                    <input type="hidden" name="templateId" class="form-control">
                                </td>
                            </tr>
                            <tr>
                                <th>제목</th>
                                <td>
                                    <input type="text" name="templateTite" class="form-control" required>
                                </td>
                            </tr>

                            <tr>
                                <th>사원명</th>
                                <td>
                                    <input type="text" name="empName" class="form-control" value="${sessionScope.sessionAccount.empName}" readonly>
                                    <input type="hidden" name="empId" value="${sessionScope.sessionAccount.empId}">
                                </td>
                            </tr>

                            <tr>
                                <th>내용</th>
                                <td>
                                    <textarea id="contentEditor" name="templateContent" class="form-control" required></textarea>
                                </td>
                            </tr>

                            <tr>
                                <th>카테고리</th>
                                <td>
                                    <input type="hidden" name="templateCategory" id="templateCategory">
                                    <select id="categorySelect" class="form-control mt-2">
                                        <option value="">카테고리 선택</option>
                                        <c:forEach var="category" items="${categoryList}">
                                            <option value="${category}">${category}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <th>사용 여부</th>
                                <td>
                                    <input class="form-check-input" type="checkbox" name="temlpateDeleted" value="N" id="useStatus" checked> 사용
                                </td>
                            </tr>

                            <tr>
                                <th>파일 첨부</th>
                                <td>
                                    <input type="file" name="files" multiple class="form-control">
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

            <div class="card-footer bg-transparent border-0 pt-0 pb-3 px-4"> <div class="row mt-3"> <div class="col-sm-6">
                        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/approval/list">
                            <i class="fas fa-list"></i> 양식 목록
                        </a>
                    </div>
                    <div class="col-sm-6 text-end">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-plus"></i>양식 등록
                        </button>
                    </div>
                </div>
            </div>
            </form>
    </div> </section>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const categorySelect = document.getElementById("categorySelect");
        const templateCategory = document.getElementById("templateCategory");

        categorySelect.addEventListener("change", function() {
            templateCategory.value = categorySelect.value;  // 선택된 카테고리 값을 저장
        });

        // CKEditor 초기화 (설치된 플러그인에 맞게 설정)
        CKEDITOR.replace('contentEditor', {
            versionCheck: false
            // 필요시 CKEditor 추가 설정
        });
    });
</script>