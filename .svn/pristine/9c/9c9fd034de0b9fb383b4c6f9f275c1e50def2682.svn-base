document.addEventListener("DOMContentLoaded", () => {
	const myModalEl = document.getElementById("exampleModal1");

	// 기존 이벤트 제거 후 다시 바인딩
	myModalEl.addEventListener("show.bs.modal", handleModalShow);
});
  
function handleModalShow(event) {
	const myModalEl = document.getElementById("exampleModal1"); // 다시 선언
	myModalEl.removeEventListener("show.bs.modal", handleModalShow); // 기존 이벤트 제거
	const modalBody = myModalEl.querySelector(".modal-body");
	const button = event.relatedTarget;
	const empId = button.getAttribute("data-emp-id");  // 버튼에서 empId 가져오기

	// 모달이 열릴 때마다 처리
	console.log("asdasdasd" + empId)

	// 상세보기 요청
	fetch(`/employee/empDetail?empWho=${empId}`)
		.then(response => response.text())
		.then(html => {
			modalBody.innerHTML = html; // 서버에서 받은 HTML로 모달 내용 업데이트
		})
		.catch(error => {
			modalBody.innerHTML = "<p>데이터를 불러오는 중 오류 발생</p>";
			console.error("Error loading employee details:", error);
			
		});
		const editBtn = myModalEl.querySelector('.btn');
		editBtn.href = `/employee/empUpdate?empWho=${empId}`;
	// 다시 이벤트 등록 (한 번만 실행되도록)
	setTimeout(() => {
		myModalEl.addEventListener("show.bs.modal", handleModalShow);
	}, 100);
};
