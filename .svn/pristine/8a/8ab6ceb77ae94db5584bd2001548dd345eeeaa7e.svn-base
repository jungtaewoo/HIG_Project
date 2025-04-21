<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
		/* #body_comment tr {
	    border-bottom: 1px solid #ddd;  /* 회색 줄 */
	    width: 100%;
	} */
	
		 #body_comment td {
	    padding: 13px 80px; /* 위아래 10px, 좌우 15px 패딩 추가 */
	} 
	
	#tableWidth {
		width: 100%;
	}
</style>
<hr>
	<section class="section">
		<div class="card">
			<div class="card-header">
				<h5 class="card-title">게시글 상세 조회</h5>
			</div>
			
			
			<div class="card-body">
				<table class="table table-dark table-striped-columns table-bordered border-primary">
					<tr>
						<td>게시글 번호</td>
						<td>${vo.deptnoticeId}</td>
						<td>부서 이름</td>
						<td>${vo.department.departmentName }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${vo.title}</td>


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
						<tr>
							<th>내용</th>
						</tr>
						<tr>
							<td><pre>${vo.content}</pre></td>
						</tr>
					</table>
				</div>

				<div class="card-body">
					<table class="table table-striped">
					
					
						<tr>
							<th>첨부파일</th>
							<td>
								<c:forEach items="${vo.fileDetails}" var="file">
									<img src="${pageContext.request.contextPath}/file/view/${file.fileSavename}"
									 	alt="첨부 이미지"
                         				style="max-width: 800px; max-height: 800px; margin-right: 40px;">
                         				<c:set var="downloadUrl" value="${pageContext.request.contextPath}/file/download/${file.detailId}" />

				                	<div style="margin-bottom: 10px;">
				                    	<a href="${downloadUrl}" target="_blank" download>${file.fileName} (다운로드)</a>
				                    </div>
								</c:forEach>
								 
							</td>
						</tr>
					
					
						</table>
						
					</div>
					
					<div class="card-body">
						<!-- 댓글 전체 리스트 섹션 -->
						<hr>
						<br>
						<h5>댓글</h5>
						<hr>
						<table id="tableWidth">
							<thead>
								
							</thead>
							<tbody id="body_comment">
								
							</tbody>
							
							<tbody id="body_insert">
								<tr>
									<td>
										<input type="text" id="commentForm" placeholder="댓글을 입력하세요." style="width: 75%; row:4px;"/>
									</td>
									<td>
										<button class="btn btn-sm btn-outline-primary" data-deptnotice-id="${vo.deptnoticeId}" style="position: absolute; right: 300px; bottom: 110px;">
							                댓글등록
							            </button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				<div>
						<div>
							<a class="btn btn-primary" href="${pageContext.request.contextPath}/departmentboard/update?deptnoticeId=${vo.deptnoticeId}" style="position: absolute; right: 30px; bottom: 25px;">게시글 수정</a>
						</div>
					<br> <a
						href="${pageContext.request.contextPath}/departmentboard/list"
						class="btn btn-primary">목록으로</a>
				</div>
			</div>
			</div>
	</section>
	<!-- 게시글 상세 섹션 -->
	
	
	<script src="${pageContext.request.contextPath}/resources/js/deptcomment/deptCommentForm.js"></script>
	
	<script>

	


		fetch("/deptcomment/form?deptnoticeId=${vo.deptnoticeId}")
			.then(response => {
				if (!response.ok) {
					throw new Error('Network response was not ok');
				}
				return response.json();
			})
			.then(data => {
				console.log(data);

				// DeptCommentVO가 배열인지 확인 후 반복문 실행
				if (Array.isArray(data.DeptCommentVO)) {
					let row = ""; // 테이블 행을 담을 변수

					data.DeptCommentVO.forEach(item => {
						console.log(item); // 각 항목 로그 출력

						// 날짜 포맷 변경
						let formattedDate = "-"; // 기본값 설정
						if (item.createAt) {
							const date = new Date(item.createAt);
							const yyyy = date.getFullYear();
							const mm = String(date.getMonth() + 1).padStart(2, "0"); // 월 (0부터 시작하므로 +1)
							const dd = String(date.getDate()).padStart(2, "0"); // 일
							const hh = String(date.getHours()).padStart(2, "0"); // 시 (00~23)
							const min = String(date.getMinutes()).padStart(2, "0"); // 분 (00~59)
							const ss = String(date.getSeconds()).padStart(2, "0"); // 초 (00~59)
							formattedDate = `\${yyyy}-\${mm}-\${dd} \${hh}:\${min}:\${ss}`;
						}

						// 테이블 행 추가
						row += `
							<tr>
								<td>\${item.name || '-'}&nbsp;\${item.rank.rankName}</td>
								<td>\${formattedDate || '-'}</td>
								<td>  
									<button id="commentDel" onclick="fn_del(event)" class="btn btn-sm btn-outline-danger" data-comment-id="\${item.commentId}" style="bottom: 120px;">
									삭제
									</button> 
								</td>
							</tr>
							
							<tr>
								<td colspan="3">
									\${item.commentContent || '-'}
									<hr>
								</td>

							</tr>
						
						`;

					});

					// 테이블에 데이터 삽입
					document.querySelector("#body_comment").innerHTML = row;
// 					delFunc();
				} else {
					console.error("DeptCommentVO is not an array:", data.DeptCommentVO);
				}
			})
			.catch(error => {
				console.error('There was a problem with the fetch operation:', error);
			});

	function fn_del(event){
		const commentId = event.target.getAttribute("data-comment-id");
// 		const response = await fetch('/dept/delete', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify({ commentId }),
//         });

		fetch('/dept/delete', {
		    method: 'POST',
		    headers: {
		        'Content-Type': 'application/json'
		    },
		    body: JSON.stringify({ commentId }) // 예시
		})
		.then(response => {
		    if (!response.ok) {
		        throw new Error('Network response was not ok');
		    }
		    return response.json();
		})
		.then(data => {
		    window.location.reload();
		})
		.catch(error => {
		    console.error('There was a problem with the fetch operation:', error);
		});
			
	}


	</script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/js/deptcomment/deptCommentDel.js"></script> --%>
