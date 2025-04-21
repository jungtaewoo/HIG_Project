<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 29.     	정태우            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">

<style>
body {
	font-family: 'Pretendard', 'Noto Sans KR', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f6f9;
	color: #212529;
}

.container {
	width: 100%;
	max-width: 1800px;
	margin-top: 20px;
	padding: 30px;
	background: #ffffff;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	margin-bottom: 50px;
}
h2 {
	text-align: center;
	font-weight: 700;
	font-size: 1.8rem;
	color: #343a40;
	margin-top: 10px;
	margin-bottom: 30px;
}

h5 {
	font-size: 1.3rem;
	font-weight: 600;
	margin: 10px 0 20px;
	text-align: center;
	color: #495057;
}

.summary {
	display: flex;
	justify-content: space-between;
	gap: 20px;
}

.card {
	background-color: #f8f9fa;
	border-radius: 10px;
	padding: 20px;
	width: 30%;
	text-align: center;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	transition: all 0.3s ease;
}

.card:hover {
	transform: translateY(-4px);
	box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
}

.card span {
	display: block;
	font-size: 24px;
	font-weight: bold;
	color: #0d6efd;
	margin-top: 8px;
}

select#yearSelect {
	padding: 6px 12px;
	border: 1px solid #ced4da;
	border-radius: 6px;
	margin-left: 10px;
	font-size: 0.95rem;
}

#annualHL {
	text-align: center;
	margin-top: 20px;
	font-weight: 600;
	color: #495057;
}

.accordion {
	margin-top: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	font-size: 0.95rem;
	background-color: #fff;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	overflow: hidden;
}

th, td {
	padding: 14px 16px;
	border-bottom: 1px solid #e9ecef;
	white-space: nowrap;
	text-align: center;
	font-size: 16px;
}

th {
	background-color: #f1f3f5;
	font-weight: 600;
	color: #495057;
}

tr:hover {
	background-color: #f8f9fa;
}

hr {
	margin-top: 20px;
	margin-bottom: 20px;
	border: none;
	border-top: 1px solid #dee2e6;
}
</style>

  <security:authentication property="principal" var="principal" />
<div class="page-container container-fluid" style="width: 1800px">
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
          <a href="${pageContext.request.contextPath }/myAttendance?empId=${principal.realUser.empId}">내 근태 현황</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">내 연차 내역</li>
      </ol>
    </nav>

  </div>
</div>

<div class="container">
<h3>연차 내역</h3>
	<h5>
		${employee.department.departmentName }
		${employee.team.teamName}
		${employee.name}
		${employee.rank.rankName}
	</h5>
			<input type="hidden" value="${employee.empId}" id="empId" /> 
	<h2>
		<span id="currentYear"></span>
	</h2>

	<div class="summary">
		<div class="card">
			총 연차: <span id="yearTotalAnnual"> ${annualCount.totalAnnual}</span>
		</div>
		<div class="card">
			사용 연차: <span id="userAnnual"> ${annualCount.usedAnnual}</span>
		</div>
		<div class="card">
			잔여 연차: <span id="remainAnnual"> ${annualCount.remainingAnnual}</span>
		</div>
	</div>
</div>

<div class="container">
<div>
연차 사용 기간 :
	 <select id="yearSelect" onchange="changeYear()">
        <!-- 동적으로 생성될 연도 옵션이 여기에 추가됩니다. -->
    </select>
    <h5 id="annualHL">사용 내역</h5>
</div>
<hr>
	<div class="accordion" id="weeksAccordion">
		<table>
			<thead>
				<tr>
					<th>유형</th>
					<th>기간</th>
					<th>사용 일수</th>
					<th>사유</th>
				</tr>
			</thead>
			<tbody id="annualData">
			</tbody>
		</table>
	</div>
</div>

<script>
// 현재 연도를 기준으로 10년 전까지 연도 옵션을 동적으로 생성하는 함수
let HY = "${employee.hireDate}".trim(); // 공백 제거

function generateYearOptions() {
    let selectElement = document.getElementById('yearSelect');
    let currentYear = new Date().getFullYear(); // 현재 연도 가져오기

    let hireDateStr = HY.split(" ")[0]; // "2023-08-22"만 추출
    let hireDate = new Date(hireDateStr); // Date 객체로 변환
    let hireYear = hireDate.getFullYear(); // 입사 연도 가져오기

    selectElement.innerHTML = ""; // 기존 옵션 초기화

    // 입사 연도부터 현재 연도까지 옵션 추가
    for (let year = currentYear; year >= hireYear; year--) {
        let option = document.createElement('option');
        option.value = year;
        option.textContent = `\${year}-01-01 ~ \${year}-12-31`; // 옵션 텍스트

        if (year === currentYear) {
            option.selected = true; // 현재 연도를 기본 선택으로 설정
        }

        selectElement.appendChild(option); // select 요소에 옵션 추가
    }

    changeYear();
}

//연차 데이터 업데이트 함수
function updateAnnualSummary(year) {
    let empId = document.getElementById('empId').value;

    $.ajax({
        url: '/MyAnnual',
        type: 'GET',
        data: { date: year, empId: empId },
        dataType: "json",
        success: function(data) {
            let dd = data.annualDetail;
            let tableBody = document.getElementById("annualData");
            tableBody.innerHTML = "";
            
            let annualDataHTML = '';
            // 연차 내역이 없을 때 처리
            if (dd && dd.length === 0 ) {
                annualDataHTML += `
                    <tr>
                        <td id="xx" colspan="5">연차 내역이 없습니다</td>
                    </tr>
                `;
                tableBody.innerHTML = annualDataHTML;
                return; // 데이터가 없을 경우 처리 후 종료
            }
            
            dd.forEach(function(item) {

                let formattedLeaveDate = item.leaveDate.split(' ')[0];
                let formattedLeaveEndDate = item.leaveEndDate.split(' ')[0];
				
                let leaveDay = 0;
                const name = item.annualType.annualName;

                if (name === '오전반차' || name === '오후반차') {
                    leaveDay = 0.5;
                } else {
                    const start = new Date(formattedLeaveDate);
                    const end = new Date(formattedLeaveEndDate);
                    leaveDay = Math.floor((end - start) / (1000 * 60 * 60 * 24)) + 1;
                }
                
                annualDataHTML += `
                    <tr>
                        <td>\${item.annualType.annualName}</td>
                        <td>\${formattedLeaveDate} ~ \${formattedLeaveEndDate}</td>
                        <td>\${leaveDay}</td>
                        <td>\${item.reason || '없음'}</td>
                    </tr>
                `;
            });

            // ✅ 테이블에 데이터 삽입
            tableBody.innerHTML = annualDataHTML;
        },
        error: function(xhr, status, error) {
            console.error("연차 내역 로딩 실패:", error);
        }
    });
}

// 연도 변경 시 호출되는 함수
function changeYear() {
    let selectedYear = document.querySelector("#yearSelect").value;
    document.querySelector("#currentYear").innerText = selectedYear;
    updateAnnualSummary(selectedYear);
}

// 초기 페이지 로드 시 현재 연도로 연차 내역을 업데이트
document.addEventListener('DOMContentLoaded', function() {
    let selectedYear = document.querySelector("#yearSelect").value;
    document.querySelector("#currentYear").innerText = selectedYear;
    generateYearOptions();
    updateAnnualSummary(selectedYear);
});


</script>

