<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="year" value="2025"/>
<c:set var="half" value="1"/>

<title>KPI 평가 기준 안내</title>

<style>
body {
    background: linear-gradient(to right, #f8f9fa, #e9ecef);
	font-family: 'Segoe UI', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f6f9;
}

.start-eval-card {
    width: 100%;
    max-width: 1400px;
    margin: 5px auto 5px;
    text-align: center;
    background-color: #ffffff;
    border: 1px solid #dee2e6;
    border-radius: 12px;
    padding: 40px 20px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.05);
    transition: transform 0.2s;
}

.start-eval-card:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(0,0,0,0.08);
}

.start-eval-card h2 {
    font-size: 2rem;
    margin-bottom: 10px;
    color: #2b3e5c;
    font-weight: 700;
}

.start-eval-card p {
    color: #555;
    font-size: 1.2rem;
    margin-bottom: 25px;
}

.start-eval-card button {
    background-color: #007bff;
    color: white;
    font-size: 1.05rem;
    padding: 12px 26px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    transition: background-color 0.2s, transform 0.2s;
    display: inline-flex;
    align-items: center;
    gap: 6px;
}

.start-eval-card button:hover {
    background-color: #0056b3;
    transform: scale(1.02);
}

.start-eval-card button::before {
    content: '📝';
    font-size: 1.2rem;
}

.container {
    width: 100%;
    max-width: 1600px;
    margin: 20px auto 50px;
    background-color: #fff;
    padding: 50px 80px;
    border-radius: 12px;
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.08);
}

h3 {
    margin-top: 5px;
    color: #333;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 16px;
}

th, td {
    padding: 12px;
    border: 1px solid #ddd;
    text-align: center;
}

th {
    background-color: #f0f4f7;
    font-weight: bold;
}

th:nth-child(1), td:nth-child(1) { width: 10%; }
th:nth-child(2), td:nth-child(2) { width: 10%; }
th:nth-child(3), td:nth-child(3) { width: 10%; }
th:nth-child(4), td:nth-child(4) { width: 10%; }
th:nth-child(5), td:nth-child(5) { width: 15%; }
th:nth-child(6), td:nth-child(6) { width: 7%; }
th:nth-child(7) { width: 45%; } 
td:nth-child(7) { width: 45%; text-align: left; }
</style>

<div class="page-container container-fluid" style="font-size: 18px;">
	<div class="d-flex justify-content-between align-items-center mb-2">
		<!-- 좌측: 버튼 그룹 -->
		<div>
			<button type="button" class="btn btn-outline-secondary"
				onclick="history.back();">
				<i class="fas fa-arrow-left"></i>
			</button>
		</div>

		<!-- 우측: Breadcrumb -->
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb mb-0">
				<li class="breadcrumb-item fw-bold text-primary"><a
					href="${pageContext.request.contextPath }/account/login/home">📌Main</a></li>
				<li class="breadcrumb-item active" aria-current="page">평가 하기</li>
			</ol>
		</nav>
	</div>
</div>
<br />

<!-- ✅ KPI 평가 시작 카드 -->
<div class="start-eval-card">
    <h2>📌 평가 시작 전 안내</h2>
    <p>직급별 항목 및 가중치를 확인한 후 아래 버튼을 클릭해 KPI 평가를 시작하세요.</p>
    <a href="/evaluation/KPIList?year=${year}&half=${half}">
        <button>평가 시작하기</button>
    </a>
</div>

<!-- ✅ 평가 기준 테이블 영역 -->
<div class="container">
    <h3 style="text-align:center;">직급별 평가 기준</h3>

    <c:set var="printedRanks" value="," />
    <c:forEach var="x" items="${evaluationList}">
        <c:if test="${not fn:contains(printedRanks, x.rank.rankName)}">
            <c:set var="printedRanks" value="${printedRanks},${x.rank.rankName}," />

            <h3>▶ ${x.rank.rankName}</h3>
            <table>
                <thead>
                    <tr>
                        <th>년도</th>
                        <th>반기</th>
                        <th>평가명</th>
                        <th>유형</th>
                        <th>항목</th>
                        <th>가중치</th>
                        <th>상세내용</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="evaluation" items="${evaluationList}">
                        <c:if test="${evaluation.rank.rankName eq x.rank.rankName}">
                            <tr>
                                <td>${evaluation.evaluationYear}</td>
                                <td>
	                                <c:choose>
										<c:when test="${evaluation.halfYear eq '1'}">상반기</c:when>
										<c:when test="${evaluation.halfYear eq '2'}">하반기</c:when>
									</c:choose>
								</td>
                                <td>${evaluation.evaluaName}</td>
                                <td>${evaluation.evaluaType}</td>
                                <td>${evaluation.evaluaCriteria}</td>
                                <td>${evaluation.evaluaWeight}</td>
                                <td>&nbsp;${evaluation.evaluaComment}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:forEach>
</div>
