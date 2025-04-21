<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
    pre {
        font-family: inherit; /* 부모 요소(`td`)의 폰트 스타일을 따름 */
        margin: 0; 
        padding: 0; 
    }
</style>
<h4>지원서 상세</h4>
${applicationDetail }
<table class="table">
	<tr>
		<th>이름</th>
		<td>${applicationDetail.appName }</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>${applicationDetail.appGender }</td>
	</tr>
	<tr>
		<th>생년월일</th>
		<td>${applicationDetail.appYeardate }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${applicationDetail.appEmail }</td>
	</tr>
	<tr>
		<th>학력</th>
		<td>${applicationDetail.appGrade }</td>
	</tr>
	<tr>
	    <th>경력</th>
	    <td><pre>${applicationDetail.appCareer}</pre></td>
	</tr>
	<tr>
	    <th>자기소개서</th>
	    <td><pre>${applicationDetail.appPl}</pre></td>
	</tr>
	<tr>
	    <th>평가상태</th>
	    <td>
	    	${applicationDetail.applicationStatus.currentStatus}
	    	<c:if test="${applicationDetail.applicationStatus.currentStatus eq '면접예정'}">
	    		<br>
	    		면접일 : ${fn:substringBefore(applicationDetail.applicationStatus.interviewDate, ' ')}
	    	</c:if>
	    </td>
	</tr>
</table>

<form action="${pageContext.request.contextPath }/recruit/applicant/update" method="post">
    <input type="hidden" name="statusId" value="${applicationDetail.applicationStatus.statusId}">
    <input type="hidden" name="appId" value="${applicationDetail.appId}">
    <label>
        <input type="radio" name="currentStatus" value="서류탈락" id="option1"
        ${applicationDetail.applicationStatus.currentStatus eq '서류탈락' ? 'checked' : ''}> 
        서류탈락
    </label>
    <br>
    <label>
        <input type="radio" name="currentStatus" value="면접예정" id="option2"
        ${applicationDetail.applicationStatus.currentStatus eq '면접예정' ? 'checked' : ''}> 
        면접예정
    </label>
    
    <!-- 면접예정 선택 시 보이게 할 캘린더 입력 필드 -->
    <span id="datePickerContainer" style="display: none;">
        <label for="interviewDate"> 날짜 선택:</label>
        <input type="date" id="interviewDate" name="interviewDate"
        value="${applicationDetail.applicationStatus.interviewDate}">
    </span>
	<br>
	<br>
	<!-- 버튼 컨테이너 -->
	<div style="display: flex;">
        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">저장</button>
        <button type="button" class="btn btn-secondary" style="width:100px;" onclick="history.go(-1);">뒤로가기</button>
    </div>
</form>
<br>
<br>
<script>
document.getElementById('option1').addEventListener('change', function() {
    document.getElementById('datePickerContainer').style.display = 'none';
    document.getElementById('interviewDate').value = ''; // 면접 날짜 초기화
});

document.getElementById('option2').addEventListener('change', function() {
    document.getElementById('datePickerContainer').style.display = 'inline-block';
});

//면접예정 선택 시 날짜입력을 필수로 
document.querySelector("form").addEventListener("submit", function(event) {
    const status = document.querySelector('input[name="currentStatus"]:checked').value;
    const dateInput = document.getElementById("interviewDate");

    if (status === "면접예정" && (!dateInput.value || dateInput.value.trim() === "")) {
        alert("면접 날짜를 선택해주세요.");
        event.preventDefault(); // 폼 제출 중단
    }
});

window.onload = function() {
    document.getElementById('datePickerContainer').style.display = 
        document.getElementById('option2').checked ? 'inline-block' : 'none';
};



</script>








