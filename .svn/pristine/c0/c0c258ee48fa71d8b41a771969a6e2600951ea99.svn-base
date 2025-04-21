<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>근태 관리</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f6f9;
        }

        .container {
            width: 90%;
            max-width: 1200px;
            margin: auto;
            padding: 30px;
            background: white;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
            border-radius: 12px;
        }

        h2 {
            text-align: center;
            font-weight: 700;
            margin-bottom: 30px;
            color: #333;
        }

        .summary {
            display: flex;
            justify-content: space-between;
            margin-bottom: 30px;
        }

        .card {
            color: black;
            padding: 20px;
            border-radius: 8px;
            font-size: 18px;
            font-weight: bold;
            width: 30%;
            text-align: center;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card span {
            display: block;
            font-size: 22px;
        }

        .accordion {
            width: 100%;
        }

        .week {
            margin-bottom: 15px;
            border-radius: 10px;
            overflow: hidden;
            border: 1px solid #ccc;
        }

        .week-header {
            background: #007bff;
            color: white;
            padding: 18px;
            font-size: 18px;
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
            transition: background 0.3s ease;
        }

        .week-header:hover {
            background: #0056b3;
        }

        .toggle-icon {
            font-weight: bold;
        }

        .week-content {
            display: none;
            padding: 20px;
            background: #f9f9f9;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f1f1f1;
        }

        tr:hover {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>

<div class="container">
     <h2>
        <span class="month-nav" onclick="changeMonth(-1)"> &#60;</span>
        <span id="currentMonth">2025.03</span>
        <span class="month-nav" onclick="changeMonth(1)"> &#62;</span>
    </h2>

    <div class="summary">
        <div class="card">이번달 누적: <span id="monthTotal">63h 35m 54s</span></div>
        <div class="card">이번달 초과: <span id="monthOvertime">0h 0m 0s</span></div>
        <div class="card">이번달 잔여: <span id="monthRemain">33h 23m 43s</span></div>
    </div>

    <!-- 주차별 아코디언 -->
    <div class="accordion" id="weeksAccordion"></div>

      
</div>

<script>
let currentDate = new Date('2025-03-01');  // 시작 날짜를 2025년 3월 1일로 설정

function updateMonthDisplay() {
    let year = currentDate.getFullYear();
    let month = currentDate.getMonth() + 1;  // 0-based index (0 = January, 11 = December)
    document.getElementById('currentMonth').textContent = `\${year}.\${month.toString().padStart(2, '0')}`;
}

function changeMonth(direction) {
    currentDate.setMonth(currentDate.getMonth() + direction);  // -1이면 한 달 전, 1이면 한 달 후
    updateMonthDisplay();
}

// 주차별 날짜와 업무 데이터를 테이블에 동적으로 생성
function loadWeeksData() {
    const weeksAccordion = document.getElementById('weeksAccordion');
    weeksAccordion.innerHTML = ''; // 기존 내용 초기화

    const startDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1); // 첫 번째 날
    const dayOfWeek = startDate.getDay(); // 첫 번째 날의 요일 (0 = 일요일, 1 = 월요일 ...)
    const daysInMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0).getDate();

    let currentDay = 1; // 첫 번째 날부터 시작
    let prevMonthLastDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 0).getDate();  // 전 달의 마지막 날
    let currentWeekStartDay = prevMonthLastDay - dayOfWeek + 1; // 첫 번째 주의 시작을 전 달 마지막 주로 계산

    // 1주차를 2월 24일부터 시작하게 설정
    if (currentWeekStartDay < 1) {
        currentWeekStartDay += daysInMonth; // 1주차가 전 달의 마지막 날부터 시작하는 경우, 그 날짜로 맞춤
    }

    for (let week = 1; week <= 6; week++) { // 최대 6주
        const weekElement = document.createElement('div');
        weekElement.classList.add('week');
        const weekHeader = document.createElement('div');
        weekHeader.classList.add('week-header');
        weekHeader.innerHTML = `<span>📅 \${week}주차</span> <span class="toggle-icon">+</span>`;
        weekElement.appendChild(weekHeader);

        const weekContent = document.createElement('div');
        weekContent.classList.add('week-content');
        weekContent.id = `week\${week}`;
        const table = document.createElement('table');

        table.innerHTML = `
            <thead>
                <tr>
                    <th>일자</th>
                    <th>업무 시작</th>
                    <th>업무 종료</th>
                    <th>총 근무 시간</th>
                    <th>근무 상세</th>
                </tr>
            </thead>
            <tbody>
        `;

        for (let day = 0; day < 7; day++) {
            if (currentDay > daysInMonth) break; // 해당 월의 마지막 날을 넘으면 종료

            const date = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDay); // 날짜 객체 생성
            const dayOfWeek = date.getDay(); // 요일 값 (0 = 일요일, 1 = 월요일, ...)

            // 요일을 한글로 변환
            const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
            const dayOfWeekString = weekdays[dayOfWeek];

            const currentDateString = `\${currentDay.toString().padStart(2, '0')} \${dayOfWeekString}`;
            const startTime = `08:\${String(Math.floor(Math.random() * 60)).padStart(2, '0')}:00`;
            const endTime = `18:\${String(Math.floor(Math.random() * 60)).padStart(2, '0')}:00`;
            const workTime = `9시간 0분`;  // 예시로 계산
            const workDetail = `기본 8시간 30분 / 연장 0시간 30분 / 야간 0시간 0분`;

            table.innerHTML += `
                <tr>
                    <td>\${currentDateString}</td>
                    <td>\${startTime}</td>
                    <td>\${endTime}</td>
                    <td>\${workTime}</td>
                    <td>\${workDetail}</td>
                </tr>
            `;

            currentDay++;
        }

        weekContent.appendChild(table);
        weekElement.appendChild(weekContent);
        weeksAccordion.appendChild(weekElement);

        // 각 weekHeader에 클릭 이벤트 리스너 추가
        weekHeader.addEventListener("click", function () {
            toggleWeek(weekElement);
        });
    }

}
function toggleWeek(weekElement) {
    let content = weekElement.querySelector('.week-content');
    let icon = weekElement.querySelector(".toggle-icon");

    if (content.style.display === "none" || content.style.display === "") {
        content.style.display = "block";
        icon.innerText = "−";
    } else {
        content.style.display = "none";
        icon.innerText = "+";
    }
}

// 페이지 로드 시 모든 주차 닫기
document.addEventListener("DOMContentLoaded", function () {
    updateMonthDisplay();
    loadWeeksData();  // 이 부분 추가
    document.querySelectorAll(".week-content").forEach(content => {
        content.style.display = "none";
    });
});
</script>


</body>
</html>
