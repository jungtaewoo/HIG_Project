<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h4>지원서 작성</h4>
<br>
<br>
<form:form id="insert-form" modelAttribute="application" 
method="post" action="${pageContext.request.contextPath}/recruit/applicant/registerProcess">
	recruitId : ${application.recruitId}
	<input type="hidden" name="recruitId" id="recruitId" value="${application.recruitId}" />
	
	<div class="row mb-3">
		<label for="appName" class="col-sm-2 col-form-label">이름</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="appName" id="appName"
				value="${application.appName}" placeholder="이름 입력">
			<form:errors path="appName" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="appYeardate" class="col-sm-2 col-form-label">생년월일</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="appYeardate" id="appYeardate"
				value="${application.appYeardate}" placeholder="YYYYMMDD 형식 (예: 19990909)"
				pattern="\d{4}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])" >
			<form:errors path="appYeardate" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="appGender" class="col-sm-2 col-form-label">성별</label>
		<div class="col-sm-10">
			<select class="form-control" name="appGender" id="appGender">
            <option value="">선택하세요</option>
            <option value="남자" ${application.appGender == '남자' ? 'selected' : ''}>남자</option>
            <option value="여자" ${application.appGender == '여자' ? 'selected' : ''}>여자</option>
        </select>
			<form:errors path="appGender" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="appEmail" class="col-sm-2 col-form-label">이메일</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" name="appEmail" id="appEmail"
				value="${application.appEmail}" placeholder="user@example.com">
			<form:errors path="appEmail" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="appGrade" class="col-sm-2 col-form-label">학력</label>
		<div class="col-sm-10">
			 <select class="form-control" name="appGrade" id="appGrade">
	            <option value="">선택하세요</option>
	            <option value="고졸" ${application.appGrade == '고졸' ? 'selected' : ''}>고졸</option>
	            <option value="대졸" ${application.appGrade == '대졸' ? 'selected' : ''}>대졸</option>
	            <option value="석사" ${application.appGrade == '석사' ? 'selected' : ''}>석사</option>
	            <option value="박사" ${application.appGrade == '박사' ? 'selected' : ''}>박사</option>
	        </select>
			<form:errors path="appGrade" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="appCareer" class="col-sm-2 col-form-label">경력</label>
		<div class="col-sm-10">
			<textarea class="form-control" name="appCareer" id="appCareer" rows="5" >
				<c:out value="${application.appCareer}" escapeXml="false" />
			</textarea>
			<form:errors path="appCareer" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="appPl" class="col-sm-2 col-form-label">자기소개서</label>
		<div class="col-sm-10">
			<textarea class="form-control" name="appPl" id="appPl" rows="5">
				<c:out value="${application.appPl}" escapeXml="false" />
			</textarea>
			<form:errors path="appPl" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">지원서 제출</button>
		</div>
	</div>
</form:form>


<script>
    document.getElementById("insert-form").addEventListener("submit", function(event) {
        let isValid = true; // 입력 검증 여부
        let fields = document.querySelectorAll("#insert-form input, #insert-form textarea, #insert-form select");

        // 경력(appCareer)이 비어 있으면 기본값 "없음" 입력
        let careerField = document.getElementById("appCareer");
        if (careerField.value.trim() === "") {
            careerField.value = "없음";
        }
        
        fields.forEach(field => {
            if (field.name && field.value.trim() === "") {
                isValid = false;
            }
        });

        // 미입력된 항목이 있으면 경고창 띄우고 제출 중지
        if (!isValid) {
            alert("미입력된 항목이 존재합니다.");
            event.preventDefault(); // 폼 제출 중지
        }

    });
</script>
