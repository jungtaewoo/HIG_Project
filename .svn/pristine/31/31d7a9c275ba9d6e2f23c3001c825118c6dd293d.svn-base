/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 14.     	정태우            최초 생성
 *
 * </pre>
 */

// 상태 변경 메뉴 열기
	document.getElementById('changeStatus').addEventListener('click',function() {
		var layer = document.getElementById('layer_transition');
		if (layer.style.display === 'none'
				|| layer.style.display === '') {
			layer.style.display = 'block';
		} else {
			layer.style.display = 'none';
		}
	});

	// 상태 선택
	var statusItems = document.querySelectorAll('.timelineStatus');
	statusItems.forEach(function(item) {
		item.addEventListener('click', function() {
			var statusText = item.textContent;
			var myStatus = document.getElementById('myStatus');
			myStatus.innerHTML = statusText + '&nbsp;&nbsp;<span class="ic_side ic_show_down"></span>';

			// 메뉴 닫기
			document.getElementById('layer_transition').style.display = 'none';
		});
	});