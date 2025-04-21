 document.addEventListener("DOMContentLoaded", function() {
    // ID가 "commentDel"인 요소 가져오기
    const commentDel = document.querySelector("#commentDel");
	console.log(commentDel);

    // 요소가 존재할 때만 실행
    if (commentDel) {
        commentDel.addEventListener("click", async function (event) {
            if (event.target.classList.contains("btn-outline-danger")) {
                console.log("클릭된 버튼:", event.target.outerHTML); // 버튼 HTML 확인
                
                const commentId = event.target.getAttribute("data-comment-id");

                console.log("삭제 버튼 클릭됨, commentId:", commentId);

                if (!commentId) {
                    alert("댓글 ID를 찾을 수 없습니다.");
                    return;
                }

                if (!confirm("해당 댓글을 삭제하시겠습니까?")) {
                    return;
                }

                try {
                    const response = await fetch('/dept/delete', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify({ commentId }),
                    });

                    const data = await response.text();
                    console.log("서버 응답:", data);

                    if (data === "삭제 완료") {
                        alert("댓글이 삭제되었습니다.");
                        
                        // 삭제 버튼이 포함된 가장 가까운 <tr>을 찾아서 제거
                        const commentRow = event.target.closest("tr");
                        if (commentRow) {
                            commentRow.remove();
                        }
                    } else {
                        alert("댓글 삭제에 실패했습니다.");
                    }
                } catch (error) {
                    console.error('삭제 오류:', error);
                    alert("댓글 삭제 중 오류가 발생했습니다.");
                }
            }
        });
    } else {
        console.log("삭제 버튼 컨테이너(#commentDel)가 존재하지 않습니다. 이벤트 리스너를 등록하지 않음.");
    }
});


function fn_del(){
	console.log("88888888888888888888888888");
}


console.log("im first");



document.addEventListener("DOMContentLoaded", function() {
	console.log("im second");
	const commentId = event.target.getAttribute("data-comment-id");
}


