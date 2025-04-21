<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 
<h4>채용공고 작성</h4>
<br>
<form:form id="insert-form" modelAttribute="recruitBoard" 
method="post" action="${pageContext.request.contextPath}/recruit/board/registerProcess">
	<div class="row mb-3">
		<label for="recruitTitle" class="col-sm-2 col-form-label">제목</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="recruitTitle" id="recruitTitle"
				value="${recruitBoard.recruitTitle}" placeholder="YYYY 부서(팀)명 직무명 채용">
			<form:errors path="recruitTitle" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitWorkplace" class="col-sm-2 col-form-label">근무지</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="recruitWorkplace" id="recruitWorkplace"
				value="${recruitBoard.recruitWorkplace}">
			<form:errors path="recruitWorkplace" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitHiretype" class="col-sm-2 col-form-label">고용형태</label>
		<div class="col-sm-10">
			<%-- <input type="text" class="form-control" name="recruitHiretype" id="recruitHiretype"
				value="${recruitBoard.recruitHiretype}"> --%>
			<select class="form-control" name="recruitHiretype" id="recruitHiretype">
	            <option value="정규직" ${recruitBoard.recruitHiretype == '정규직' ? 'selected' : ''}>정규직</option>
	            <option value="계약직" ${recruitBoard.recruitHiretype == '계약직' ? 'selected' : ''}>계약직</option>
	        </select>
			<form:errors path="recruitHiretype" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitSalary" class="col-sm-2 col-form-label">급여</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="recruitSalary" id="recruitSalary"
				value="${recruitBoard.recruitSalary}">
			<form:errors path="recruitSalary" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitWorkdetail" class="col-sm-2 col-form-label">업무내용</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="recruitWorkdetail" id="recruitWorkdetail"
				value="${recruitBoard.recruitWorkdetail}">
			<form:errors path="recruitWorkdetail" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitPq" class="col-sm-2 col-form-label">우대사항</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="recruitPq" id="recruitPq"
				value="${recruitBoard.recruitPq}">
			<form:errors path="recruitPq" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitEnddate" class="col-sm-2 col-form-label">마감일</label>
		<div class="col-sm-10">
			<input type="date" class="form-control" name="recruitEnddate" id="recruitEnddate"
				value="${recruitBoard.recruitEnddate}">
			<form:errors path="recruitEnddate" class="text-danger" element="span" />
		</div>
	</div>
	<div class="row mb-3">
		<label for="recruitContact" class="col-sm-2 col-form-label">문의처</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="recruitContact" id="recruitContact"
				value="${recruitBoard.recruitContact}">
			<form:errors path="recruitContact" class="text-danger" element="span" />
		</div>
	</div>
	<!-- 버튼 컨테이너 -->
	<div style="display: flex;">
		<button type="submit" class="btn btn-primary" style="margin-right: 5px;">등록</button>
		<button type="button" class="btn btn-secondary" style="width:100px;" onclick="history.go(-1);">뒤로가기</button>
	</div>	
</form:form>
