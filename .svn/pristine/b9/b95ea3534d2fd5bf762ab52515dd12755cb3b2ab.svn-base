<!--
 * == 개정이력(Modification Information) ==
 *
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 11.     	 KHS           최초 생성
 *  2025. 3. 21.     	 KHS	       ck에디터, 파일업로드 적용
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <form:form method="post" modelAttribute="board" action="${pageContext.request.contextPath}/board/register/commit"
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
		         <li class="breadcrumb-item active" aria-current="page">공지작성</li>
		      </ol>
		    </nav>

		  </div>
		</div>

        <h3>공지 등록</h3>
        <table class="table">
            <tr>
                <th>공지사항 제목 (1~30글자 제한)</th>
                <td><form:input path="title" class="form-control" /></td>
            </tr>
            <tr>
                <th>공지사항 내용 (1~200자 제한)</th>
                <td><form:textarea path="content" class="form-control" id="contentEditor" /></td>
            </tr>
            <tr>
			    <th>카테고리</th>
			    <td>
			        <select name="categoryId" class="form-control" required>
			            <c:forEach var="category" items="${categoryList}">
			                <option value="${category.categoryId}">
			                    ${category.categoryName}
			                </option>
			            </c:forEach>
			        </select>
			    </td>
			</tr>
            <tr>
                <th>중요 공지 여부</th>
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
            <tr>
	            <th>파일 첨부</th>
	            <td><input type="file" name="files" multiple class="form-control"></td>
	       </tr>
<!--             <tr> -->
<!--                 <th>파일 첨부</th> -->
<%--                 <td><form:input path="noticeFile" type="file" class="form-control" /></td> --%>
<!--             </tr> -->
        </table>

<%-- 		<a href="${pageContext.request.contextPath}/board/list" class="btn btn-secondary">목록으로</a> --%>

		<div class="button-container" style="display: flex; justify-content: flex-end; gap: 10px;">
<!-- 	        <button type="submit" class="btn btn-primary">등록</button> -->
			<button id="success" class="btn btn-primary ">등록</button>
	        <a href="${pageContext.request.contextPath}/board/list" class="btn btn-danger">취소</a>
        </div>


    </form:form>

    <script>
    document.getElementById("success").addEventListener("click", function(event) {
        event.preventDefault();  // 기본 제출 막기

        Swal.fire({
            title: "등록하시겠습니까?",
            text: "입력한 내용으로 공지를 등록합니다.",
            icon: "warning",
            showCancelButton: true,  // 취소 버튼 추가
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "등록",
            cancelButtonText: "취소"
        }).then((result) => {
            if (result.isConfirmed) {
                document.querySelector("form").submit(); // 폼 제출 실행
            }
        });
    });



	    document.addEventListener('DOMContentLoaded', function(){
	    	CKEDITOR.replace( 'contentEditor', {
				versionCheck : false
			} );
	    })
        function toggleImportance() {
	        let checkbox = document.getElementById("importanceCheckbox");
	        let hiddenInput = document.getElementById("importance");
	        hiddenInput.value = checkbox.checked ? "Y" : "N";
	    }
    </script>
