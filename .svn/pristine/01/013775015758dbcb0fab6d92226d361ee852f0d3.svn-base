<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
<script type="text/javascript">
function toggleDeleteButton() {
	let myinputs = document.getElementsByClassName("myinputs");
	let deleteButton = document.getElementById('deleteButton');
	let myinput = document.getElementsByClassName("myinputall")[0];

    var sum_check = 0;
    
    for(let i=0;i<myinputs.length;i++){
    	if(myinputs[i].checked){
    		sum_check ++;
    	}
    }

    console.log(myinputs.length);
    console.log("sum_check",sum_check);
    
    if(sum_check == 0){
    	deleteButton.style.display = 'none';
    }else{
    	deleteButton.style.display = 'block';
    }
    
    if(sum_check==myinputs.length){
    	myinput.checked = true;
    }else{
    	myinput.checked = false;
    }
    
    
    
}


	function mychange(event){
		event.preventDefault();
		event.stopPropagation();
		let myinputs = document.getElementsByClassName("myinputs");
		let deleteButton = document.getElementById('deleteButton');
		let myinput = document.getElementsByClassName("myinputall")[0];

		console.log(myinputs);
		const obj = event.target;
		
		if(obj.checked){
			for(let i=0;i<myinputs.length;i++){
				myinputs[i].checked = true;
			}
			deleteButton.style.display = 'block';
		}else{
			for(let i=0;i<myinputs.length;i++){
				myinputs[i].checked = false;
			}
			deleteButton.style.display = 'none';
		}
	}

</script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
	<security:authentication property="principal" var="principal" /> 
<script src="${pageContext.request.contextPath}/resources/departmentboard/departmentboardDelete.js"></script>
	
		<div class="page-title">
		
			<div class="row">
				<div class="col-12 col-md-6 order-md-1 order-last">
					
				</div>
				
				<div class="col-12 col-md-6 order-md-2 order-first">
					<nav aria-label="breadcrumb"
						class="breadcrumb-header float-start float-lg-end">
						
					</nav>
				</div>
			</div>
		</div>
		<hr>
		 <div>
	      <button type="button" class="btn btn-outline-secondary" onclick="history.back();">
	        <i class="fas fa-arrow-left"></i> 
	      </button>
	    </div>
	    <br>
			<div class="card">
			<div class="card-header">
			<h3>부서 게시판 </h3> <h6> ${principal.realUser.department.departmentName}</h6>
					
					<br>
						<p class="text-subtitle text-muted"></p>
                </div>
			<div class="card-body">
				<!-- 중요 공지 섹션 -->
                    <c:if test="${not empty topFixedList}">
                        <div class="fixed-notice-section">
                            <h5 class="text-primary mb-3"><i class="fas fa-exclamation-circle"></i> 중요 공지</h5>
                            
                            	<div class="form-check">
							    	<input class="form-check-input myinputall" type="checkbox" onchange="mychange(event)" value="${list.deptnoticeId}" id="flexCheckDefault" >
							    	<label class="form-check-label" for="flexCheckDefault">전체선택</label>
								</div>
								<hr>
					
                            <table class="table table-hover fixed-notice-table">
                            
                                <tbody>
                                    <c:forEach items="${topFixedList}" var="fix">
                                    
                                        <tr data-importance="Y">
                                        
                                        
									
                                        <td width="9%">
											<div class="form-check">
											    <input class="form-check-input myinputs" type="checkbox" value="${fix.deptnoticeId}" id="flexCheckDefault" onclick="toggleDeleteButton()">
											    <label class="form-check-label" for="flexCheckDefault"></label>
											</div>
										</td>
                                           <td width="14%">
                                                <i class="fa fa-thumbtack text-primary"
                                                   ${fix.deptnoticeId}></i>
                                            </td>
                                           <td width="15%">${fix.department.departmentName}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/departmentboard/detail?deptnoticeId=${fix.deptnoticeId}">${fix.title }</a>
                                            </td>
                                            <td width="15%">${fix.author}</td>
                                            <td width="15%"><fmt:formatDate value="${fix.noticeDate}" pattern="yyyy-MM-dd"/></td>
                                            <td width="10%">${fix.viewCount}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="separator"></div>
                       
                    </c:if>
              
                    
                    
                    
                    
                    
		<!-- 일반 공지 섹션 -->
		<section class="section">
			<div class="card">
			
				
				<div class="card-body">
					<div class="dataTable-wrapper dataTable-loading no-footer sortable searchable fixed-columns">
										
                    	<div class="dataTable-container">
           					
						
						<table class="table table-striped" id="table1">
							
							<thead>
								<tr>
								
									<th data-sortable="false">
										&nbsp;								
									</th>
									<th data-sortable=""><a href="#" class="dataTable-sorter">게시글 번호</a></th>
									<th data-sortable=""><a href="#" class="dataTable-sorter">부서</a></th>
									<th data-sortable=""><a href="#" class="dataTable-sorter">제목</a></th>
									<th data-sortable=""><a href="#" class="dataTable-sorter">작성자</a></th>
									<th data-sortable=""><a href="#" class="dataTable-sorter">작성일시</a></th>
									<th data-sortable=""><a href="#" class="dataTable-sorter">조회수</a></th>
								</tr>
							</thead>
							
							
							
							
							<!-- 일반 게시물 -->
							<tbody>
								<c:forEach items="${departmentboardList}" var="list">
									<tr>
										<td>
											<div class="form-check">
											    <input class="form-check-input myinputs" type="checkbox" value="${list.deptnoticeId}" id="flexCheckDefault" onclick="toggleDeleteButton()">
											    <label class="form-check-label" for="flexCheckDefault"></label>
											</div>
										</td>
										
										<td>${list.deptnoticeId}</td>
										<!-- 게시글 번호 -->
										
										<td>${list.department.departmentName }</td>
										<!-- 부서 이름 -->
										
	
	
										<td><a
											href="${pageContext.request.contextPath}/departmentboard/detail?deptnoticeId=${list.deptnoticeId}">${list.title }</a>
										</td>
										<!-- 제목 -->
	
										<td>${list.author }</td>
										<!-- 작성자 -->
	
										<td><fmt:formatDate value="${list.noticeDate }"
												pattern="yyyy-MM-dd" /> <!-- 작성일자 --></td>
										<td>${list.viewCount }</td>
										<!-- 조회수 -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						
					
						
					
						
						<!-- 체크박스 클릭하면 나오는 버튼 -->
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal" id="deleteButton" style="display: none; position: absolute; right: 160px; bottom: -33px;">삭제</button>
						
						<!-- 삭제 버튼을 클릭하면 자동으로 띄워지는 모달창 -->
						<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">게시글 삭제</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <p>정말 삭제하시겠습니까?
						           삭제된 게시물은 복구되지 않습니다.</p>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
						        <button type="button" class="btn btn-primary" onclick="deleteList()">삭제</button>
						      </div>
						    </div>
						  </div>
						</div>
						</div>
						</div>
					
					
				</div>
				
			</div>
					<div style="position: absolute; right: 30px; bottom: 25px; margin-top: 20px;">
							<form action="${pageContext.request.contextPath}/departmentboard/insert" method="get">
									<input class="btn btn-primary" type="submit" value="게시글 작성">
							</form>
						</div>
		</section>
				
	</div>
</div>
