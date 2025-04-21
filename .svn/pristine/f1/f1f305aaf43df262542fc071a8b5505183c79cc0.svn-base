<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 4. 1.     	CHOI            종결함 리스트 
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section class="section">
   <div class="card">
       <div class="card-header">
    		<h2 class="card-title">종결함</h2>
		</div>
		
		
		<div class="card-body">
            <!-- 상태 필터 select -->
            <div style="margin-bottom: 15px;">
                <label for="statusFilter">결재 상태 선택 </label>
                <select id="statusFilter" class="form-select" aria-label="Default select example">
                    <option value="all">전체</option>
                    <option value="반려">반려</option>
                    <option value="승인">승인</option>
                </select>
            </div>
			<!-- 데이터 테이블 -->
		    <table id="approverComplitedTable" class="table table-striped datatable">
		        <thead>
		            <tr>
		                <th><a href="#" class="dataTable-sorter">문서 ID</a></th>
		                <th><a href="#" class="dataTable-sorter">제목</a></th>
		                <th><a href="#" class="dataTable-sorter">기안일</a></th>
		                <th><a href="#" class="dataTable-sorter">상태</a></th>
		                <th><a href="#" class="dataTable-sorter">기안자</a></th>
		                <th><a href="#" class="dataTable-sorter">부서</a></th>
		                <th><a href="#" class="dataTable-sorter">결재자</a></th>
		                <th><a href="#" class="dataTable-sorter">결재 진행 상태</a></th>  
		                <th><a href="#" class="dataTable-sorter">첨부파일</a></th>
		            </tr>
		        </thead>
		        <tbody id="approverComplitedListContainer">
		            <tr>
		                <td colspan="10"></td>
		            </tr>
		        </tbody>
		    </table>
	 	</div>
	</div>
</section>

<!-- Simple DataTables 스크립트 -->
<script src="${pageContext.request.contextPath}/resources/js/approval/approverComplitedList.js"></script>
<script src="${pageContext.request.contextPath}/assets/extensions/simple-datatables/umd/simple-datatables.js"></script>
<script src="${pageContext.request.contextPath}/resources/template/dist/assets/static/js/pages/simple-datatables.js"></script>
