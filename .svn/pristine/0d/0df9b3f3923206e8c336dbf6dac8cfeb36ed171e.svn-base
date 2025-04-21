document.addEventListener("DOMContentLoaded", () => {
  console.log(" accountFind.js 로딩됨");

  const myModal = document.getElementById("exampleModal");

  if (!myModal) {
    console.warn(" 모달 요소 없음");
    return;
  }

  // 모달 열릴 때 Ajax로 JSP 조각 로드
  myModal.addEventListener("show.bs.modal", () => {
    const modalBody = myModal.querySelector(".modal-body");
    if (!modalBody) return;

    $.ajax({
      url: "/account/find",
      type: "GET",
      dataType: "html",
      success: function (html) {
        modalBody.innerHTML = html;
		
        const form = modalBody.querySelector("#accountSearchForm");
        const resultDiv = modalBody.querySelector("#searchResult");

        if (!form || !resultDiv) return;

        form.addEventListener("submit", function (e) {
          e.preventDefault();

          const formData = new FormData(form);
          const params = new URLSearchParams(formData).toString();

          $.ajax({
            url: "/account/find/getId?" + params,
            type: "GET",
            dataType: "json",
            success: function (data) {
              if (data && data.accountId) {
                resultDiv.innerHTML = `
                  <div class="alert alert-success">
                    🔐 계정 ID는 <strong>${data.accountId}</strong> 입니다.
                  </div>
                `;
              } else {
                resultDiv.innerHTML = `
                  <div class="alert alert-warning">해당 정보를 가진 계정이 없습니다.</div>
                `;
              }
            },
            error: function () {
              resultDiv.innerHTML = `
                <div class="alert alert-danger">조회 실패. 다시 시도해주세요.</div>
              `;
            }
          });
        });
      },
      error: function () {
        modalBody.innerHTML = "<p class='text-danger'>로딩 실패</p>";
      }
    });
  });
});
