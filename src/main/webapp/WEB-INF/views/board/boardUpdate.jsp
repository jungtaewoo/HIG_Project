<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 11.     	 KHS           최초 생성
 *  2025. 3. 21.     	 KHS	       ck에디터, 파일업로드 적용
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="post" modelAttribute="board" action="${pageContext.request.contextPath}/board/update/success"
    enctype="multipart/form-data">
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
			         <li class="breadcrumb-item active" aria-current="page">공지수정</li>
			      </ol>
			    </nav>

			  </div>
		</div>

    <h3>공지 수정</h3>
    <table class="table">
        <!-- noticeId가 null이 아닌 경우에만 숨겨진 필드에 noticeId 값을 설정 -->
        <c:if test="${not empty board.noticeId}">
            <input type="hidden" name="noticeId" value="${board.noticeId}" />
        </c:if>

        <tr>
            <th>제목</th>
            <td>
                <input type="text" name="title" class="form-control" value="${board.title}" />
            </td>
        </tr>

        <tr>
            <th>내용</th>
            <td>
                <!-- CKEditor 적용할 textarea -->
                <form:textarea path="content" class="form-control" id="contentEditor"/>
            </td>
        </tr>
        <tr>
           <th>공지 카테고리 (기본값: 기타)</th>
           <td>
		        <select name="categoryId" class="form-control" required>
				    <c:forEach var="category" items="${categoryList}">
				        <option value="${category.categoryId}" ${category.categoryId eq board.categoryId ? 'selected' : ''}>
				            ${category.categoryName}
				        </option>
				    </c:forEach>
				</select>

		    </td>
       </tr>
        <tr>
                <th>중요 공지 여부 (Y, N)</th>
			<td>
			    <!-- importance 값이 "Y"면 체크 상태 유지 -->
			    <input type="checkbox" id="importanceCheckbox"
			           ${noticeVO.importance eq 'Y' ? 'checked' : ''}
			           onchange="toggleImportance()" />

			    <!-- 체크박스 변경에 따라 값이 바뀌는 숨겨진 input -->
			    <input type="hidden" name="importance" id="importance"
			           value="${noticeVO.importance}" />
			</td>
            </tr>
            <th>파일 첨부</th>
            <td><input type="file" name="files" multiple class="form-control"></td>
        </tr>

        <tr>
		<td colspan="2">
		    <a href="${pageContext.request.contextPath}/board/detail?noticeId=${board.noticeId}" class="btn btn-secondary" style="vertical-align: middle;">상세보기로</a>
		    <div class="button-container" style="display: flex; justify-content: flex-end; gap: 10px;">
		        <button type="submit" class="btn btn-primary" style="vertical-align: middle;">수정완료</button>
		    </div>
		</td>

        </tr>
    </table>
</form:form>

<!-- CKEditor 적용 -->
<script>
    document.addEventListener('DOMContentLoaded', function(){
        CKEDITOR.replace('contentEditor', {
            versionCheck: false
        });
    });
    function toggleImportance() {
        let checkbox = document.getElementById("importanceCheckbox");
        let hiddenInput = document.getElementById("importance");
        hiddenInput.value = checkbox.checked ? "Y" : "N";
    }
</script>
