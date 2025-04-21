<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- 뒤로가기, Breadcrumb 통일 -->
<div class="page-container container-fluid">
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
          <a href="${pageContext.request.contextPath }/recruit/board/list">채용공고 목록</a>
        </li>
        <li class="breadcrumb-item active" aria-current="page">채용공고 수정</li>
      </ol>
    </nav>
  </div>
</div>

<section class="section">
	<div class="card">
		<div class="card-header">
			<h3>채용공고 수정</h3><hr>
		</div>
		<div class="card-body">
			<%-- ${recruitBoard} --%>
			<%-- 
			<form:form id="insert-form" modelAttribute="recruitBoard" 
			method="post" action="${pageContext.request.contextPath}/recruit/board/updateProcess">
				<input type="hidden" name="recruitId" id="recruitId" value="${recruitBoard.recruitId}" />
				
				<div class="row mb-3">
					<label for="recruitTitle" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="recruitTitle" id="recruitTitle"
							value="${recruitBoard.recruitTitle}">
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
					<label for="recruitPosition" class="col-sm-2 col-form-label">모집부서</label>
					<div class="col-sm-10">
						<select class="form-control" name="recruitPosition" id="recruitPosition">
				            <option value="경영지원" ${recruitBoard.recruitPosition == '경영지원' ? 'selected' : ''}>경영지원</option>
				            <option value="마케팅" ${recruitBoard.recruitPosition == '마케팅' ? 'selected' : ''}>마케팅</option>
				            <option value="연구개발" ${recruitBoard.recruitPosition == '연구개발' ? 'selected' : ''}>연구개발</option>
				            <option value="생산" ${recruitBoard.recruitPosition == '생산' ? 'selected' : ''}>생산</option>
				        </select>
						<form:errors path="recruitPosition" class="text-danger" element="span" />
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
			        <label for="recruitStartdate" class="col-sm-2 col-form-label">시작일</label>
			        <div class="col-sm-10">
			            <!-- 날짜 값 표시 -->
			            <input type="date" class="form-control" name="recruitStartdate" id="recruitStartdate" value="${formattedStartDate}">
			            <form:errors path="recruitStartdate" class="text-danger" element="span" />
			        </div>
			    </div>
				<div class="row mb-3">
			        <label for="recruitEnddate" class="col-sm-2 col-form-label">마감일</label>
			        <div class="col-sm-10">
			            <!-- 날짜 값 표시 -->
			            <input type="date" class="form-control" name="recruitEnddate" id="recruitEnddate" value="${formattedEndDate}">
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
				<div class="row mb-3">
					<label for="recruitHirenum" class="col-sm-2 col-form-label">모집인원</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="recruitHirenum" id="recruitHirenum"
							value="${recruitBoard.recruitHirenum}">
						<form:errors path="recruitHirenum" class="text-danger" element="span" />
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-10">
						
					</div>
				</div>
				<!-- 버튼 컨테이너 -->
				<div style="display: flex;">
					<button type="submit" class="btn btn-primary" style="margin-right: 5px;">저장</button>
				</div>
			</form:form> --%>
			<!-- 폼 시작 -->
<form:form id="insert-form" modelAttribute="recruitBoard" 
  method="post" action="${pageContext.request.contextPath}/recruit/board/updateProcess">
  <input type="hidden" name="recruitId" id="recruitId" value="${recruitBoard.recruitId}" />

  <div class="row mb-3">
    <div class="col-md-6">
      <label for="recruitTitle" class="form-label" style="font-weight:bold;">제목</label>
      <input type="text" class="form-control" name="recruitTitle" id="recruitTitle" value="${recruitBoard.recruitTitle}">
      <form:errors path="recruitTitle" class="text-danger" element="span" />
    </div>
    <div class="col-md-6">
      <label for="recruitWorkplace" class="form-label" style="font-weight:bold;">근무지</label>
      <input type="text" class="form-control" name="recruitWorkplace" id="recruitWorkplace" value="${recruitBoard.recruitWorkplace}">
      <form:errors path="recruitWorkplace" class="text-danger" element="span" />
    </div>
  </div>

  <div class="row mb-3">
    <div class="col-md-6">
      <label for="recruitHiretype" class="form-label" style="font-weight:bold;">고용형태</label>
      <select class="form-control" name="recruitHiretype" id="recruitHiretype">
        <option value="정규직" ${recruitBoard.recruitHiretype == '정규직' ? 'selected' : ''}>정규직</option>
        <option value="계약직" ${recruitBoard.recruitHiretype == '계약직' ? 'selected' : ''}>계약직</option>
      </select>
      <form:errors path="recruitHiretype" class="text-danger" element="span" />
    </div>
    <div class="col-md-6">
      <label for="recruitSalary" class="form-label" style="font-weight:bold;">급여</label>
      <input type="text" class="form-control" name="recruitSalary" id="recruitSalary" value="${recruitBoard.recruitSalary}">
      <form:errors path="recruitSalary" class="text-danger" element="span" />
    </div>
  </div>

  <div class="row mb-3">
    <div class="col-md-6">
      <label for="recruitPosition" class="form-label" style="font-weight:bold;">모집부서</label>
      <select class="form-control" name="recruitPosition" id="recruitPosition">
        <option value="경영지원" ${recruitBoard.recruitPosition == '경영지원' ? 'selected' : ''}>경영지원</option>
        <option value="마케팅" ${recruitBoard.recruitPosition == '마케팅' ? 'selected' : ''}>마케팅</option>
        <option value="연구개발" ${recruitBoard.recruitPosition == '연구개발' ? 'selected' : ''}>연구개발</option>
        <option value="생산" ${recruitBoard.recruitPosition == '생산' ? 'selected' : ''}>생산</option>
      </select>
      <form:errors path="recruitPosition" class="text-danger" element="span" />
    </div>
    <div class="col-md-6">
      <label for="recruitWorkdetail" class="form-label" style="font-weight:bold;">업무내용</label>
      <input type="text" class="form-control" name="recruitWorkdetail" id="recruitWorkdetail" value="${recruitBoard.recruitWorkdetail}">
      <form:errors path="recruitWorkdetail" class="text-danger" element="span" />
    </div>
  </div>

  <div class="row mb-3">
    <div class="col-md-6">
      <label for="recruitPq" class="form-label" style="font-weight:bold;">우대사항</label>
      <input type="text" class="form-control" name="recruitPq" id="recruitPq" value="${recruitBoard.recruitPq}">
      <form:errors path="recruitPq" class="text-danger" element="span" />
    </div>
    <div class="col-md-6">
      <label for="recruitContact" class="form-label" style="font-weight:bold;">문의처</label>
      <input type="text" class="form-control" name="recruitContact" id="recruitContact" value="${recruitBoard.recruitContact}">
      <form:errors path="recruitContact" class="text-danger" element="span" />
    </div>
  </div>

  <div class="row mb-3">
    <div class="col-md-6">
      <label for="recruitStartdate" class="form-label" style="font-weight:bold;">시작일</label>
      <input type="date" class="form-control" name="recruitStartdate" id="recruitStartdate" value="${formattedStartDate}">
      <form:errors path="recruitStartdate" class="text-danger" element="span" />
    </div>
    <div class="col-md-6">
      <label for="recruitEnddate" class="form-label" style="font-weight:bold;">마감일</label>
      <input type="date" class="form-control" name="recruitEnddate" id="recruitEnddate" value="${formattedEndDate}">
      <form:errors path="recruitEnddate" class="text-danger" element="span" />
    </div>
  </div>

  <div class="row mb-3">
    <div class="col-md-6">
      <label for="recruitHirenum" class="form-label" style="font-weight:bold;">모집인원</label>
      <input type="text" class="form-control" name="recruitHirenum" id="recruitHirenum" value="${recruitBoard.recruitHirenum}">
      <form:errors path="recruitHirenum" class="text-danger" element="span" />
    </div>
  </div>

  <div class="d-flex justify-content-end">
    <button type="submit" class="btn btn-primary">저장</button>
  </div>
</form:form>
			
			
			
		</div>
	</div>
</section>


