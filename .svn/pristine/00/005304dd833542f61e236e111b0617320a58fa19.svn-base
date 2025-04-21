/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 20.     	CHOI            결재 문서에 대한 승인/반려/보류 기능 
 *
 * </pre>
 */

// 페이지 로드 될 때 이벤트 리스너
document.addEventListener("DOMContentLoaded", function () {
    console.log("결재 승인/반려/보류 기능 로드 완료");

    // 결재 승인 버튼 리스너 추가
    document.querySelector("#approveDraftBtn")?.addEventListener("click", () => handleApproval("승인"));

    // 결재 반려 버튼 리스너 추가 
    document.querySelector("#rejectDraftBtn")?.addEventListener("click", () => handleApproval("반려"));

    // 결재 보류 버튼 리스너 추가
    document.querySelector("#holdDraftBtn")?.addEventListener("click", () => handleApproval("보류"));
});

/*
    결재 승인/반려/보류 처리
*/
function handleApproval(status) {
    let draftId = document.querySelector("#draftId").value;
    let approverId = document.querySelector("#empId").value; // 로그인한 결재자
    let aprReason = document.querySelector("#aprReason")?.value || "";
	let empId = docudocument.querySelector("#empId");
    // 결재 문서 및 결재자 확인
    if (!draftId || !approverId) {
        alert("결재 문서와 결재자를 선택해주세요.");
        return;
    }

    // 반려 이유가 있을 경우에만 반려, 승인, 보류 상태 처리
    if (status !== "승인" && !aprReason.trim()) {
        alert("반려 사유를 입력해주세요.");
        return;
    }

    // 승인, 반려, 보류에 대한 데이터 구성
    let approvalData = {
        draftId: draftId,
        approverId: approverId,
        aprStatus: status,
        aprReason: aprReason,
		draftEmpId: empId
    };

    // 각 상태에 맞는 API 호출
    let url = "";
	//조건문으로 승인/반려/보류 처리 가능 
    if (status === "승인") {
        url = "/approval/draft/approve";
    } else if (status === "반려") {
        url = "/approval/draft/reject";
    } else if (status === "보류") {
        url = "/approval/draft/hold";  
    }

    // API 호출
    axios.post(url, approvalData)
        .then(resp => {
            alert(`문서가 ${status}되었습니다.`);
            location.reload();
        })
        .catch(error => {
            console.error(`문서 ${status} 실패:`, error);
            alert(`문서 ${status} 실패하였습니다.`);
        });
}
