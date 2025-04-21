<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>




<section class="section">
	<form:form method="post" modelAttribute="vo"
		action="${pageContext.request.contextPath}/departmentboard/update/commit">

		<div class="card">
			<div class="card-header">
				<h5 class="card-title">게시글 수정</h5>
			</div>
			<div class="card-body">
				<table
					class="table table-dark table-striped-columns table-bordered border-primary">
					<c:if test="${not empty vo.deptnoticeId}">
						<input type="hidden" name="deptnoticeId"
							value="${vo.deptnoticeId}" />
					</c:if>
					<tr>
						<td>게시글 번호</td>
						<td>${vo.deptnoticeId}</td>
						<td>부서 이름</td>
						<td>${vo.department.departmentName }</td>
					</tr>
					<tr>
						<th>제목(수정 할 제목 입력)</th>
						<td>
							<input type="text" name="title" class="form-control" value="${vo.title}" />

						</td>


						<th>작성일</th>
						<td><fmt:formatDate value="${vo.noticeDate }"
								pattern="yyyy-MM-dd" /> <!-- 작성일자 --></td>

					</tr>
					<tr>
						<td>작성자</td>
						<td>${vo.author}</td>
						<th>조회수</th>
						<td>${vo.viewCount}</td>

					</tr>


				</table>
				<hr>
				<div class="card-body">
					<table class="table table-striped">
					<c:if test="${not empty vo.deptnoticeId}">
						<input type="hidden" name="deptnoticeId"
							value="${vo.deptnoticeId}" />
					</c:if>
						<tr>
							<th>내용(수정 할 내용 입력)</th>
						</tr>
						<tr>
							<td>
								<form:textarea path="content" class="form-control" id="contentEditor"/>
							</td>
						</tr>
					</table>
				</div>

				<div class="card-body">
					<table class="table table-striped">

						<tr>
							<th>첨부파일</th>
							<td>${vo.deptFile}</td>
						</tr>
					</table>
				</div>
				<div style="position: relative;">
					<div style="position: absolute; right: 80px; bottom: -22.9px;">
						<form action="${pageContext.request.contextPath}/departmentboard/update/commit" method="post">
								<input class="btn btn-primary" type="submit" value="수정">
						</form>
					</div>
				</div>
				<br>
				<div class="d-grid gap-2 d-md-block">
					<a href="${pageContext.request.contextPath}/departmentboard/list"
						class="btn btn-primary" style="position: absolute; right: 30px; bottom: 25px;">목록</a>
				</div>
			</div>
		</div>


	</form:form>

</section>

<script>
        document.addEventListener('DOMContentLoaded', function(){
        	CKEDITOR.replace( 'contentEditor', {
    			versionCheck : false
    		} );
        })
</script>
