<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025-03-12    	   정태우            최초 생성
 *  
 *
-->
<style>
.container {
	display: flex;
	align-items: center;
	gap: 30px;
}

.info-container {
	display: flex;
	flex-direction: column;
	gap: 15px; /* 항목 간의 간격을 늘립니다 */
}

.info-item {
	display: flex;
	align-items: center;
	gap: 15px; /* 라벨과 입력 필드 간의 간격을 늘립니다 */
}

img {
	width: 150px; /* 가로 크기 */
	height: 150px; /* 세로 크기 */
	margin-right: 20px;
}

label {
	width: 50px; /* 라벨의 가로 크기 */
}
</style>

<div class="container">
	<img
		src="${pageContext.request.contextPath}/resources/empImage/cat1.jpg"
		alt="Description" type="image/png">
	<div class="info-container">
		<div class="info-item">
			<label>Now :</label> <span id="current-date-time"></span>
			<input type="date" id="startDate" name="startDate"> ~ <input type="date" id="endDate" name="endDate">
			<button class="btn btn-outline-success" onclick="downloadExcel()">엑셀
				다운로드</button>
			<button class="btn btn-outline-danger" onclick="exportPDF()">PDF
				다운로드</button>
		</div>
		<div class="info-item">
			<label>부서 :</label>  	${ employee.department.departmentName}
		</div>
		<div class="info-item">
			<label>팀 :</label>     ${ employee.team.teamName}
		</div>
		<div class="info-item">
			<label data-empid="${employee.empId}" data-empname="${ employee.name}">이름 :</label>   ${ employee.name}
		</div>
		<div class="info-item">
			<label>직급 :</label> ${ employee.rank.rankName} 
		</div>
		<div class="info-item">
			<label>번호 :</label> 	${ employee.phoneNumber}
		</div>
	</div>
</div>


<br />
<br />
<br />
<br />
<table class="table table-striped" id="table1">
	<thead>
		<tr>
			<th>날짜</th>
			<th>출근시간</th>
			<th>퇴근시간</th>
			<th>근태상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${attendanceList}" var="attendanceVO">
			<tr>
				<td>${attendanceVO.workDate}</td>
				<td>${attendanceVO.workStartTime}</td>
				<td>${attendanceVO.workEndTime}</td>
				<td>${attendanceVO.attendanceStatus}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
	var labelData = document.querySelector("label[data-empid]");
	var empId = labelData.getAttribute("data-empid");
	var empName = labelData.getAttribute("data-empname");
	// 날짜 변경 시 필터링된 데이터를 가져오는 함수
	$("#startDate").change(function() {
	    DataFilter(empId); // 날짜 변경 시 데이터 새로 불러오기
	});
	
	$("#endDate").change(function() {
	    DataFilter(empId); // 날짜 변경 시 데이터 새로 불러오기
	});
	
	// 필터링된 데이터를 가져오는 함수
    function DataFilter(empId) {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var empId = empId;
        
        // 선택된 날짜가 없을 경우 null 또는 빈 문자열로 처리
        var sendData = {};
        if(empId){
        	sendData.empId = empId; 
        }
        if (startDate) {
            sendData.startDate = startDate; // 시작 날짜가 있을 경우만 보내기
        }
        if (endDate) {
            sendData.endDate = endDate; // 끝 날짜가 있을 경우만 보내기
        }
        
        $.ajax({
            url: "/check/Detail", 
            data: sendData ,
            dataType: "json",
            success: function(data) {
            	updateDetailTable(data.attendanceList);
            },
            error: function() {
                alert("데이터를 불러오는 중 오류가 발생했습니다.");
            }
        });
    }
    // 테이블 업데이트
    function updateDetailTable(data) {
        var tableBody = $("#table1 tbody");
        tableBody.empty(); // 기존 데이터 삭제

        if (data.length === 0) {
            tableBody.append("<tr><td class='text-center' colspan='8'>데이터가 없습니다.</td></tr>");
            return;
        }
        
        $.each(data, function(index, attendanceVO) {
            var row = 
            	"<tr>" +
	                "<td>" + (attendanceVO.workDate ? attendanceVO.workDate : " ") + "</td>" +
	                "<td>" + (attendanceVO.workStartTime ? attendanceVO.workStartTime : " ") + "</td>" +
	                "<td>" + (attendanceVO.workEndTime ? attendanceVO.workEndTime : " ") + "</td>" +
	                "<td>" + (attendanceVO.attendanceStatus ? attendanceVO.attendanceStatus : " ") + "</td>" +
                "</tr>";
            tableBody.append(row);
        });
    }
	
	function showTime() {
		const currentDate = new Date();
		const hours = currentDate.getHours().toString().padStart(2, '0');
		const minutes = currentDate.getMinutes().toString().padStart(2, '0');
		const seconds = currentDate.getSeconds().toString().padStart(2, '0');
		const timeString = `\${hours}:\${minutes}:\${seconds}`;
		const dateString = currentDate.toISOString().split('T')[0];
		const dateTimeString = `\${dateString} \${timeString}`;

		document.getElementById("current-date-time").innerText = dateTimeString;
	}
	showTime();
	setInterval(showTime, 1000); // 1초마다 시간 갱신
	
	// PDF 다운로드
	function exportPDF() {
		const element = document.getElementById("table1");

		const opt = {
			margin : 1,
			filename : empName+'-출퇴현황.pdf',
			html2canvas : {
				scale : 2
			},
			jsPDF : {
				unit : 'mm',
				format : 'a4',
				orientation : 'portrait'
			}
		};

		html2pdf().from(element).set(opt).save();
	}
	function downloadExcel() {
		// 테이블 데이터를 가져옵니다.
		var table = document.getElementById("table1");

		// 엑셀로 변환하는 과정
		var wb = XLSX.utils.table_to_book(table, {
			sheet : "출퇴현황"
		});

		// 엑셀 파일을 다운로드
		XLSX.writeFile(wb, empName+"-출퇴현황.xlsx");

	}
	// 현재 날짜를 YYYY-MM-DD 형식으로 설정
	var today = new Date();
	var day = ("0" + today.getDate()).slice(-2); // 일
	var month = ("0" + (today.getMonth() + 1)).slice(-2); // 월
	var year = today.getFullYear(); // 연도

	// "YYYY-MM-DD" 형식으로 날짜를 입력 필드에 설정
	document.getElementById('date').value = year + "-" + month + "-" + day;
</script>
