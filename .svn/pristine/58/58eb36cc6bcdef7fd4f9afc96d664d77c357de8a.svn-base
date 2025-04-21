<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<%-- ${empList } --%>
<%-- ${departmentList } --%>

<h2>퇴사자 등록</h2>
<form:form method="post" modelAttribute="resignation">

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>항목</th>
				<th>값</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
			    <td>부서</td>
			    <td>
			        <select name="newDepartmentId" id="newDepartmentId" class="form-control">
			            <option value="">부서를 선택하세요</option>
			            <c:forEach items="${departmentList}" var="x">
			                <option value="${x.departmentId}">${x.departmentName}</option>
			            </c:forEach>
			        </select>
			    </td>
			</tr>
			<tr>
			    <td>소속팀</td>
			    <td>
			        <select name="newTeamId" id="newTeamId" class="form-control">
			            <option value="">팀을 선택하세요</option>
			            <c:forEach items="${teamList}" var="x">
			                <option value="${x.teamId}">${x.teamName}</option>
			            </c:forEach>
			        </select>
			    </td>
			</tr>
			<tr>
				<td>직원 선택</td>
				<td><select id="empSelect" class="form-control">
						<option value="">직원을 선택하세요</option>
						<c:forEach items="${empList}" var="x">
							<option value="${x.empId}">${x.department.departmentName } ▶ ${x.name} ◀ (${x.empId})</option>
						</c:forEach>
				</select></td>
			</tr>
	</tbody>
	</table>			
</form:form>
			