/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 13.     	youngjun            최초 생성
 *
 * </pre>
 */

document.addEventListener("DOMContentLoaded", () => {
    const myModalEl = document.getElementById("exampleModal");

    myModalEl.addEventListener("show.bs.modal", event => {
        const button = event.relatedTarget; // 클릭한 요소 (a 태그)
        const modalBody = myModalEl.querySelector(".modal-body");

        // 기존 내용 초기화
        modalBody.innerHTML = "<p>로딩 중...</p>";

        // AJAX 요청 실행
        fetch(`/unAccount/list`)
            .then(response => response.text())
            .then(html => {
                modalBody.innerHTML = html; // 모달에 내용 삽입
            })
            .catch(error => {
                modalBody.innerHTML = "<p>데이터를 불러오는 중 오류 발생</p>";
                console.error("Error loading unAccountList:", error);
            });
    });
});
