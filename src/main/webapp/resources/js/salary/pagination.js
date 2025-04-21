/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일       			수정자           수정내용
 * -----------    	-------------    ---------------------------
 * 2025. 4. 5.     	youngjun        페이징 기능 분리 및 적용
 *
 * </pre>
 */

function renderPagination() {
  const totalPages = Math.ceil(filteredList.length / pageSize);
  let html = "";

  for (let i = 1; i <= totalPages; i++) {
    html += `<li class="page-item ${i === currentPage ? 'active' : ''}">
      <a class="page-link" href="#" onclick="goPage(${i})">${i}</a>
    </li>`;
  }

  document.getElementById("pagination").innerHTML = html;
}

function goPage(page) {
  currentPage = page;
  renderSalaryTable();
}

function changePageSize(size) {
  pageSize = parseInt(size);
  currentPage = 1;
  renderSalaryTable();
}

function filterTable(keyword = "") {
  keyword = keyword.toLowerCase();
  filteredList = salaryList.filter(row =>
    Object.values(row).some(val => val.toLowerCase().includes(keyword))
  );
  currentPage = 1;
  renderSalaryTable();
}

