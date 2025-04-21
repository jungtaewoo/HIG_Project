<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 11.     	 KHS           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authentication property="principal" var="principal" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/css/style.css">

    <section class="section">
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
		        <li class="breadcrumb-item active" aria-current="page">
		        	<a href="${pageContext.request.contextPath }/board/list">공지사항</a>
		        </li>
		         <li class="breadcrumb-item active" aria-current="page">상세보기</li>
		      </ol>
		    </nav>

		  </div>
		</div>
        <div class="card">
<%--         ${board} --%>
<!--             <div class="card-header"> -->
<!--                 <h5 class="card-title">공지글 상세보기</h5> -->
<!--             </div> -->
            <div class="card-body">
            	<table class="table table-dark table-striped-columns table-bordered border-primary">
					<tr>
						<td>게시글 번호</td>
						<td>${board.noticeId}</td>
						<td>카테고리</td>
						<td>${board.category.categoryName }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${board.title}</td>


						<th>작성일</th>
						<td><fmt:formatDate value="${board.createdAt }"
								pattern="yyyy-MM-dd" /> <!-- 작성일자 --></td>

					</tr>
					<tr>
						<td>작성자</td>
						<td>${board.emp.name} ${board.role.roleName}</td>
<%-- 						<td>${board.role.roleName}</td> --%>

						<th>조회수</th>
						<td>${board.viewCount}</td>

					</tr>

				</table>


				<div class="card-body">
					<table class="table table-striped">
						<tr>
							<th>내용</th>
						</tr>
						<tr>
							<td>${board.content}</td>
						</tr>
					</table>
				</div>

				<div class="card-body">
					<table class="table table-striped">

                    <tr>
                    	<th>첨부파일</th>
                        <td>
                        	<c:forEach items="${board.fileDetails}" var="file">
								<img src="${pageContext.request.contextPath}/file/view/detail/${file.detailId}"
								 	alt="첨부 이미지"
                        				style="max-width: 500px; max-height: 500px; margin-right: 40px;">
                        				<c:set var="downloadUrl" value="${pageContext.request.contextPath}/file/download/${file.detailId}" />

			                	<div style="margin-bottom: 10px;">
			                    	<a href="${downloadUrl}" target="_blank" download>${file.fileName} (다운로드)</a>
			                    </div>
							</c:forEach>
                        </td>
                    </tr>
                </table>


                <a href="${pageContext.request.contextPath}/board/list" class="btn btn-secondary">목록으로</a>

			<security:authorize access="hasAnyRole('HR_MANAGER', 'HR_ADMIN')">
                <div class="button-container" style="display: flex; justify-content: flex-end; gap: 10px;">
	                <a href="${pageContext.request.contextPath}/board/update?noticeId=${board.noticeId}" class="btn btn-primary">수정</a>

	                <a href="${pageContext.request.contextPath}/board/delete?noticeId=${board.noticeId}" class="btn btn-danger">삭제</a>
				</div>
			</security:authorize>
            </div>
        </div>
	</div>
  </section>
<script>
        document.addEventListener('DOMContentLoaded', function(){
        	CKEDITOR.replace( 'contentEditor', {
    			versionCheck : false
    		} );
        })
</script>
