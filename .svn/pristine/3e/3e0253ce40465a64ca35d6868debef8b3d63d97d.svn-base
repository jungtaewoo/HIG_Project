<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


    
    <!-- 폰트 및 아이콘 (필요 시) -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 10px;
        }

        /* Breadcrumb 통일 스타일 */
        .breadcrumb {
            background-color: transparent;
            margin-bottom: 0;
            padding: 0;
            font-size: 0.95rem;
        }
        .breadcrumb .breadcrumb-item {
            color: #666;
        }
        .breadcrumb .breadcrumb-item a {
            color: #007bff;
            text-decoration: none;
        }
        .breadcrumb .breadcrumb-item a:hover {
            text-decoration: underline;
        }
        .breadcrumb .breadcrumb-item.active {
            color: #999;
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
        .btn-outline-secondary {
            border: 1px solid #ccc;
            background-color: #fff;
            color: #333;
            cursor: pointer;
            padding: 6px 12px;
            border-radius: 4px;
            font-weight: 500;
            transition: background-color 0.2s ease;
        }
        .btn-outline-secondary i {
            margin-right: 5px;
        }
        .btn-outline-secondary:hover {
            background-color: #f0f0f0;
        }

        /* 섹션 및 카드 스타일 */
        .section {
            max-width: 1500px;
            margin: 0 auto;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 15px;
        }
        .card {
            border: none;
            background: transparent;
        }
        .card-header {
            border-bottom: 1px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 15px;
        }
        .card-header h2 {
            margin: 0;
            font-size: 1.2rem;
            font-weight: 700;
            display: flex;
            align-items: center;
        }
        .card-body {
            padding: 0;
        }

        /* 문서 기본 정보 */
        .top-container {
            margin-bottom: 20px;
        }
        .document-info {
            border-radius: 6px;
            padding: 16px;
            border: 3px solid #eee;
            width: 100%;
        }
        .document-info p {
            margin: 6px 0;
        }
        .document-info p strong {
            display: inline-block;
            margin-right: 10px;
            min-width: 120px;
            color: #333;
        }

        /* 결재 사유, 버튼 등 */
        .approval-action-container {
            background-color: #fff;
            border-radius: 6px;
            border: 1px solid #eee;
            padding: 15px;
            margin-bottom: 20px;
        }
        .reason-area label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        .reason-area textarea {
            width: 100%;
            height: 80px;
            box-sizing: border-box;
            margin-bottom: 10px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .btn-container {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
            padding: 10px 0;
            border-top: 1px solid #eee;
        }
        .btn {
            padding: 6px 12px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            text-decoration: none;
            display: inline-block;
            margin-left: 5px;
        }
        .btn-default {
            background-color: #f0f0f0;
            color: #333;
            border: 1px solid #ddd;
        }
        .btn-primary {
            background-color: #4a6fdc;
            color: white;
            border: none;
        }
        /* etc... (btn-success, btn-danger, btn-warning 등 필요시 추가) */

        .form-container {
            background-color: #fff;
            border-radius: 6px;
            border: 5px solid #eee;
            padding: 15px;
        }
        .annual-form-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        .annual-form-table th, .annual-form-table td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        .annual-form-table th {
            background-color: #f5f5f5;
            text-align: center;
        }
        .annual-form-table td {
            text-align: left;
        }
        /* 폼 내용 영역 */
        #annualFormContainer {
            margin-top: 10px;
/*             border: 5px solid #eee; */
            border-radius: 4px;
            padding: 15px;
            min-height: 150px;
            background-color: #fff;
        }
        #annualFormContainer p {
            margin: 0;
            color: #999;
        }
        .current-approver-banner {
		  background-color: #ffeeba;    /* 옅은 노란색 배경 */
		  border: 2px solid #f0ad4e;    /* 주황색 계열의 테두리 */
		  border-radius: 4px;
		  padding: 10px;
		  font-size: 1.3em;
		  font-weight: bold;
		  margin-top: 10px;
		  text-align: center;
		}
    </style>
</head>
<body>

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
            <!-- "퀵메뉴" 부분을 다른 li들과 동일 구조/스타일로 맞춤 -->
            <li class="breadcrumb-item fw-bold text-primary"><a href="${pageContext.request.contextPath}/account/login/home">📌 Main</a> </li>
            <li class="breadcrumb-item"><a href="/approval/templateList">문서작성</a></li>
            <li class="breadcrumb-item"><a href="/approval/mydrafts">제출문서</a></li>
            <li class="breadcrumb-item active" aria-current="page">결재현황</li>
            <security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
                <li class="breadcrumb-item"><a href="/approval/list">결재양식</a></li>
            </security:authorize>
        </ol>
    </nav> 
</div>

<section class="section">
    <div class="card">
        <div class="card-header">
            <h2 class="mb-0"><i class="fas fa-file-signature"></i>결재문서 상세보기</h2>
        </div>
        <div class="card-body">
            <!-- 숨은 필드들 -->
            <input type="hidden" id="draftId" name="draftId" value="${param.draftId}"/>
            <input type="hidden" id="approverId" name="approverId" value=""/>
            <input type="hidden" id="aprStatus" name="aprStatus" value=""/>

            <div class="top-container">
                <div class="document-info" id="detailContainer">
                    <!-- JS로 문서 정보를 동적으로 삽입 -->
                    <p>문서 정보를 불러오는 중...</p>
                </div>
            </div>

            <!-- 연차 신청서 폼 영역 -->
            <div class="form-container">
                <div id="annualFormContainer">
                    <p>연차 신청서 폼 로딩 중...</p>
                </div>
            </div>

            <!-- 하단 영역: 결재 사유 및 승인/반려 버튼 -->
            <div class="approval-action-container">
                <div class="reason-area">
                    <label for="aprReason">결재 사유</label>
                    <textarea class="form-control" id="aprReason" name="aprReason"
                              placeholder="결재 사유를 입력하세요..."></textarea>
                </div>
                <div class="btn-container">
                    <div>
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/approval/approverDrafts">
                            목록
                        </a>
                    </div>
                    <div>
                        <button class="btn btn-primary" id="approveBtn">승인</button>
                        <button class="btn btn-primary" id="rejectBtn">반려</button>
                    </div>
                </div>
            </div>
        </div><!-- // card-body -->
    </div><!-- // card -->
</section>

<hr/>

<script>
  // contextPath 설정
  window.contextPath = "${pageContext.request.contextPath}";
</script>

<!-- 외부 JS 로직: approvalDraftDetail.js -->
<script type="module" src="${pageContext.request.contextPath}/resources/js/approval/approvalDraftDetail.js"></script>
</body>

