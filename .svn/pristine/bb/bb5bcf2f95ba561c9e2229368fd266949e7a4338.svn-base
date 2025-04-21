<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.header-title {
	font-size: 1.5rem;
	font-weight: 600;
	color: #333;
	margin-bottom: 6px;
}

.header-title .highlight {
	color: #0d6efd;
	font-weight: 700;
	margin-left: 4px;
}

.header-title .subtle {
	color: #555;
	font-size: 1rem;
	margin-left: 8px;
}

.header-desc {
	font-size: 0.95rem;
	color: #666;
	margin-top: 4px;
}

.header-title {
	font-size: 1.6rem;
	font-weight: bold;
	color: #333;
	margin-bottom: 8px;
}

.header-period {
	font-size: 1rem;
	color: #666;
	margin-bottom: 6px;
}

.header-desc {
	font-size: 1.2rem;
	color: #555;
}

.card {
	border: 1px solid #ddd;
	border-radius: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
	margin-bottom: 20px;
}

.card-header {
	background-color: #f8f9fa;
	padding: 20px;
	border-bottom: 1px solid #eee;
}

.card-header .title-row {
	font-size: 1.3rem;
	font-weight: bold;
}

.card-header .subtitle-row {
	margin-top: 6px;
	font-size: 0.95rem;
	color: #555;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 10px;
}

th, td {
	padding: 10px;
	border-bottom: 1px solid #ddd;
	text-align: center;
}

.candidate-row.done {
	background-color: #f1f1f1;
	color: #888;
	font-style: italic;
}

.toggle-eval-btn {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 6px 12px;
	border-radius: 6px;
	cursor: pointer;
}

.toggle-eval-btn[disabled] {
	background-color: #ccc;
	cursor: not-allowed;
}

.eval-form-row {
	background-color: #fdfdfd;
}

.score-input {
	width: 80px;
	padding: 4px;
}

textarea {
	width: 100%;
	padding: 8px;
	border-radius: 6px;
	border: 1px solid #ccc;
}

.btn-submit {
	background-color: #28a745;
	color: white;
	padding: 6px 14px;
	border-radius: 6px;
	border: none;
}

.btn-cancel {
	background-color: #dc3545;
	color: white;
	padding: 6px 14px;
	border-radius: 6px;
	border: none;
	margin-left: 8px;
}

.badge {
	padding: 4px 8px;
	border-radius: 12px;
	font-size: 0.85rem;
	font-weight: 500;
}
</style>

<div class="card">
	<div class="card-header">
		<div class="header-title">
		<br>
			📋 <strong>${year}년</strong> <span class="highlight"> <c:choose>
					<c:when test="${half == '1'}">상반기</c:when>
					<c:otherwise>하반기</c:otherwise>
				</c:choose>
			</span> <span class="subtle">KPI 평가 대상자 목록</span>
		</div>
		<div class="header-desc">
			평가자: <strong>${name}</strong> (${empId}) / 대상자 <strong>${fn:length(kpiList)}</strong>명
		</div>
		<br>
		<br>


<table>
	<thead>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>부서</th>
			<th>팀</th>
			<th>직급</th>
			<th>상태</th>
			<th>평가</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="target" items="${kpiList}" varStatus="status">
			<tr class="candidate-row <c:if test='${target.evaluationStatus == "Y"}'>done</c:if>'">
				<td>${target.empId}</td>
				<td>${target.name}</td>
				<td>${target.departmentName}</td>
				<td>${target.teamName}</td>
				<td>${target.rankName}</td>
				<td>
					<c:choose>
						<c:when test='${target.evaluationStatus == "Y"}'>
							<span class="badge"	style="background-color: #28a745; color: white;">✔ 완료</span>
						</c:when>
					
						<c:otherwise>
							<span class="badge" style="background-color: #ffc107; color: black;">🕒 미완료</span>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<button class="toggle-eval-btn" data-emp-id="${target.empId}"
						<c:if test='${target.evaluationStatus == "Y"}'>disabled</c:if>>
						📝 평가하기</button>
				</td>
			</tr>

			<tr class="eval-form-row" id="eval-form-${target.empId}"
				style="display: none;">
				<td colspan="7">
					<form method="post" action="/evaluation/saveEvaluation">
						<input type="hidden" name="evaluationList[0].empId" value="${target.empId}" /> 
						<input type="hidden" name="evaluationList[0].evaluatorEmpId" value="${empId}" /> 
						<input type="hidden" name="evaluationList[0].evaluationYear" value="${year}" /> 
						<input type="hidden" name="evaluationList[0].halfYear" value="${half}" />

						<table>
							<thead>
								<tr>
									<th>항목명</th>
									<th>가중치</th>
									<th>점수</th>
									<th>의견</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${criteriaList}" varStatus="itemStat">
									<tr>
										<td>${item.evaluaCriteria}
											<input type="hidden" name="evaluationList[${itemStat.index}].evaluatypeId" value="${item.evaluaTypeId}" />
										</td>
										<td>${item.evaluaWeight}</td>
										<td>
											<input type="number" step="0.1" min="1.0" max="10.0" name="evaluationList[${itemStat.index}].evaluationScore"
												   class="score-input form-control-sm text-end" placeholder="1.0 ~ 10.0" required />
										</td>
										<td>
											<input type="text" name="evaluationList[${itemStat.index}].evaluationComments" 
												   class="form-control-sm" placeholder="의견을 입력하세요" style="width: 90%;" />
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>

						<div style="text-align: right; margin-top: 10px;">
							<label><strong>총점:</strong></label> <span class="total-score">0.0</span>점
							<input type="hidden" name="evaluationList[0].evaluationFinalScore" class="total-score-input"/>
						</div>

						<div style="margin-top: 10px;">
							<label>종합 의견</label><br />
							<textarea name="evaluationList[0].evaluationFinalComments" rows="3"></textarea>
						</div>
						
						<div style="text-align: right; margin-top: 10px;">
							<button type="submit" class="btn-submit">💾 저장</button>
							<button type="button" class="btn-cancel close-eval-btn">❌닫기</button>
						</div>
						
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</div>

<script>
	$(document).ready(function() {
		$('.toggle-eval-btn').click(function() {
			if ($(this).is('[disabled]'))
				return;
			const empId = $(this).data('emp-id');
			$('.eval-form-row').not('#eval-form-' + empId).slideUp();
			$('#eval-form-' + empId).slideToggle();
		});

		$('.close-eval-btn').click(function() {
			$(this).closest('tr').slideUp();
		});

		$(document).on('input', '.score-input', function() {
			const form = $(this).closest('form');
			let total = 0.0;
			form.find('.score-input').each(function() {
				const val = parseFloat($(this).val());
				if (!isNaN(val))
					total += val;
			});
			const fixed = total.toFixed(1);
			form.find('.total-score').text(fixed);
			form.find('.total-score-input').val(fixed);
		});

		$('form').submit(function() {
			$(this).find('.total-score-input').each(function() {
				let val = $(this).val().trim();
				if (val === '' || isNaN(parseFloat(val))) {
					$(this).val('0.0');
				}
			});
		});
	});
</script>
