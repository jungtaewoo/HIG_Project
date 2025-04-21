/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일        수정자           수정내용
 *  -----------  -------------    ---------------------------
 * 2025. 3. 19.      CHOI            최초 생성
 * 2025. 4.  1.      CHOI            기안자 문서 상태 필터 select 태그 추가 및 랜더링 로직 수정
 * 2025. 4.  5.      CHOI            최종 승인/반려 결재자 표시 로직 수정
 * </pre>
 */
import {formatKoreanDateTime} from '../common/koreanDateType.js';

$(document).ready(function () {
    // 1) 전체 문서 조회
    fetchMyDraftDocument();

    // 2) 페이지 로드 후 localStorage 확인
    const savedStatus = localStorage.getItem("draftStatusFilterValue");
    if (savedStatus) {
        // select 태그에 값 설정
        $("#draftStatusFilter").val(savedStatus);
        // 이미 fetchMyDraftDocument()가 끝나면 window.draftData가 있으니 필터 가능
        // 혹은 setTimeout(...) 으로 약간 지연해도 됨
        filterDraftDocuments(window.draftData);

        // 사용 후 제거(옵션)
        localStorage.removeItem("draftStatusFilterValue");
    }
});

function fetchMyDraftDocument(){
    $.ajax({
        url:"/approvalProcess/mydrafts",
        type:"GET",
        dataType: "json",
        success : function (data){
            console.log("기안문서 목록:", data);
            // 전역 변수에 저장
            window.draftData = data;
            // HTML에 상태 필터 select 태그와 테이블을 함께 랜더링
            readerDraftDocuments(data);
            // 필터 select에 이벤트 바인딩
            $('#draftStatusFilter').on('change', function(){
                filterDraftDocuments(window.draftData);
            });
        },
        error: function(xhr, status, error){
            console.error("기안 문서 조회 실패:", error);
        }
    });
}

/**
 * 결재현황 텍스트를 반환하는 함수 (최종 승인 또는 반려일 경우 "최종 승인 / 결재자이름" 형태로 표시)
 * @param {Object} doc - 기안 문서 데이터
 * @returns {String} 결재현황을 나타내는 문자열
 */
function renderApproverText(doc) {
  // 기본값은 "결재 진행 중"
  let approversText = "결재 진행 중";

  // 결재자 목록이 존재하는 경우
  if (doc.draftapproverList && doc.draftapproverList.length > 0) {
    // 1) 우선 '반려' 결재자를 찾는다 (최종 결재자가 반려한 경우)
    let finalReject = doc.draftapproverList.find(a => a.approverStatus === "반려");
    if (finalReject) {
      // 반려자가 존재하면 "최종 반려 / 결재자이름" 형태로 표시
      approversText = `최종 반려 / ${finalReject.approverName}`;
    } else {
      // 2) 반려자가 없으면 '승인' 결재자를 찾는다 (최종 결재)
      let finalApprove = doc.draftapproverList.find(a => a.approverStatus === "승인");
      if (finalApprove) {
        approversText = `최종 승인 / ${finalApprove.approverName}`;
      } else {
        // 3) '승인'도 '반려'도 없으면, 전체 결재 라인을 표시
        approversText = doc.draftapproverList
          .map(a => `${a.approverName} (${a.approverStatus})`)
          .join(", ");
      }
    }
  } else {
    // 결재자 목록이 비어있을 경우, 문서 상태에 따라 표시
    if (doc.draftStatus === "완료") {
      approversText = "최종 승인";
    } else if (doc.draftStatus === "반려") {
      approversText = "반려";
    } else if (doc.draftStatus === "회수") {
      approversText = "문서 회수됨";
    }
  }
 
  return approversText;
}

/**
 * 기안 문서 목록과 상태 필터 select 태그를 함께 HTML에 랜더링하는 함수.
 * @param {Array} data - REST API에서 받은 기안 문서 데이터
 */
function readerDraftDocuments(data) {
    let html = `
    <!-- 상태 필터 select (기안자 기준) -->
    <div style="margin-bottom: 15px;">
        <label for="draftStatusFilter">문서 상태 선택</label>
        <select id="draftStatusFilter" class="form-select" aria-label="Default select example">
            <option value="all">전체</option>
            <option value="대기">대기</option>
            <option value="회수">회수</option>
            <option value="완료">완료</option>
            <option value="반려">반려</option>
        </select>
    </div>
    <table id="approvalTable" class="table table-striped datatable">
        <thead>
            <tr>
                <th><a href="#" class="dataTable-sorter">문서번호</a></th>
                <th><a href="#" class="dataTable-sorter">제목</a></th>
                <th><a href="#" class="dataTable-sorter">기안일</a></th>
                <th><a href="#" class="dataTable-sorter">기안자</a></th>
                <th><a href="#" class="dataTable-sorter">부서명</a></th>
                <th><a href="#" class="dataTable-sorter">결재현황</a></th>
                <th><a href="#" class="dataTable-sorter">최종결과</a></th>
            </tr>
        </thead>
        <tbody>`;
    
    data.forEach(function (doc) {
        // 결재자 목록
        let approvers = renderApproverText(doc);
        // **이 부분이 핵심**: draftStatus 대신 getStatusBadge(doc.draftStatus) 사용
        let statusBadge = getStatusBadge(doc.draftStatus);
		
        html += `<tr>
            <td>${doc.draftId || '-'}</td>
            <td>
                <a href="/approval/myDraftDetailView?draftId=${doc.draftId}" class="draftDetailLink">
                    ${doc.draftTitle || '-'}
                </a>
            </td>
            <td>${formatKoreanDateTime(doc.draftDate) || '-'}</td>
            <td>${doc.employee ? doc.employee.name : '미등록'}</td>
            <td>${doc.draftDepartmentName || '-'}</td>
            <td>${approvers}</td>
            <td>${statusBadge}</td>
        </tr>`;
    });

    html += `</tbody></table>`;

    $("#draftDocumentsContainer").html(html);
    initializeDataTable();
}

/**
 * 선택한 상태에 따라 기안 문서 목록을 필터링하여 랜더링하는 함수.
 * @param {Array} data - 전체 기안 문서 데이터
 */
function filterDraftDocuments(data) {
  let filterStatus = $('#draftStatusFilter').val();
  let filteredData;

  if(filterStatus === 'all') {
    filteredData = data;
  } else if(filterStatus === '회수') {
    filteredData = data.filter(doc => doc.draftStatus === '회수');
  } else {
    filteredData = data.filter(doc => doc.draftStatus === filterStatus);
  }

  let html = `
  <!-- 상태 필터 select (기안자 기준) -->
  <div style="margin-bottom: 15px;">
      <label for="draftStatusFilter">문서 상태 선택: </label>
      <select id="draftStatusFilter" class="form-select" aria-label="Default select example">
          <option value="all" ${filterStatus === 'all' ? 'selected' : ''}>전체</option>
          <option value="대기" ${filterStatus === '대기' ? 'selected' : ''}>대기</option>
          <option value="회수" ${filterStatus === '회수' ? 'selected' : ''}>회수</option>
          <option value="완료" ${filterStatus === '완료' ? 'selected' : ''}>완료</option>
          <option value="반려" ${filterStatus === '반려' ? 'selected' : ''}>반려</option>
      </select>
  </div>
  <table id="approvalTable" class="table table-striped datatable">
      <thead>
          <tr>
              <th>문서번호</th>
              <th>제목</th>
              <th>기안일</th>
              <th>기안자</th>
              <th>부서명</th>
              <th>결재현황</th>
              <th>최종결과</th>
          </tr>
      </thead>
      <tbody>`;

  if (!filteredData || filteredData.length === 0) {
    html += `<tr><td colspan="8">등록된 문서가 없습니다.</td></tr>`;
  } else {
    filteredData.forEach(function (doc) {
      let approvers = renderApproverText(doc);
      let statusBadge = getStatusBadge(doc.draftStatus);

      html += `
        <tr>
          <td>${doc.draftId || '-'}</td>
          <td>
            <a href="/approval/myDraftDetailView?draftId=${doc.draftId}" class="draftDetailLink">
              ${doc.draftTitle || '-'}
            </a>
          </td>
          <td>${formatKoreanDateTime(doc.draftDate) || '-'}</td>
          <td>${doc.employee ? doc.employee.name : '미등록'}</td>
          <td>${doc.draftDepartmentName || '-'}</td>
          <td>${approvers}</td>
          <td>${statusBadge}</td>
        </tr>
      `;
    });
  }

  html += `</tbody></table>`;

  $("#draftDocumentsContainer").html(html);
  initializeDataTable();

  // 이벤트 재바인딩
  $('#draftStatusFilter').on('change', function(){
      filterDraftDocuments(window.draftData);
  });
}

/**
 * 날짜 포맷 변환 (YYYY-MM-DD HH:mm 형식)
 * @param {String} dateString - 날짜 문자열
 * @returns {String} 포맷된 날짜 문자열
 */
function formatDate(dateString) {
    if (!dateString) return "-";
    let date = new Date(dateString);
    if (isNaN(date.getTime())) {
        console.error("Invalid date format:", dateString);
        return "-";
    }
    let year = date.getFullYear();
    let month = ('0' + (date.getMonth() + 1)).slice(-2);
    let day = ('0' + date.getDate()).slice(-2);
    let hours = ('0' + date.getHours()).slice(-2);
    let minutes = ('0' + date.getMinutes()).slice(-2);
    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

/**
 * 상태값(문자열)에 따라 Bootstrap 배지를 반환하는 함수
 * @param {String} status - 문서 상태(대기, 회수, 반려, 완료 등)
 * @returns {String} HTML 문자열(색상이 적용된 <span>)
 */
function getStatusBadge(status) {
  if (!status) return '-';

  let label;
  switch (status) {
    case '완료':
      // 초록색
      label = `<span class="badge bg-success">${status}</span>`;
      break;
    case '반려':
      // 빨간색
      label = `<span class="badge bg-danger">${status}</span>`;
      break;
    case '대기':
      // 회색
      label = `<span class="badge bg-secondary">${status}</span>`;
      break;
    case '회수':
      // 노란색
      label = `<span class="badge bg-info">${status}</span>`;
      break;
    default:
      // 그 외 상태는 파란색(info) 등
      label = `<span class="badge bg-info">${status}</span>`;
      break;
  }
  return label;
}

async function openDraftDetailModal(draftId) {
  if (!draftId) {
    alert("문서 ID가 올바르지 않습니다.");
    return;
  }
  
  // 모달 열기 (Bootstrap 5 방식)
  const modalElement = document.getElementById('draftDetailModal');
  const myModal = new bootstrap.Modal(modalElement);
  myModal.show();
  
  // 모달 콘텐츠 영역 초기화 (로딩 상태 표시)
  document.getElementById('draftDetailContent').innerHTML = `<p class="text-center">로딩중...</p>`;
  
  // URL 구성 (contextPath 필요 시 적용)
  const contextPath = window.contextPath || "";
  const url = contextPath + "/approval/myDraftDetailViewPartial?draftId=" + draftId;
  
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error("네트워크 응답이 올바르지 않습니다.");
    }
    
    // 불러온 HTML 콘텐츠를 문자열로 추출
    const html = await response.text();
    
    // 모달 내부 콘텐츠 영역에 삽입
    document.getElementById('draftDetailContent').innerHTML = html;
  } catch (error) {
    console.error("문서 상세 내용을 불러오는 중 오류 발생:", error);
    document.getElementById('draftDetailContent').innerHTML = "<p>상세 내용을 불러오는 중 오류가 발생했습니다.</p>";
  }
}

/**
 * DataTable 초기화 함수
 */
function initializeDataTable() {
    if ($.fn.DataTable && $.fn.DataTable.isDataTable('#approvalTable')) {
        $('#approvalTable').DataTable().destroy();
    }
    const dataTable = new simpleDatatables.DataTable("#approvalTable", {
        searchable: true,
        fixedHeight: false
    });

    document.getElementById("entriesPerPage")?.addEventListener("change", function() {
        dataTable.pageLength = parseInt(this.value);
        dataTable.refresh();
    });

    document.getElementById("searchBox")?.addEventListener("input", function() {
        dataTable.search(this.value);
    });
}
