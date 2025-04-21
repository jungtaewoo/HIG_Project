<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
thead th {
    text-align: center !important;
}
.modal-title {
    color: white !important;
    flex-grow: 1; /* 제목이 가운데 정렬될 수 있도록 설정 */
    text-align: center;
}
.modal-header .btn-close {
    filter: invert(1); /* 아이콘 색 반전 (검정 → 흰색) */
    opacity: 1; /* 기본 부트스트랩 스타일보다 더 뚜렷하게 표시 */
}
.modal-body .list-group-item {
    border: none;  /* 테두리 제거 */
    padding: 10px 15px;
    background-color: #f9f9f9; /* 연한 배경색 */
    border-radius: 5px;
    margin-bottom: 5px;
}
.modal-body strong {
    display: inline-block;
    width: 120px; /* 라벨 너비 통일 */
    font-weight: bold;
    color: #333;
}
.radio-label {
        margin-right: 20px; /* 라디오 버튼 간격 조정 */
}
.modal.fade .modal-dialog {
    transition: transform 0.3s ease-out;
    transform: translateY(-10px);
}

.modal.show .modal-dialog {
    transform: translateY(0);
}
.status-container {
    display: flex;
    align-items: center;
    gap: 10px; /* 요소 간격 */
    margin-bottom: 25px;
}
.card {
    margin-bottom: 5px;
}
/* 1차평가 css */
.status-container {
    display: block;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 12px;
    margin-top: 20px;
    margin-bottom: 25px;
    border: 1px solid #e0e0e0;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); /* 그림자 추가 */
}
.status-container h6 {
    margin-bottom: 15px;
    font-weight: bold;
    font-size: 16px;
    color: #0d6efd;
}
.status-container .radio-label {
    display: inline-flex;
    align-items: center;
    margin-right: 20px;
    font-weight: normal;
    color: #555;
}
.dataTable-top {
 	display: none; 
}
.dataTable-bottom {
 	display: none; 
}
.dataTable-dropdown {
 	display: none !important; 
}
.summary-stats {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    margin-bottom: 1rem;
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
    font-size: 0.9rem;
    color: #6c757d;
    margin-bottom: 0.5rem;
}
.stat-card .stat-value .badge {
    font-size: 1.2rem;
    font-weight: bold;
}
#table1.table.table-striped.text-center tbody tr td,
#table1.table.table-striped.text-center tbody tr td * {
  font-size: 15px !important;
}





/* applicationModal 전용 스타일 - 개별 모달에만 적용 */
[id^="applicationModal-"] .modal-title {
    color: white !important;
    font-weight: bold;
    text-align: center;
    flex: 1;
}

[id^="applicationModal-"] .modal-header {
    background-color: var(--bs-primary);
    border-bottom: none;
    color: white;
}

[id^="applicationModal-"] .btn-close {
    filter: invert(1);
    opacity: 1;
}

[id^="applicationModal-"] .modal-body {
    padding: 1.5rem 2rem;
    background-color: #f8f9fa;
}

[id^="applicationModal-"] .card {
    background-color: #fff;
    border: 1px solid #e0e0e0;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
}

[id^="applicationModal-"] .list-group-item {
    border: none;
    border-bottom: 1px solid #eee;
    padding: 12px 10px;
    background-color: transparent;
    font-size: 15px;
}

[id^="applicationModal-"] .list-group-item:last-child {
    border-bottom: none;
}

[id^="applicationModal-"] .list-group-item strong {
    width: 100px;
    display: inline-block;
    color: #555;
}

[id^="applicationModal-"] .status-container {
    margin: 1rem 0 1.5rem;
    padding: 1rem;
    border-radius: 8px;
    background: #fff;
    border: 1px solid #ccc;
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05);
}

[id^="applicationModal-"] .status-container h6 {
    font-size: 16px;
    font-weight: bold;
    color: #0d6efd;
    margin-bottom: 1rem;
}

[id^="applicationModal-"] .radio-label input[type="radio"] {
    margin-right: 5px;
}

[id^="applicationModal-"] .btn {
    min-width: 100px;
}

[id^="applicationModal-"] .modal-footer {
    border-top: none;
}

</style>

<!-- 뒤로가기, Breadcrumb 통일 -->
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
        <li class="breadcrumb-item">
          <a href="${pageContext.request.contextPath }/recruit/board/list">채용공고 관리</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">지원서 관리</li>
        <li class="breadcrumb-item">
          <a href="${pageContext.request.contextPath }/recruit/interview/list">면접자 관리</a>
        </li>
      </ol>
    </nav>
  </div>
</div>

<section class="section">
	<div class="card">
		<div class="card-header">
			<h3>지원서 관리</h3><hr>
			<!-- 기존 상단 공고정보  -->
			<%-- 
			<div class="container">
			<h5 class="mb-4" style="font-weight: bold; font-size: 1.3rem;">${recruit.recruitTitle}</h5>
				<div class="row">
					<!-- 왼쪽 열 -->
					<div class="col-md-6">
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">공고명</div>
							<div class="fw-bold">${recruit.recruitTitle}
								<span class="text-muted">(${recruit.recruitHiretype})</span>
							</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">급여</div>
							<div>월 ${recruit.recruitSalary}만원</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">업무내용</div>
							<div>${recruit.recruitWorkdetail}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">모집기간</div>
							<div>
								<fmt:formatDate value="${recruit.recruitStartdate}" pattern="yyyy-MM-dd" />
								~
								<fmt:formatDate value="${recruit.recruitEnddate}" pattern="yyyy-MM-dd" />
							</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">모집인원</div>
							<div>${recruit.recruitHirenum}명</div>
						</div>
					</div>
					<!-- 오른쪽 열 (마진 왼쪽에 줌) -->
					<div class="col-md-6 ps-md-5">
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">근무지</div>
							<div>${recruit.recruitWorkplace}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">모집부서</div>
							<div>${recruit.recruitPosition}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">우대사항</div>
							<div>${recruit.recruitPq}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">문의처</div>
							<div>${recruit.recruitContact}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">누적지원서</div>
							<div class="fw-bold text-dark">${recruit.applicantCount}</div>
						</div>
					</div>
				</div>
				<c:set var="recruit" value="${applicationList[0].recruitment}" />
				모집인원${recruit.recruitHirenum}명, 
				누적지원서${recruit.applicantCount}건, 
				평가대기${recruit.waitingCount}건, 
				합격자수${recruit.passCount}명
			</div>
			<hr class="my-3">
			 --%>
			 
			<div class="row">
				<div class="col-12">
					<div class="text-dark p-2" style="font-weight: bold;">
						⚠️ 입사지원서 열람 → 1차 평가 (평가 저장 시 자동 메일 전송)</div>
					<div class="text-dark p-2" style="font-weight: bold;">⚠️
						면접예정 버튼 → 면접자관리 페이지 </div>
				</div>
			</div>
			<!-- 모집인원/누적지원서/평가대기/합격자수 가져오기  -->
			<c:set var="recruit" value="${applicationList[0].recruitment}" />
			<!-- 면접예정 수 카운트 -->
			<c:set var="interviewCount" value="0" />
			<c:forEach var="application" items="${applicationList}">
			    <c:if test="${application.applicationStatus.currentStatus == '면접예정'}">
			        <c:set var="interviewCount" value="${interviewCount + 1}" />
			    </c:if>
			</c:forEach>
			<!-- 상태 요약 패널 -->
            <div class="summary-stats">
                <div class="stat-card">
                    <div class="stat-title">누적</div>
                    <div class="stat-value">
                        <span class="badge bg-info" data-status="누적">${recruit.applicantCount}건</span>
                    </div>
                </div>
                <div class="stat-card">
                    <div class="stat-title">평가필요</div>
                    <div class="stat-value">
                        <span class="badge bg-secondary" data-status="대기">${recruit.waitingCount}건</span>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-title">
	                    <a href="${pageContext.request.contextPath}/recruit/interview/list" class="text-decoration-none">
				            면접예정
				        </a>
                    </div>
                    <div class="stat-value">
	                    <a href="${pageContext.request.contextPath}/recruit/interview/list" class="text-decoration-none">
				            <span class="badge bg-warning" data-status="면접">${interviewCount}명</span>
				        </a>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-title">합격자</div>
                    <div class="stat-value">
                        <span class="badge bg-success" data-status="합격">${recruit.passCount}명</span>
                    </div>
                </div>
                <%-- <div class="stat-card">
                    <div class="stat-title">반려</div>
                    <div class="stat-value">
                        <span class="badge bg-danger" data-status="반려">${APRSTATUS}</span>
                    </div>
                </div> --%>
            </div>
		</div>

		<div class="card-body">
			<table class="table table-striped text-center" id="table1">
				<thead>
					<tr>
						<th>이름</th>
						<th>생년월일</th>
						<th>성별</th>
						<th>이메일</th>
						<th>입사지원서</th>
						<th>평가상태</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty applicationList }">
							<c:forEach items="${applicationList }" var="application">
								<tr>
									<td>
									<%-- ${application.appId },  --%>
									${application.appName }</td>
									<td>
										${fn:substring(application.appYeardate, 0, 4)}-${fn:substring(application.appYeardate, 4, 6)}-${fn:substring(application.appYeardate, 6, 8)}
									</td>
									<td>${application.appGender }</td>
									<td>${application.appEmail }</td>
									<td>
										<%-- <a href="${pageContext.request.contextPath }/recruit/applicant/detail?appId=${application.appId}">
										    <button class="btn btn-primary">페이지</button>
										</a> --%>
										<button class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#applicationModal-${application.appId}">
											열람
										</button>
									</td>
									<td>
										<c:choose>
											<c:when test="${application.applicationStatus.currentStatus == '면접예정' }">
												<button class="btn btn-warning btn-sm" style="width:90px;"
												onclick="window.location.href='${pageContext.request.contextPath}/recruit/interview/list';"
												title="클릭 시 면접자 조회 페이지로 이동">
													면접예정
												</button>
											</c:when>
											<c:when test="${application.applicationStatus.currentStatus == '합격' }">
												<span style="color: green; font-weight: bold;">합격</span>
											</c:when>
											<c:when test="${application.applicationStatus.currentStatus == '서류탈락' }">
												<span style="color: red; font-weight: bold;">서류탈락</span>
											</c:when>
											<c:when test="${application.applicationStatus.currentStatus == '불합격' }">
												<span style="color: red; font-weight: bold;">불합격</span>
											</c:when>
											<c:otherwise>
												<!-- 그 외 상태일때는 일반텍스트로 표시 -->
												<span style="font-weight: bold;">${application.applicationStatus.currentStatus}</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" class="text-center">지원서가 존재하지 않습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<!-- 공고정보 하단으로 이동 -->
		<div class="card-footer">
			<h5 class="mb-4" style="font-weight: bold; font-size: 1.3rem;">${recruit.recruitTitle}</h5>
				<div class="row">
					<!-- 왼쪽 열 -->
					<div class="col-md-6">
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">공고명</div>
							<div class="fw-bold">${recruit.recruitTitle}
								<span class="text-muted">(${recruit.recruitHiretype})</span>
							</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">급여</div>
							<div>월 ${recruit.recruitSalary}만원</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">업무내용</div>
							<div>${recruit.recruitWorkdetail}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">모집기간</div>
							<div>
								<fmt:formatDate value="${recruit.recruitStartdate}" pattern="yyyy-MM-dd" />
								~
								<fmt:formatDate value="${recruit.recruitEnddate}" pattern="yyyy-MM-dd" />
							</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">모집인원</div>
							<div>${recruit.recruitHirenum}명</div>
						</div>
					</div>
					<!-- 오른쪽 열 (마진 왼쪽에 줌) -->
					<div class="col-md-6 ps-md-5">
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">근무지</div>
							<div>${recruit.recruitWorkplace}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">모집부서</div>
							<div>${recruit.recruitPosition}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">우대사항</div>
							<div>${recruit.recruitPq}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">문의처</div>
							<div>${recruit.recruitContact}</div>
						</div>
						<div class="d-flex mb-2">
							<div class="text-secondary fw-semibold" style="width: 100px;">누적지원서</div>
							<div class="fw-bold text-dark">${recruit.applicantCount}</div>
						</div>
					</div>
				</div>
				<%-- 
				<c:set var="recruit" value="${applicationList[0].recruitment}" />
				모집인원${recruit.recruitHirenum}명, 
				누적지원서${recruit.applicantCount}건, 
				평가대기${recruit.waitingCount}건, 
				합격자수${recruit.passCount}명
				 --%>
			</div>
		
		
		
	</div>
<%-- ${applicationList } --%>
</section>
<!-- 지원서 상세 모달창 -->
<c:forEach items="${applicationList}" var="application">
    <div class="modal fade" id="applicationModal-${application.appId}" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <h5 class="modal-title">지원서 상세 정보</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                <div class="container">
                <div class="card">
                <div class="card-body">
                    <ul class="list-group">
					    <li class="list-group-item"><strong>이름</strong> ${application.appName}</li>
					    <li class="list-group-item"><strong>성별</strong> ${application.appGender}</li>
					    <li class="list-group-item"><strong>생년월일</strong> 
					        ${fn:substring(application.appYeardate, 0, 4)}-${fn:substring(application.appYeardate, 4, 6)}-${fn:substring(application.appYeardate, 6, 8)}
					    </li>
					    <li class="list-group-item" data-email="${application.appEmail}"><strong>이메일</strong> ${application.appEmail}</li>
					    <li class="list-group-item"><strong>학력</strong> ${application.appGrade}</li>
					    <li class="list-group-item"><strong>경력</strong> 
						    <span style="white-space: pre-line; display: block;">${application.appCareer}</span>
						</li>
					    <li class="list-group-item"><strong>자기소개서</strong> 
						    <span style="white-space: pre-line; display: block;">${application.appPl}</span>
						</li>
						<li class="list-group-item">
							<strong>평가상태</strong><br>
						    <c:choose>
						        <c:when test="${application.applicationStatus.currentStatus eq '면접예정'}">
						            <span style="color: orange; font-weight: bold;">
						                ${application.applicationStatus.currentStatus}
						            </span>
						            <br>면접일 : ${fn:substringBefore(application.applicationStatus.interviewDate, ' ')}
						        </c:when>
						        <c:when test="${application.applicationStatus.currentStatus eq '합격'}">
						            <span style="color: green; font-weight: bold;">
						                ${application.applicationStatus.currentStatus}
						            </span>
						        </c:when>
						        <c:when test="${application.applicationStatus.currentStatus eq '서류탈락' or application.applicationStatus.currentStatus eq '불합격'}">
						            <span style="color: red; font-weight: bold;">
						                ${application.applicationStatus.currentStatus}
						            </span>
						        </c:when>
						        <c:otherwise>
						            <span style="font-weight: bold;">
						                ${application.applicationStatus.currentStatus}
						            </span>
						        </c:otherwise>
						    </c:choose>
						</li>
					</ul>
                </div>
                </div>
                </div>
               	<!-- 상태 변경 폼 -->
				<form id="statusForm-${application.appId}" action="${pageContext.request.contextPath }/recruit/applicant/update" method="post">
					<input type="hidden" name="statusId" value="${application.applicationStatus.statusId}"> 
					<input type="hidden" name="appId" value="${application.appId}">
					<!-- 라디오버튼 & 날짜선택  -->
					<div class="status-container list-group-item" 
					style="display: ${application.applicationStatus.currentStatus eq '평가필요' ? 'block' : 'none'};
							margin-left:10px; width:420px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); border-radius: 8px;">
		                <div style="font-weight: bold; margin-bottom: 10px; margin-left: 10px;">
		                	<h6>1차 평가</h6> 
		                </div>
					    <div style="display: flex; align-items: center; gap: 5px;">
						    <label class="radio-label" style="margin-left: 10px;">
						        <input type="radio" name="currentStatus-${application.appId}" value="서류탈락" id="option1-${application.appId}"
						        ${application.applicationStatus.currentStatus eq '서류탈락' ? 'checked' : ''}>
						        서류탈락
						    </label>
						    <label class="radio-label">
						        <input type="radio" name="currentStatus-${application.appId}" value="면접예정" id="option2-${application.appId}"
						        ${application.applicationStatus.currentStatus eq '면접예정' ? 'checked' : ''}>
						        면접예정
						    </label>
						    
						    <div id="datePickerContainer-${application.appId}" 
						         style="display: ${application.applicationStatus.currentStatus eq '면접예정' ? 'inline-block' : 'none'};">
						        <input type="date" class="form-control" id="interviewDate-${application.appId}" 
						               name="interviewDate" value="${application.applicationStatus.interviewDate.substring(0,10)}" 
						               style="width: 150px;">
						    </div>
						</div>
					</div><br>
					<!-- 저장 및 닫기 버튼 -->
					<div class="d-flex justify-content-center" style="gap: 10px">
						<button type="button" class="btn btn-primary save-btn" data-app-id="${application.appId}"
						${application.applicationStatus.currentStatus != '평가필요' ? 'disabled' : ''}>
							저장
						</button>
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="closeRecruitModal">
							닫기
						</button>
					</div>
				</form>
				</div>
			</div>
        </div>
    </div>
</c:forEach>

<script>

document.addEventListener("DOMContentLoaded", function () {
	
    function toggleDatePicker(appId) {
        let datePickerContainer = document.getElementById("datePickerContainer-" + appId);
        let isInterviewSelected = document.getElementById("option2-" + appId).checked;

        datePickerContainer.style.display = isInterviewSelected ? "inline-block" : "none";
    }

    // 상태 변경 시 날짜 필드 표시
    document.querySelectorAll("input[type='radio']").forEach(radio => {
        radio.addEventListener("change", function () {
            const appId = this.name.split("-")[1]; // appId 가져오기
            toggleDatePicker(appId);
        });
    });

    // 페이지 로드 시 기본 상태 설정
    document.querySelectorAll("[id^='datePickerContainer-']").forEach(container => {
        const appId = container.id.split("-")[1];
        toggleDatePicker(appId);
    });

    // 저장 버튼 클릭 시 데이터 전송 (axios)
    document.querySelectorAll(".save-btn").forEach(button => {
        button.addEventListener("click", function () {
            const appId = this.dataset.appId;  // appId를 저장 버튼에 데이터 속성으로 저장
            const form = document.getElementById("statusForm-" + appId);  // form을 id로 찾기

            // 상태 및 면접일 값 가져오기
            const currentStatusRadio = form.querySelector("input[name='currentStatus-" + appId + "']:checked");
            // 평가 선택하지 않으면 취소 Swal
            if (!currentStatusRadio) {
                Swal.fire({
                    icon: 'warning',
                    title: '평가 선택 필요',
                    text: '평가를 선택해야 합니다.'
                });
                return;
            }
            const currentStatus = currentStatusRadio.value;

            const interviewDateInput = form.querySelector("#interviewDate-" + appId);
            const interviewDateValue = interviewDateInput ? interviewDateInput.value : ""; // 날짜 필드 값 가져오기

            // 면접예정 상태일 때 날짜가 선택되었는지 Swal 확인
            if (currentStatus === "면접예정" && !interviewDateValue) {
                Swal.fire({
                    icon: 'warning',
                    title: '면접일 선택 필요',
                    text: '면접일을 선택해야 합니다.'
                });
                return; 
            }

        	// 저장 확인 Swal창 띄우기
        	const modal = document.getElementById("applicationModal-" + appId);
        	const emailElement = modal.querySelector('li[data-email]'); 
			const applicantEmail = emailElement.dataset.email;				
			const message = `저장한 평가는 수정할 수 없습니다.<br><small>(\${applicantEmail} 로 결과 메일 발송)</small>`;

			Swal.fire({
                title: '저장하시겠습니까?',
                html: message,
                icon: 'question',
                showCancelButton: true,
                confirmButtonText: '저장',
                cancelButtonText: '취소'
			}).then((result) => {
                if (result.isConfirmed) {
		            // axios로 데이터 전송
		            axios.post("/recruit/applicant/update", {
		                statusId: form.querySelector("input[name='statusId']").value,
		                appId: appId,
		                currentStatus: currentStatus,
		                interviewDate: interviewDateValue 
		            })
		            .then(response => {
		                Swal.fire({
                            icon: 'success',
                            title: '저장 완료',
                            text: response.data.message
                        }).then(() => {
		                	// 성공 후 모달 닫기 및 페이지 새로고침
			                const modal = document.getElementById("applicationModal-" + appId);
			                const modalInstance = bootstrap.Modal.getInstance(modal); 
			                modalInstance.hide();
			                location.reload();
                        });
		            })
		            .catch(error => {
		                console.error("Error updating status:", error);
		                Swal.fire({
                            icon: 'error',
                            title: '저장 실패',
                            text: '상태 업데이트 중 오류가 발생했습니다.'
                        });
		            });
		        }
		    });
		});

    });

});
</script>


