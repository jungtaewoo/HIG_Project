document.addEventListener("DOMContentLoaded", () => {
    const myModalEl = document.getElementById("exampleModal");
    const modalBody = myModalEl.querySelector(".modal-body");

    myModalEl.addEventListener("show.bs.modal", event => {
        const button = event.relatedTarget;
        const accountId = button.getAttribute("data-account-id");
        const mode = button.getAttribute("data-mode");

        modalBody.innerHTML = "<p>로딩 중...</p>";

        if (mode === "detail") {
            // 상세보기 요청
            fetch(`/account/read/accountId/${accountId}`)
                .then(response => response.text())
                .then(html => {
                    modalBody.innerHTML = html;
                    setupEditMode(accountId); // 수정 버튼 이벤트 설정
                })
                .catch(error => {
                    modalBody.innerHTML = "<p>데이터를 불러오는 중 오류 발생</p>";
                    console.error("Error loading account details:", error);
                });
        }
    });
});
