/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 18.     		        LIG           		최초 생성
 *
 * </pre>
 */

async function deleteList() {
	const selectedIds = [];

	// 선택된 체크박스 찾기
	const checkboxes = document.querySelectorAll("input[type='checkbox']:checked");

	checkboxes.forEach(checkbox => {
		selectedIds.push(checkbox.value);
	});

	if (selectedIds.length === 0) {
		alert("삭제할 게시글을 선택하세요.");
		return;
	}

	try {
		// 비동기 요청 처리
		const response = await fetch('/departmentboard/delete', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(selectedIds),
		});
		

		// 응답 데이터 확인
		const data = await response.text(); // 서버에서 받은 응답을 텍스트로 처리
		
		console.log("=======================================================ingu2");
		console.log(data);
		
		
		console.log("응답 데이터:", data); // 서버 응답 데이터 확인

		if (data === "삭제 완료") {
			alert("게시글이 삭제되었습니다.");
			location.reload();  // 페이지 새로 고침
		} else {
			alert("게시글이 삭제되었습니다.");
			location.reload();
		}
	} catch (error) {
		console.error('Error:', error);
		alert("삭제 요청 처리 중 오류가 발생했습니다.");
	}
}