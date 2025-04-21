<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<style> 
 /* 배경을 흰색으로 지정 */
body {
    margin: 0;
    padding: 0;
}
/* 버튼 컨테이너를 오른쪽 정렬 (버튼들이 오른쪽에 배치됨) */
.button-container {
    text-align: right;
    margin-bottom: 1rem; /* 여백 */
}
/* 버튼 간격을 주고 싶다면 (Bootstrap 사용 시) me-2 등 클래스 활용 가능 */
.btn-spacing {
    margin-right: 10px;
}

.ck-content {
    font-family: "Noto Sans KR", sans-serif;
    line-height: 1.6;
}
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

/* 기존 테이블 스타일 관련 CSS(텍스트 사이즈를 제외한 다른 속성들은 그대로 유지) */
/* 예시로 패딩, 폰트 패밀리 등은 그대로 두고, 폰트 크기만 15px로 변경해야 함 */
.dataTable-wrapper table.dataTable tbody tr,
.dataTable-wrapper table.dataTable tbody tr.even,
.dataTable-wrapper table.dataTable tbody tr.odd,
.dataTable-wrapper table.dataTable tbody tr td,
.dataTable-wrapper table.dataTable tbody tr td * {
    font-family: 'Nunito', sans-serif !important;
    /* font-size 항목은 아래에서 override 할 예정이므로 여기서는 주석처리 */
    /* font-size: 15px !important; */
    line-height: 1.4 !important;
    color: #212529 !important;
    padding: 0.75rem !important;
    margin: 0 !important;
    transform: none !important;
}

/* 결재 양식 상세보기 테이블 (class="table table-striped datatable")에 적용 */
.table.table-striped.datatable {
  width: 100%;
  table-layout: fixed; /* 고정 레이아웃 */
}

/* 모든 TH와 TD의 기본 폰트, 패딩, 정렬 등 */
.table.table-striped.datatable th,
.table.table-striped.datatable td {
  font-size: 15px !important; /* 글자 크기 조정 */
  padding: 12px;
  vertical-align: middle;
}

/* 왼쪽 TH(항목명)에 원하는 너비/스타일 지정 */
.table.table-striped.datatable th {
  width: 180px; /* 혹은 20% 등 비율로 설정 가능 */
  white-space: nowrap; /* 항목명이 줄바꿈되지 않도록 (필요 시) */
  text-align: left; /* 왼쪽 정렬 */
}
/* (예) 결재 양식 상세보기 테이블의 tr, td에 공통 폰트 크기 15px 적용 */
.table.table-striped.datatable tbody tr td,
.table.table-striped.datatable tbody tr td * {
    font-size: 15px !important;
}

</style>

<!-- 퀵메뉴바 영역 - 기존 코드 유지 -->
<div class="top-bar">
    <!-- 좌측: 버튼 그룹 -->
    <div>	 
        <button type="button" class="btn btn-outline-secondary" onclick="history.back();">
            <i class="fas fa-arrow-left"></i> 
        </button>
    </div>
    
    <!-- 우측: Breadcrumb -->
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
        <!-- 헤더 -->
        <div class="card-header">
            <h2><i class="fas fa-file-alt me-2"></i>결재 양식 상세보기</h2>
        </div>
     <div class="card-body">

<table class="table table-striped datatable">
    <tr>
        <th>기안양식제목</th>
        <td>${template.templateTite}</td>
    </tr>
    <tr>
        <th>기안양식내용</th>
        <td>
            <div class="ck-content" style="border: 1px solid #ccc; padding: 10px; min-height: 100px; max-height: 500px; overflow-y: auto;">
                <c:out value="${template.templateContent}" escapeXml="false" />
            </div>
        </td>
    </tr>
    <tr>
        <th>기안양식카테고리</th>
        <td>${template.templateCategory}</td>
    </tr>
    <tr>
        <th>기안양식 사용여부</th>
        <td>
            <c:choose>
                <c:when test="${template.temlpateDeleted eq 'N'}">
                    <span class="badge bg-success">사용가능</span>
                </c:when>
                <c:otherwise>
                    <span class="badge bg-danger">사용불가</span>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th>기안양식 최초 등록일시</th>
        <td><fmt:formatDate value="${template.templateCreate}" pattern="yyyy년MM월dd일" /></td>
    </tr>

    <tr>
        <th>등록자권한</th>
        <td>${template.roleName}</td>
    </tr>

    <tr>
        <th>첨부 파일</th>
        <td>
            <c:forEach var="file" items="${template.fileDetails}">
                <c:set var="fileUrl" value="${pageContext.request.contextPath}/file/view/${file.fileSavename}" />
                <c:set var="downloadUrl" value="${pageContext.request.contextPath}/file/download/${file.detailId}" />

                <div style="margin-bottom: 10px;">
                    <a href="${downloadUrl}" target="_blank" download>${file.fileName} (다운로드)</a>
                    <button type="button" class="btn btn-outline-info btn-sm"
                            onclick="previewFormModal('${file.fileSavename}', '${file.fileName}')">
                        미리보기
                    </button>
                </div>
            </c:forEach>
        </td>
    </tr>
</table>
<div class="row mt-3">
		<!-- '양식 목록' 버튼 (왼쪽 하단) -->
		<div class="col-sm-6">
        <a class="btn btn-secondary btn-spacing" 
           href="${pageContext.request.contextPath}/approval/list">
            <i class="fas fa-list"></i> 양식 목록
        </a>
        </div>
        
        <!-- 오른쪽에 수정 버튼 -->
	    <div class="col-sm-6 text-end">
        <a class="btn btn-primary"
           href="${pageContext.request.contextPath}/approval/approvalUpdateForm?templateId=${template.templateId}">
            <i class="fas fa-edit"></i> 양식 수정
        </a>
        </div>
        
    </div>
    </div>
</div>
</section>
<!-- 미리보기용 모달 -->
<div class="modal fade" id="previewFormModal" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="previewFormModalLabel">결재 양식 미리보기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body" id="previewFormContainer">
        <div class="text-center py-4">
          <div class="spinner-border text-primary"></div>
          <p class="mt-2">결재 양식을 불러오는 중입니다...</p>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!--  미리보기 스크립트 -->
<script>
function previewFormModal(fileName, title) {
    document.getElementById('previewFormModalLabel').textContent = `${title} 미리보기`;

    const modalBody = document.getElementById('previewFormContainer');
    modalBody.innerHTML = `
        <div class="text-center py-4">
          <div class="spinner-border text-primary"></div>
          <p class="mt-2">결재 양식을 불러오는 중입니다...</p>
        </div>
    `;

    const encodedFileName = encodeURIComponent(fileName);
    const url = "${pageContext.request.contextPath}/file/view/" + encodedFileName;

    const modal = new bootstrap.Modal(document.getElementById('previewFormModal'));
    modal.show();

    fetch(url)
      .then(response => {
        if (!response.ok) throw new Error("양식을 불러올 수 없습니다.");
        return response.text();
      })
      .then(html => {
        modalBody.innerHTML = html;

        // 모든 입력 요소를 비활성화 (읽기 전용 처리)
        modalBody.querySelectorAll("input, textarea, select, button").forEach(el => {
            el.setAttribute("readonly", true);
            el.setAttribute("disabled", true);
        });
      })
      .catch(err => {
        modalBody.innerHTML = `<div class='text-danger text-center'>양식을 불러오는 데 실패했습니다.</div>`;
      });
}
</script>
