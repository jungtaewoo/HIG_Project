<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
<script>
    const roomId = "${roomId}";
</script>
<script src="/resources/js/messenger/ChatRoom.js"></script>
<script src="/resources/js/messenger/ChatRoomList.js"></script>

<style>
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
  background-color: #f8f9fa;
  font-family: 'Segoe UI', sans-serif;
  color: #212529;
}

#chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 20px;
  box-sizing: border-box;
  background-color: #ffffff;
}

#chat-header {
  padding-bottom: 10px;
  border-bottom: 1px solid #dee2e6;
}

#chat-box {
  flex: 1;
  overflow-y: auto;
  border: 1px solid #dee2e6;
  border-radius: 10px;
  padding: 15px;
  margin: 15px 0;
  background-color: #ffffff;
}

#chat-input-area {
  display: flex;
  align-items: center;
  gap: 10px;
}

#chatInput {
  flex: 1;
  padding: 10px;
  font-size: 16px;
  height: 44px;
  box-sizing: border-box;
  border: 1px solid #ced4da;
  border-radius: 8px;
}

#sendBtn {
  padding: 10px 20px;
  height: 44px;
  font-size: 15px;
  cursor: pointer;
  background-color: #0d6efd;
  color: white;
  border: none;
  border-radius: 8px;
}

.chat-message {
  display: flex;
  margin-bottom: 12px;
  max-width: 90%;
  clear: both;
}

.chat-message.mine {
  justify-content: flex-end;
  text-align: right;
}

.chat-message.other {
  justify-content: flex-start;
  text-align: left;
}

.chat-bubble {
  background-color: #f1f3f5;
  padding: 10px 14px;
  border-radius: 15px;
  max-width: 100%;
  word-break: break-word;
  display: inline-block;
  color: #212529;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
}

.chat-message.mine .chat-bubble {
  background-color: #d1e7ff;
}

.chat-time {
  font-size: 0.75em;
  color: #6c757d;
  margin-top: 5px;
  text-align: right;
}

.drop-zone {
  border: 2px dashed #ced4da;
  border-radius: 10px;
  background-color: #f8f9fa;
  color: #495057;
  padding: 20px;
  text-align: center;
  font-size: 14px;
  margin-top: 20px;
  transition: background-color 0.3s;
}

.drop-zone.dragover {
  background-color: #e9f5ff;
  border-color: #0d6efd;
}
</style>
</head>
<body>
<div id="chat-container">

  <div id="chat-header">
    <c:if test="${not empty chatempInfo}">
      <h2>${chatempInfo.EMP_NAME} (${chatempInfo.RANK_NAME})님과의 채팅</h2>
    </c:if>

    <c:if test="${not empty chatMemberList}">
      <button id="toggleMembersBtn" style="margin-bottom: 10px;">👥 참여자 목록 보기</button>

      <div id="chatMemberListContainer" style="display: none; margin-bottom: 15px; border-bottom: 1px solid #666;">
        <h3>그룹 채팅 참여자 목록:</h3>
        <ul style="margin-left: 15px;">
          <c:forEach var="member" items="${chatMemberList}">
            <li>
              ${member.EMP_NAME} (${member.RANK_NAME}) -
              <span style="color:${member.STATUS eq '온라인' ? 'limegreen' : 'gray'};">
                ${member.STATUS}
              </span>
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:if>
  </div>

  <input type="hidden" id="senderName" value="${principal.realUser.empName}" />

  <div id="chat-box">
    <!-- 메시지 출력 영역 -->
  </div>

  <div id="chat-input-area">
    <input type="text" id="chatInput" placeholder="메시지를 입력하세요">
    <button id="sendBtn">전송</button>
  </div>

  <div id="dropZone" class="drop-zone">📁 여기에 파일을 드래그하여 업로드하세요</div>
  <button id="closeBtn" style="margin-top:10px;">채팅방 닫기</button>

</div>

<script>
  $(document).ready(function () {
    $("#toggleMembersBtn").on("click", function () {
      $("#chatMemberListContainer").slideToggle(200);
      const btnText = $(this).text();
      $(this).text(btnText.includes("보기") ? "👥 참여자 목록 닫기" : "👥 참여자 목록 보기");
    });

    // 드래그 앤 드롭 이벤트 처리
    const dropZone = document.getElementById("dropZone");

    if (dropZone) {
      dropZone.addEventListener("dragover", e => {
        e.preventDefault();
        dropZone.classList.add("dragover");
      });

      dropZone.addEventListener("dragleave", () => {
        dropZone.classList.remove("dragover");
      });

      dropZone.addEventListener("drop", e => {
        e.preventDefault();
        dropZone.classList.remove("dragover");

        const files = e.dataTransfer.files;
        if (files.length > 0) {
          uploadFile(files[0]); 
        }
      });
    }
  });
</script>

</body>
</html>
