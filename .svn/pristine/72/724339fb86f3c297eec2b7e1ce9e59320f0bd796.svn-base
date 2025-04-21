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
window.onload = function() {
	//  const debugInfo = document.getElementById('debug-info');
	//             debugInfo.textContent += "페이지 로드됨\n";  // 페이지 로드 확인

	if (navigator.geolocation) {
		//                 debugInfo.textContent += "위치 정보 요청 시작\n";  // 위치 정보 요청 시작

		navigator.geolocation.getCurrentPosition(function(position) {
			//   debugInfo.textContent += "위치 정보 가져오기 성공\n";  // 위치 정보 가져오기 성공
			let userLatitude = position.coords.latitude;
			let userLongitude = position.coords.longitude;

			//                     debugInfo.textContent += "위치 정보: " + userLatitude + ", " + userLongitude + "\n";  // 위치 정보 출력

			// URL에서 유저 ID 가져오기
			const urlParams = new URLSearchParams(window.location.search);
			const userId = urlParams.get('who');
			//                     debugInfo.textContent += "유저 ID: " + userId + "\n";  // 유저 ID 확인

			fetch('/scan-qr', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({
					who: userId,
					latitude: userLatitude,
					longitude: userLongitude
				})
			})
				.then(response => {
					// debugInfo.textContent += "서버 응답 받음: " + response.status + "\n";  // 서버 응답 확인
					return response.json();
				})
				.then(data => {
					//  debugInfo.textContent += "서버 데이터: " + JSON.stringify(data) + "\n";  // 서버로부터 받은 데이터 출력
					if (data.success) {
						alert(data.message);
					} else {
						alert(data.message);
					}
				})
				.catch(error => {
					//   debugInfo.textContent += "서버 요청 실패: " + error + "\n";  // 요청 실패 시 에러 출력
					alert("서버 요청에 실패했습니다.");
				});

		}, function(error) {
			//   debugInfo.textContent += "위치 정보 에러: " + error.code + "\n";  // 위치 정보 요청 실패 시 에러 출력
			alert("위치 정보를 가져올 수 없습니다. 오류 코드: " + error.code);
		});
	} else {
		alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
	}
};


