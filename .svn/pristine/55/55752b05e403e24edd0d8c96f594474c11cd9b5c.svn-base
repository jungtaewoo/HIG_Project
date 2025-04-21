/**
 * 
 */
document.addEventListener("DOMContentLoaded",function(){
	$("#delBtn").on("click",function(){
//		1. 탈퇴 의사 확인 : confirm
		let flag = confirm("진짜 탈퇴??");
		if(flag){
	//		2. 비밀번호 입력창 : prompt 
			let password = prompt("비밀번호 입력");
			//스크립트는 password 길이를 자동으로 불러옴 ->boolean타입
			if(password){
		//		3. 탈퇴 요청이 post 방식으로 전송
				let formSelector = this.dataset['targetForm'];
				let $deleteForm = $(formSelector);
				let $hidden = $deleteForm.find('[name="password"]'); 
				$hidden.val(password);
				$deleteForm.submit();
			}
		}
		

	});
});