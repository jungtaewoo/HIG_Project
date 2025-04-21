// 문서가 완전히 로드되었을 때 실행
document.addEventListener('DOMContentLoaded', function() {

    // id가 table1이거나 클래스가 table1인 모든 테이블에 대해 DataTable 초기화
    document.querySelectorAll("#table1, .table1").forEach(table => {
        new simpleDatatables.DataTable(table);
    });

	// 🔻 "페이지당 항목 수 선택(select)" 요소를 Bootstrap 5 스타일에 맞게 위치 조정하고 클래스 추가
	function adaptPageDropdown() {
		// 테이블 wrapper 내부에서 .dataTable-selector를 찾음 (select 요소)
		const selector = dataTable.wrapper.querySelector(".dataTable-selector");

		// 부모 요소를 기준으로 위치를 조정 (Bootstrap 구조에 맞게 이동)
		selector.parentNode.parentNode.insertBefore(selector, selector.parentNode);

		// Bootstrap 5 스타일의 select 박스를 적용
		selector.classList.add("form-select");
	}

	// 🔻 페이지네이션 요소들을 Bootstrap 5 스타일로 바꾸는 함수
	function adaptPagination() {
		// 페이지네이션 리스트에 Bootstrap용 클래스 추가
		const paginations = dataTable.wrapper.querySelectorAll("ul.dataTable-pagination-list");
		for (const pagination of paginations) {
			pagination.classList.add("pagination", "pagination-primary");
		}

		// 각 페이지네이션 li 항목에 Bootstrap 스타일 적용
		const paginationLis = dataTable.wrapper.querySelectorAll("ul.dataTable-pagination-list li");
		for (const paginationLi of paginationLis) {
			paginationLi.classList.add("page-item");
		}

		// 페이지 번호 링크(a 태그)에 Bootstrap 클래스 추가
		const paginationLinks = dataTable.wrapper.querySelectorAll("ul.dataTable-pagination-list li a");
		for (const paginationLink of paginationLinks) {
			paginationLink.classList.add("page-link");
		}
	}

	// 🔁 페이지네이션을 갱신하는 함수 (재호출용)
	const refreshPagination = () => {
		adaptPagination();
	}

	// 🔄 테이블이 처음 렌더링 되었을 때 select, pagination에 Bootstrap 스타일 적용
	dataTable.on("datatable.init", () => {
		adaptPageDropdown();
		refreshPagination();
	});

	// 🔄 테이블이 업데이트 될 때 (정렬, 검색 등) 페이지네이션 재적용
	dataTable.on("datatable.update", refreshPagination);
	dataTable.on("datatable.sort", refreshPagination);

	// 🔄 페이지 넘길 때마다 페이지네이션 재적용
	dataTable.on("datatable.page", adaptPagination);
});
