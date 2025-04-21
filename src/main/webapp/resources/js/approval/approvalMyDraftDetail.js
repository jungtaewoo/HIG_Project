/**
 * << 개정이력(Modification Information)>>
 *   수정일            수정자         수정내용
 * -----------    -------------    ---------------------------
 * 2025. 4.  5.      CHOI        제출 문서 상세보기 및 기안 회수/재요청 처리 (연차 필수)
 */

import { 
  saveTempDraft, listAllDrafts, openSavedDraft, removeDraft,
  generateUniqueDraftId, formatTimestampToLocal, fillDraftForm,
  storeRecalledDocInSession, submitReDraft, getLoggedInUserIdSomehow
} from './approvalTempDraftSave.js';
import { showConfirm, showError, showInfo, showSuccess } from '../common/alertModule.js';
import {formatKoreanDateTime} from '../common/koreanDateType.js';
document.addEventListener("DOMContentLoaded", async function() {

  // 공통 함수: 로그인 사용자 ID
  async function getLoggedInUserId() {
    try {
      const resp = await axios.get(window.contextPath + "/approvalProcess/getApproverId");
      return resp.data;
    } catch (error) {
      console.error("로그인 사용자 ID 조회 실패:", error);
      return null;
    }
  }

  function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
  }

  // 서버에서 연차 옵션(annualTypes) 가져오기
  async function loadAnnualCodeOptionsFromAPI() {
    try {
      const contextPath = window.contextPath || "";
      const response = await axios.get(contextPath + "/approval/draft/data/annualTypes");
      return response.data; // 예: [{cd: 'AAA', nm: '연차'}, ...]
    } catch (error) {
      console.error("연차 옵션 불러오기 실패:", error);
      return [];
    }
  }

  async function fillAnnualCodeOptionsFromAPI() {
    const select = document.getElementById("annualCode");
    if (!select) {
      console.warn("annualCode 셀렉트 요소가 존재하지 않습니다.");
      return;
    }
    const options = await loadAnnualCodeOptionsFromAPI();
    select.innerHTML = "";
    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.text = "선택하세요";
    select.appendChild(defaultOption);
  
    options.forEach(opt => {
      const option = document.createElement("option");
      option.value = opt.cd;
      option.text = opt.nm;
      select.appendChild(option);
    });
  }
  
  // 연차 신청서 폼 HTML 로드
  async function loadAnnualFormHtml() {
    try {
      const resp = await axios.get(window.contextPath + "/resources/html/approval/approval1_1.html");
      const annualFormContainer = document.getElementById("annualFormContainer");
      if (annualFormContainer) {
        annualFormContainer.innerHTML = resp.data;
        const reasonElem = document.getElementById("reason");
        if (reasonElem) {
          if (window.CKEDITOR && CKEDITOR.instances && CKEDITOR.instances.reason) {
            CKEDITOR.instances.reason.destroy(true);
          }
          if (window.CKEDITOR) {
            CKEDITOR.replace('reason', { versionCheck: false });
          } else {
            console.warn("CKEDITOR 로드되지 않음");
          }
        }
      } else {
        console.warn("annualFormContainer 요소가 없음");
      }
    } catch (err) {
      console.error("연차 신청서 폼 로드 실패:", err);
    }
  }
  
  // 상세보기 관련 함수
  function extractDateString(dbDateString) {
    if (!dbDateString) return "";
    return dbDateString.split(" ")[0];
  }
  
  // CKEditor가 준비되었는지 확인하여 reason 데이터 세팅하는 헬퍼 함수
    function setReasonData(reasonContent) {
      if (window.CKEDITOR) {
        if (CKEDITOR.instances.reason) {
          // 인스턴스가 이미 생성되어 있다면 바로 세팅
          CKEDITOR.instances.reason.setData(reasonContent);
        } else {
          // 인스턴스가 아직 생성되지 않았다면 instanceReady 이벤트를 기다림
          CKEDITOR.on('instanceReady', function(event) {
            if (event.editor.name === 'reason') {
              event.editor.setData(reasonContent);
            }
          });
        }
      } else {
        console.warn("CKEDITOR 로드되지 않음");
      }
    }
  
  
  function loadAnnualForm(docInfo) {
    const pureLeaveDate = extractDateString(docInfo.leaveDate);
    const pureLeaveEndDate = extractDateString(docInfo.leaveEndDate);
    const pureDraftDate = extractDateString(docInfo.draftDate);
    const nameFields = [
      { name: "draftUser", value: docInfo.draftEmpName },
      { name: "draftDept", value: docInfo.draftDepartmentName },
      { name: "draftDate", value: pureDraftDate },
      { name: "docNo", value: docInfo.draftId },
      { name: "leaveDate", value: pureLeaveDate },
      { name: "leaveEndDate", value: pureLeaveEndDate },
      { name: "annualCode", value: docInfo.annualCode || "" }
    ];
  
    nameFields.forEach(field => {
      const element = document.getElementsByName(field.name)[0];
      if (element) {
        element.value = field.value || "";
      } else {
        console.warn(`요소 [name="${field.name}"] 없음`);
      }
    });
    
    const reasonContent = (docInfo.annualHistory && docInfo.annualHistory.reason) || docInfo.reason || "";
    if (window.CKEDITOR && CKEDITOR.instances && CKEDITOR.instances.reason) {
      CKEDITOR.instances.reason.setData(reasonContent);
    } else {
      console.warn("CKEDITOR 인스턴스(reason) 미생성");
    }
	
	//CK인스턴스가 준비되어 있을 때 해당 내용 셋팅 
	setReasonData(reasonContent);
  }
  
  function fillApprovers(draftapproverList) {
    const approverTableBody = document.getElementById("approverTableBody");
    if (!approverTableBody) {
      console.warn("approverTableBody 요소가 없음");
      return;
    }
    approverTableBody.innerHTML = "";
    draftapproverList.forEach(function(approver) {
      let row = document.createElement("tr");
      row.innerHTML = `
         <td style="text-align:center;">${approver.approverName || ""}</td>
         <td style="text-align:center;">${approver.approverDepartmentName || ""}</td>
         <td style="text-align:center;">${approver.approverRankName || ""}</td>
         <td style="text-align:center;">${approver.aprSeq || ""}</td>
         <td style="text-align:center;">${approver.aprStatus || ""}</td>
       `;
      approverTableBody.appendChild(row);
    });
  }
  
  async function loadDraftDetail() {
    const draftId = getQueryParam("draftId");
    if (!draftId) {
      console.warn("URL에 draftId 파라미터가 없음");
    }
    try {
      const loggedInUser = await getLoggedInUserIdSomehow();
      if (!loggedInUser) console.warn("로그인된 사용자 없음");
      if (draftId) {
        const response = await axios.get(window.contextPath + "/approvalProcess/myDraftDetail", {
          params: { draftId: draftId, empId: loggedInUser }
        });
        const data = response.data;
        console.log("상세 데이터:", data);
        const status = data.draftStatus ? data.draftStatus.trim() : "";
  
        // 상단 정보 세팅
        const docTitleEl = document.getElementById("draftTitle");
        const draftEmpNameEl = document.getElementById("draftEmpName");
        const draftDateEl = document.getElementById("draftDate");
        const draftStatusEl = document.getElementById("draftStatus");
        if (docTitleEl)  docTitleEl.textContent = data.draftTitle || "[제목 없음]";
        if (draftEmpNameEl) draftEmpNameEl.textContent = data.draftEmpName || "작성자 없음";
        if (draftDateEl)    draftDateEl.textContent = data.draftDate ? formatKoreanDateTime(data.draftDate) : "작성일 없음";
        if (draftStatusEl)  draftStatusEl.textContent = status || "상태 없음";
  
		
		
        // 연차 폼 로드 및 매핑
        await loadAnnualFormHtml();
        await fillAnnualCodeOptionsFromAPI();
        loadAnnualForm(data);
		// 문서번호 행 보이게 처리 (상세보기에서 문서번호를 보여줌)
	    
       document.querySelectorAll('.docNoRow').forEach(function(row) {
         row.style.display = 'table-row';
       });

        fillApprovers(data.draftapproverList || []);
  		
		
        // 버튼 제어
        const retrieveBtn = document.getElementById("retrieveBtn");
        const resubmitDetailBtn = document.getElementById("resubmitDetailBtn");
        if (resubmitDetailBtn) {
          resubmitDetailBtn.style.display = (status === "반려") ? "inline-block" : "none";
        }
        if (retrieveBtn) {
          let displayRetrieve = false;
          if (status === "대기" || status === "결재 진행 중" || status === "보류" || status === "회수") {
            displayRetrieve = true;
          }
          retrieveBtn.style.display = displayRetrieve ? "inline-block" : "none";
        }
      } else {
        await loadAnnualFormHtml();
        await fillAnnualCodeOptionsFromAPI();
      }
    } catch (error) {
      console.error("상세 정보 불러오기 실패:", error);
    }
  }
  
  // 회수 버튼 동작
  const retrieveBtn = document.getElementById("retrieveBtn");
  if (retrieveBtn) {
    retrieveBtn.addEventListener("click", async function() {
      // showConfirm 함수 호출 시 옵션을 원하는 문구로 전달합니다.
      const confirmed = await showConfirm({
        title: "회수 확인",
        text: "회수하시겠습니까?",
        confirmButtonText: "회수",
        cancelButtonText: "취소"
      });
      if (!confirmed.value) return;  // 사용자가 취소하면 종료

      let draftId = getQueryParam("draftId") || $("#draftId").val();
      if (!draftId) {
        await showError({ title: "회수", text: "회수할 문서 ID가 없습니다." });
        return;
      }
      const empId = await getLoggedInUserId();
      if (!empId) {
        await showError({ title: "회수", text: "로그인 사용자 정보가 없습니다." });
        return;
      }
      try {
        console.log("[회수 요청] draftId=", draftId, "empId=", empId);
        const resp = await axios.post(window.contextPath + "/approval/drafter/recall", {
          draftId: draftId,
          empId: empId
        });
        if (resp.status === 200) {
          await showSuccess({ title: "회수", text: resp.data });
          let docData = null;
          try {
            const detailResp = await axios.get(window.contextPath + "/approvalProcess/myDraftDetail", {
              params: { draftId: draftId, empId: empId }
            });
            docData = detailResp.data;
          } catch (err) {
            console.warn("회수 후 상세 재조회 실패", err);
          }
          if (docData) {
            console.log("회수된 문서:", docData);
            storeRecalledDocInSession(docData, empId);
            await listAllDrafts();
          }
          window.location.href = window.contextPath + "/approval/templateList";
        } else {
          await showError({ title: "회수", text: "회수 호출 실패. 응답: " + resp.status });
        }
      } catch (error) {
        console.error("회수 호출 실패", error);
        await showError({ title: "회수", text: "회수 호출 실패" });
      }
    });
  }

  
  // 재요청 버튼 동작 (임시저장된 문서를 열어 재상신)
    const resubmitBtn = document.getElementById("resubmitBtn");
    if (resubmitBtn) {
      resubmitBtn.addEventListener("click", async function() {
        const confirmed = await showConfirm({
          title: "재요청 확인",
          text: "재요청 하시겠습니까?",
          confirmButtonText: "재요청",
          cancelButtonText: "취소"
        });
        if (!confirmed.value) return;
        let draftId = getQueryParam("draftId") || $("#draftId").val();
        if (!draftId) {
          await showError({ title: "재요청", text: "재상신할 문서 ID가 없습니다." });
          return;
        }
        const empId = await getLoggedInUserId();
        if (!empId) {
          await showError({ title: "재요청", text: "로그인된 사용자 정보가 없습니다." });
          return;
        }
        const key = `tempDraft_${empId}_${draftId}`;
        const storedData = sessionStorage.getItem(key);
        if (!storedData) {
          await showError({ title: "재요청", text: "임시저장 데이터를 찾을 수 없습니다." });
          return;
        }
        let draftData = JSON.parse(storedData);
        await submitReDraft(draftData);  // 재상신 시 내부 연차 필드 검증
        const modalElement = document.getElementById("approvalFormModal");
        let modalInstance = bootstrap.Modal.getInstance(modalElement);
        if (modalInstance) {
          modalInstance.hide();
        }
        sessionStorage.removeItem(key);
        await listAllDrafts();
      });
    }
  
  // “상세보기”에서 바로 재요청하는 케이스 (재요청 버튼 동작)
  const resubmitDetailBtn = document.getElementById("resubmitDetailBtn");
  if (resubmitDetailBtn) {
    resubmitDetailBtn.addEventListener("click", async function() {
      const confirmed = await showConfirm({
        title: "재요청 확인",
        text: "재요청 하시겠습니까?",
        confirmButtonText: "재요청",
        cancelButtonText: "취소"
      });
      if (!confirmed.value) return;  // 사용자가 취소하면 종료

      let draftId = getQueryParam("draftId") || $("#draftId").val();
      if (!draftId) {
        await showError({ title: "재요청", text: "재요청할 문서 ID가 없습니다." });
        return;
      }
      const empId = await getLoggedInUserId();
      if (!empId) {
        await showError({ title: "재요청", text: "로그인된 사용자 정보가 없습니다." });
        return;
      }
      try {
        const resp = await axios.post(window.contextPath + "/approval/drafter/recall", {
          draftId: draftId,
          empId: empId
        });
        if (resp.status === 200) {
          await showSuccess({ title: "재요청", text: resp.data });
          let docData = null;
          try {
            const detailResp = await axios.get(window.contextPath + "/approvalProcess/myDraftDetail", {
              params: { draftId: draftId, mpId: empId }
            });
            docData = detailResp.data;
          } catch (error) {
            console.warn("재요청 후 상세 재조회 실패", error);
          }
          if (docData) {
            storeRecalledDocInSession(docData, empId);
            await listAllDrafts();
          }
          window.location.href = window.contextPath + "/approval/templateList";
        } else {
          await showError({ title: "재요청", text: "재요청 실패. 응답 상태: " + resp.status });
        }
      } catch (error) {
        console.error("재요청 호출 실패", error);
        await showError({ title: "재요청", text: "재요청 호출 실패" });
      }
    });
  }

  
  // 페이지 로드시 상세 정보 호출
  loadDraftDetail();
});
