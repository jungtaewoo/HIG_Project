<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


    <!-- 스타일: 기존 레이아웃 및 테이블 기본 스타일 -->
    <style>
        .dashboard-container {
            display: flex;
            flex-direction: column;
            gap: 2.0rem;
        }
        .summary-stats {
            display: flex;
            flex-wrap: wrap;
            gap: 2.0rem;
            margin-bottom: 2.0rem;
        }
        .stat-card {
            flex: 1;
            min-width: 120px;
            padding: 1rem;
            border-radius: 0.5rem;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
            background: #f8f9fa;
        }
        .stat-card .stat-title {
            font-size: 1.1rem;
            color: #6c757d;
            margin-bottom: 0.75rem;
        }
        .stat-card .stat-value .badge {
            font-size: 2.0rem;  /* 배지 폰트 크기 확대 */
		    font-weight: bold;
		    padding: 0.75rem 1rem; /* 배지의 상하좌우 여백 확장 */
        }
        
        /* 테이블 컨테이너 및 레이아웃 (기존 스타일 유지) */
        .dataTable-wrapper {
            width: 100%;
            overflow-x: auto;
            background-color: #fff;
        }
        .dataTable-wrapper table {
            width: 100% !important;
            table-layout: fixed;
        }
        
        /* a.draftDetailLink에 대한 오버라이드 (기존 스타일 유지) */
        a.draftDetailLink,
        a.draftDetailLink:hover,
        a.draftDetailLink:focus,
        a.draftDetailLink:active {
            font-family: 'Nunito', sans-serif !important;
            font-size: 15px !important;
            line-height: 1.4 !important;
            color: #435EBE !important;
            text-decoration: none !important;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            background-color: transparent;
            border: none;
            box-shadow: none;
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
        
        .dataTable-wrapper table.dataTable tbody tr {
            height: 50px !important;
            line-height: 1.4 !important;
        }
        
        /* -------------------------------------------
           [최종 override] 아래 코드는 오직 폰트 크기만 강제 변경합니다.
           테이블의 기존 스타일(패딩, 줄 높이 등)은 그대로 유지됨
           #draftDocumentsContainer 내부의 테이블 셀만 대상입니다.
           ------------------------------------------- */
        #draftDocumentsContainer .table-striped tbody td,
        #draftDocumentsContainer .table-striped tbody td * {
            font-size: 15px !important;
        }
    </style>

    <!-- 외부 스크립트 -->
    <script src="${pageContext.request.contextPath}/resources/js/approval/approverChartStatusCount.js"></script>
    <!-- Top Bar -->
    <div class="top-bar">
        <div>
            <button type="button" class="btn btn-outline-secondary" onclick="history.back();">
                <i class="fas fa-arrow-left"></i>
            </button>
        </div>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb mb-0">
                <li class="breadcrumb-item fw-bold text-primary">
                  <a href="${pageContext.request.contextPath}/account/login/home">📌 Main</a>
                </li>
                <li class="breadcrumb-item"><a href="/approval/templateList">기안작성</a></li>
                <li class="breadcrumb-item active" aria-current="page">제출문서</li>
                <li class="breadcrumb-item"><a href="/approval/approverDrafts">결재현황</a></li>
                <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
                    <li class="breadcrumb-item"><a href="/approval/list">결재양식</a></li>
                </security:authorize>
            </ol>
        </nav>
    </div>

    <section class="section">
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h2 class="mb-0">제출문서</h2>
                <div>
                    <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/approval/templateList">기안작성</a>
                    <button class="btn btn-outline-secondary btn-sm" onclick="location.reload()">새로고침</button>
                </div>
            </div>

            <div class="card-body dashboard-container">
                <!-- 상태 요약 패널 -->
                <div class="summary-stats">
                    <div class="stat-card">
                        <div class="stat-title">대기</div>
                        <div class="stat-value">
                            <span class="badge bg-secondary" data-status="대기">${APRSTATUS}</span>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-title">회수</div>
                        <div class="stat-value">
                            <span class="badge bg-info" data-status="회수">${APRSTATUS}</span>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-title">완료</div>
                        <div class="stat-value">
                            <span class="badge bg-success" data-status="승인">${APRSTATUS}</span>
                        </div>
                    </div>
                    <div class="stat-card">
                        <div class="stat-title">반려</div>
                        <div class="stat-value">
                            <span class="badge bg-danger" data-status="반려">${APRSTATUS}</span>
                        </div>
                    </div>
                </div>

                <!-- 데이터 테이블 컨테이너 (테이블 스타일은 기본 그대로 유지) -->
                <div id="draftDocumentsContainer" class="table table-striped">
                    <div class="dataTable-top"></div>
                    <!-- 실제 테이블은 JavaScript로 동적으로 생성되거나 서버에서 렌더링됩니다 -->
                </div>
            </div>
        </div>
    </section>

    <!-- JavaScript: Simple-DataTables 및 approvalProcess -->
    <script type="module" src="${pageContext.request.contextPath}/resources/js/approval/approvalProcess.js"></script>
    <script>
      $(document).ready(function () {
          // 전체 문서 조회
          fetchMyDraftDocument();

          // 로컬스토리지 복원
          const savedStatus = localStorage.getItem("draftStatusFilterValue");
          if (savedStatus) {
              $("#draftStatusFilter").val(savedStatus);
              filterDraftDocuments(window.draftData);
              localStorage.removeItem("draftStatusFilterValue");
          }

          // 요약 카드 클릭 이벤트
          $('.stat-card .badge')
            .css('cursor', 'pointer')
            .on('click', function() {
                const aprStatus = $(this).data('status'); // '대기', '완료', '반려', '회수'
                const mapping = {
                    '대기': '대기',
                    '완료': '승인',  // 완료 -> 승인으로 매핑
                    '반려': '반려',
                    '회수': '회수'
                };
                const filterVal = mapping[aprStatus] || 'all';
                $('#draftStatusFilter').val(filterVal);
                filterDraftDocuments(window.draftData);
            });
      });
    </script>
