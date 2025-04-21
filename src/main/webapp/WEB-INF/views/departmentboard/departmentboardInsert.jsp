<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- CKEditor CDN -->
    
<form:form method="POST" modelAttribute="insert"
	action="${pageContext.request.contextPath}/departmentboard/insert/commit"
	enctype="multipart/form-data">
	<table class="table">
		<tr>
			<th>
				<div class="form-check">
				    <input class="form-check-input topFixed" type="checkbox" id="flexCheckDefault" name="importance" onchange="topFixedChkbox(this)" value="Y">
				    <label class="form-check-label" for="flexCheckDefault">상단 고정</label>
				</div>
			</th>
		</tr>

		<tr>
			<th>게시글 제목 (1~30글자 제한)</th>
			<td><form:input path="title" class="form-control" /></td>
		</tr>

		<tr>
			<th>게시글 내용 (1~200자 제한)</th>
			<td><form:textarea path="content" class="form-control"
					id="contentEditor" /></td>
		</tr>
		
		<!-- 파일 첨부 -->
        <tr>
            <th>파일 첨부</th>
            <td><input type="file" name="files" multiple class="form-control"></td>
       </tr>
	</table>

	<div>
		<button type="submit" class="btn btn-primary" style="position: absolute; right: 120px; bottom: 250px;">등록</button>
	</div>
	</form:form>
	
	<br>
	<div>
		<a href="${pageContext.request.contextPath}/departmentboard/list"
			class="btn btn-primary" style="position: absolute; right: 55px; bottom: 250px;">취소</a>
	</div>

<script>
        document.addEventListener('DOMContentLoaded', function(){
        	CKEDITOR.replace( 'contentEditor', {
    			versionCheck : false
    		} );
        })
        // 체크박스가 선택되면 Y, 아니면 N을 반환
        let checkboxValue = $(".topFixed").is(":checked") ? "Y" : "N";
        
        console.log("12",checkboxValue);
        
        function topFixedChkbox(obj){
        	let topFixed = document.getElementsByClassName("topFixed");
        	 console.log("12",topFixed);
        }

        
  
</script>
       