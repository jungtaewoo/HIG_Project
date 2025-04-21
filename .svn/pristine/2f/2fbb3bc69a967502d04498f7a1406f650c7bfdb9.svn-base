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
// 일한 시간 업데이트
function updateWorkedTime() {
	document.querySelectorAll(".workDuration").forEach(span => {
		let workStart = span.dataset.workstart;  // 출근 시간 (HH24:MI 형식)
		let workEnd = span.dataset.workend;      // 퇴근 시간 (HH24:MI 형식)

		if (!workStart || workStart.trim() === "") {
			return; // 출근 시간이 없으면 계산하지 않음
		}

		let [startHour, startMinute] = workStart.split(":").map(Number);
		let startTime = new Date();
		startTime.setHours(startHour, startMinute, 0); // 출근 시간 설정

		let workedSeconds = 0;

		if (workEnd && workEnd.trim() !== "") {
			// 퇴근 시간이 있을 경우, 출근 시간과 퇴근 시간 사이의 차이를 계산
			let [endHour, endMinute] = workEnd.split(":").map(Number);
			let endTime = new Date();
			endTime.setHours(endHour, endMinute, 0);
			workedSeconds = Math.floor((endTime - startTime) / 1000);
		} else {
			// 퇴근 시간이 없으면 현재 시간과 출근 시간의 차이를 계산
			let now = new Date();
			workedSeconds = Math.floor((now - startTime) / 1000);
		}

		if (workedSeconds >= 0) {
			let hours = Math.floor(workedSeconds / 3600);
			let minutes = Math.floor((workedSeconds % 3600) / 60);
			span.innerText = `${hours}시간 ${minutes}분`;
		} else {
			span.innerText = "";
		}
	});
}
