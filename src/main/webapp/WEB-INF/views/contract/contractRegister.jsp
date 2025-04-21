<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	<style>
		body {
			background-color: #f5f7fa;
			font-family: 'Noto Sans KR', sans-serif;
			padding: 40px 20px;
		}
		.contract-card {
			max-width: 850px;
			margin: auto;
			background: #fff;
			padding: 40px 50px;
			border-radius: 16px;
			box-shadow: 0 8px 24px rgba(0, 0, 0, 0.05);
		}
		.contract-title {
			font-size: 28px;
			font-weight: bold;
			margin-bottom: 30px;
			text-align: center;
			color: #2c3e50;
		}
		label {
			font-weight: 600;
			margin-bottom: 6px;
		}
		input[type="text"], input[type="number"], input[type="date"], select {
			height: 42px;
		}
		.form-control:focus {
			border-color: #4e73df;
			box-shadow: 0 0 0 0.2rem rgba(78, 115, 223, 0.25);
		}
		.btn-primary, .btn-secondary {
			padding: 10px 20px;
			border-radius: 8px;
			font-weight: 500;
		}
	</style>

<div class="contract-card">
	<div class="contract-title">📄 근로계약서 등록</div>

	<!-- 등록 성공 시 alert -->
	<c:if test="${success}">
		<script>alert("근로계약서 등록이 완료되었습니다.");</script>
	</c:if>

	<!-- 오류 메시지 -->
	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger" role="alert">${errorMsg}</div>
	</c:if>

	<!-- 자동 급여 적용용 직원 선택 폼 -->
	<form action="${pageContext.request.contextPath}/contract/auto" method="get" class="mb-4">
		<div class="d-flex gap-2">
			<select name="empId" class="form-control select2" required>
				<option value="">-- 자동 급여 적용할 사원 선택 --</option>
				<c:forEach var="emp" items="${unContractList}">
					<option value="${emp.empId}" <c:if test="${not empty contract and emp.empId == contract.empId}">selected</c:if>>
						${emp.name} (${emp.empId})
					</option>
				</c:forEach>
			</select>
			<button type="submit" class="btn btn-outline-primary">급여 자동 적용</button>
		</div>
	</form>

	<!-- 실제 등록 폼 -->
	<form action="${pageContext.request.contextPath}/contract/register" method="post">
		<input type="hidden" name="empId" value="${empty contract ? '' : contract.empId}" />

		<div class="row g-4">
			<div class="col-md-6">
				<label>근무 장소</label>
				<input type="text" name="workPlace" class="form-control" value="${empty contract ? '' : contract.workPlace}" required>
			</div>

			<div class="col-md-6">
				<label>근무 형태</label>
				<select name="workType" class="form-control">
					<option value="풀타임" <c:if test="${not empty contract and contract.workType == '풀타임'}">selected</c:if>>풀타임</option>
					<option value="파트타임" <c:if test="${not empty contract and contract.workType == '파트타임'}">selected</c:if>>파트타임</option>
				</select>
			</div>

			<div class="col-md-6">
				<label>계약 유형</label>
				<select name="contractType" class="form-control">
					<option value="정규직" <c:if test="${not empty contract and contract.contractType == '정규직'}">selected</c:if>>정규직</option>
					<option value="계약직" <c:if test="${not empty contract and contract.contractType == '계약직'}">selected</c:if>>계약직</option>
					<option value="인턴" <c:if test="${not empty contract and contract.contractType == '인턴'}">selected</c:if>>인턴</option>
				</select>
			</div>

			<div class="col-md-6">
				<label>시작일</label>
				<input type="date" name="startDate" class="form-control" value="${empty contract ? '' : contract.startDate}" required>
			</div>

			<div class="col-md-6">
				<label>종료일</label>
				<input type="date" name="endDate" class="form-control" value="${empty contract ? '' : contract.endDate}">
			</div>

			<div class="col-md-6">
				<label>기본 급여</label>
				<input type="text" name="baseSalary" class="form-control currency-input" value="${empty contract ? '' : contract.baseSalary}" required>
			</div>

			<div class="col-md-6">
				<label>주간 근무 시간</label>
				<input type="number" name="weeklyHours" class="form-control" value="${empty contract ? '' : contract.weeklyHours}" required>
			</div>

			<div class="col-md-6">
				<label>계약 상태</label>
				<select name="contractStatus" class="form-control">
					<option value="일시 중지" <c:if test="${not empty contract and contract.contractStatus == '일시 중지'}">selected</c:if>>일시 중지</option>
					<option value="유효" <c:if test="${not empty contract and contract.contractStatus == '유효'}">selected</c:if>>유효</option>
				</select>
			</div>

			<div class="col-md-6">
				<label>교통비</label>
				<input type="text" name="transportAllowance" class="form-control currency-input" value="${empty contract ? '' : contract.transportAllowance}">
			</div>

			<div class="col-md-6">
				<label>식대</label>
				<input type="text" name="foodAllowance" class="form-control currency-input" value="${empty contract ? '' : contract.foodAllowance}">
			</div>

			<div class="col-md-6">
				<label>직책 수당</label>
				<input type="text" name="positionAllowance" class="form-control currency-input" value="${empty contract ? '' : contract.positionAllowance}">
			</div>
		</div>

		<div class="d-flex justify-content-center gap-3 mt-5">
			<input type="submit" value="등록" class="btn btn-primary">
			<a href="${pageContext.request.contextPath}/contract/List" class="btn btn-secondary">취소</a>
		</div>
	</form>
</div>

<script>
	$(document).ready(function () {
		$('.select2').select2({
			placeholder: "직원 이름 또는 사번 검색",
			width: '100%'
		});

		// 초기 통화 포맷
		$('.currency-input').each(function () {
			const val = $(this).val().replace(/[^\d]/g, '');
			if (val) {
				$(this).val(Number(val).toLocaleString() + '원');
			}
		});

		// 입력 시 실시간 포맷
		$('.currency-input').on('input', function () {
			const rawVal = $(this).val().replace(/[^\d]/g, '');
			if (rawVal) {
				$(this).val(Number(rawVal).toLocaleString() + '원');
			} else {
				$(this).val('');
			}
		});

		// 제출 전 숫자만 
		$('form').on('submit', function () {
			$('.currency-input').each(function () {
				const numberOnly = $(this).val().replace(/[^\d]/g, '');
				$(this).val(numberOnly);
			});
		});
	});
</script>
