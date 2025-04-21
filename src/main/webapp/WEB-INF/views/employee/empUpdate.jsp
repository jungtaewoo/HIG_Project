<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 12.     	LJW            최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="page-container container-fluid">
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
				<li class="breadcrumb-item active" aria-current="page">직원 관리</li>
				<li class="breadcrumb-item active" aria-current="page">정보 수정</li>
			</ol>
		</nav>

	</div>
</div>
<br/>

<c:set var="fullPath" value="${employee.empPath}" />
<c:set var="startIndex" value="${fn:indexOf(fullPath, 'fileImages')}" />
<c:set var="cutStart" value="${startIndex + 10}" />
<c:set var="cutEnd" value="${fn:length(fullPath)}" />
<c:set var="fileName"
	value="${fn:substring(fullPath, cutStart, cutEnd)}" />
<c:set var="webPath" value="/resources/fileImages/${fileName}" />

<div class="row">
	<div class="col-md-6">
		<div class="card">
			<div class="card-header">
				<h2>직원정보수정</h2>
			</div>
			<div class="card-body">
				<form:form method="post" modelAttribute="employee"
					enctype="multipart/form-data">
					<div class="row mb-3">
						<label for="empId" class="col-sm-2 col-form-label">직원ID</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="empId" id="empId"
								value="${employee.empId}">
							<form:errors path="empId" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="name" class="col-sm-2 col-form-label">이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="name" id="name"
								value="${employee.name}">
							<form:errors path="name" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="hireDate" class="col-sm-2 col-form-label">입사일자</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" name="hireDate"
								id="hireDate" value="${employee.hireDate}">
							<form:errors path="hireDate" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="email" class="col-sm-2 col-form-label">이메일주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="email" id="email"
								value="${employee.email}">
							<form:errors path="email" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="address" class="col-sm-2 col-form-label">기본주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="address"
								id="address" value="${employee.address}">
							<form:errors path="address" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="addressDetail" class="col-sm-2 col-form-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="addressDetail"
								id="addressDetail" value="${employee.addressDetail}">
							<form:errors path="addressDetail" class="text-danger"
								element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="finalLevel" class="col-sm-2 col-form-label">최종학력</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="finalLevel"
								id="finalLevel" value="${employee.finalLevel}">
							<form:errors path="finalLevel" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="phoneNumber" class="col-sm-2 col-form-label">휴대폰번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="phoneNumber"
								id="phoneNumber" value="${employee.phoneNumber}">
							<form:errors path="phoneNumber" class="text-danger"
								element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="juminFront" class="col-sm-2 col-form-label">주민번호
							앞자리</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="juminFront"
								id="juminFront" value="${employee.juminFront}">
							<form:errors path="juminFront" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="juminBack" class="col-sm-2 col-form-label">주민번호
							뒷자리</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="juminBack"
								id="juminBack" value="${employee.juminBack}">
							<form:errors path="juminBack" class="text-danger" element="span" />
						</div>
					</div>
					<div class="row">
						<div class="col text-center">
							<button type="submit" class="btn btn-primary">전송</button>
						</div>
					</div>
			</div>
		</div>
	</div>

	<div class="col-md-6">
		<div class="card">
			<div class="card-header">
				<h2>사진</h2>
			</div>
			<div class="card-body text-center">
				<div class="d-flex justify-content-center align-items-start gap-4">
					<!-- 기존 사진 영역 -->
					<div class="border p-2 rounded text-center" style="width: 240px;">
						<h6>기존 사진</h6>
						<img
							src="${empty webPath ? '/images/default-profile.png' : webPath}"
							class="img-fluid" alt="직원 사진"
							style="width: 220px; height: 220px; object-fit: cover; background-color: #f8f9fa; border-radius: 8px;" />
					</div>

					<!-- 변경할 사진 미리보기 -->
					<div class="border p-2 rounded text-center" style="width: 240px;">
						<h6>변경할 사진</h6>
						<img id="previewImage" src="" class="img-fluid" alt="미리보기"
							style="width: 220px; height: 220px; object-fit: cover; background-color: #f8f9fa; border-radius: 8px;" />
					</div>
				</div>
				<div class="mt-3">
					<input type="file" name="file" multiple class="form-control"
						onchange="previewFile(this)">
				</div>
			</div>
		</div>
	</div>


</div>
</form:form>

<script>
	window.onload = function() {
		var today = new Date().toISOString().split('T')[0]; // 오늘 날짜를 yyyy-mm-dd 형식으로 가져옴
		document.getElementById('hireDate').value = today; // hireDate 필드에 값 설정
	};
	function previewFile(input) {
		console.log(input.files)
		const file = input.files[0];
		const preview = document.getElementById('previewImage');

		if (file && file.type.startsWith('image/')) {
			const reader = new FileReader();
			reader.onload = function(e) {
				preview.src = e.target.result;
			};
			reader.readAsDataURL(file);
		} else {
			preview.src = 'default-profile.png'; // 이미지 아니면 기본 이미지로
		}
	}
</script>