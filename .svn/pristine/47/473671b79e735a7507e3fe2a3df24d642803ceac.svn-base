<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

    <style>
    /* --- 기존 스타일 유지 --- */
    body {
        background-color: #f8f9fa;
        font-family: 'Noto Sans KR', sans-serif;
        margin: 0;
        padding: 20px;
    }
    .page-container {
        width: 100%;
        padding: 20px;
    }
    section.section {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
    }
    .card {
        border: 1px solid #dee2e6 !important;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08) !important;
        border-radius: 10px;
    }
    .card-header {
        border-bottom: 1px solid #ced4da !important;
    }
    .card-body {
        background-color: white;
        border-radius: 0 0 10px 10px;
    }
    .alert-info-custom {
        background-color: #e9f7fd;
        color: #31708f;
        padding: 12px 15px;
        border: 1px solid #bce8f1;
        border-radius: 4px;
        margin-bottom: 20px;
    }
    .category-list, .template-container {
        min-height: 400px;
        max-height: 650px;
        overflow-y: auto;
    }
    .category-btn {
        width: 100%;
        text-align: left;
        margin-bottom: 8px;
        padding: 12px 15px;
        transition: all 0.3s;
        border-radius: 6px;
        font-weight: 500;
    }
    .category-btn:hover {
        background-color: #e9ecef;
    }
    .category-btn.active {
        background-color: #0d6efd;
        color: white;
    }
    .template-item {
        padding: 12px 15px;
        transition: all 0.3s;
        border-radius: 6px;
        margin-bottom: 8px;
        border-left: 5px solid transparent;
    }
    .template-item:hover {
        background-color: #f1f3f5;
    }
    .template-item a {
        color: #495057;
        text-decoration: none;
        display: block;
    }
    .example-highlight {
        border-left: 5px solid #ffa94d;
        background-color: #fff8e6;
    }
    .highlight-node {
	    background-color: #ffff99; /* 연한 노란색 */
	    border: 1px solid #f1c40f;
	}
    .category-title {
        border-left: 4px solid #0d6efd;
        padding-left: 10px;
        margin-bottom: 15px;
    }
    .template-list {
        display: none;
    }
    .template-list.active {
        display: block;
    }
    .modal-xl {
        max-width: 90%;
    }
    .modal-content {
        background-color: #f8f9fa;
    }
    #approvalFormContainer {
        border: 3px solid #aaa;
        border-radius: 8px;
        margin-top: 10px;
        display: flex;
        justify-content: center;
        align-items: flex-start;
        width: auto;
        max-width: 800px;
        margin: 0 auto;
        padding: 10px;
    }
    .approval-buttons-container {
        display: flex;
        justify-content: flex-start;
        gap: 5px;
        margin-bottom: 15px;
    }
    .modal-custom {
        max-width: 920px;
        margin: auto;
    }
    .modal-orgCustom {
        max-width: 900px;
        margin: auto;
    }
    #orgTree {
        max-height: 500px;
        overflow-y: auto;
    }
    #orgTree .fancytree-container {
        background-color: transparent !important;
        border: none !important;
    }
    
	    /* 조직도 노드 하이라이트 스타일 */
	.fancytree-node.highlight-node > .fancytree-title, /* 제목 부분을 하이라이트 */
	.fancytree-node.highlight-node > span > .fancytree-title /* 좀 더 구체적인 선택자 */
	{
	  background-color: #ffff99 !important; /* 노란색 배경 */
	  font-weight: bold; /* 글자 강조 (선택 사항) */
	  border-radius: 3px; /* 약간 둥글게 (선택 사항) */
	  padding: 1px 3px; /* 약간의 내부 여백 (선택 사항) */
	}
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
    .page-title {
        text-align: center;
        padding: 20px 20px 20px 20px;
    }
    .card h2 {
        font-size: 1.6rem;
        font-weight: 500;
        color: #343a40;
        display: flex;
        align-items: center;
/*         gap: 8px; */
        margin: 0;
    }
    .card .page-title h2 {
        color: #25396F !important;
    }
    .icon-closer {
    	margin-right: 2px; /* 원하는 크기로 조정 */
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
                <li class="breadcrumb-item active" aria-current="page">기안작성</li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/approval/mydrafts">제출문서</a></li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/approval/approverDrafts">결재현황</a></li>
                <security:authorize access="hasAnyRole('HR_MANAGER','HR_ADMIN')">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/approval/list">결재양식</a></li>
                </security:authorize>
            </ol>
        </nav>
    </div>

    <div class="alert-info-custom">
        <i class="fas fa-info-circle"></i>
          새 문서를 작성할 때 결재선을 먼저 지정해주세요!
    </div>

    <!-- 숨김 필드: 'draftDeptId' 라는 ID로 현재 부서 저장 -->
	<input type="hidden" id="draftDeptId" value="${currentDeptId}" />
	
	<!-- 이미 있는 rank, approverId 등은 그대로 사용 -->
	<input type="hidden" id="draftRank" value="${currentRank}" />
	<input type="hidden" class="approverId" value="${approver.approverId}" />
	
	<!-- 본인 팀 정보 (예: 데이터 처리팀의 ID)가 있다면 'teamId'로 저장 -->
	<input type="hidden" id="teamId" value="${currentTeamId}" />
	
	<!-- 본인 이름 -->
	<input type="hidden" id="empName" value="${currentEmpName}" />

	
	
    <c:set var="countGeuntae" value="0" scope="page" />
    <c:set var="countInsa" value="0" scope="page" />

    <c:forEach var="template" items="${draftEmpTemplate}">
        <c:choose>
            <c:when test="${template.templateCategory eq '근태관리'}">
                <c:set var="countGeuntae" value="${countGeuntae + 1}" scope="page" />
            </c:when>
            <c:when test="${template.templateCategory eq '인사관리'}">
                <c:set var="countInsa" value="${countInsa + 1}" scope="page" />
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <section class="section">
        <div class="card">
            <div class="page-title">
                <h2><i class="fas fa-file-signature me-1 icon-closer"></i>기안작성</h2>
            </div>
            <div class="page-container">
                <div class="row">
                    <!-- 좌측: 문서 종류(카테고리) -->
                    <div class="col-md-3 mb-4 mb-md-0">
                        <div class="card h-100">
                            <div class="card-header">
                                <h5><i class="fas fa-folder me-2"></i>기안종류</h5>
                            </div>
                            <div class="card-body p-3 category-list">
                                <button class="btn category-btn active" data-category="all" onclick="showTemplates('all')">
                                    <i class="fas fa-folder"></i> 전체 양식 보기
                                </button>
                                <c:forEach var="category" items="${categoryList}">
                                    <c:if test="${category eq '근태관리' or category eq '인사관리'}">
                                        <button class="btn category-btn" data-category="${category}" onclick="showTemplates('${category}')">
                                            <i class="fas fa-folder"></i> ${category}
                                        </button>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- 가운데: 문서 양식 리스트 -->
                    <div class="col-md-6 mb-4 mb-md-0">
                        <div class="card h-100">
                            <div class="card-header">
                                <h5><i class="fas fa-file-signature me-2"></i>기안양식</h5>
                            </div>
                            <div class="card-body p-3 template-container">
                                <div class="row row-cols-2 g-3">
                                    <div class="col">
                                        <div id="templates-근태관리" class="template-list">
                                            <h4 class="category-title">
                                                근태관리
                                                <span>[<c:out value="${countGeuntae}" />]</span>
                                            </h4>
                                            <div class="list-group">
                                                <c:set var="hasGeuntae" value="false" />
                                                <!-- 1. '근태관리' 중 '[작성예시]' -->
                                                <c:forEach var="template" items="${draftEmpTemplate}">
                                                    <c:if test="${template.templateCategory eq '근태관리' and fn:contains(template.templateTite, '[작성예시]')}">
                                                        <c:set var="hasGeuntae" value="true" />
                                                        <div class="template-item example-highlight">
                                                            <a href="javascript:void(0);"
                                                               onclick="openApprovalForm('${template.templateId}', '${template.templateTite}', event)">
                                                                <i class="fas fa-star template-icon" style="color:#ffa94d;"></i>
                                                                ${template.templateTite}
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 2. '근태관리' 중 정확히 '연차신청서' -->
                                                <c:forEach var="template" items="${draftEmpTemplate}">
                                                    <c:if test="${template.templateCategory eq '근태관리' and template.templateTite eq '연차신청서'}">
                                                        <c:set var="hasGeuntae" value="true" />
                                                        <div class="template-item">
                                                            <a href="javascript:void(0);"
                                                               onclick="openApprovalForm('${template.templateId}', '${template.templateTite}', event)">
                                                                <i class="fas fa-file-alt template-icon"></i>
                                                                ${template.templateTite}
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 3. '근태관리' 중 '[작성예시]' 미포함 & '연차신청서' 아닌 것 -->
                                                <c:forEach var="template" items="${draftEmpTemplate}">
                                                    <c:if test="${template.templateCategory eq '근태관리' 
                                                                 and not fn:contains(template.templateTite, '[작성예시]') 
                                                                 and template.templateTite ne '연차신청서'}">
                                                        <c:set var="hasGeuntae" value="true" />
                                                        <div class="template-item">
                                                            <a href="javascript:void(0);"
                                                               onclick="openApprovalForm('${template.templateId}', '${template.templateTite}', event)">
                                                                <i class="fas fa-file-alt template-icon"></i>
                                                                ${template.templateTite}
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 4. 없으면 -->
                                                <c:if test="${hasGeuntae eq 'false'}">
                                                    <div class="text-center text-muted py-2">
                                                        <i class="fas fa-search mb-1" style="font-size: 1.5rem;"></i><br />
                                                        <span>해당 문서 양식이 없습니다.</span>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div id="templates-인사관리" class="template-list">
                                            <h4 class="category-title">
                                                인사관리
                                                <span>[<c:out value="${countInsa}" />]</span>
                                            </h4>
                                            <div class="list-group">
                                                <c:set var="hasInsa" value="false" />
                                                <!-- 1. '인사관리' 중 '[작성예시]' 포함 -->
                                                <c:forEach var="template" items="${draftEmpTemplate}">
                                                    <c:if test="${template.templateCategory eq '인사관리' and fn:contains(template.templateTite, '[작성예시]')}">
                                                        <c:set var="hasInsa" value="true" />
                                                        <div class="template-item example-highlight">
                                                            <a href="javascript:void(0);"
                                                               onclick="openApprovalForm('${template.templateId}', '${template.templateTite}', event)">
                                                                <i class="fas fa-star template-icon" style="color:#ffa94d;"></i>
                                                                ${template.templateTite}
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 2. '인사관리' 중 '[작성예시]' 미포함 -->
                                                <c:forEach var="template" items="${draftEmpTemplate}">
                                                    <c:if test="${template.templateCategory eq '인사관리' and not fn:contains(template.templateTite, '[작성예시]')}">
                                                        <c:set var="hasInsa" value="true" />
                                                        <div class="template-item">
                                                            <a href="javascript:void(0);"
                                                               onclick="openApprovalForm('${template.templateId}', '${template.templateTite}', event)">
                                                                <i class="fas fa-file-alt template-icon"></i>
                                                                ${template.templateTite}
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <!-- 3. 없으면 -->
                                                <c:if test="${hasInsa eq 'false'}">
                                                    <div class="text-center text-muted py-2">
                                                        <i class="fas fa-search mb-1" style="font-size: 1.5rem;"></i><br />
                                                        <span>해당 문서 양식이 없습니다.</span>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- row-cols-2 -->
                            </div>
                        </div>
                    </div>

                    <!-- 우측: 내 임시 저장 목록 -->
                    <div class="col-md-3">
                        <div class="card h-100">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <h5 class="mb-0"><i class="fas fa-save me-2"></i>내 임시저장</h5>
                                <button class="btn btn-outline-secondary" onclick="location.reload()">
                                    <i class="fas fa-sync-alt"></i> 새로고침
                                </button>
                            </div>
                            <div class="card-body">
                                <div id="draftListContainer">
                                    <!-- 임시저장된 문서 목록 표시 영역 -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- row -->
            </div> <!-- page-container -->
        </div> <!-- card -->
    </section>

    <!-- 문서 작성 모달 -->
    <div class="modal fade" id="approvalFormModal" tabindex="-1">
        <div class="modal-dialog modal-custom">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="approvalFormModalLabel">기안 양식</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="approval-buttons-container d-flex justify-content-end">
                        <button type="button" id="openOrgModalBtn" class="btn btn-primary">결재선 지정</button>
                    </div>
                    <div id="approvalFormContainer">
                        <div class="text-center py-4">
                            <div class="spinner-border text-primary"></div>
                            <p class="mt-2">문서 양식을 불러오는 중입니다...</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a id="downloadButton" class="btn btn-secondary" download>문서 다운로드</a>
                    <button type="button" class="btn btn-outline-secondary" id="autoFillVacationBtn">
					  연차 자동 입력
					</button>
                    <button type="button" class="btn btn-primary" id="submitApprovalBtn">결재요청</button>
                    <button class="btn btn-primary" id="resubmitBtn">재요청</button>
                    <button class="btn btn-primary" id="tempSaveDraftBtn">임시 저장</button>
                    <button class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>

       <!-- 조직도(결재선) 모달 -->
		<div class="modal fade" id="orgModal" tabindex="-1" aria-labelledby="orgModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-orgCustom">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="orgModalLabel">결재선 지정</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      </div>
		      <div class="modal-body">
		        <div style="margin-bottom: 10px;">
		          <input type="text" id="empSearchInput" placeholder="이름, 팀, 부서, 직급 검색" class="form-control" aria-label="Search">
		          <button id="empSearchBtn" class="btn btn-primary"><i class="fas fa-search"></i> 검색</button>
		          <button id="collapseAllBtn" class="btn btn-secondary">전체 닫기</button>
		          <button id="expandAllBtn" class="btn btn-secondary">전체 열기</button>
		          <button id="expandSameDeptBtn" class="btn btn-secondary">본인 부서</button>
		        </div>
		        <!-- 좌측과 우측 영역을 나눔 -->
		        <div style="display: flex; gap: 20px;">
		          <!-- 좌측: 전체 조직도(기존 orgTree)는 그대로 둔다 -->
		          <div id="orgTree" style="min-height: 400px; border: 1px solid #ddd; border-radius: 5px; padding: 10px; width: 452px;"></div>
		          <!-- 우측: 즐겨찾기 결재라인을 보여줄 새 컨테이너 -->
		          <div id="favoriteOrgTree" style="flex: 1; border: 1px dashed #ccc; border-radius: 5px; padding: 10px;">
		            <!-- 즐겨찾기 결재라인 목록이 여기 표시됩니다. 상단에 "즐겨찾는 결재라인" 제목 삽입 -->
		            <h6 style="margin-bottom: 10px; color:blue;">주 결재라인</h6>
		          </div>
		        </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" id="saveApprovalLineBtn" class="btn btn-success">지정</button>
		        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
		      </div>
		    </div>
		  </div>
		</div>


    <!-- 사용자 정의 JS -->
    <!-- 임시저장, 상세보기, 템플릿 불러오기, 결재 JS 등 순차로드 -->
    <script type="module" src="${pageContext.request.contextPath}/resources/js/approval/approvalTempDraftSave.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/resources/js/approval/approvalMyDraftDetail.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/approval/approvalTemplate.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/resources/js/approval/approvalDraft.js"></script>

    <!-- UI 제어용 스크립트 (카테고리 버튼, 모달 열기 등) -->
    <script>
    // 페이지 로드시 기본 'all' 카테고리 표시
    document.addEventListener("DOMContentLoaded", function() {
        showTemplates('all');

        // '결재선 지정' 버튼 → openOrgModal() 호출
        const openOrgBtn = document.getElementById("openOrgModalBtn");
        if (openOrgBtn) {
            openOrgBtn.addEventListener("click", function() {
                if (typeof openOrgModal === "function") {
                    openOrgModal();
                } else {
                    console.error("openOrgModal 함수를 찾을 수 없습니다.");
                }
            });
        }
    });

    // 카테고리별 문서 양식 목록 표시
    function showTemplates(categoryName) {
        document.querySelectorAll(".category-btn").forEach(btn => btn.classList.remove("active"));
        const selectedBtn = document.querySelector(`.category-btn[data-category='${categoryName}']`);
        if (selectedBtn) selectedBtn.classList.add("active");

        // 모든 템플릿 목록 숨기기
        $(".template-list").removeClass("active").hide();

        if (categoryName === "all") {
            // 전체 보이기
            $(".template-list").addClass("active").fadeIn(400);
        } else {
            // 해당 카테고리만 보이기
            const selectedList = $("#templates-" + categoryName);
            if (selectedList.length > 0) {
                selectedList.addClass("active").fadeIn(400);
            }
        }
    }

    // 문서 양식 모달 열기
    async function openApprovalForm(templateId, templateTitle, event) {
        if (!templateId) {
            alert("템플릿 ID가 올바르지 않습니다.");
            return;
        }
        // 클릭 애니메이션(선택 사항)
        if(event) {
            const $link = $(event.target).closest("a");
            if($link.length) {
                $link.fadeOut(200, function(){
                    $link.fadeIn(200);
                });
            }
        }
        // 모달 제목 변경, 로딩화면 세팅
        document.getElementById('approvalFormModalLabel').textContent = templateTitle + ' 기안 양식';
        document.getElementById('approvalFormContainer').innerHTML = `
            <div class="text-center py-4">
                <div class="spinner-border text-primary"></div>
                <p class="mt-2">문서 양식을 불러오는 중입니다...</p>
            </div>
        `;
        var modal = new bootstrap.Modal(document.getElementById('approvalFormModal'));
        modal.show();

        // 템플릿 로드 함수 (approvalTemplate.js 등 참고)
        await loadTemplate(templateId);

        // 만약 "연차신청서"라면 초기에 문서 제목 자동 입력
        if(templateTitle === "연차신청서") {
            $("input[name='draftTitle']").val("연차신청서");
        }
    }
    </script>
