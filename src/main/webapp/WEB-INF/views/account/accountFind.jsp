\
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
  #accountSearchForm .form-control {
    background-color: #fff;
    color: #000;
  }
  #accountSearchForm .form-control::placeholder {
    color: #aaa;
  }
</style>

<div class="container px-2">
  <form id="accountSearchForm">
    <div class="mb-3">
      <label for="empName" class="form-label fw-semibold">👤 이름</label>
      <input type="text" name="empName" id="empName" class="form-control" placeholder="예: 홍길동" required />
    </div>

    <div class="mb-3">
      <label for="juminFront" class="form-label fw-semibold">🆔 주민등록번호 앞자리</label>
      <input type="text" name="juminFront" id="juminFront" class="form-control" maxlength="6" placeholder="예: 900101" required />
    </div>

    <div class="text-end">
      <button type="submit" class="btn btn-primary w-100">
        🔍 계정(ID) 조회
      </button>
    </div>
  </form>

  <hr>
  <div id="searchResult" class="mt-3"></div>
</div>
