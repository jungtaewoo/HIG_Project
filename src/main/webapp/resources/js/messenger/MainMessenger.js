let mainStompClient = null;
const currentUserId = sessionStorage.getItem("currentUserId");

// 알림 상태 확인 (기본값은 on)
function isNotificationEnabled() {
  return localStorage.getItem("chatNotifications") !== "off";
}

// 아이콘 상태 업데이트
function updateNotificationIcon() {
  const icon = document.getElementById("notificationIcon");
  if (!icon) return;

  if (isNotificationEnabled()) {
    icon.classList.remove("bi-bell-slash");
    icon.classList.add("bi-bell");
  } else {
    icon.classList.remove("bi-bell");
    icon.classList.add("bi-bell-slash");
  }
}

// 알림 토글 동작확인욤
function toggleNotificationSetting() {
  const enabled = isNotificationEnabled();
  localStorage.setItem("chatNotifications", enabled ? "off" : "on");
  updateNotificationIcon();
  console.log("알림 상태:", enabled ? "OFF" : "ON");
}

function connectMainWebSocket() {
  const socket = new SockJS("/wss");
  mainStompClient = Stomp.over(socket);

  mainStompClient.connect({}, function (frame) {
    console.log("메인 WebSocket 연결됨:", frame);

    // 모든 채팅방의 메시지 수신
    mainStompClient.subscribe("/topic/chat/global", function (message) {
      const chat = JSON.parse(message.body);
      handleIncomingMessage(chat);
    });
  });
}

function handleIncomingMessage(chat) {
  const isMine = chat.senderId === currentUserId;
  const openRoomId = sessionStorage.getItem("openRoomId");

  // 알림 조건: 내가 보낸 거 X, 권한 있음, 다른 채팅방, 알림 ON 상태
  if (!isMine && Notification.permission === "granted" && isNotificationEnabled()) {
    if (String(chat.roomId) !== openRoomId) {
      console.log("🔔 메신저 메인 - 알림 발생!");
		
	  const title = chat.roomType ==="GROUP"
	  	? `[${chat.roomName}] ${chat.senderName}`
		: `${chat.senderName}`;
	  console.log("title 확인용!!!!!!!!!", title);
      const notification = new Notification(title, {
        body: chat.messageText,
        icon: "https://cdn-icons-png.flaticon.com/512/3588/3588530.png",
		 tag: `room-${chat.roomId}`
      });

      notification.onclick = function () {
        const popupName = `chatRoom_${chat.roomId}`;
        const popupOptions = "width=600,height=500,left=300,top=100,resizable=yes,scrollbars=yes";
        window.open(`/messenger/room?roomId=${chat.roomId}`, popupName, popupOptions);
        notification.close();
      };
	  setTimeout(() => {
	    notification.close();
	  }, 4000);
    } else {
      console.log("메신저 메인 - 열려있는 채팅방이어서 알림 X");
    }
  }
}

function requestNotificationPermission() {
  if (!("Notification" in window)) return;
  if (Notification.permission === "default") {
    Notification.requestPermission().then(permission => {
      console.log("알림 권한 요청 결과:", permission);
    });
  }
}

$(document).ready(function () {
  requestNotificationPermission();
  connectMainWebSocket();

  // 알림 설정 버튼에 토글 연결
  $("#toggleNotificationBtn").on("click", function (e) {
    e.preventDefault();
    toggleNotificationSetting();
  });

  updateNotificationIcon(); // 처음 로딩 시 아이콘 상태 반영
});
