<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>평가 현황</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h2>평가 현황</h2>

<!-- 평가 기준 표시용 영역 -->
<div id="criteria-panel" style="border:1px solid #ccc; padding:10px; float:right; width:300px; min-height:150px;">
    <strong>평가 기준</strong>
    <div id="criteria-content">
        <!-- 클릭 시 여기에 기준 내용 동적 삽입 -->
    </div>
</div>

<!-- 평가 요약 테이블 -->
<table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
        <th>년도</th>
        <th>구분</th>
        <th>부장 <a href="#" class="criteria-link" data-rank="부장">[?]</a></th>
        <th>차장 <a href="#" class="criteria-link" data-rank="차장">[?]</a></th>
        <th>과장 <a href="#" class="criteria-link" data-rank="과장">[?]</a></th>
        <th>대리 <a href="#" class="criteria-link" data-rank="대리">[?]</a></th>
        <th>사원 <a href="#" class="criteria-link" data-rank="사원">[?]</a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="summary" items="${summaryList}">
        <tr>
            <td>${summary.year}</td>
            <td>${summary.half}</td>
            <td><a href="#" class="count-link" data-rank="부장" data-year="${summary.year}" data-half="${summary.half}">${summary.managerCount}</a></td>
            <td><a href="#" class="count-link" data-rank="차장" data-year="${summary.year}" data-half="${summary.half}">${summary.deputyCount}</a></td>
            <td><a href="#" class="count-link" data-rank="과장" data-year="${summary.year}" data-half="${summary.half}">${summary.sectionChiefCount}</a></td>
            <td><a href="#" class="count-link" data-rank="대리" data-year="${summary.year}" data-half="${summary.half}">${summary.assistantManagerCount}</a></td>
            <td><a href="#" class="count-link" data-rank="사원" data-year="${summary.year}" data-half="${summary.half}">${summary.staffCount}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>

<!-- 요약 영역 -->
<div>
    <strong>전체 :</strong> ${totalCount}
    <strong>대상 :</strong> ${targetCount}명
    <strong>제외 :</strong> ${excludedCount}명
</div>

<br/>

<!-- 평가 대상자 리스트 -->
<table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
        <th>사번</th>
        <th>이름</th>
        <th>부서</th>
        <th>팀</th>
        <th>평가대상</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${targetList}">
        <tr>
            <td>${emp.empId}</td>
            <td>${emp.empName}</td>
            <td>${emp.departmentName}</td>
            <td>${emp.teamName}</td>
            <td>${emp.isTarget}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>

<!-- 버튼 영역 (form:form 사용) -->
<form:form method="post" action="/evaluation/doSomething">
    <input type="hidden" name="someData" value="..." />
    <form:button>선택자 일괄 처리</form:button>
    <form:button>PDF 다운로드</form:button>
</form:form>

<!-- 모달 영역 -->
<div id="modal" style="display:none; position:fixed; top:20%; left:30%; background:#fff; border:1px solid #000; padding:20px;">
    <h4>상세 평가건 목록</h4>
    <div id="modal-content">
        <!-- Ajax로 데이터 로드 -->
    </div>
    <button onclick="$('#modal').hide();">닫기</button>
</div>

<script>
    // 평가 기준 클릭 시
    $('.criteria-link').on('click', function (e) {
        e.preventDefault();
        const rank = $(this).data('rank');
        // Ajax 또는 템플릿에서 기준 가져오기
        $('#criteria-content').html(rank + '의 평가 기준을 여기에 표시');
    });

    // 인원수 클릭 시 모달 표시
    $('.count-link').on('click', function (e) {
        e.preventDefault();
        const year = $(this).data('year');
        const half = $(this).data('half');
        const rank = $(this).data('rank');

        $.ajax({
            url: '/evaluation/modalDetail',
            type: 'get',
            data: { year, half, rank },
            success: function (data) {
                $('#modal-content').html(data);
                $('#modal').show();
            }
        });
    });
</script>

</body>
</html>

