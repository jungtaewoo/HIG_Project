/**
 * << 개정이력(Modification Information)>>
 *   수정일            수정자         수정내용
 * -----------    -------------    ---------------------------
 * 2025. 4.  5.      CHOI          임시 저장 기능 모듈화 및 기안 회수/재요청 처리 (연차 필드 선택사항으로 수정)
 */

/* =======================================
   [Module] 임시저장 / 결재라인 모듈 (approvalTempDraftSave.js)
   ======================================= */
   
import { showSuccess, showError, showConfirm, showInfo } from '../common/alertModule.js';   
  
// 전역 변수 (하나의 파일에서 중복 선언하지 않도록 수정)
var orgModalClosedBySave = false;

$(document).ready(async function(){
    // 페이지 로드시 임시저장 리스트를 가져옴
    await listAllDrafts();

    // 기안 모달 열릴 때, draftId가 없으면 임시 ID 생성
    $('#approvalFormModal').on('shown.bs.modal', function () {
        let currentDraftId = $("#draftId").val().trim();
        if (!currentDraftId) {
            currentDraftId = "TMP_" + new Date().getTime();
            $("#draftId").val(currentDraftId);
            console.log("새로운 draftId 생성: " + currentDraftId);
        }
        // 기본 제목 설정
        const currentTitle = $("input[name='draftTitle']").val().trim();
        if (currentTitle === "" || currentTitle === "draftTitle") {
            $("input[name='draftTitle']").val("연차신청서");
        }
        loadTempDraft();
    });
    
    // 임시저장 버튼 클릭 이벤트
    $("#tempSaveDraftBtn").on("click", function() {
        saveTempDraft();
    });
});

/* ---- 조직도(결재라인) 관련 함수 ---- */

// [조직도 모달] 결재자 선택 후 -> "결재라인 저장" 버튼
function saveApprovalLineFromOrg(autoSelect = false) {
    console.log("saveApprovalLineFromOrg 호출됨");

    const newApproverList = [];

    if (autoSelect) {
       
    } else {
        // 👉 기존 조직도 선택 기반 로직
        const tree = $.ui.fancytree.getTree("#orgTree");
        const selectedNodes = tree.getSelectedNodes();

        selectedNodes.forEach(function(node) {
            if (!node.folder && node.data) {
                const empId = node.data.empId || node.key;
                if (empId) {
                    newApproverList.push({
                        draftId: null,
                        approverId: empId,
                        aprSeq: newApproverList.length + 1,
                        approverName: node.title,
                        departmentName: node.data.departmentName,
                        rankName: node.data.rankName,
                        aprStatus: "대기"
                    });
                }
            }
        });

        if (newApproverList.length === 0) {
            alert("결재 후보로 추가할 직원을 선택하세요.");
            return;
        }
    }

    // 이후 로직은 동일하게 처리
    const currentDraftId = $("#draftId").val();
    const empId = $("#empId").val() || "UNKNOWN";
    const key = `tempDraft_${empId}_${currentDraftId}`;
    let draftData = {};
    const draftDataStr = sessionStorage.getItem(key);

    if (draftDataStr) {
        try {
            draftData = JSON.parse(draftDataStr);
        } catch (e) {
            console.error("기존 임시저장 데이터 파싱 오류:", e);
        }
    } else {
        draftData = {
            draftId: currentDraftId,
            templateId: $("#templateId").val() || "",
            empId: empId,
            departmentId: $("#departmentId").val() || "",
            draftTitle: $("input[name='draftTitle']").val() || "",
            draftContent: $("#draftContent").val() || "",
            draftUrgent: $("#draftUrgent").prop("checked") ? "Y" : "N",
            timestamp: new Date().toISOString()
        };
    }

    draftData.draftapproverList = newApproverList;
    fillApproverTable(newApproverList);
    sessionStorage.setItem(key, JSON.stringify(draftData));

    const modalEl = document.getElementById("orgModal");
    const modal = bootstrap.Modal.getInstance(modalEl);
    if (modal) modal.hide();
}
document.querySelector("#autoSelectBtn")?.addEventListener("click", function () {
    saveApprovalLineFromOrg(true); // autoSelect 모드로 호출
});

// 조직도 모달 버튼 이벤트 (두 군데에서 호출하지 않도록 통일)
document.querySelector(".saveApprovalLineBtn")?.addEventListener("click", function() {
	if (e.currentTarget.getAttribute("data-mode") === "resubmitBtn") {
	
		if (!confirm("결재라인 지정하시겠습니까?")) {
	        return;
	    }
	    // 전역 플래그 사용
	    orgModalClosedBySave = true;
	    
	    // 결재자 지정 저장 처리
	    saveApprovalLineFromOrg();
	  
	    // 모달 닫기 후 기안 모달 다시 열기
	    const orgModalEl = document.getElementById("orgModal");
	    const orgModalInstance = bootstrap.Modal.getInstance(orgModalEl);
	    if (orgModalInstance) {
	        orgModalInstance.hide();
	    }
	    setTimeout(function(){
	        const approvalModalEl = document.getElementById("approvalFormModal");
	        const approvalModalInstance = bootstrap.Modal.getOrCreateInstance(approvalModalEl);
	        approvalModalInstance.show();
	    }, 300);
  }
});

/* ---- 임시저장, 로드, 목록, 삭제 관련 함수 ---- */

export async function saveTempDraft() {
    let rawDraftId = $("#draftId").val();
    let finalDraftId = rawDraftId ? rawDraftId : generateUniqueDraftId();
    if (!finalDraftId.startsWith("TMP_")) {
        const numericVal = parseInt(finalDraftId, 10);
        if (!isNaN(numericVal)) {
            finalDraftId = numericVal;
        }
    }
    const draftData = {
        draftId: finalDraftId,
        templateId: $("#templateId").val() || "",
        empId: $("#empId").val() || "",
        departmentId: $("#departmentId").val() || "",
        draftTitle: $("input[name='draftTitle']").val() || "",
        draftContent: $("#draftContent").val() || "",
        draftUrgent: $("#draftUrgent").prop("checked") ? "Y" : "N",
        timestamp: new Date().toISOString()
    };

    // 결재자 목록 수집
    draftData.draftapproverList = [];
    $(".approverRow").each(function() {
        const approver = {
            approverId: $(this).find(".approverId").val() || "",
            approverName: $(this).find(".approverName").val() || "",
            departmentName: $(this).find(".departmentName").val() || "",
            rankName: $(this).find(".rankName").val() || "",
            aprSeq: $(this).find(".aprSeq").val() || "",
            aprStatus: $(this).find(".aprStatus").val() || "대기",
            aprReason: $(this).find(".aprReason").val() || ""
        };
        draftData.draftapproverList.push(approver);
    });

    // 연차 필드 (값이 비어 있어도 그대로 저장)
    const leaveDate = $("#leaveDate").val() || "";
    const leaveEndDate = $("#leaveEndDate").val() || "";
    const annualCode = $("#annualCode").val() || "";
    const reason = (window.CKEDITOR && CKEDITOR.instances.reason)
                    ? CKEDITOR.instances.reason.getData() : "";
    draftData.annualHistory = {
        leaveDate: leaveDate.trim(),
        leaveEndDate: leaveEndDate.trim(),
        annualCode: annualCode.trim(),
        reason: reason
    };

    const empId = $("#empId").val() || "UNKNOWN";
    const key = `tempDraft_${empId}_${draftData.draftId}`;
    sessionStorage.setItem(key, JSON.stringify(draftData));
	
	// 임시저장 전 확인 모달 (SweetAlert2)
   const confirmResult = await showConfirm({
       title: "임시저장 확인",
       text: "임시저장하시겠습니까?",
       confirmButtonText: "저장",
       cancelButtonText: "취소"
   });
   
   if (!confirmResult.isConfirmed) {
       // 사용자가 취소한 경우 저장하지 않고 종료
       return;
   }
	
	
	// SweetAlert2로 임시저장 성공 알림
    await showSuccess({ title: "저장 성공", text: "임시저장되었습니다." });
    listAllDrafts();

    // 모달 닫기
    const modalElement = document.getElementById("approvalFormModal");
    const modalInstance = bootstrap.Modal.getInstance(modalElement);
    if (modalInstance) modalInstance.hide();
}

export function loadTempDraft() {
    const draftId = $("#draftId").val();
    const empId = $("#empId").val() || "UNKNOWN";
    if (draftId) {
        const key = `tempDraft_${empId}_${draftId}`;
        const draftData = sessionStorage.getItem(key);
        if (draftData) {
            try {
                const parsedData = JSON.parse(draftData);
                fillDraftForm(parsedData);
            } catch(e) {
                console.error("임시저장 데이터 파싱 오류 (draftId: " + draftId + "):", e);
            }
        } else {
            console.log("해당 draftId(" + draftId + ")에 대한 임시저장 데이터가 없습니다.");
        }
    } else {
        console.log("현재 DRAFT_ID가 없습니다.");
    }
}

export async function getLoggedInUserIdSomehow() {
    try {
        const response = await axios.get(window.contextPath + "/approvalProcess/getApproverId");
        return response.data;
    } catch (error) {
        console.error("로그인 사용자 ID 조회 실패:", error);
        return "UNKNOWN";
    }
}

export async function listAllDrafts() {
    const empId = await getLoggedInUserIdSomehow();
    const container = document.getElementById("draftListContainer");
    if (!container) {
        console.warn("draftListContainer 요소가 없습니다. listAllDrafts() 스킵");
        return;
    }
    container.innerHTML = "";
    const keys = Object.keys(sessionStorage);
    const draftKeys = keys.filter(key => key.startsWith(`tempDraft_${empId}_`));
    if (draftKeys.length === 0) {
        container.innerHTML = "<p>임시저장된 문서가 없습니다.</p>";
        return;
    }
    let html = "<ul class='list-group' id='draftUl'>";
    draftKeys.forEach(key => {
        const dataStr = sessionStorage.getItem(key);
        try {
            const draft = JSON.parse(dataStr);
            const title = draft.draftTitle || "제목 없음";
            const formattedTime = formatTimestampToLocal(draft.timestamp);
            const displayLabel = `[임시저장] ${title} (${formattedTime})`;
            html += `
                <li class="list-group-item d-flex justify-content-between align-items-center">
                  <span>${displayLabel}</span>
                  <div>
                    <button class="btn btn-primary btn-sm openDraftBtn" data-key="${key}">열기</button>
                    <button class="btn btn-danger btn-sm removeDraftBtn" data-key="${key}">삭제</button>
                  </div>
                </li>
            `;
        } catch(e) {
            console.error("Parsing error for key:", key, e);
        }
    });
    html += "</ul>";
    container.innerHTML = html;
    const draftUl = container.querySelector("#draftUl");
    draftUl.addEventListener("click", function(e) {
        if (e.target.matches(".openDraftBtn")) {
            const key = e.target.dataset.key;
            openSavedDraft(key);
        } else if (e.target.matches(".removeDraftBtn")) {
            const key = e.target.dataset.key;
            removeDraft(key);
        }
    });
}

export async function openSavedDraft(key) {
    const dataStr = sessionStorage.getItem(key);
    if (!dataStr) {
        alert("해당 임시저장 데이터를 찾을 수 없습니다.");
        return;
    }
    try {
        const draftData = JSON.parse(dataStr);
        // openApprovalForm()는 모달 열기 등 프로젝트 환경에 맞게 구현되어야 함
        await openApprovalForm(draftData.templateId, draftData.draftTitle || "임시저장 문서");
        fillDraftForm(draftData);
    } catch(e) {
        console.error(e);
        alert("임시저장 데이터를 불러오는 중 오류 발생");
    }
}

export async function removeDraft(key) {
	// SweetAlert2 confirm 모달 호출: isConfirmed가 true이면 삭제 처리
  const result = await showConfirm({
    title: '삭제 확인',
    text: '정말 이 임시저장 문서를 삭제하시겠습니까?',
    confirmButtonText: '삭제',
    cancelButtonText: '취소'
  });
  if (result.isConfirmed) {
	await showSuccess({ title: "삭제 성공", text: "삭제완료되었습니다." });
    sessionStorage.removeItem(key);
    listAllDrafts();
  }
}

export function generateUniqueDraftId() {
    return "TMP_" + new Date().getTime();
}

export function formatTimestampToLocal(isoString) {
    if (!isoString) return "";
    const dateObj = new Date(isoString);
    if (isNaN(dateObj.getTime())) return "";
    const year = dateObj.getFullYear();
    const month = String(dateObj.getMonth() + 1).padStart(2, "0");
    const day = String(dateObj.getDate()).padStart(2, "0");
    const hours = String(dateObj.getHours()).padStart(2, "0");
    const minutes = String(dateObj.getMinutes()).padStart(2, "0");
    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

/* ---- 폼 채우기 및 재상신 관련 함수 ---- */

export function fillDraftForm(draftData) {
    $("#draftId").val(draftData.draftId);
    $("#templateId").val(draftData.templateId);
    $("#empId").val(draftData.empId);
    $("#departmentId").val(draftData.departmentId || draftData.draftDepartmentId || "");
    $("input[name='draftTitle']").val(draftData.draftTitle);
    $("#draftContent").val(draftData.draftContent);
    $("#draftUrgent").prop("checked", draftData.draftUrgent === "Y");

    // 결재자 목록 렌더링
    $("#approverTableBody").empty();
    if (draftData.draftapproverList && draftData.draftapproverList.length > 0) {
        draftData.draftapproverList.forEach(function(approver) {
            // 회수나 반려 상태이면, 결재자 aprStatus값이 없을 경우 기본값 설정
            let displayStatus = approver.aprStatus;
            if (draftData.draftStatus === "회수" && (!displayStatus || displayStatus.trim() === "")) {
                displayStatus = "대기";
            }
            if (draftData.draftStatus === "반려" && (!displayStatus || displayStatus.trim() === "")) {
                displayStatus = "반려";
            }
            const rowHtml = `
                <tr class="approverRow">
                    <td>
                        <input type="hidden" class="approverId" value="${approver.approverId}" />
                        <input type="text" class="form-control approverName" value="${approver.approverName}" placeholder="승인자명" />
                    </td>
                    <td style="min-width: 100px;">
                        <input type="text" class="form-control departmentName" value="${approver.departmentName || approver.approverDepartmentName || ""}" placeholder="부서명" />
                    </td>
                    <td>
                        <input type="text" class="form-control rankName" value="${approver.rankName || approver.approverRankName || ""}" placeholder="직급명" />
                    </td>
                    <td>
                        <input type="number" class="form-control aprSeq" value="${approver.aprSeq}" readonly />
                    </td>
                    <td style="min-width: 60px;">
                        <select class="form-control aprStatus" disabled>
                            <option value="대기" ${displayStatus === "대기" ? "selected" : ""}>대기</option>
                            <option value="반려" ${displayStatus === "반려" ? "selected" : ""}>반려</option>
                            <option value="승인중" ${displayStatus === "승인중" ? "selected" : ""}>승인중</option>
                        </select>
                        <input type="hidden" class="aprStatusHidden" value="${displayStatus}" />
                    </td>
                </tr>
            `;
            $("#approverTableBody").append(rowHtml);
        });
    }

    // 연차(annualHistory) 처리
    const annualHistory = draftData.annualHistory || { leaveDate: "", leaveEndDate: "", annualCode: "", reason: "" };
    const leaveDateValue = annualHistory.leaveDate ? annualHistory.leaveDate.split(" ")[0] : "";
    $("#leaveDate").val(leaveDateValue);
    const leaveEndDateValue = annualHistory.leaveEndDate ? annualHistory.leaveEndDate.split(" ")[0] : "";
    $("#leaveEndDate").val(leaveEndDateValue);
    $("#annualCode").val(annualHistory.annualCode || "");

    if (CKEDITOR.instances && CKEDITOR.instances.reason) {
        CKEDITOR.instances.reason.setData(annualHistory.reason || "");
    }

    // 재상신 버튼과 제출 버튼 토글: 회수, 반려, 임시문서인 경우 재상신 버튼 보임
    if (
      (typeof draftData.draftId === "string" && draftData.draftId.startsWith("TMP_")) ||
      draftData.draftStatus === "회수" ||
      draftData.draftStatus === "반려"
    ) {
      $("#submitApprovalBtn").hide();
      $("#resubmitBtn").show();
    } else {
      $("#submitApprovalBtn").show();
      $("#resubmitBtn").hide();
    }
}

export function storeRecalledDocInSession(docData, empId) {
    const recalledDraft = {
        draftId: docData.draftId,
        templateId: docData.templateId || "",
        empId: empId,
        departmentId: docData.draftDepartmentId || docData.departmentId || $("#departmentId").val() || "",
        draftTitle: docData.draftTitle || "",
        draftContent: docData.draftContent || "",
        draftUrgent: docData.draftUrgent === "Y" ? "Y" : "N",
        timestamp: new Date().toISOString(),
        draftStatus: docData.draftStatus || "",
        draftapproverList: [],
        annualHistory: {
            leaveDate: (docData.annualHistory && docData.annualHistory.leaveDate) || docData.leaveDate || "",
            leaveEndDate: (docData.annualHistory && docData.annualHistory.leaveEndDate) || docData.leaveEndDate || "",
            annualCode: (docData.annualHistory && docData.annualHistory.annualCode) || docData.annualCode || "",
            reason: (docData.annualHistory && docData.annualHistory.reason) || docData.reason || ""
        }
    };

    if (docData.draftapproverList && Array.isArray(docData.draftapproverList)) {
        recalledDraft.draftapproverList = docData.draftapproverList.map(ap => {
            let finalStatus = ap.aprStatus;
            // 기안 회수 시, 결재자 상태 기본값 처리
            if (docData.draftStatus === "회수" && (!finalStatus || finalStatus.trim() === "")) {
                finalStatus = "대기";
            }
            /*// 재요청 시, 문서 전체 상태를 "대기"로 변환
            if (docData.draftStatus === "반려") {
                recalledDraft.draftStatus = "대기";
            }*/
            return {
                approverId: ap.approverId,
                approverName: ap.approverName,
                approverDepartmentName: ap.approverDepartmentName,
                approverRankName: ap.approverRankName,
                aprSeq: ap.aprSeq,
                aprStatus: finalStatus,
                aprReason: ap.aprReason || ""
            };
        });
    }
    const key = `tempDraft_${empId}_${recalledDraft.draftId}`;
    sessionStorage.setItem(key, JSON.stringify(recalledDraft));
}


/**
 * 지정된 휴가 시작일과 종료일에 대해 연차 충돌 여부를 서버로부터 확인합니다.
 * @param {string} leaveStartDate - 휴가 시작일 (예: "2025-04-15")
 * @param {string} leaveEndDate - 휴가 종료일 (예: "2025-04-20")
 * @returns {Promise<boolean>} - 충돌되면 true, 아니면 false를 반환합니다.
 */
async function checkLeaveConflictCondition(leaveStartDate, leaveEndDate) {
    try {
        // 세션에서 empId를 가져오므로 leaveStartDate, leaveEndDate만 전달합니다.
        const response = await axios.get("/approval/checkLeaveConflict", {
            params: { 
                leaveStartDate: leaveStartDate.trim(), 
                leaveEndDate: leaveEndDate.trim() 
            }
        });
        // 서버 응답이 boolean 값(true: 충돌 존재)이라고 가정합니다.
        return response.data === true;
    } catch (error) {
        console.error("연차 충돌 검사 실패:", error);
        throw new Error("연차 충돌 검사 중 오류가 발생했습니다.");
    }
}





/**
 * 재상신 요청 함수
 * - 만약 draftId가 "TMP_"로 시작하면 null로 처리하여 서버에서는 새 문서로 인식
 * - 원본 문서가 회수 또는 반려 상태였다면 상태를 "대기"로 변경
 * - 필요한 필드 (결재선, 휴가 시작/종료일, 연차 코드)가 누락되면 오류를 표시하여 재상신을 중단함
 *  - 성공 시 true, 오류가 발생하거나 검증에 실패하면 false를 반환함
 */
export async function submitReDraft(draftData) {
    // 1. 결재선 검증: 적어도 한 명 이상의 결재자가 있어야 함
    if (!draftData.draftapproverList || draftData.draftapproverList.length === 0) {
        await showError({ title: "입력오류", text: "결재선을 지정해주세요. 적어도 한 명의 결재자가 필요합니다." });
        return false;
    }
    
    // 2. 연차 신청서 관련 필드 검증: 휴가 시작일, 종료일, 연차 코드는 반드시 입력되어야 함
    const updatedLeaveDate = $("#leaveDate").val().trim();
    const updatedLeaveEndDate = $("#leaveEndDate").val().trim();
    const updatedAnnualCode = $("#annualCode").val().trim();
    
    if (!updatedLeaveDate || !updatedLeaveEndDate) {
        await showError({ title: "입력오류", text: "휴가 시작일과 종료일을 모두 입력해주세요." });
        return false;
    }
    
    if (!updatedAnnualCode) {
        await showError({ title: "입력오류", text: "연차 코드를 입력해주세요." });
        return false;
    }
    
	// ★ 연차 충돌 체크 추가 ★
   try {
       const conflict = await checkLeaveConflictCondition(updatedLeaveDate, updatedLeaveEndDate);
       if (conflict) {
           await showError({ title: "연차 신청 불가", text: "해당 기간에는 이미 연차 신청 내역이 있습니다. 다른 날짜를 선택해주세요." });
           return false; 
       }
   } catch (err) {
       await showError({ title: "연차 확인 오류", text: err.message });
       return false;
   }
	
	
    // 3. 재상신 내용 기본값 설정: 내용이 비어 있을 경우 기본 문구 지정
    if (!draftData.draftContent || draftData.draftContent.trim() === "") {
        draftData.draftContent = "기안상신합니다.";
    }
    
    // 4. CKEDITOR 또는 입력 필드에서 최신 연차 사유/내용 가져오기
    const updatedReason = (window.CKEDITOR && CKEDITOR.instances.reason)
                          ? CKEDITOR.instances.reason.getData()
                          : ($("#reason").val() || "");
    
    // 5. 연차 관련 필드 업데이트 (날짜 필드는 날짜 부분만 사용)
    draftData.annualHistory = {
        leaveDate: updatedLeaveDate.split(" ")[0],
        leaveEndDate: updatedLeaveEndDate.split(" ")[0],
        annualCode: updatedAnnualCode,
        reason: updatedReason
    };
    
    // 6. 임시 문서인 경우 새 문서로 처리 (TMP_ 접두사 제거)
    if (typeof draftData.draftId === "string" && draftData.draftId.startsWith("TMP_")) {
        draftData.draftId = null;
    } else if (typeof draftData.draftId === "string") {
        const numericVal = parseInt(draftData.draftId, 10);
        if (!isNaN(numericVal)) {
            draftData.draftId = numericVal;
        }
    }
    
    // 7. 원본 문서가 회수 또는 반려 상태이면 문서 상태를 "대기"로 변경
    if (draftData.draftStatus === "회수" || draftData.draftStatus === "반려") {
        draftData.draftStatus = "대기"; 
    }
    
    // 8. 재상신 AJAX 호출
    try {
        const resp = await axios.post(window.contextPath + "/approval/draft/submit", draftData);
        await showSuccess({ title: "재상신 완료", text: resp.data });
        const empId = $("#empId").val() || "UNKNOWN";
        const key = `tempDraft_${empId}_${draftData.draftId}`;
		return true; // 성공시 true 반환
       /* sessionStorage.removeItem(key);*/ // 여기서는 임시저장된 데이터 삭제
    } catch (error) {
        console.error("재상신 실패:", error);
        await showError({ title: "오류", text: "재상신 중 오류가 발생했습니다. (서버 로그 참고)" });
		return false;
    }
}
