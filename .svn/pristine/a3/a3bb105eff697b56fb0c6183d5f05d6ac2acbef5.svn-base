<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
    <img src="${pageContext.request.contextPath}/resources/template/dist/cat3.png" alt="Description" type="image/png">
    <div class="info-container">
        <div class="info-item">
            <label>부서 :</label> 경영관리부 인사팀
            <input type="date" id="startDate" name="startDate"> ~ <input type="date" id="endDate" name="endDate">
        </div>
        <div class="info-item">
            <label>이름 :</label> 홍길동
            <button type="button">엑셀 다운로드</button>
            <div>2025-03-05 21:03:25</div>
        </div>
        <div class="info-item">
            <label>직급 :</label> 사원
            <button type="button">PDF 다운로드</button>
        </div>
    </div>
</div>


<br/>
<br/>
<br/>
<br/>
<table class="table">
    <tr>
        <th>날짜</th>
        <th>출근시간</th>
        <th>퇴근시간</th>
        <th>기본근로시간</th>
        <th>연장근로시간</th>
        <th>야간근로시간</th>
        <th>업무상태</th>
    </tr>
    <tr>
        <td>2024-12-20</td>
        <td>09:00</td>
        <td>18:00</td>
        <td>8</td>
        <td>0</td>
        <td>0</td>
        <td>퇴근</td>
    </tr>
    <tr>
        <td>2024-12-21</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>휴가</td>
    </tr>
    <tr>
        <td>...</td>
        <td>...</td>
        <td>...</td>
        <td>...</td>
        <td>...</td>
        <td>...</td>
        <td>...</td>
    </tr>
    <tr>
        <th>2025-01-20</th>
        <td>09:00</td>
        <td>11:40</td>
        <td>2:40</td>
        <td>0</td>
        <td>0</td>
        <td>오후반차</td>
    </tr>
    <tr>
        <td>2025-01-21</td>
        <td>09:05</td>
        <td>20:45</td>
        <td>8</td>
        <td>2</td>
        <td>0</td>
        <td>퇴근</td>
    </tr>
</table>

<script>
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

    setInterval(showTime, 1000); // 1초마다 시간 갱신
</script>
    