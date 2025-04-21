<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재설정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom right, #f0f2f5, #c9d6ff);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card {
            width: 100%;
            max-width: 420px;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .form-label {
            font-weight: bold;
        }
        .btn-primary {
            width: 100%;
        }
        #passwordValidationMsg {
            font-size: 0.9rem;
            margin-top: 5px;
        }
    </style>
</head>
<body>

<div class="card bg-white">
    <h4 class="text-center mb-4">🔐 새 비밀번호 설정</h4>

    <form action="${pageContext.request.contextPath}/password/update" method="post" id="resetForm">
        <input type="hidden" name="token" value="${token}" />

        <div class="mb-3">
            <label for="newPassword" class="form-label">새 비밀번호</label>
            <input type="password" id="newPassword" name="newPassword" class="form-control" required>
            <div id="passwordValidationMsg" class="text-danger"></div>
        </div>

        <button type="submit" class="btn btn-primary">비밀번호 변경</button>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
</div>

<!-- JS 유효성 검사 -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const form = document.getElementById("resetForm");
        const passwordInput = document.getElementById("newPassword");
        const validationMsg = document.getElementById("passwordValidationMsg");

        form.addEventListener("submit", function (e) {
            const password = passwordInput.value;
            const hasMinLength = password.length >= 8;
            const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);

            if (!hasMinLength || !hasSpecialChar) {
                e.preventDefault();
                validationMsg.textContent = "비밀번호는 8자 이상이며 특수문자를 최소 1개 포함해야 합니다.";
                passwordInput.focus();
            } else {
                validationMsg.textContent = "";
            }
        });
    });
</script>

</body>
</html>
