/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자           수정내용
 *  -----------      -------------    ---------------------------
 * 2025. 4. 1.        youngjun            최초 생성
 * 2025. 4. 11.       youngjun             일괄확정/단건확정 버튼 상태, 모달 처리 동기화 개선
 * 2025. 4. 11.       youngjun             페이지 전체 반영 및 payStatus 동기화 개선
 * </pre>
 */
let allStatus = false;
let salaryTableInstance = null;

function initializeDataTable() {
  if ($.fn.DataTable.isDataTable("#salaryTable")) {
    $("#salaryTable").DataTable().clear().destroy();
  }

  salaryTableInstance = $("#salaryTable").DataTable({
    responsive: true,
    scrollX: true,
    language: {
      url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/ko.json"
    }
  });
}

let targetFinalButton = null;
let targetFinalInfo = {};

function salaryFinal(button) {
  const tr = button.closest("tr");
  const empId = tr.querySelector("td[data-salaryid]")?.textContent.trim();
  const salaryId = tr.querySelector("td[data-salaryid]")?.getAttribute("data-salaryid");
  const netSalary = parseInt(tr.querySelector("td:nth-child(11)")?.innerText.replace(/,/g, "") || 0);
  const payStatus = button.innerText.trim();
  const requestBtn = tr.querySelector("button[onclick*='salaryRequest']");
  const requestStatus = requestBtn?.innerText.trim();

  if (payStatus === "확정" && requestStatus === "승인") {
    alert("확정취소 불가: 승인된 급여입니다.");
    return;
  }

  targetFinalButton = button;
  targetFinalInfo = { empId, salaryId, payStatus };

  console.log("payStatus1",payStatus);
  
  document.getElementById("modalEmpCount").innerText = 1;
  document.getElementById("modalTotalAmount").innerText = netSalary.toLocaleString();

  const modal = new bootstrap.Modal(document.getElementById("confirmModal"));
  modal.show();

  const finalBtn = document.getElementById("confirmFinal");
  const newFinalBtn = finalBtn.cloneNode(true);
  finalBtn.parentNode.replaceChild(newFinalBtn, finalBtn);

  newFinalBtn.innerText = payStatus === "확정" ? "확정취소" : "확정진행";
  newFinalBtn.addEventListener("click", () => doFinalSingle(modal));
}

function doFinalSingle(modalInstance) {
  const btn = targetFinalButton;
  const { empId, salaryId, payStatus } = targetFinalInfo;
  const afterStatus = payStatus === "확정" ? "확정대기" : "확정";

  const selectedDate = document.querySelector("#payDate").value;
  const [payYear, payMonth] = selectedDate.split("-");

  $.ajax({
    url: "/salary/final",
    type: "POST",
    data: { empId, salaryId, date: payYear, date: payMonth },
    success: function (res) {
		console.log("단건[res 데이터]",res);
      if (res.cnt === "1") {
        btn.innerText = afterStatus;
        btn.classList.toggle("btn-danger", afterStatus === "확정");
        btn.classList.toggle("btn-outline-primary", afterStatus === "확정대기");

        const td = btn.closest("td");
        let dateDiv = td.querySelector(".confirm-date");

        if (afterStatus === "확정") {
          if (!dateDiv) {
            dateDiv = document.createElement("small");
            dateDiv.className = "confirm-date text-muted mt-1";
            td.appendChild(dateDiv);
          }
          dateDiv.innerText = res.finalDate;
        } else {
          if (dateDiv) dateDiv.remove();
        }

        //  DataTable 내 데이터 직접 반영
        const table = $('#salaryTable').DataTable();
        const rowIdx = table.rows().indexes().filter((idx) => {
          const data = table.row(idx).data();
          return data.salaryId == salaryId;
        })[0];
        if (rowIdx !== undefined) {
          const rowData = table.row(rowIdx).data();
          rowData.payStatus = afterStatus;
          table.row(rowIdx).data(rowData).draw(false);
        }

        document.getElementById("confirmCount").innerText = `${res.finalCount}명`;
        updateAllStatusfinalFlag();
        updateBulkButtonText();
		
        showToast("✅ 작업이 성공적으로 완료되었습니다.");
        modalInstance.hide();
      } else {
        alert("처리 중 오류 발생");
      }
    },
    error: function () {
      alert("서버 통신 실패");
    }
  });
}

async function doFinalBulk(obj) {
  const { payYear, payMonth, salaryList } = window.bulkParam;
  const param = { salaryList, payYear, payMonth };

  try {
    const res = await $.ajax({
      url: `/salary/final/all?payYear=${payYear}&payMonth=${payMonth}`,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(param)
    });

	console.log("일괄[res데이터]",res);
    //  모든 페이지의 버튼을 갱신하기 위해 DataTable 전체 갱신
    const table = $('#salaryTable').DataTable();
	
	console.log("일괄[table데이터]",table);
    table.rows().every(function () {
      const data = this.data();
      if (salaryList.find(s => s.salaryId == data.salaryId)) {
        data.payStatus = allStatus ? "확정대기" : "확정";
        data.confirmDate = allStatus ? null : new Date().toISOString().split("T")[0];
        this.data(data);
      }
	  console.log("일괄[data데이터]",data);
    });
    table.draw(false);

    document.querySelector("#confirmCount").innerText = `${res.finalCount}명`;
    updateAllStatusfinalFlag();
    updateBulkButtonText();
	
    showToast("✅ 일괄처리 완료");
    return true;
  } catch (e) {
    alert("일괄 처리 실패");
    return false;
  }
}

function openFinalBulkModal(obj) {
  const selectedDate = document.querySelector("#payDate").value;
  const [selectYear, selectMonth] = selectedDate.split("-");
  updateAllStatusfinalFlag();

  const table = $('#salaryTable').DataTable();
  const allRows = table.rows().data().toArray();

  const salaryList = [];
  let totalNetAmount = 0;

  allRows.forEach(row => {
    const { empId, salaryId, netSalary, payStatus, paymentRequest } = row;
   
   
   //  확정취소일 경우, '승인' 상태인 건 제외
    if (allStatus && payStatus === "확정") {
       if (paymentRequest === "승인") return; //  승인된 건은 확정취소 불가
     }

     if ((!allStatus && payStatus === "확정대기") || (allStatus && payStatus === "확정")) {
       if (empId && salaryId) {
         salaryList.push({ empId: String(empId), salaryId: String(salaryId) });
         totalNetAmount += netSalary;
       }
     }
   });

  if (salaryList.length === 0) {
    alert(allStatus ? "확정취소 대상이 없습니다." : "확정할 대상이 없습니다.");
    return;
  }

  window.bulkParam = { obj, payYear: selectYear, payMonth: selectMonth, salaryList };

  document.getElementById("modalEmpCount").innerText = salaryList.length;
  document.getElementById("modalTotalAmount").innerText = totalNetAmount.toLocaleString();

  const finalBtn = document.getElementById("confirmFinal");
  const newFinalBtn = finalBtn.cloneNode(true);
  finalBtn.parentNode.replaceChild(newFinalBtn, finalBtn);

  updateBulkButtonText(newFinalBtn);

  newFinalBtn.addEventListener("click", async () => {
    const result = await doFinalBulk(obj);
    if (result) {
      const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("confirmModal"));
      modal.hide();
    }
  });

  const modal = new bootstrap.Modal(document.getElementById("confirmModal"));
  modal.show();
}

function updateAllStatusfinalFlag() {
  const allRows = $('#salaryTable').DataTable().rows().data().toArray();
  allStatus = allRows.every(row => row.payStatus === '확정');
}

function updateBulkButtonText(targetButton = null) {
  const btn = targetButton || document.getElementById("confirmFinal");
  if (!btn) return;
  btn.innerText = allStatus ? "일괄확정취소" : "일괄확정";
}



////////////////////////////////////////////////////////////////////////////////////////
let targetReqButton = null;
let targetReqInfo = {};
let allReqStatus = false;

//단건승인 모달
function salaryRequest(button) {
  const tr = button.closest("tr");
  const empId = tr.querySelector("td[data-salaryid]")?.textContent.trim();
  const salaryId = tr.querySelector("td[data-salaryid]")?.getAttribute("data-salaryid");
  const netSalary = parseInt(tr.querySelector("td:nth-child(11)")?.innerText.replace(/,/g, "") || 0);
  const payStatus = button.innerText.trim();

  const finalBtn = tr.querySelector("button[onclick*='salaryFinal']");
  const finalStatus = finalBtn?.innerText.trim();

  if (finalStatus === "확정대기") {
    alert("지급 요청은 급여가 확정된 이후 가능합니다.");
    return;
  }

  targetReqButton = button;
  targetReqInfo = { empId, salaryId, payStatus };

  document.getElementById("requestEmpCount").innerText = 1;
  document.getElementById("requestTotalAmount").innerText = netSalary.toLocaleString();

  const modal = new bootstrap.Modal(document.getElementById("requestModal"));
  modal.show();

  const reqBtn = document.getElementById("confirmRequest");
  const newReqBtn = reqBtn.cloneNode(true);
  reqBtn.parentNode.replaceChild(newReqBtn, reqBtn);

  newReqBtn.innerText = payStatus === "승인" ? "승인취소" : "지급승인";
  newReqBtn.addEventListener("click", () => doRequestSingle(modal));
}


//단건 승인 ajax
function doRequestSingle(modalInstance) {
  const btn = targetReqButton; // salaryRequest 함수에서 설정된 대상 버튼
  const { empId, salaryId, payStatus } = targetReqInfo; // salaryRequest 함수에서 설정된 정보
  const afterStatus = payStatus === "승인" ? "승인대기" : "승인"; // 변경될 새로운 상태

  const selectedDate = document.querySelector("#payDate").value;
  const [payYear, payMonth] = selectedDate.split("-");

  $.ajax({
    url: "/salary/request", // 단건 승인 요청 URL
    type: "POST",
    data: $.param({ empId, salaryId, payYear, payMonth }), // 서버에 필요한 데이터 전송
    success(res) {
      if (res.cnt === 1) {
        const table = $('#salaryTable').DataTable();

        // 업데이트할 행의 인덱스 찾기
        const rowIdx = table.rows().indexes().filter((idx) => {
          const data = table.row(idx).data();
          // salaryId 비교 시 타입 일치 고려
          return String(data.salaryId) == String(salaryId);
        })[0];

        // 해당 행이 테이블에 존재하는 경우
        if (rowIdx !== undefined) {
          //데이터 객체 가져오기 (let으로 선언하여 수정 가능하게)
          let rowData = table.row(rowIdx).data();

          // 1. 'paymentRequest' 상태 및 관련 날짜 업데이트
          rowData.paymentRequest = afterStatus;
          rowData.transferRequestDate = (afterStatus === "승인") ? (res.reqDate || new Date().toISOString().split("T")[0]) : null; // 서버 응답 날짜 우선 사용


          // 2. '승인' 상태가 되었을 경우, 3초 후 'paid' 상태 업데이트 예약
          if (afterStatus === "승인") {
            // setTimeout 내부에서 사용할 정보 저장
            let currentRowIndex = rowIdx; // 클로저를 위해 현재 인덱스 저장
            let currentSalaryId = salaryId; // 디버깅용 ID

            console.log(`[${currentSalaryId}] 단건 승인 성공. 3초 후 지급완료 처리 예약.`);

            setTimeout(() => {
              // 3초 후 실행될 코드
              console.log(`[${currentSalaryId}] 3초 경과. 지급완료 처리 시작.`);
              const targetRow = table.row(currentRowIndex);

              // 행이 여전히 존재하는지 확인 후 데이터 업데이트
              if (targetRow.any()) {
                  let rowDataForPaid = targetRow.data(); // 최신 데이터 다시 가져오기
                  if (rowDataForPaid) {
                      //데이터 객체의 'paid' 상태와 날짜 업데이트
                      rowDataForPaid.paid = '지급완료';
                      rowDataForPaid.payDate = new Date().toISOString().split("T")[0]; // 예시 지급 날짜

                      //변경된 데이터로 해당 행만 다시 그리기 (renderActionButton 자동 호출)
                      targetRow.data(rowDataForPaid).draw(false);
                      console.log(`[${currentSalaryId}] 지급완료 상태로 데이터 업데이트 및 redraw 완료.`);

                      // 필요 시: 지급 완료 건수 등 요약 정보 업데이트
                      // reloadSummary();
                  } else {
                     console.warn(`[${currentSalaryId}] (단건) 3초 후 데이터 업데이트 시점의 행 데이터를 찾을 수 없음`);
                  }
              } else {
                  console.warn(`[${currentSalaryId}] (단건) 3초 후 데이터 업데이트 시점의 행을 찾을 수 없음`);
              }
            }, 3000);
          }

          // 3. 우선 'paymentRequest' 상태 변경사항만 먼저 테이블에 반영하여 그리기
          console.log(`[${salaryId}] paymentRequest '${afterStatus}' 상태로 데이터 업데이트 및 redraw 실행.`);
          table.row(rowIdx).data(rowData).draw(false);

        } else {
           console.warn(`[${salaryId}] 업데이트할 행을 찾지 못함 (rowIdx: ${rowIdx})`);
        }

        //  나머지 로직 (요약정보 업데이트, 플래그, 토스트, 모달 닫기)
        document.querySelector("#reqPayCount").innerText = `${res.reqCount}명`; // 서버 응답의 reqCount 사용
        updateAllRequestStatus();
        updateBulkRequestButtonText();
        showToast("✅ 승인 작업이 성공적으로 완료되었습니다.");
        modalInstance.hide(); // salaryRequest 함수에서 받은 modalInstance 사용

      } else {
        alert("승인 처리 중 서버에서 오류가 발생했습니다.");
      }
    },
    error() {
      alert("서버 통신 실패");
    }
  });
}



// 일괄 승인 Ajax 요청
function openRequestBulkModal(obj) {
   updateAllRequestStatus(); //  현재 전체 승인 상태 최신화
  const selectedDate = document.querySelector("#payDate").value;
  const [selectYear, selectMonth] = selectedDate.split("-");

  const table = $('#salaryTable').DataTable();
  const allRows = table.rows().data().toArray();

  const salaryList = [];
  let totalNetAmount = 0;

  allRows.forEach(row => {
    const { empId, salaryId, netSalary, paymentRequest, payStatus } = row;
    if (payStatus !== '확정') return; //  확정된 건만 승인 가능
    if ((!allReqStatus && paymentRequest === "승인대기") || (allReqStatus && paymentRequest === "승인")) {
      if (empId && salaryId) {
        salaryList.push({ empId, salaryId });
        totalNetAmount += netSalary;
      }
    }
  });

  if (salaryList.length === 0) {
    alert(allReqStatus ? "승인취소 대상이 없습니다." : "확정된 급여 중 승인할 대상이 없습니다.");
    return;
  }

  window.reqBulkParam = {
    obj,
    payYear: selectYear,
    payMonth: selectMonth,
    salaryList
  };

  document.getElementById("requestEmpCount").innerText = salaryList.length;
  document.getElementById("requestTotalAmount").innerText = totalNetAmount.toLocaleString();

  const reqBtn = document.getElementById("confirmRequest");
  const newReqBtn = reqBtn.cloneNode(true);
  reqBtn.parentNode.replaceChild(newReqBtn, reqBtn);

  newReqBtn.innerText = allReqStatus ? "일괄 승인취소" : "일괄 승인";

  newReqBtn.addEventListener("click", async () => {
    const result = await doRequestBulk(obj);
    if (result) {
      const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById("requestModal"));
      modal.hide();
    }
  });

  const modal = new bootstrap.Modal(document.getElementById("requestModal"));
  modal.show();
}

async function doRequestBulk(obj) {
  // 함수 시작 로그 (디버깅 시 유용)
  console.log("doRequestBulk 함수 시작");
  const { payYear, payMonth, salaryList } = window.reqBulkParam;
  const param = { salaryList, payYear, payMonth };

  try {
    console.log("AJAX 요청 시작:", `/salary/requestAll?payYear=${payYear}&payMonth=${payMonth}`);
    const res = await $.ajax({
      url: `/salary/requestAll?payYear=${payYear}&payMonth=${payMonth}`,
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(param)
    });
    console.log("AJAX 성공 응답 수신:", res);

    console.log("DataTable 행 데이터 업데이트 시작...");
    const table = $('#salaryTable').DataTable();

    // DataTables의 모든 행을 순회하며 데이터 업데이트
    table.rows().every(function (rowIdx, tableLoop, rowLoop) { // 행 인덱스(rowIdx)를 받음
      let data = this.data(); // 현재 행의 데이터 객체 (let으로 선언하여 수정 가능하게)
      const rowNode = this.node(); // 현재 행의 TR 요소 (필요 시 사용)

      // 서버로 보낸 salaryList에 현재 행이 포함되어 있고, '확정' 상태인지 확인
      const match = salaryList.find(s => String(s.salaryId) == String(data.salaryId) && String(s.empId) == String(data.empId));

      if (match && data.payStatus === '확정') {
        // 새로운 승인 상태 결정 (일괄 승인/취소 여부에 따라)
        const newPaymentRequestStatus = allReqStatus ? "승인대기" : "승인";

        // 1. 데이터 객체의 'paymentRequest' 상태와 날짜 업데이트
        data.paymentRequest = newPaymentRequestStatus;
        data.transferRequestDate = newPaymentRequestStatus === "승인" ? (res.reqDate || new Date().toISOString().split("T")[0]) : null; // 서버 응답 날짜 우선 사용

        // ❗❗ 중요: 수정된 데이터로 DataTable 내부 캐시 업데이트 ❗❗
        //      (이것만 해도 아래 table.draw(false) 호출 시 '승인'/'승인대기' 상태는 반영됨)
        this.data(data);

        // 2. '승인'으로 상태가 변경된 경우, 3초 후 '지급완료' 상태로 변경 예약
        if (newPaymentRequestStatus === "승인") {
          // setTimeout에 전달할 현재 행의 인덱스와 salaryId 저장
          let currentRowIndex = rowIdx;
          let currentSalaryId = data.salaryId;

          console.log(`[${currentSalaryId}] 3초 후 지급완료 처리 예약`);

          setTimeout(() => {
            // 3초 후 실행될 코드
            // 중요: setTimeout 내부에서는 this나 data 변수가 현재 루프의 것을 참조한다는 보장이 없으므로,
            //       table 인스턴스와 저장해둔 행 인덱스(currentRowIndex)를 사용해 다시 데이터를 가져와 수정
            const targetRow = table.row(currentRowIndex);
            // 행이 여전히 테이블에 존재하는지 확인
            if (targetRow.any()) {
                let rowDataForPaid = targetRow.data(); // 해당 행의 최신 데이터 다시 가져오기
                if (rowDataForPaid) {
                    rowDataForPaid.paid = '지급완료'; // 'paid' 상태 업데이트
                    rowDataForPaid.payDate = new Date().toISOString().split("T")[0]; // 지급 날짜 업데이트 (예시)

                    // 변경된 데이터로 해당 행만 다시 그리기 (renderActionButton 호출됨)
                    targetRow.data(rowDataForPaid).draw(false);
                    console.log(`[${currentSalaryId}] 3초 후 지급완료 상태로 데이터 업데이트 및 redraw 완료`);
                } else {
                }
            } else {
            }
          }, 3000); // 3초 딜레이
        }

        // ❗❗ 여기서 버튼 DOM 직접 조작 코드 모두 제거! ❗❗
        //    (예: btn.innerText, btn.classList.toggle 등)
      }
    });

    // 모든 행 순회가 끝난 후, 테이블 전체를 한번 다시 그려서
    // 'paymentRequest' 상태 변경 등을 일괄 반영 (renderActionButton 호출됨)
    table.draw(false);
    console.log("DataTable 전체 redraw 완료 (일괄 승인/승인대기 상태 반영)");

    // --- 나머지 로직 (요약정보 업데이트, 플래그, 토스트 등) ---
    // res 객체에 reqPayCount 키가 있는지 확인 (서버 응답 확인 필요)
    const reqPayCountValue = res.reqPayCount ?? (allReqStatus ? 0 : salaryList.length); // 서버 응답 우선, 없으면 추정치
    document.querySelector("#reqPayCount").innerText = `${reqPayCountValue}명`;

    updateAllRequestStatus(); // 전체 승인 상태 플래그 업데이트
    updateBulkRequestButtonText(); // 상단 일괄 버튼 텍스트 업데이트

    showToast(allReqStatus ? "✅ 일괄 승인취소 완료" : "✅ 일괄 승인 완료"); // Toast 메시지
    console.log("doFinalBulk 함수 성공적으로 완료, true 반환");
    return true; // 성공

  } catch (e) {
    console.error("doFinalBulk 함수 실행 중 오류 발생:", e);
    alert("일괄 승인 처리 중 오류가 발생했습니다. 콘솔 로그를 확인하세요.");
    return false; // 실패
  }
}


//  전체 승인 상태 계산 함수 (모든 row가 '승인'이면 true)
function updateAllRequestStatus() {
  const table = $('#salaryTable').DataTable();
  const allRows = table.rows().data().toArray();
  allReqStatus = allRows
    .filter(row => row.payStatus === '확정')
    .every(row => row.paymentRequest === '승인');
}

function updateBulkRequestButtonText() {
     const reqBtn = document.querySelector("#allRequest");
     if (reqBtn) {
       reqBtn.innerText = allReqStatus ? "승인 일괄취소" : "승인 일괄신청";
     }
   }



// 공통 토스트 메시지
function showToast(msg) {
  const container = document.getElementById("toastArea");
  container.innerHTML = `
    <div class="toast align-items-center text-bg-success border-0 show" role="alert">
      <div class="d-flex">
        <div class="toast-body">${msg}</div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
      </div>
    </div>
  `;
  const toastEl = container.querySelector(".toast");
  const bsToast = new bootstrap.Toast(toastEl);
  bsToast.show();
}






