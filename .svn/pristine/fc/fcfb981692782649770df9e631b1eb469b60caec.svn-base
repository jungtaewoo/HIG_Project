/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 4. 1.     	CHOI             종결함 리스트 
 *
 * </pre>
 */


$(document).ready(function () {
    // 로그인한 결재자 ID를 먼저 가져온 후, 결재 진행함 목록을 조회한다.
    fetchApproverId();
    
    // 상태 필터 select의 값이 변경되면 재렌더링
    $('#statusFilter').on('change', function(){
        renderApproverProcessList(window.approverData);
    });
});

// 전역 변수에 결재자 ID와 데이터를 저장
window.approverId = null;
window.approverData = [];

/**
 * 로그인한 결재자 ID를 AJAX로 조회하는 함수.
 */
function fetchApproverId() {
    $.ajax({
        url: "/approvalProcess/getApproverId",
        type: "GET",
        dataType: "text",
        success: function(empId) {
            console.log("로그인한 결재자 ID:", empId);
            window.approverId = empId;
            // hidden input에도 설정 (뷰에서 활용)
            $('#approverId').val(empId);
            // 결재 진행함 목록을 조회
            fetchApproverProcessList();
        },
        error: function(xhr, status, error) {
            console.error("결재자 ID 조회 실패:", error);
            // 에러 처리 (예: 로그인 페이지로 리다이렉트)
        }
    });
}

/**
 * 결재 진행함 목록 데이터를 AJAX로 조회
 */
function fetchApproverProcessList(){
    // approverId가 전역변수에 설정되어 있다면 사용, 아니면 기본값 사용
    const approverId = window.approverId || 'defaultApproverId';

    $.ajax({
        url: "/approval/approver/complitedList",
        type: "GET",
        data: { approverId: approverId },
        dataType: "json",
        success: function(data) {
            console.log("결재 진행함 리스트:", data);
            // 저장해두고 랜더링
            window.approverData = data;
            renderApproverProcessList(data);
        },
        error: function(xhr, status, error){
            console.error("결재 진행함 문서 조회 실패:", error);
            $("#approverComplitedListContainer").html(`<tr><td colspan="10">문서 조회 중 오류가 발생했습니다.</td></tr>`);
        }
    });
}

/**
 * 조회한 결재 진행함 목록을 테이블에 랜더링
 * @param {Array} data - REST API에서 받은 결재 진행함 데이터
 */
function renderApproverProcessList(data) {
    // 선택된 필터 값 (all, 반려, 승인)
    let filterStatus = $('#statusFilter').val();

    // 필터링: 선택값이 'all'이 아니면, 데이터 중에서 aprStatus가 해당 값과 일치하는 것만 사용
    let filteredData = (filterStatus === 'all')
        ? data
        : data.filter(doc => doc.aprStatus === filterStatus);

    let html = "";

    if (!filteredData || filteredData.length === 0) {
        html = `<tr><td colspan="10">등록된 문서가 없습니다.</td></tr>`;
    } else {
        filteredData.forEach(function(doc) {
            // 결재 진행 상태: 만약 aprStatus가 "승인"이면 "승인중 (결재자명)"으로 표시
            let processStatus = doc.aprStatus;
            if (processStatus === "승인") {
                processStatus = "최종승인 (" + (doc.approverName || '-') + ")";
            }
            
            // 첨부파일은 쿼리 결과에 없는 경우 'N' 처리
            let fileHtml = 'N';

            html += `<tr>
                <td>${doc.draftId || '-'}</td>
                <td>
                    <a href="/approval/draft/detailView?draftId=${doc.draftId}" class="draftDetailLink">
                        ${doc.draftTitle || '-'}
                    </a>
                </td>
                <td>${formatDate(doc.draftDate) || '-'}</td>
                <td>${doc.draftStatus || '-'}</td>
                <td>${doc.draftEmpName || '-'}</td>
                <td>${doc.approverDepartmentName || '-'}</td>
                <td>${doc.approverName || '-'}</td>
                <td>${processStatus}</td>
                <td>${fileHtml}</td>
            </tr>`;
        });
    }

    $("#approverComplitedListContainer").html(html);
    initializeDataTable();
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
 * DataTable 초기화 함수
 */
function initializeDataTable() {
    // DataTable 인스턴스를 새로 생성하기 전에 기존 인스턴스가 있으면 파기 (재랜더링 시 중복 초기화 방지)
    if ($.fn.DataTable && $.fn.DataTable.isDataTable('#approverComplitedTable')) {
        $('#approverComplitedTable').DataTable().destroy();
    }
    const dataTable = new simpleDatatables.DataTable("#approverComplitedTable", {
        searchable: true,
        fixedHeight: false,
    });

    // 페이지 수 조절
    document.getElementById("entriesPerPage")?.addEventListener("change", function() {
        dataTable.pageLength = parseInt(this.value);
        dataTable.refresh();
    });

    // 검색 기능
    document.getElementById("searchBox")?.addEventListener("input", function() {
        dataTable.search(this.value);
    });
}