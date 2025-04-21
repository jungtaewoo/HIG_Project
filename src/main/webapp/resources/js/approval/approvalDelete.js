async function deleteTemplate() {
    const selectedIds = [];

    // 선택된 체크박스 찾기
    const checkboxes = document.querySelectorAll("input[type='checkbox']:checked");

    checkboxes.forEach(checkbox => {
        selectedIds.push(checkbox.value);
    });

    if (selectedIds.length === 0) {
        alert("삭제할 양식을 선택하세요.");
        return;
    }

    // 확인 창
    if (confirm("선택한 양식을 정말 삭제하시겠습니까?")) {
        try {
            // 비동기 요청 처리
            const response = await fetch('/approval/delete', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(selectedIds),
            });

            // 응답 데이터 확인
            const data = await response.text(); // 서버에서 받은 응답을 텍스트로 처리
            console.log("응답 데이터:", data); // 서버 응답 데이터 확인

            if (data === "삭제 완료") {
                alert("양식이 삭제되었습니다.");
                location.reload();  // 페이지 새로 고침
            } else {
                alert("양식이 삭제되었습니다.");
				location.reload();
            }
        } catch (error) {
            console.error('Error:', error);
            alert("삭제 요청 처리 중 오류가 발생했습니다.");
        }
    }
}
