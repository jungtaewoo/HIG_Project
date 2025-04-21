/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 28.     	KHS            최초 생성
 *
 * </pre>
 */


document.addEventListener("DOMContentLoaded", function(){
// 아래가 사용할 js코드
const menuItem = document.querySelector(`a[href*="${location.pathname}"]`);
if (menuItem) {
    // 해당 메뉴 링크의 직접 부모 (대개 <li class="submenu-item">)에 active 클래스 추가
    const activeSubItem = menuItem.parentElement;
    if (activeSubItem) {
        activeSubItem.classList.add("active");
    }
	$(activeSubItem).parents(".submenu").addClass("submenu-open");
	$(activeSubItem).parents(".has-sub").addClass("active");

	}
})