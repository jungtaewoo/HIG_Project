<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<style>
body {
	padding: 40px;
	background-color: #f0f2f5;
	font-family: 'Segoe UI', 'Malgun Gothic', sans-serif;
}

h2 {
	margin-bottom: 25px;
	font-weight: 600;
}

.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	padding: 12px 8px;
	font-size: 16px;
	line-height: 1.6;
}

.table thead th {
	background-color: #eff6ff;
	font-weight: 600;
	color: #0d47a1;
	border-top: 2px solid #c6dafc;
}

.table-hover tbody tr:hover {
	background-color: #e3f2fd;
}

.table-bordered td, .table-bordered th {
	border: 1px solid #ced4da;
}

.table-primary {
	background-color: #e0ecff !important;
}

.table-warning {
	background-color: #fff7e6 !important;
}

.form-control {
	font-size: 15px;
}

input[readonly], .form-control[readonly] {
	background-color: #f1f3f5 !important;
}

select:focus, input:focus {
	border-color: #86b7fe;
	box-shadow: 0 0 0 0.15rem rgba(13, 110, 253, 0.25);
}

.btn+.btn {
	margin-left: 8px;
}

.text-danger {
	font-size: 0.85em;
	margin-top: 4px;
	display: block;
}
</style>

<div class="container-fluid">
	<div class="card shadow-sm">
		<div class="card-body">
			<h2>
				<i class="bi bi-pencil-square me-2"></i>인사발령 등록
			</h2>
			<form:form method="post" modelAttribute="appointment">
				<div class="d-flex justify-content-end mb-3">
					<button type="button" id="addRow" class="btn btn-outline-secondary">
						<i class="bi bi-plus-lg me-1"></i> 행 추가
					</button>
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-check-circle me-1"></i> 등록
					</button>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-hover align-middle"
						id="appointmentTable">
						<thead>
							<tr>
								<th colspan="4">직원정보</th>
								<th colspan="4" class="table-primary">현재</th>
								<th colspan="8" class="table-warning">발령정보</th>
							</tr>
							<tr>
								<th>직원 선택</th>
								<th>사번</th>
								<th>이름</th>
								<th>부서</th>
								<th>팀</th>
								<th>직급</th>
								<th>직책</th>
								<th>직무</th>
								<th>발령일자</th>
								<th>발령구분</th>
								<th>발령사유</th>
								<th>발령 부서</th>
								<th>발령 팀</th>
								<th>발령 직급</th>
								<th>발령 직책</th>
								<th>발령 직무</th>
							</tr>
						</thead>
						<tbody id="appointmentRows">
							<tr>
								<td>
									<select name="appointmentList[0].empId" class="form-control empSelect">
										<option value="">직원을 선택하세요</option>
										<c:forEach items="${empList}" var="x">
											<option value="${x.empId}">${x.department.departmentName} ▶ ${x.name} ◀ (${x.empId})</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="text" class="form-control empId" readonly>
								</td>
								<td>
									<input type="text" class="form-control name" name="appointmentList[0].name" readonly>
								</td>
								<td>
									<input type="text" class="form-control departmentName" readonly> 
									<input type="hidden" class="prevDepartmentId" name="appointmentList[0].prevDepartmentId">
								</td>
								<td>
									<input type="text" class="form-control teamName" readonly> 
									<input type="hidden" class="prevTeamId" name="appointmentList[0].prevTeamId">
								</td>
								<td>
									<input type="text" class="form-control rankName" readonly> 
									<input type="hidden" class="prevRankId" name="appointmentList[0].prevRankId">
								</td>
								<td>
									<input type="text" class="form-control jobName" readonly>
									<input type="hidden" class="prevJobId" name="appointmentList[0].prevJobId">
								</td>
								<td>
									<input type="text" class="form-control positionName" readonly>
									<input type="hidden" class="prevPositionId" name="appointmentList[0].prevPositionId">
								</td>
								<td>
									<input type="date" class="form-control appDate" name="appointmentList[0].appDate"> 
									<form:errors path="appointmentList[0].appDate" cssClass="text-danger" />
								</td>
								<td>
									<input type="text" class="form-control" name="appointmentList[0].appType">
									<form:errors path="appointmentList[0].appType" cssClass="text-danger" />
								</td>
								<td>
									<input type="text" class="form-control"	name="appointmentList[0].appReason"> 
									<form:errors path="appointmentList[0].appReason" cssClass="text-danger" />
								</td>
								<td>
									<select name="appointmentList[0].newDepartmentId" class="form-control">
										<option value="">부서를 선택하세요</option>
										<c:forEach items="${departmentList}" var="x">
											<option value="${x.departmentId}">${x.departmentName}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select name="appointmentList[0].newTeamId"	class="form-control">
										<option value="">팀을 선택하세요</option>
										<c:forEach items="${teamList}" var="x">
											<option value="${x.teamId}">${x.teamName}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select name="appointmentList[0].newRankId"	class="form-control">
										<option value="">직급을 선택하세요</option>
										<c:forEach items="${rankList}" var="x">
											<option value="${x.rankId}">${x.rankName}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select name="appointmentList[0].newJobId" class="form-control">
										<option value="">직책을 선택하세요</option>
										<c:forEach items="${jobList}" var="x">
											<option value="${x.jobId}">${x.jobName}</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select name="appointmentList[0].newPositionId" class="form-control">
										<option value="">직무를 선택하세요</option>
										<c:forEach items="${positionList}" var="x">
											<option value="${x.positionId}">${x.positionName}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form:form>
		</div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(document).ready(function () {
	    const employeeInfoUrl = "/employee/appointFormUI/getEmployeeInfo";
	    const today = new Date().toISOString().split('T')[0];
	
	    // 🔄 직원 선택 시 정보 자동 채우기
	    $(document).on("change", ".empSelect", function () {
	        const empId = $(this).val();
	        const row = $(this).closest("tr");
	
	        if (empId) {
	            $.ajax({
	                url: employeeInfoUrl,
	                type: "GET",
	                data: { empId },
	                dataType: "json",
	                success: function (data) {
	                    if (data) {
	                        row.find(".empId").val(data.empId);
	                        row.find(".name").val(data.name);
	                        row.find(".departmentName").val(data.department.departmentName);
	                        row.find(".prevDepartmentId").val(data.department.departmentId);
	                        row.find(".teamName").val(data.team.teamName);
	                        row.find(".prevTeamId").val(data.team.teamId);
	                        row.find(".rankName").val(data.rank.rankName);
	                        row.find(".prevRankId").val(data.rank.rankId);
	                        row.find(".jobName").val(data.job.jobName);
	                        row.find(".prevJobId").val(data.job.jobId);
	                        row.find(".positionName").val(data.position.positionName);
	                        row.find(".prevPositionId").val(data.position.positionId);
	                    }
	                },
	                error: function () {
	                    alert("❗직원 정보를 불러오지 못했습니다.");
	                    row.find("input").val("");
	                    row.find("select").prop("selectedIndex", 0);
	                }
	            });
	        } else {
	            row.find("input").val("");
	        }
	    });
	
	    // ➕ 행 추가 버튼 클릭 시
	    $("#addRow").click(function () {
	        const lastIndex = $("#appointmentRows tr").length;
	        const newRow = $("#appointmentRows tr:first").clone();
	
	        newRow.find("input, select").each(function () {
	            const nameAttr = $(this).attr("name");
	            if (nameAttr) {
	                const newName = nameAttr.replace(/\[\d+\]/, "[" + lastIndex + "]");
	                $(this).attr("name", newName);
	            }
	
	            if ($(this).is("input")) {
	                if ($(this).attr("type") === "date") {
	                    $(this).val(today);
	                } else {
	                    $(this).val("");
	                }
	            } else if ($(this).is("select")) {
	                $(this).prop("selectedIndex", 0);
	            }
	        });
	
	        // 삭제 버튼 재부착
	        newRow.find(".removeRow").remove();
	        newRow.append('<td><button type="button" class="btn btn-danger removeRow">✕</button></td>');
	
	        $("#appointmentRows").append(newRow);
	
	        // 포커스 자동 이동
	        newRow.find(".empSelect").focus();
	    });
	
	    // ❌ 행 삭제
	    $(document).on("click", ".removeRow", function () {
	        const rowCount = $("#appointmentRows tr").length;
	        if (rowCount > 1) {
	            $(this).closest("tr").remove();
	        } else {
	            alert("⚠️ 최소 한 개의 행은 남아있어야 합니다.");
	        }
	    });
	
	    // 최초 행 날짜 자동 세팅
	    $(".appDate").val(today);
	});
    </script>