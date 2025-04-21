/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 20.     	정태우            최초 생성
 *
 * </pre>
 */
const now = new Date();
const year = now.getFullYear();
const month = String(now.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
const day = String(now.getDate()).padStart(2, '0');
let formattedDate = `${year}-${month}-${day}`;
const workDate = formattedDate;


document.addEventListener("DOMContentLoaded", () => {



function leavework() {
	$.ajax({
		url: '/workstauts/updateWorkStatus',
		type: 'POST',
		data: {
			statusId: 'STAT_03',
			empId: empId,
			workDate: workDate
		},
		success: function(response) {
		},
		error: function() {
		}
	});
}
function srartwork() {
	$.ajax({
		url: '/workstauts/updateWorkStatus',
		type: 'POST',
		data: {
			statusId: 'STAT_01',
			empId: empId,
			workDate: workDate
		},
		success: function(response) {
		},
		error: function() {
		}
	});
}

	
});
