<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
모달 실패 버전
<head>
<title>인사평가 현황판</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
.dashboard-card {
	border-radius: 1rem;
	padding: 1.5rem;
	color: white;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-blue {
	background-color: #007bff;
}

.card-green {
	background-color: #28a745;
}

.card-red {
	background-color: #dc3545;
}

.card-orange {
	background-color: #fd7e14;
}

hr.border-dark {
	border: 1px solid #444;
	margin: 6px 0;
}

.table-group-header td {
	background-color: #f0f2f5;
	border-top: 2px solid #888;
	font-weight: bold;
}
</style>
</head>
<body class="p-4">
	<%-- 	<h2 class="mb-4">📊 인사평가 현황판 - ${evaluationYear} ${halfYear == '1' ? '상반기' : '하반기'}</h2> --%>
	<h2 class="mb-4">
		📊 인사평가 현황판 
		<c:choose>
			<c:when test="${empty evaluationYear}"></c:when>
			<c:otherwise> - ${evaluationYear}년 
	      		<c:choose>
					<c:when test="${halfYear == '1'}">상반기</c:when>
					<c:when test="${halfYear == '2'}">하반기</c:when>
					<c:otherwise>미정</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</h2>

	<!-- 숨김값으로 필터 파라미터 전달용 -->
	<input type="hidden" id="hiddenYear" value="${evaluationYear}" />
	<input type="hidden" id="hiddenHalf" value="${halfYear}" />

	<!-- 필터 영역 -->
	<form class="row g-3 mb-4" method="get"
		action="/evaluation/evaluationDashboard">
		<div class="col-md-2">
			<label class="form-label">평가 연도</label> <select name="evaluationYear"
				class="form-select">
				<c:forEach var="year" begin="2020" end="2025">
					<option value="${year}"
						<c:if test="${evaluationYear == year}">selected</c:if>>${year}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-2">
			<label class="form-label">반기</label> <select name="halfYear"
				class="form-select">
				<option value="1" <c:if test="${halfYear == '1'}">selected</c:if>>상반기</option>
				<option value="2" <c:if test="${halfYear == '2'}">selected</c:if>>하반기</option>
			</select>
		</div>
		<div class="col-md-2 d-flex align-items-end">
			<button class="btn btn-primary w-100">조회</button>
		</div>
	</form>

	<!-- 요약 카드 영역 -->
	<div class="row mb-4">
		<div class="col-md-3">
			<div class="dashboard-card card-blue text-center">
				<h5>전체 평가 대상자</h5>
				<h2>${summary.totalTarget}명</h2>
			</div>
		</div>
		<div class="col-md-3">
			<div class="dashboard-card card-green text-center">
				<h5>평가 완료</h5>
				<h2>${summary.completed}명</h2>
			</div>
		</div>
		<div class="col-md-3">
			<div class="dashboard-card card-red text-center">
				<h5>평가 미완료</h5>
				<h2>${summary.notCompleted}명</h2>
			</div>
		</div>
		<div class="col-md-3">
			<div class="dashboard-card card-orange text-center">
				<h5>평가 진행률</h5>
				<h2>${summary.completionRate}%</h2>
			</div>
		</div>
	</div>

	<!-- 차트 영역 -->
	<div class="card p-4 mb-4">
		<h5>현황</h5>
		<canvas id="departmentChart" height="300"></canvas>
	</div>

	<!-- 상세 테이블1 영역 -->
	<div class="card p-4">
		<h5 class="mb-3">본부별</h5>
		<table class="table table-bordered text-center">
			<thead class="table-light">
				<tr class="table-primary fw-bold">
					<th>부서</th>
					<th>대상자 수</th>
					<th>완료</th>
					<th>미완료</th>
					<th>완료율</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${departmentSummary}">
					<tr>
						<td>${row.departmentName}</td>
						<td>${row.targetCount}</td>
						<td>${row.completedCount}</td>
						<td>${row.notCompletedCount}</td>
						<td>${row.completionRate}%</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- 상세 테이블2 영역 -->
	<div class="card p-4">
		<h5 class="mb-3">팀별</h5>
		<table class="table table-bordered text-center">
			<thead class="table-light">
				<tr class="table-primary fw-bold">
					<th>본부</th>
					<th>팀</th>
					<th>대상자 수</th>
					<th>완료</th>
					<th>미완료</th>
					<th>완료율</th>
					<th>미제출자</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="currentHQ" value="" />
				<c:forEach var="row" items="${dashboardList}">
					<c:if test="${currentHQ != row.departmentName}">
						<tr class="table-group-header">
							<td colspan="7" class="fw-bold">${row.departmentName}</td>
						</tr>
						<c:set var="currentHQ" value="${row.departmentName}" />
					</c:if>
					<tr>
						<td></td>
						<td>${row.teamName}</td>
						<td>${row.targetCount}</td>
						<td>${row.completedCount}</td>
						<td>${row.notCompletedCount}</td>
						<td>${row.completionRate}%</td>
						<td>
							<button class="btn btn-sm btn-outline-danger view-unsubmitted"
								data-teamid="${row.teamId}">미제출자 보기</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- 미제출자 모달 -->
	<div class="modal fade" id="unsubmittedModal" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">미제출자 목록</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" id="unsubmittedTable">
						<thead>
							<tr>
								<th>사번</th>
								<th>이름</th>
								<th>팀</th>
								<th>상태</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- 통합 스크립트 -->
	<script>
	  document.addEventListener("DOMContentLoaded", function () {
	    // ✅ 차트
	    try {
	      const chartData = JSON.parse('<c:out value="${chartDataJson}" escapeXml="false" />');
	      const labels = chartData.map(item => item.DEPARTMENT_NAME);
	      const completeData = chartData.map(item => item.COMPLETEDCOUNT);
	      const incompleteData = chartData.map(item => item.NOTCOMPLETEDCOUNT);
	
	      const ctx = document.getElementById('departmentChart').getContext('2d');
	      new Chart(ctx, {
	        type: 'bar',
	        data: {
	          labels: labels,
	          datasets: [
	            { label: '완료', data: completeData, backgroundColor: '#28a745' },
	            { label: '미완료', data: incompleteData, backgroundColor: '#dc3545' }
	          ]
	        },
	        options: {
	          responsive: true,
	          plugins: {
	            title: { display: true, text: '진행률' },
	            legend: { position: 'top' }
	          },
	          scales: {
	            x: { stacked: true },
	            y: { stacked: true, beginAtZero: true }
	          }
	        }
	      });
	    } catch (e) {
	      console.error("차트 데이터 파싱 오류:", e);
	    }

  	  // ✅ 미제출자 모달
	    document.querySelectorAll('.view-unsubmitted').forEach(btn => {
	        btn.addEventListener('click', function () {
	          const teamId = this.dataset.teamid;
	          const year = document.getElementById("hiddenYear").value;
	          const half = document.getElementById("hiddenHalf").value;

	          console.log("👉 미제출자 요청값:", { year, half, teamId });

	          if (!year || !half || !teamId) {
	            console.warn("❌ 필수 파라미터 누락", { year, half, teamId });
	            alert("연도/반기/팀 정보가 올바르지 않습니다.");
	            return;
	          }

	          fetch(`/evaluation/unsubmittedList?evaluationYear=${year}&halfYear=${half}&teamId=${teamId}`)
	            .then(res => res.json())
	            .then(data => {
	                console.log("📦 fetch 응답 데이터:", data);

	            	
	              const tbody = document.querySelector('#unsubmittedTable tbody');
	              if (!tbody) {
	                  console.error("❌ tbody 선택 실패");
	                  return;
	                }
	              
	              tbody.innerHTML = '';
	              if (data.length === 0) {
	                tbody.innerHTML = `<tr><td colspan="4">미제출자가 없습니다.</td></tr>`;
	              } else {
	                data.forEach(emp => {
	                  const status = emp.evaluationStatus === null || emp.evaluationStatus === "N" ? "미제출" : emp.evaluationStatus;
	                  const tr = document.createElement('tr');
	                  tr.innerHTML = `
	                    <td>${emp.empId}</td>
	                    <td>${emp.name}</td>
	                    <td>${emp.teamName}</td>
	                    <td>${status}</td>
	                  `;
	                  tbody.appendChild(tr);
	                });
	              }
	              new bootstrap.Modal(document.getElementById('unsubmittedModal')).show();
	            })
	            .catch(err => {console.error("미제출자 요청 실패", err);
	            });
	        });
	      });
	    });
  </script>
</body>



ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<html>
<head>
    <title>평가 대상자 조회</title> 
    한명은 정상적으로 전송 // 두명부터는 400 오류 // 전체는 테스트 X 
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            $('#checkAll').on('click', function () {
                $('.rowCheck').prop('checked', this.checked);
            });

            $('#applyBulk').on('click', function () {
                const year = $('#bulkYear').val();
                const half = $('#bulkHalf').val();
                const isTarget = $('#bulkTarget').val();

                if (!year || !half || !isTarget) {
                    alert('모든 값을 입력해주세요.');
                    return;
                }

                $('.rowCheck:checked').each(function () {
                    const idx = $(this).val();
                    $('.evalYear').eq(idx).val(year);
                    $('.halfSelect').eq(idx).val(half);
                    $('.targetSelect').eq(idx).val(isTarget);
                });

                alert("일괄 입력 완료!");
            });
        });

        function validateForm() {
            let valid = true;
            let hasSelection = false;

            $('.rowCheck:checked').each(function () {
                hasSelection = true;
                const row = $(this).closest('tr');
                
                const year = row.find('.evalYear').val()?.trim();
                const half = row.find('.halfSelect').val();
                const target = row.find('.targetSelect').val();

                if (!year || !half || !target) {
                    alert("입력값 누락: 선택한 대상자 중 일부 항목이 비어 있습니다.\n년도/구분/대상 여부를 확인해주세요.");
                    valid = false;
                    return false; // break
                }
            });

            if (!hasSelection) {
                alert("선택된 대상자가 없습니다.");
                return false;
            }

            return valid;
        }
        
    </script>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 30px;
            background-color: #f9fafb;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px 10px;
            text-align: center;
            font-size: 13px;
        }
        th {
            background-color: #f0f2f5;
        }
        input, select {
            padding: 4px 6px;
            font-size: 13px;
            width: 100%;
            box-sizing: border-box;
        }
        .bulk-input-row {
            display: flex;
            gap: 10px;
            margin: 15px 0 0 0;
            align-items: center;
        }
        .bulk-input-row label {
            font-weight: bold;
        }
        .bulk-input-row button {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 6px 14px;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-buttons {
            margin-top: 15px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }
        .form-buttons button {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 6px 14px;
            border-radius: 4px;
            cursor: pointer;
        }
        .message.success {
            background-color: #e0ffe0;
            color: #2d7a2d;
            padding: 10px;
            margin-bottom: 10px;
        }
        .message.error {
            background-color: #ffe0e0;
            color: #b00;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<!-- 안내 메시지 -->
<div>
    <strong>현재는 <c:out value="${currentYear}" />년 <c:out value="${currentHalf}" /> 평가 대상자 등록 기간입니다.</strong><br>
    <a href="/evaluation/evaluationCandidateList?year=${currentYear}&half=${currentHalf}">[현재 등록기간 바로가기]</a>
</div>

<!-- 메시지 -->
<c:if test="${not empty success}">
    <div class="message success">${success}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="message error">${error}</div>
</c:if>

<!-- 조회 조건 -->
<form method="get" action="/evaluation/evaluationCandidateList">
    <label>년도</label>
    <select name="year">
        <c:forEach var="y" begin="2020" end="2026">
            <option value="${y}" <c:if test="${y == year}">selected</c:if>>${y}</option>
        </c:forEach>
    </select>

    <label>구분</label>
    <select name="half">
        <option value="상반기" <c:if test="${half == '상반기'}">selected</c:if>>상반기</option>
        <option value="하반기" <c:if test="${half == '하반기'}">selected</c:if>>하반기</option>
    </select>

    <label>직급</label>
    <select name="rank">
        <option value="">전체</option>
        <c:forEach var="r" items="${rankList}">
            <option value="${r.rankId}" <c:if test="${r.rankId == rank}">selected</c:if>>${r.rankName}</option>
        </c:forEach>
    </select>

    <button type="submit">조회</button>
    <button type="button" onclick="location.href='/evaluation/evaluationCandidateList?year=${currentYear}&half=${currentHalf}'">초기화</button>
</form>

<!-- 등록 화면 (현재 평가 기간만 노출) -->
<c:if test="${isCurrent}">
    <div class="bulk-input-row">
        <label>일괄입력:</label>
        <input type="text" id="bulkYear" placeholder="년도" />
        <select id="bulkHalf">
            <option value="">구분</option>
            <option value="상반기">상반기</option>
            <option value="하반기">하반기</option>
        </select>
        <select id="bulkTarget">
            <option value="">대상여부</option>
            <option value="Y">Y</option>
            <option value="N">N</option>
        </select>
        <button type="button" id="applyBulk">일괄 적용</button>
    </div>

    <form:form method="post" action="/evaluation/evaluationCandidateUpdate" modelAttribute="wrapper" id="evaluationForm" onsubmit="return validateForm()">
        <table>
            <thead>
                <tr>
                    <th><input type="checkbox" id="checkAll" /></th>
                    <th>사번</th>
                    <th>이름</th>
                    <th>부서</th>
                    <th>팀</th>
                    <th>직급</th>
                    <th>년도</th>
                    <th>구분</th>
                    <th>평가대상</th>
                    <th>평가여부</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="candidate" items="${wrapper.candidateList}" varStatus="status">
                    <tr>
                        <td>
                            <input type="checkbox" class="rowCheck" name="candidateList[${status.index}].selected" value="${status.index}" />
                            <form:hidden path="candidateList[${status.index}].empId" />
                        </td>
                        <td><c:out value="${candidate.empId}" /></td>
                        <td><c:out value="${candidate.employee.name}" /></td>
                        <td><c:out value="${candidate.department.departmentName}" /></td>
                        <td><c:out value="${candidate.team.teamName}" /></td>
                        <td><c:out value="${candidate.rank.rankName}" /></td>
                        <td><form:input path="candidateList[${status.index}].evaluationYear" cssClass="evalYear" /></td>
                        <td>
                            <form:select path="candidateList[${status.index}].halfYear" cssClass="halfSelect">
                                <form:option value="">선택하세요</form:option>
                                <form:option value="상반기" />
                                <form:option value="하반기" />
                            </form:select>
                        </td>
                        <td>
                            <form:select path="candidateList[${status.index}].isTarget" cssClass="targetSelect">
                                <form:option value="">선택하세요</form:option>
                                <form:option value="Y" />
                                <form:option value="N" />
                            </form:select>
                        </td>
                        <td><c:out value="${candidate.evaluationStatus}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="form-buttons">
            <button type="submit">선택 대상자 등록</button>
        </div>
    </form:form>
</c:if>

<!-- 과거 평가 내역 조회 -->
<c:if test="${!isCurrent}">
    <h3>과거 평가 내역 조회 화면</h3>
    <table>
        <thead>
            <tr>
                <th>사번</th>
                <th>이름</th>
                <th>부서</th>
                <th>팀</th>
                <th>직급</th>
                <th>년도</th>
                <th>구분</th>
                <th>평가대상</th>
                <th>평가여부</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="candidate" items="${wrapper.candidateList}">
                <tr>
                    <td><c:out value="${candidate.empId}" /></td>
                    <td><c:out value="${candidate.employee.name}" /></td>
                    <td><c:out value="${candidate.department.departmentName}" /></td>
                    <td><c:out value="${candidate.team.teamName}" /></td>
                    <td><c:out value="${candidate.rank.rankName}" /></td>
                    <td><c:out value="${candidate.evaluationYear}" /></td>
                    <td><c:out value="${candidate.halfYear}" /></td>
                    <td><c:out value="${candidate.isTarget}" /></td>
                    <td><c:out value="${candidate.evaluationStatus}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
