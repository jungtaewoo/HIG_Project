/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 28.     		        LIG           		최초 생성
 *
 * </pre>
 */

document.addEventListener("DOMContentLoaded", function () {
	
	
    document.querySelector(".btn-outline-primary").addEventListener("click", function (e) {
        e.preventDefault(); // 기본 이벤트 방지

        let commentInput = document.querySelector("#commentForm");
        let deptnoticeId = this.getAttribute("data-deptnotice-id"); // 데이터 속성에서 가져오기
		
		console.log("============================================");
		console.log("deptnoticeId : ", this);
		console.log("commentInput : ", document.querySelector("#commentForm"));
		console.log("deptnoticeId : ", this.getAttribute("data-deptnotice-id"));
		console.log("style : ", this.getAttribute("style"));
		
		console.log(deptnoticeId);

        if (commentInput.value.trim() === "") {
            alert("댓글을 입력하세요.");
            return;
        }

        let commentData = {
            deptnoticeId: deptnoticeId,
            commentContent: commentInput.value.trim()
        };

		
        fetch("/insertComment", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(commentData)
        })
        .then(response => response.json())
        .then(data => {
			
            console.log("댓글 등록 성공", data);
			
    //    let newComment = document.createElement("tr");
    //    newComment.innerHTML = `<td colspan="3">${data.commentContent}<hr></td>`;
	//	
    //    document.querySelector("#body_comment").prepend(newComment); // 댓글 목록에 추가
	//	
	//	
	//	let newCommentInfo = document.createElement("tr");
	//	newCommentInfo.innerHTML = `
	//					<td >${data.name}&nbsp;${data.rank.rankName}</td>
	//					<td >${data.createAt}</td>
	//					<td>  <button class="btn btn-sm btn-outline-danger" data-comment-id="${data.commentId}" style="bottom: 120px;">삭제</button></td>
	//				`;
	//	document.querySelector("#body_comment").prepend(newCommentInfo); // 댓글 목록에 추가
   //    commentInput.value = ""; // 입력 필드 초기화
						
			
			window.location.reload();
			
        })
        .catch(error => {
            console.error("댓글 등록 실패", error);
            alert("댓글 등록 실패!");
        });
		
    });
});
