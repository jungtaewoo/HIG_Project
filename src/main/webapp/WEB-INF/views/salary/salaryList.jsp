<%@page import="java.util.HashMap"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
   int currentYear = LocalDate.now().getYear();
   int currentMonth = LocalDate.now().getMonthValue();
%>

  <!-- DataTables CSS (CDN 예시) -->
  <link rel="stylesheet" type="text/css"
        href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.3.6/css/buttons.bootstrap5.min.css">

<style>
  /* 기존 스타일 유지 */
  .chart-container { display: flex; flex-wrap: wrap; gap: 20px; margin-bottom: 2rem; }
  .chart-box { flex: 1 1 45%; min-width: 300px; background: #fff; border-radius: 0.75rem; box-shadow: 0 2px 6px rgba(0,0,0,0.05); padding: 1.5rem; }
  canvas { width: 100% !important; height: 280px !important; }
  html { scroll-behavior: smooth; }

  /* === 개선된 스타일 === */

  /* 요약 카드 개선 */
  .summary-card-improved {
    border: 1px solid #dee2e6;
    border-top: 3px solid var(--bs-primary); /* 상단 보더로 강조 */
    transition: all 0.3s ease;
  }
  .summary-card-improved:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  }
  .summary-card-improved .card-body { padding: 1rem; }
  .summary-card-improved .card-title { font-size: 0.8rem; font-weight: 600; color: #6c757d; margin-bottom: 0.3rem; text-transform: uppercase; }
  .summary-card-improved .card-value { font-size: 1.4rem; font-weight: 700; color: #343a40; }
  .summary-card-improved .card-icon { font-size: 1.8rem; opacity: 0.6; margin-top: 0.5rem; }
  /* 특정 카드별 상단 보더 색상 */
  .summary-card-improved.border-top-primary { border-top-color: var(--bs-primary); }
  .summary-card-improved.border-top-info { border-top-color: var(--bs-info); }
  .summary-card-improved.border-top-secondary { border-top-color: var(--bs-secondary); }
  .summary-card-improved.border-top-success { border-top-color: var(--bs-success); }
  .summary-card-improved.border-top-warning { border-top-color: var(--bs-warning); }
  .summary-card-improved.border-top-danger { border-top-color: var(--bs-danger); }

  /* 테이블 액션 바 */
  .table-action-bar {
    background-color: #f8f9fa;
    padding: 0.75rem 1rem;
    border: 1px solid #dee2e6;
    border-bottom: none;
    border-radius: 0.5rem 0.5rem 0 0;
  }

  /* 테이블 컨테이너 */
  .salaryListContainer {
    border: 1px solid #dee2e6;
    border-radius: 0 0 0.5rem 0.5rem;
    padding: 1rem;
    background-color: #fff;
  }

  /*   틀고정  */
  /* 고정 열 스타일 */
  #salaryTable thead th {
    background: #fff;
    z-index: 2;
  }

  #salaryTable th.sticky-col {
    position: sticky;
    left: 0;
    background: #fff;
    z-index: 3;
  }

  #salaryTable th.sticky-col-2 {
    position: sticky;
    left: 100px; /* 1번째 열 너비 */
    background: #fff;
    z-index: 3;
  }

  #salaryTable th.sticky-col-3 {
    position: sticky;
    left: 200px; /* 1+2열 너비 */
    background: #fff;
    z-index: 3;
  }

  /* tbody에서도 동일하게 고정 */
  #salaryTable td.sticky-col {
    position: sticky;
    left: 0;
    background: #f8f9fa;
    z-index: 1;
  }

  #salaryTable td.sticky-col-2 {
    position: sticky;
    left: 100px;
    background: #f8f9fa;
    z-index: 1;
  }

  #salaryTable td.sticky-col-3 {
    position: sticky;
    left: 200px;
    background: #f8f9fa;
    z-index: 1;
  }


  /* 업무 흐름 시각화 (Stepper) */
  .stepper-wrapper { display: flex; justify-content: space-between; margin-bottom: 1.5rem; }
  .step-item { display: flex; flex-direction: column; align-items: center; flex: 1; position: relative; }
  .step-counter { width: 40px; height: 40px; border-radius: 50%; background: #e9ecef; color: #6c757d; display: flex; justify-content: center; align-items: center; font-weight: bold; margin-bottom: 0.5rem; z-index: 2; }
  .step-name { font-size: 0.85rem; font-weight: 600; color: #6c757d; text-align: center; }
  .step-item:not(:last-child)::after { /* 연결선 */ content: ''; position: absolute; top: 20px; left: 50%; width: 100%; height: 2px; background-color: #e9ecef; z-index: 1; transform: translateX(calc(-50% + 20px)); }
  .step-item.active .step-counter { background-color: var(--bs-primary); color: white; }
  .step-item.active .step-name { color: var(--bs-primary); }
  .step-item.completed .step-counter { background-color: var(--bs-success); color: white; }
  .step-item.completed .step-name { color: var(--bs-success); }
  .step-item.completed:not(:last-child)::after { background-color: var(--bs-success); }

  /* 아코디언 버튼 크기 조정 */
  .accordion-button.small { font-size: 0.9rem; padding: 0.75rem 1rem; }
  .accordion-body.small { font-size: 0.9rem; }

  /* DataTables 검색창 등 위치 조정 */
  .dataTables_wrapper .dataTables_filter { float: right; margin-bottom: 0.5rem; }
  .dataTables_wrapper .dataTables_length { float: left; }
  .dataTables_wrapper .dt-buttons { float: right; margin-left: 1rem; }

</style>


    <div class="d-flex justify-content-between align-items-center mb-3">
        <button onclick="history.back()" class="btn btn-outline-secondary btn-sm">
            <i class="bi bi-arrow-left"></i> 뒤로가기
        </button>
            
            <div class="quick-menu p-2 rounded-3 shadow-sm" style="background-color: #f8f9fa;">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb mb-0 small">
                    <li class="breadcrumb-item"><i class="bi bi-link-45deg"></i> Quick Menu</li>
                    <li class="breadcrumb-item active" aria-current="page"><span>📄 사원급여관리</span></li>
<!--                     <li class="breadcrumb-item"><a href="/salary/transfer/list">💰 급여이체현황</a></li> -->
                    <li class="breadcrumb-item"><a href="/Allowance/list">📗 수당/공제관리</a></li>
                    <li class="breadcrumb-item"><a href="#salaryRegisterSection">🗂 급여등록</a></li>
                    <li class="breadcrumb-item"><a href="#salaryStandardsSection">📋 급여 기준</a></li>
                </ol>
            </nav>
        </div>
    </div>
            
<div class="card section-card shadow-sm">
        <div class="card-body d-flex justify-content-between align-items-center p-3">
             <h5 class="mb-0 text-primary fw-bold"><i class="bi bi-clipboard-data me-2"></i>급여 현황 조회</h5>
             <div class="d-flex align-items-center gap-3">
                <div class="input-group input-group-sm" style="width: auto;">
                    <input type="month" id="payDate" class="form-control" style="width:auto;" />
                    <button type="button" class="btn btn-primary" onclick="updateChartList()">📅 조회</button>
                    </button>
                </div>
<!--                 <button type="button" class="btn btn-outline-danger btn-sm" id="sendAllPaystubsBtn">📧 급여명세서 일괄 발송</button> -->
             </div>
        </div>
    </div>

    <div class="row g-3 mb-4" id="summarySection">
      <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-primary h-100">
          <div class="card-body text-center">
            <div class="card-title">귀속년월</div>
            <div class="card-value" id="selectedDate">-</div>
            <div class="card-icon text-primary"><i class="bi bi-calendar-month"></i></div>
          </div>
        </div>
      </div>
      <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-info h-100">
          <div class="card-body text-center">
            <div class="card-title">급여 대상자(총)</div>
            <div class="card-value" id="totalTargetCount"></div>
            <div class="card-icon text-info"><i class="bi bi-people-fill"></i></div>
          </div>
        </div>
      </div>
      <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-secondary h-100">
          <div class="card-body text-center">
            <div class="card-title">급여 등록</div>
            <div class="card-value" id="totalSalaryCount">-</div>
            <div class="card-icon text-secondary"><i class="bi bi-pencil-square"></i></div>
          </div>
        </div>
      </div>
      <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-secondary h-100">
          <div class="card-body text-center">
            <div class="card-title">급여 확정</div>
            <div class="card-value" id="confirmCount"></div>
            <div class="card-icon text-secondary"><i class="bi bi-check2-square"></i></div>
            </div>
        </div>
      </div>
       <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-secondary h-100">
          <div class="card-body text-center">
            <div class="card-title">승인 요청</div>
            <div class="card-value" id="reqPayCount"></div>
             <div class="card-icon text-secondary"><i class="bi bi-envelope-check"></i></div>
             </div>
        </div>
      </div>
      <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-success h-100">
          <div class="card-body text-center">
            <div class="card-title">지급 완료</div>
            <div class="card-value" id="paidCount"></div>
            <div class="card-icon text-success"><i class="bi bi-check-circle-fill"></i></div>
          </div>
        </div>
      </div>
      <div class="col-lg col-md-4 col-sm-6">
        <div class="card summary-card-improved border-top-warning h-100">
          <div class="card-body text-center">
            <div class="card-title">총 실지급액</div>
            <div class="card-value text-won" id="totalNetSalary">-</div>
            <div class="card-icon text-warning"><i class="bi bi-currency-won"></i></div>
          </div>
        </div>
      </div>
    </div>

    <div class="chart-container">
       <div class="chart-box">
          <h5 class="fw-bold mb-3"><i class="bi bi-pie-chart-fill me-2 text-info"></i>부서별 평균 급여</h5>
          <canvas id="deptChart"></canvas>
       </div>
       <div class="chart-box">
          <h5 class="fw-bold mb-3"><i class="bi bi-bar-chart-line-fill me-2 text-success"></i>직급별 급여 분포</h5>
          <canvas id="rankChart"></canvas>
       </div>
    </div>

    <div class="card shadow-sm mb-4">
        <div class="card-body">
            <h5 class="card-title fw-bold mb-3"><i class="bi bi-signpost-split me-2"></i>급여 처리 업무 흐름</h5>
            <div class="stepper-wrapper">
                <div class="step-item completed"> <div class="step-counter">1</div>
                    <div class="step-name">급여 등록</div>
                </div>
                <div class="step-item active">
                    <div class="step-counter">2</div>
                    <div class="step-name">급여 확정</div>
                </div>
                <div class="step-item">
                    <div class="step-counter">3</div>
                    <div class="step-name">승인 요청</div>
                </div>
                <div class="step-item">
                    <div class="step-counter">4</div>
                    <div class="step-name">지급 완료</div>
                </div>
            </div>
            <small class="text-muted d-block text-end">※ 각 단계는 관리자 승인 및 일정에 따라 진행됩니다.</small>
        </div>
    </div>


    <div class="mb-4">
        <div class="table-action-bar d-flex justify-content-between align-items-center">
             <h5 class="mb-0 fw-bold"><i class="bi bi-list-ul me-2"></i>임직원 급여 리스트</h5>
             <div>
                 <button type="button" id="allFinal" class="btn btn-outline-primary btn-sm me-1" onclick="openFinalBulkModal(this)">
                     <i class="bi bi-check2-square me-1"></i> 일괄 확정
                 </button>
<!--                   <button type="button" class="btn btn-outline-danger btn-sm" id="sendAllPaystubsBtn">📧 급여명세서 일괄 발송</button> -->
                  
                 <button type="button" id="allRequest" class="btn btn-outline-success btn-sm" onclick="openRequestBulkModal(this)">
                     <i class="bi bi-envelope-check me-1"></i> 일괄 승인 요청
                 </button>
                 
                 <button type="button" id="sendAllPaystubsBtn" class="btn btn-outline-danger btn-sm">
                     <i class="bi bi-envelope-check me-1"></i> 급여명세서 일괄 발송 </button>
<!--                   <div id="dataTableButtons" class="d-inline-block ms-2"></div> -->
             </div>
        </div>
        <div class="salaryListContainer">
            <div class="table-responsive">
                <table id="salaryTable" class="table table-bordered table-striped display nowrap w-100">
                    <thead>
				      <tr>
				        <th class="sticky-col">귀속연월</th>       <!-- 1 -->
				        <th class="sticky-col-2">사원번호</th>       <!-- 2 -->
				        <th class="sticky-col-3">사원명</th>         <!-- 3 -->
				        <th>부서</th>           <!-- 4 -->
				        <th>팀</th>             <!-- 5 -->
				        <th>직급</th>           <!-- 6 -->
				        <th>기본급</th>         <!-- 7 -->
				        <th>총수당액</th>       <!-- 8 -->
				        <th>지급총액</th>       <!-- 9 -->
				        <th>공제총액</th>       <!-- 10 -->
				        <th>실지급액</th>       <!-- 11 -->
				        <th>급여명세</th>       <!-- 12 -->
				        <th>급여확정①</th>     <!-- 13 -->
				        <th>승인요청②</th>     <!-- 14 -->
				        <th>지급상태③</th>     <!-- 15 -->
				      </tr>
				    </thead>
				    
                    <tbody>
<!--                     	summary.js -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <!--    Toast 영역  -->
 <div class="toast-container position-fixed bottom-0 end-0 p-3" id="toastArea"></div> 

    

    <div class="card shadow-sm mb-5" id="salaryRegisterCard">
        <div class="card-header bg-light">
             <h5 class="mb-0 fw-bold"><i class="bi bi-folder-plus me-2"></i> 신규 급여 등록</h5>
        </div>
        <div class="card-body">
             <form id="salaryForm" class="row g-3 align-items-end justify-content-center">
                <div class="col-md-4">
                  <label for="payYearInput" class="form-label small">등록연도</label>
                  <input type="number" name="payYear" value="<%= currentYear  %>" class="form-control form-control-sm" required>
                </div>
                <div class="col-md-4">
                  <label for="payMonthInput" class="form-label small">등록월</label>
                   <input type="number" name="payMonth" value="<%= currentMonth %>" class="form-control form-control-sm" required>
                </div>
                <div class="col-md-4 d-flex align-items-end">
                  <button type="button" id="saveBtn" class="btn btn-primary w-100 btn-sm" aria-labelledby="exSalaryModalLabel">
                      <i class="bi bi-calculator me-1"></i> 예상금액 확인 및 등록
                  </button>
                </div>
             </form>
             <div class="accordion mt-4" id="calculationGuideAccordion">
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingCalcGuide">
                  <button class="accordion-button collapsed small" type="button" data-bs-toggle="collapse" data-bs-target="#collapseCalcGuide" aria-expanded="false" aria-controls="collapseCalcGuide">
                    <i class="bi bi-info-circle me-2"></i> 급여산정기준 (펼쳐보기)
                  </button>
                </h2>
                <div id="collapseCalcGuide" class="accordion-collapse collapse" aria-labelledby="headingCalcGuide" data-bs-parent="#calculationGuideAccordion">
                  <div class="accordion-body small">
                    <ul class="mb-2 ps-3">
                        <li>급여는 <strong>기본급, 회사 내규 수당, 법정 공제 항목</strong>을 기준으로 자동 계산됩니다.</li>
                        <li>계산 순서: <strong>기본급 → 수당 반영 → 법정공제 적용 → 실지급액 산출</strong></li>
                    </ul>
                    <h6 class="fw-bold mt-3">📘 항목 설명</h6>
                    <ul class="ps-3">
                        <li><strong>총 지급액</strong>: 기본급 + 총 수당액</li>
                        <li><strong>총 공제액</strong>: 소득세, 4대보험 등 법정 공제액 합계</li>
                        <li><strong>실지급액</strong>: 총 지급액 - 총 공제액</li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
        </div>
    </div>


    <div class="row">
        <div class="col-lg-6 mb-4">
            <div class="accordion shadow-sm rounded-3 h-100" id="salaryStandardsAccordion">
              <div class="accordion-item">
                <h2 class="accordion-header" id="headingSalaryStandards">
                  <button class="accordion-button collapsed fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSalaryStandards" aria-expanded="false" aria-controls="collapseSalaryStandards">
                    <i class="bi bi-journal-bookmark-fill me-2"></i> 급여기준표(연봉/수당)
                  </button>
                </h2>
                <div id="collapseSalaryStandards" class="accordion-collapse collapse" aria-labelledby="headingSalaryStandards" data-bs-parent="#salaryStandardsAccordion">
                  <div class="accordion-body p-3">
                     <h6 class="mb-2 fw-bold"><i class="bi bi-building me-1"></i> 부서별 직급 연봉 기준표 <small class="text-muted">(단위: 만 원)</small></h6>
                     <div class="table-responsive mb-3">
                       <table id="salaryStdTable1" class="table table-bordered table-hover text-center align-middle small">
                         <thead class="table-light"> <tr> <th rowspan="2">부서</th> <th colspan="10">직급</th> </tr> <tr> <th>사원</th><th>대리</th><th>과장</th><th>차장</th><th>부장</th> <th>이사</th><th>상무</th><th>전무</th><th>부사장</th><th>사장</th> </tr> </thead>
                         <tbody>
                           <tr> <th>경영지원</th> <td>3,200</td><td>3,600</td><td>4,200</td><td>5,200</td><td>6,400</td> <td>7,600</td><td>9,000</td><td>10,000</td><td>13,000</td><td>16,000</td> </tr>
                           <tr> <th>생산</th> <td>3,400</td><td>3,800</td><td>4,500</td><td>5,400</td><td>6,600</td> <td>7,800</td><td>9,200</td><td>10,200</td><td>13,000</td><td>16,000</td> </tr>
                           <tr> <th>마케팅</th> <td>3,600</td><td>4,000</td><td>4,700</td><td>5,700</td><td>6,800</td> <td>8,000</td><td>9,400</td><td>10,400</td><td>13,000</td><td>16,000</td> </tr>
                           <tr> <th>연구개발</th> <td>3,800</td><td>4,200</td><td>5,000</td><td>6,000</td><td>7,000</td> <td>8,200</td><td>9,600</td><td>10,600</td><td>13,000</td><td>16,000</td> </tr>
                         </tbody>
                       </table>
                     </div>
                     <h6 class="mt-3 mb-2 fw-bold"><i class="bi bi-plus-circle-dotted me-1"></i> 월별 수당 기준</h6>
                     <div class="table-responsive">
                       <table id="salaryStdTable2" class="table table-bordered table-hover text-center align-middle small">
                         <thead class="table-light"> <tr> <th>직급</th><th>교통비 (원)</th><th>식대 (원)</th><th>직책수당 (원)</th> </tr> </thead>
                         <tbody>
                           <tr><td>사원/대리</td><td>100,000</td><td>100,000</td><td>-</td></tr>
                           <tr><td>과장</td><td>150,000</td><td>150,000</td><td>-</td></tr>
                           <tr><td>차장</td><td>180,000</td><td>180,000</td><td>-</td></tr>
                           <tr><td>부장 이상</td><td>200,000</td><td>200,000</td><td>-</td></tr>
                           <tr><td>팀장</td><td>-</td><td>-</td><td>100,000</td></tr>
                           <tr><td>본부장</td><td>-</td><td>-</td><td>200,000</td></tr>
                         </tbody>
                       </table>
                     </div>
                  </div>
                </div>
              </div>
            </div>
        </div>

        <div class="col-lg-6 mb-4">
             <div class="card shadow-sm h-100" id="salaryHistoryCard">
                <div class="card-header bg-light">
                    <h5 class="mb-0 fw-bold"><i class="bi bi-clock-history me-2"></i> 과거 급여 처리 요약</h5>
                </div>
                <div class="card-body">
                     <div class="table-responsive">
                        <table class="table table-striped table-hover align-middle text-center small">
                            <thead class="table-light">
                                <tr>
                                    <th>귀속연월</th>
                                    <th>임직원수</th>
                                    <th class="text-end">실지급액 합계</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${salarySummary }" var="salaryVOs">
                                    <tr>
                                        <td>${salaryVOs.payYear }년 ${salaryVOs.payMonth }월</td>
                                        <td>${salaryVOs.totalemp }</td>
                                        <td class="text-end fw-bold">
                                            <fmt:formatNumber value="${salaryVOs.totalNetSalary }" pattern="#,###" /> 원
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty salarySummary}">
                                    <tr> <td colspan="3" class="text-center text-muted py-3">데이터가 없습니다.</td> </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                    <div class="text-end mt-2">
                        <a href="#" class="btn btn-outline-secondary btn-sm">전체 내역 보기 <i class="bi bi-arrow-right-short"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="height: 50px;"></div>
 
 
<!-- 💼 급여 등록 미리보기 모달 -->
<div class="modal fade" id="sampleSalaryModal" tabindex="-1" aria-labelledby="sampleSalaryModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content shadow rounded-4 border-0">

      <!-- 모달 헤더 -->
      <div class="modal-header border-0 bg-white pt-4 pb-2">
        <h5 class="modal-title w-100 text-center fw-bold" id="sampleSalaryModalLabel">
          💼 급여 등록 예상금액 미리보기
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
      </div>

      <!-- 모달 바디 -->
      <div class="modal-body px-4">
        <div class="card shadow-sm border-0 rounded-4">
          <div class="card-body p-4">
            <table class="table table-borderless align-middle text-center mb-0">
              <thead class="text-muted small text-uppercase border-bottom">
                <tr>
                  <th>📌 대상 인원</th>
                  <th>💰 총 급여</th>
                  <th>➕ 총 수당</th>
                  <th>➖ 총 공제</th>
                  <th>✅ 예상실 지급액</th>
                </tr>
              </thead>
              <tbody class="fs-6">
                <tr>
                  <td><span class="fw-semibold">${salaryEx.totalemp}</span> 명</td>
                  <td><span class="text-dark fw-semibold">
                    <fmt:formatNumber value="${salaryEx.totalPaySum}" pattern="#,##0" /> 원
                  </span></td>
                  <td><span class="text-success fw-semibold">
                    <fmt:formatNumber value="${salaryEx.totalAllowanceSum}" pattern="#,##0" /> 원
                  </span></td>
                  <td><span class="text-danger fw-semibold">
                    <fmt:formatNumber value="${salaryEx.totalDeductionSum}" pattern="#,##0" /> 원
                  </span></td>
                  <td><span class="text-primary fw-bold fs-5">
                    <fmt:formatNumber value="${salaryEx.totalNetSalary}" pattern="#,##0" /> 원
                  </span></td>
                </tr>
              </tbody>
            </table>
            <p class="text-muted text-end small mt-3 mb-0">※ 위 내용을 확인 후 등록을 진행해주세요.</p>
          </div>
        </div>
      </div>

      <!-- 모달 푸터 -->
      <div class="modal-footer border-0 bg-white pb-4">
        <button type="button" class="btn btn-success px-4 fw-semibold" onclick="registerSalary(this)">
          <i class="bi bi-check-circle"></i> 급여 등록
        </button>
        <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">
          닫기
        </button>
      </div>
    </div>
  </div>
</div>





<!-- 모달 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content shadow-lg">
            <div class="modal-header bg-success text-white">
                <h1 class="modal-title fs-5 text-center w-100" id="exampleModalLabel"></h1>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body bg-light-subtle">
                <div id="modal-content-container"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-bs-dismiss="modal">저장</button>
                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>



<!-- 급여 확정/승인 확인 모달 -->
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content shadow-lg">
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="confirmModalLabel">급여 확정여부 확인</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p>📌 다음 급여를 확정/취소합니다.</p>
        <ul class="list-group">
          <li class="list-group-item">확정 임직원 수: <span id="modalEmpCount"></span>명</li>
          <li class="list-group-item">총 실지급액 합계: <span id="modalTotalAmount"></span>원</li>
        </ul>
      </div>
      <div class="modal-footer">
         <div class="modal-footer">
           <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
           <button type="button" class="btn btn-primary" id="confirmFinal">확정 진행</button>
         </div>
      </div>
    </div>
  </div>
</div>


<!--  지급 승인 모달 -->
<div class="modal fade" id="requestModal" tabindex="-1" aria-labelledby="requestModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="requestModalLabel">지급 승인</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
      <p>📌 급여지급을 승인/취소합니다.</p>
       <ul class="list-group"> 
          <li class="list-group-item">지급승인 임직원 수: <span id="requestEmpCount"></span>명</li>
          <li class="list-group-item">총 실지급액 합계: <span id="requestTotalAmount"></span>원</li>
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button class="btn btn-success" id="confirmRequest">지급 승인</button>
      </div>
    </div>
  </div>
</div> 
 
    

<!-- 일괄발송용 숨겨진 iframe들 -->
<!-- 반드시 렌더링 되게 hidden 대신 화면 밖 위치 지정 -->
<iframe id="previewIframe1" style="position:absolute; left:-9999px; width:1200px; height:2000px;"></iframe>
<iframe id="previewIframe2" style="position:absolute; left:-9999px; width:1200px; height:2000px;"></iframe>
<iframe id="previewIframe3" style="position:absolute; left:-9999px; width:1200px; height:2000px;"></iframe>

<script src="${pageContext.request.contextPath}/resources/js/salary/salaryFinalStatus.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/salary/salaryRequestStatus.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/js/salary/salaryList.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/salary/salaryRegister.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/salary/salarySummary.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/salary/salarySendPaymentMail.js"></script>

<!-- 타 팀원들 로딩 저하로 개인스크립트로 사용, 이메일발송 pdf -->
 <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script> 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script> 
 <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
 

<!-- jQuery, DataTables JS (CDN 예시) -->
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>

   
    

