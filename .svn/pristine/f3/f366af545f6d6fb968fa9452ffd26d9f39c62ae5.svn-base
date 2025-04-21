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

//현재 시간 표시
	function updateTime() {
		// 현재 날짜 객체 생성
		const now = new Date();
		// 요일 배열
		const weekdays = [ '일', '월', '화', '수', '목', '금', '토' ];
		// 년, 월, 일, 시, 분, 초 추출
		const year = now.getFullYear();
		const month = now.getMonth() + 1; // getMonth()는 0부터 시작하므로 +1
		const day = now.getDate();
		const weekday = weekdays[now.getDay()]; // 현재 요일
		const hours = now.getHours();
		const minutes = now.getMinutes();
		const seconds = now.getSeconds();
		// 시간 형식 맞추기 (두 자릿수로 표시)
		const formattedTime = `${year}년 ${month}월 ${day}일 (${weekday}) ${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
		// <span id="time">에 현재 시간 표시
		document.getElementById('time').textContent = formattedTime;
		
	}