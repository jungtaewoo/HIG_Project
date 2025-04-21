<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>평가 대상자 조회 및 등록</h2>

<form id="evaluationForm" action="EvaluationCandidateListController" method="post">
    <button type="submit" id="saveBtn">저장</button>

    <!-- 평가년도 일괄 변경 -->
    <div>
        <button type="button" id="bulkUpdateYearBtn">평가년도 일괄 변경</button>
    </div>

    <!-- 구분 일괄 변경 -->
    <div>
        <button type="button" id="bulkUpdateHalfYearBtn">구분 일괄 변경</button>
    </div>

    <!-- 평가대상 일괄 변경 -->
    <div>
        <button type="button" id="bulkUpdateIsTargetBtn">평가대상 일괄 변경</button>
    </div>

    <!-- 평가여부 일괄 변경 -->
    <div>
        <button type="button" id="bulkUpdateEvaluationStatusBtn">평가여부 일괄 변경</button>
    </div>

    <table border="1" cellspacing="0" cellpadding="5">
        <thead>
            <tr>
                <th>사번</th>
                <th>이름</th>
                <th>부서</th>
                <th>팀</th>
                <th>직급</th>
                <th>평가년도</th>
                <th>구분</th>
                <th>평가대상</th>
                <th>평가여부</th>
                <th><input type="checkbox" id="selectAll"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="x" items="${candidateList}">
                <tr>
                    <td>${x.empId}</td>
                    <td>${x.employee.name}</td>
                    <td>${x.department != null && x.department.departmentName != null ? x.department.departmentName : ''}</td>
                    <td>${x.team != null && x.team.teamName != null ? x.team.teamName : ''}</td>
                    <td>${x.rank != null && x.rank.rankName != null ? x.rank.rankName : ''}</td>

                    <!-- 평가년도 선택 -->
                    <td>
                        <select class="evaluationYear" name="evaluationYear_${x.empId}">
                            <c:forEach var="year" begin="2020" end="2030">
                                <option value="${year}" ${x.evaluationYear != null && x.evaluationYear == year ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <!-- 상반기/하반기 선택 -->
                    <td>
                        <select class="halfYear" name="halfYear_${x.empId}">
                            <option value="1" ${x.halfYear != null && x.halfYear == '1' ? 'selected' : ''}>상반기</option>
                            <option value="2" ${x.halfYear != null && x.halfYear == '2' ? 'selected' : ''}>하반기</option>
                        </select>
                    </td>

                    <!-- 평가 대상 여부 선택 -->
                    <td>
                        <select class="isTarget" name="isTarget_${x.empId}">
                            <option value="Y" ${x.isTarget != null && x.isTarget == 'Y' ? 'selected' : ''}>Y</option>
                            <option value="N" ${x.isTarget != null && x.isTarget == 'N' ? 'selected' : ''}>N</option>
                        </select>
                    </td>

                    <!-- 평가 여부 선택 -->
                    <td>
                        <select class="evaluationStatus" name="evaluationStatus_${x.empId}">
                            <option value="Y" ${x.evaluationStatus != null && x.evaluationStatus == 'Y' ? 'selected' : ''}>Y</option>
                            <option value="N" ${x.evaluationStatus != null && x.evaluationStatus == 'N' ? 'selected' : ''}>N</option>
                        </select>
                    </td>

                    <!-- 체크박스 -->
                    <td>
                        <input type="checkbox" class="selectEmployee" name="selectedEmpIds" value="${x.empId}">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>

<script>
    document.getElementById("selectAll").addEventListener("change", function() {
        let checkboxes = document.querySelectorAll(".selectEmployee");
        checkboxes.forEach(checkbox => checkbox.checked = this.checked);
    });

    document.getElementById("bulkUpdateYearBtn").addEventListener("click", function() {
        let evaluationYear = prompt("평가년도를 입력해주세요 (2020 ~ 2030):");

        if (!evaluationYear || isNaN(evaluationYear) || evaluationYear < 2020 || evaluationYear > 2030) {
            alert("유효한 평가년도(2020 ~ 2030)를 입력해주세요.");
            return;
        }

        // 모든 선택된 항목에 평가년도 일괄 적용
        document.querySelectorAll(".evaluationYear").forEach(select => select.value = evaluationYear);
    });

    document.getElementById("bulkUpdateHalfYearBtn").addEventListener("click", function() {
        let halfYear = prompt("구분을 선택해주세요 (상반기/하반기):");

        if (halfYear !== "1" && halfYear !== "2") {
            alert("상반기 또는 하반기를 입력해주세요.");
            return;
        }

        // 모든 선택된 항목에 구분 일괄 적용
        document.querySelectorAll(".halfYear").forEach(select => select.value = halfYear);
    });

    document.getElementById("bulkUpdateIsTargetBtn").addEventListener("click", function() {
        let isTarget = prompt("평가대상 Y 또는 N을 선택해주세요:");

        if (isTarget !== "Y" && isTarget !== "N") {
            alert("Y 또는 N만 입력 가능합니다.");
            return;
        }

        // 모든 선택된 항목에 평가대상 일괄 적용
        document.querySelectorAll(".isTarget").forEach(select => select.value = isTarget);
    });

    document.getElementById("bulkUpdateEvaluationStatusBtn").addEventListener("click", function() {
        let evaluationStatus = prompt("평가여부 Y 또는 N을 선택해주세요:");

        if (evaluationStatus !== "Y" && evaluationStatus !== "N") {
            alert("Y 또는 N만 입력 가능합니다.");
            return;
        }

        // 모든 선택된 항목에 평가여부 일괄 적용
        document.querySelectorAll(".evaluationStatus").forEach(select => select.value = evaluationStatus);
    });

    // 폼 제출 전에 대소문자 변경 처리
    document.getElementById("evaluationForm").addEventListener("submit", function(event) {
        // 평가대상과 평가여부 값을 대문자로 변환
        document.querySelectorAll(".isTarget").forEach(select => {
            select.value = select.value.toUpperCase();
        });
        document.querySelectorAll(".evaluationStatus").forEach(select => {
            select.value = select.value.toUpperCase();
        });

        let selectedEmployees = document.querySelectorAll(".selectEmployee:checked");

        if (selectedEmployees.length === 0) {
            alert("선택된 직원이 없습니다.");
            event.preventDefault();
        }
    });
</script>
