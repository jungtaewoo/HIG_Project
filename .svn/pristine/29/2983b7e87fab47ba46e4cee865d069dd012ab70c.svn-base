/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 4. 13.     	youngjun            최초 생성
 *
 * </pre>
 */
document.addEventListener('DOMContentLoaded', function(){
   const allTab = document.getElementById('all-tab');
   const retiredTab = document.getElementById('retired-tab');
   const allAccountsDiv = document.getElementById('allAccountsTable').closest('.card'); 
   const retiredAccountsDiv = document.getElementById('retiredAccountDiv').closest('.card'); 
   const showRetiredtoggle = document.getElementById('showRetiredtoggle');
   const allAccountsTable = document.getElementById('allAccountsTable');
   const retiredAccountsTable = document.getElementById('retiredAccountDiv'); 
	
	retiredAccountsDiv.style.display = 'none';//최초 로드시 퇴사자 숨김
	
	allTab.addEventListener('click',function(){
		allAccountsDiv.style.display ='block';
		retiredAccountsDiv.style.display ='none';
		allTab.classList.add('active');
		retiredTab.classList.remove('active');
	});
	retiredTab.addEventListener('click',function(){
		allAccountsDiv.style.display = 'none';
		retiredAccountsDiv.style.display = 'block';
		allTab.classList.remove('active');
		retiredTab.classList.add('active');
	});
	//퇴사자포함 여부 토글
	showRetiredtoggle.addEventListener('change', function(){
		const inRetired = this.checked;
		const tableRows = allAccountsTable.querySelectorAll('tbody tr');

		tableRows.forEach(row=>{
			const isRetired = row.dataset.retired ==='true';

			if(inRetired) {
				row.style.display='';
			}else {
				if(isRetired) {
					row.style.display ='none';
				}else {
					row.style.display  = '';
				}
			}
		});
	});
});


function accountStatus(obj) {
	console.log("계정활성여부 확인obj", obj);
	const statusTd = obj;
	const row = statusTd.closest('tr');
	const accountData = row.querySelector('a[data-account-id]');
	const accountId = accountData.dataset.accountId;
	const button = statusTd.querySelector('button');
	let currentStatusText = button.textContent.trim(); // 버튼 텍스트 (사용,미사용)
	let beforeStatus;
	let newStatus;

	if (currentStatusText === '사용') {
		beforeStatus = 'Y';
		newStatus = 'N';
	} else {
		beforeStatus = 'N';
		newStatus = 'Y';
	}

	console.log("id속성확인", row);
	console.log("accountStatus2", accountStatus);
	console.log("accountId", accountId);
	console.log("currentStatusText", currentStatusText);
	console.log("newStatus", newStatus);

	$.ajax({
		url: `/account/status/${accountId}`,
		type: "GET",
		data: { accountStatus: beforeStatus },
		success(res) {
			console.log("서버받아온status accountid", res);
			console.log("res.accountStatus", res.accountStatus);

			let confirmMessage;
			if (res.accountStatus === true) {
				confirmMessage = "선택하신 계정을 비활성화 진행하시겠습니까?";
			} else {
				confirmMessage = "확인시 선택된 계정을 활성화 시킵니다.";
			}

			if (confirm(confirmMessage)) {
				$.ajax({
					url: `/account/statusToggle/${accountId}`,
					type: "POST",
					data: { accountStatus: newStatus },
					success(res) {
						console.log("토글res", res);
						if (res && typeof res.accountStatus !== 'undefined') {
							if (button) {
								button.textContent = res.accountStatus ? '사용' : '미사용';
								button.className = res.accountStatus ? 'btn btn-sm btn-success' : 'btn btn-sm btn-danger';
							} else {
								statusTd.textContent = res.accountStatus ? '사용' : '미사용';
							}
						}
						location.reload();
					}
				});
			}
		}
	});
}