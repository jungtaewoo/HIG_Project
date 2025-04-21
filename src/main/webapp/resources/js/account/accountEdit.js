function setupEditMode(accountId) {
    const modal = document.getElementById("exampleModal");
    const editButton = modal.querySelector("#editButton");

    editButton.addEventListener("click", function () {
        fetch(`/account/update/${accountId}`)
            .then(response => response.text())
            .then(html => {
                // 수정 폼 삽입
                document.querySelector(".modal-body").innerHTML = html;

                // 중복 이벤트 방지 + 이벤트 등록
                enableEditing();
            })
            .catch(error => {
                document.querySelector(".modal-body").innerHTML = "<p>데이터 오류</p>";
                console.error("Error loading update form:", error);
            });
    });
}

function enableEditing() {
    const modal = document.getElementById("exampleModal");

    // 기존 저장 버튼 제거 후 새로 등록 (중복 이벤트 제거)
    const oldButton = modal.querySelector("#saveButton");
    const newButton = oldButton.cloneNode(true); // 이벤트 제거된 버튼
    oldButton.parentNode.replaceChild(newButton, oldButton);

    // 새로 이벤트 바인딩
    newButton.addEventListener("click", function () {
        const form = document.querySelector("#updateAccount");
        if (!form) {
            console.error("Form not found.");
            return;
        }

        // visible → hidden 필드 동기화
        const visibleToHiddenMap = [
            ["accountEmail", "email"],
            ["accountPh", "phoneNumber"],
            ["accountAdd1", "address"],
            ["accountAdd2", "addressDetail"],
            ["empName", "name"]
        ];

        visibleToHiddenMap.forEach(([visible, hidden]) => {
            const visibleInput = form.querySelector(`input[name='${visible}']`);
            const hiddenInput = form.querySelector(`input[name='${hidden}']`);
            if (visibleInput && hiddenInput) {
                hiddenInput.value = visibleInput.value;
            }
        });

        const formData = new FormData(form);

        fetch("/account/update/save", {
            method: "POST",
            body: formData
        })
        .then(response => response.json())
        .then(result => {
            if (result.success) {
                alert("수정이 완료되었습니다.");
                location.reload();
            } else {
                alert("정보 수정에 실패하였습니다: " + result.message);
            }
        })
        .catch(error => {
            console.error("Error updating account", error);
        });
    });

    // 버튼 상태 표시 조정
    document.querySelector("#editButton").style.display = "none";
    newButton.style.display = "inline-block";
}
