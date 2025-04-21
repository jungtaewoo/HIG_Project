/**
 * 
 */

    document.getElementById("dropdownBtn").addEventListener("click", function () {
        let dropdown = document.getElementById("dropdownList");
        dropdown.style.display = (dropdown.style.display === "block") ? "none" : "block";
    });

    // 옵션 클릭 시 버튼 텍스트 & value 변경
    document.querySelectorAll(".dropdown-item").forEach(item => {
        item.addEventListener("click", function () {
            let selectedValue = this.getAttribute("data-value");  // 선택한 값
            let selectedText = this.innerText; // 선택한 옵션의 텍스트

            let btn = document.getElementById("dropdownBtn");
            btn.innerText = selectedText;  // 버튼 텍스트 변경
            btn.setAttribute("data-value", selectedValue);  // 버튼에 value 저장

            document.getElementById("dropdownList").style.display = "none"; // 리스트 닫기
        });
    });

    // 드롭다운 외부 클릭 시 닫기
    document.addEventListener("click", function (event) {
        if (!document.getElementById("dropdownBtn").contains(event.target) &&
            !document.getElementById("dropdownList").contains(event.target)) {
            document.getElementById("dropdownList").style.display = "none";
        }
    });
