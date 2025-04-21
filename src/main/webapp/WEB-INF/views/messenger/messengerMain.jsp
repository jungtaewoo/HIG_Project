<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Messenger UI</title>
    
    <!-- CSS / ICON -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    
    <!-- VIEWPORT -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
        font-family: 'Noto Sans KR', sans-serif;
        background-color: #f1f3f5;
    }

    .messenger-container {
        display: flex;
        height: 100vh;
        background: #ffffff;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0px 4px 14px rgba(0, 0, 0, 0.06);
    }

    /* 사이드바 */
    .sidebar {
        width: 70px;
        background-color: #f8f9fa;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 15px 0;
        border-right: 1px solid #dee2e6;
    }

    .sidebar a {
        text-decoration: none;
        color: #6c757d;
        padding: 18px;
        margin: 12px 0;
        font-size: 22px;
        transition: all 0.2s;
        border-radius: 50%;
    }

    .sidebar a:hover, .sidebar a.active {
        background-color: #e9ecef;
        color: #0d6efd;
    }

    /* 메인 컨텐츠 영역 */
    .main-content {
        flex: 1;
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    /* 상단바 */
    .top-bar {
        padding: 15px 25px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 1px solid #dee2e6;
        background-color: #ffffff;
    }

    .top-bar .left {
        font-size: 20px;
        font-weight: 600;
        color: #343a40;
    }

    .top-bar .right {
        font-size: 14px;
        color: #6c757d;
    }

    /* 내용 영역 */
    #contentArea {
        height: 100%;
        overflow-y: auto;
        padding: 30px;
        background-color: #f8f9fa;
    }

    .card-box {
        background: #ffffff;
        padding: 25px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.03);
        max-width: 900px;
        margin: 0 auto;
    }
</style>
</head>

<body>
<script>
    sessionStorage.setItem("currentUserId", "${sessionAccount.empId}");
</script>

<div class="messenger-container">

    <!-- 사이드바 -->
    <div class="sidebar">
        <a href="#" id="empListBtn" title="직원 목록"><i class="bi bi-person"></i></a>
        <a href="#" id="chatRoomBtn" title="채팅방 목록"><i class="bi bi-chat-dots"></i></a>
        <a href="#" id="toggleNotificationBtn" title="알림 켜기/끄기">
            <i id="notificationIcon" class="bi bi-bell"></i>
        </a>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="main-content">
        
        <!-- 상단바 -->
        <div class="top-bar">
            <div class="left">사내 메신저</div>
            <div class="right">${sessionAccount.empName}님 접속중</div>
        </div>

        <!-- 본문 영역 -->
        <div id="contentArea">
            <div class="card-box">
                <h5>환영합니다 👋</h5>
                <p>채팅방을 선택하거나 좌측 버튼으로 기능을 이용하세요.</p>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="/resources/js/messenger/MainMessenger.js"></script>
<script src="/resources/js/messenger/ChatempList.js"></script>
<script src="/resources/js/messenger/ChatRoomList.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
</body>
</html>
