<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Mazer Admin Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/extensions/bootstrap-icons">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/extensions/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/pages/auth.css">


</head>

<body>
    <div id="auth">

        <div class="row h-100">
            <div class="col-lg-5 col-12">
                <div id="auth-left">
                    <div class="auth-logo">
                        <a href="index.html"><img
						src="${pageContext.request.contextPath}/resources/template/dist/assets/static/images/logo/finalnfinal.png"
						alt="Logo" srcset="" style="width: 412px;height: 229px;margin-left: -12px; margin-top: -28px;"></a>
                    </div>
                    <h1 class="auth-title">로그인ddd</h1>
                    <p class="auth-subtitle mb-5">HR Insiders 가족 여러분 환영합니다</p>

                    <form action="index.html">
                        <div class="form-group position-relative has-icon-left mb-4">
                            <input type="text" class="form-control form-control-xl" placeholder="Username">
                            <div class="form-control-icon">
                                <i class="bi bi-person"></i>
                            </div>
                        </div>
                        <div class="form-group position-relative has-icon-left mb-4">
                            <input type="password" class="form-control form-control-xl" placeholder="Password">
                            <div class="form-control-icon">
                                <i class="bi bi-shield-lock"></i>
                            </div>
                        </div>
                        <div class="form-check form-check-lg d-flex align-items-end">
                            <input class="form-check-input me-2" type="checkbox" value="" id="flexCheckDefault">
                            <label class="form-check-label text-gray-600" for="flexCheckDefault">
                                로그인기록 저장
                            </label>
                        </div>
                        <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5">로그인</button>
                    </form>
                    <div class="text-center mt-5 text-lg fs-4">
                        <p class="text-gray-600">계정분실시 아래를 클릭해주세요 <a href="auth-register.html"
                                class="font-bold">
                                </a>.</p>
                        <p><a class="font-bold" href="auth-forgot-password.html">계정찾기</a>.</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-7 d-none d-lg-block">
                <div id="auth-right">

                </div>
            </div>
        </div>

    </div>

</body>

</html>