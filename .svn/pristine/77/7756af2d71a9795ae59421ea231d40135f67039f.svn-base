<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 18.     	young           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <title>이메일 인증</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
    
    function sendAuthCode() {
        var email = $("#email").val();
        if (email == "") {
            alert("이메일을 입력하세요.");
            return;
        }

        $("#authBox").css("display", "block"); // 인증번호 입력창 표시

        $.ajax({
            url: "/mail/send",
            type: "post",
            dataType: "json",
            data: { "email": email },  //  email 값 올바르게 전달
            success: function(data) {
                alert("인증번호가 발송되었습니다.");
                $("#authCode").val(data); // 서버에서 받은 인증번호 저장
            },
            error: function(xhr, status, error) {
                console.error("에러 발생: ", error);
                alert("인증번호 전송 실패!");
            }
        });
    }


    function verifyAuthCode() {
        var enteredCode = $("#userCode").val();
        var sentCode = $("#authCode").val();

        if (enteredCode == sentCode) {
            alert("인증 성공!");
        } else {
            alert("인증번호가 다릅니다.");
        }
    }

    </script>
</head>
<body>
    <h2>이메일 인증</h2>
    <input type="text" id="email" placeholder="이메일 입력">
    <button onclick="sendAuthCode()">인증번호 받기</button>

    <div id="authBox" style="display: none;">
        <input type="text" id="userCode" placeholder="인증번호 입력">
        <button onclick="verifyAuthCode()">확인</button>
    </div>

    <input type="hidden" id="authCode"> <!-- 서버에서 받은 인증번호 저장 -->
</body>
</html>