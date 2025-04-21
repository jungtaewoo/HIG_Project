<!-- 
 * == 개정이력(Modification Information) ==
 *   
 *   수정일      			수정자           수정내용
 *  ============   	============== =======================
 *  2025. 3. 14.     	young           최초 생성
 *
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

  <title>직원 계정 수정</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="max-width: 960px; margin: 40px auto; font-family: 'Segoe UI', sans-serif;">
  <h2 class="mb-4">✏️ 직원 계정 수정</h2>

   <form id="updateAccount">
    <!-- 기본정보 카드 -->
    <div class="bg-white rounded shadow-sm p-4 mb-4">
      <h4 class="mb-3">📌 기본정보</h4>
      <div class="row g-3">
        <div class="col-md-3">
          <label>프로필 사진</label>
          <div id="imagePreviewContainer" class="border rounded d-flex align-items-center justify-content-center" style="width: 100%; height: 150px; overflow: hidden; position: relative;">
            <span id="noImageText" style="position: absolute; color: #999; font-size: 14px;">image 없음</span>
            <img id="imagePreview" src="" alt="프로필 사진" style="width: 100%; height: 100%; object-fit: cover; display: none;">
          </div>
          <input type="file" id="profileImage" class="form-control mt-2" accept="image/*">
        </div>
        <div class="col-md-9">
          <div class="mb-3">
            <label>사원번호</label>
            <input type="text" class="form-control" value="${account.accountId}" readonly>
            <input type="hidden" name="empId" value="${account.empId}">
          </div>
          <div class="mb-3">
            <label>사원명</label>
            <input type="text" class="form-control" value="${account.empName}" readonly>
            <input type="hidden" name="empName" value="${account.empName}">
            <input type="hidden" name="name" value="${account.empName}">
          </div>
          <div class="mb-3">
            <label>입사일</label>
            <input type="text" class="form-control" value="${fn:substring(account.accountDate, 0, 10)}" readonly>
          </div>
        </div>
      </div>
    </div>

    <!-- 근무정보 카드 -->
    <div class="bg-white rounded shadow-sm p-4 mb-4">
      <h4 class="mb-3">🏢 근무정보</h4>
      <div class="row g-3">
        <div class="col-md-4">
          <label>부서</label>
          <input type="text" class="form-control" value="${account.department.departmentName}" readonly>
        </div>
        <div class="col-md-4">
          <label>팀</label>
          <input type="text" class="form-control" value="${account.employee.team.teamName}" readonly>
        </div>
        <div class="col-md-4">
          <label>직급</label>
          <input type="text" class="form-control" value="${account.rank.rankName}" readonly>
        </div>
      </div>
    </div>

    <!-- 기타정보 카드 -->
    <div class="bg-white rounded shadow-sm p-4 mb-4">
      <h4 class="mb-3">📧 기타정보</h4>
      <div class="row g-3">
        <div class="col-md-4">
          <label>이메일</label>
          <input type="email" name="accountEmail" value="${account.employee.email}" class="form-control">
          <input type="hidden" name="email" value="${account.employee.email}">
        </div>
        <div class="col-md-4">
          <label>생년월일</label>
          <input type="text" class="form-control" value="${account.employee.juminFront}" readonly>
        </div>
        <div class="col-md-4">
          <label>휴대전화</label>
          <input type="text" name="accountPh" value="${account.employee.phoneNumber}" class="form-control">
          <input type="hidden" name="phoneNumber" value="${account.employee.phoneNumber}">
        </div>
        <div class="col-md-6">
          <label>주소</label>
          <input type="text" name="accountAdd1" value="${account.employee.address}" class="form-control">
          <input type="hidden" name="address" value="${account.employee.address}">
        </div>
        <div class="col-md-6">
          <label>상세주소</label>
          <input type="text" name="accountAdd2" value="${account.employee.addressDetail}" class="form-control">
          <input type="hidden" name="addressDetail" value="${account.employee.addressDetail}">
        </div>
        <div class="col-md-4">
          <label>계정생성일</label>
          <input type="text" class="form-control" value="${fn:substring(account.accountDate, 0, 10)}" readonly>
        </div>
      </div>
    </div>
  </form>
</div>
