<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="page-heading">
	<div class="page-title">
		<div class="row">
			<div class="col-12 col-md-6 order-md-1 order-last">
				<h3>전체 결재 문서 조회</h3>
				<p class="text-subtitle text-muted">DB연결 테스트용</p>
			</div>
			<div class="col-12 col-md-6 order-md-2 order-first">
				<nav aria-label="breadcrumb"
					class="breadcrumb-header float-start float-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
						<li class="breadcrumb-item active" aria-current="page">DataTable</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<section class="section">
		<div class="card">
			<div class="card-header">Simple Datatable</div>
			<div class="card-body">
				<table class="table table-striped" id="table1">
					<thead>
						<tr>
							<th>draft_id</th>
							<th>e.name as 기안자명</th>
							<th>approver_id</th>
							<th>e2.name as 결재자명</th>
							<th>doc_title as 제목</th>
							<th>doc_status as 문서상태</th>
							<th>doc_category as 문서함유형</th>
							<th>doc_type as 문서유형</th>
							<th>last_update as 최종업데이트날짜</th>
							<th>create_date as 문서등록일</th>
							<th>doc_file as 첨부파일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty documentList }">
								<c:forEach items="${documentList }" var="document">
									<tr>
										<td>${document.draftId }</td>
										<td>${document.empName }</td>
										<td>${document.approverId }</td>
										<td>${document.approverName }</td>
										<td>${document.docTitle }</td>
										<%-- <td>${document.docStatus }</td> --%>
										<td>
											<c:choose>
												<c:when test="${document.docStatus == '반려' }">
													<button class="btn btn-danger">반려</button>
												</c:when>
												<c:when test="${document.docStatus == '보류' }">
													<button class="btn btn-warning">보류</button>
												</c:when>
												<c:otherwise>
													<!-- '승인' 상태일때는 일반텍스트로 표시 -->
													<span>승인</span>
												</c:otherwise>
											</c:choose>
										</td>
										<td>${document.docCategory }</td>
										<td>${document.docType }</td>
										<!-- 포맷팅 사용하기 위해 JSTL 태그 라이브러리를 선언, documentVO 에서 타입을 String -> Date 로 변환 -->
										<%-- <td>${document.lastUpdate }</td> --%>
										<td>
											<fmt:formatDate value="${document.lastUpdate }" pattern="yyyy년MM월dd일" />
										</td>
										<%-- <td>${document.createDate }</td> --%>
										<td>
											<fmt:formatDate value="${document.createDate }" pattern="yyyy년MM월dd일" />
										</td>
										<td>${document.docFile }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="10">문서가 존재하지 않습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</div>