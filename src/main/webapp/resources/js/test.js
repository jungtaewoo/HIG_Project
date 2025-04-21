
document.addEventListener('DOMContentLoaded', function() {
	let now = new Date();
	let formattedDate = now.toISOString().split('T')[0]; 
	let scheduleList = window.scheduleList;
	
	var calendarEl = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(calendarEl, {
		headerToolbar: {
			left: 'prevYear,prev,next,nextYear today',
			center: 'title',
			right: 'dayGridMonth,dayGridWeek,dayGridDay'
		},
		initialDate: formattedDate,
		navLinks: true, // can click day/week names to navigate views
		editable: true,
		dayMaxEvents: true, // allow "more" link when too many events
		events: scheduleList.map(event => ({
			title: event.scheduleTitle,   // 제목
			start: event.startDate,       // 시작 날짜
			end: event.endDate,           // 종료 날짜
			description: event.scheduleContext,  // 설명 (추가)
			backgroundColor: event.colorCode,  // 색상 (추가)
			borderColor: event.colorCode, // 테두리 색상
			allDay: true   // 하루 종일 이벤트 여부 (필요 시)
		})),
		eventTimeFormat: { // 시간 형식 설정
		  hour: '2-digit',
		  minute: '2-digit',
		  hour12: false // 24시간 형식
		}
	});

	calendar.render();
});