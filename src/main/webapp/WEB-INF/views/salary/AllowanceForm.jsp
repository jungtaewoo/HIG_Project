<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/salary/list"> 사원급여관리</a></li>
    <li class="breadcrumb-item"><a href="/salary/transfer/list">급여이체현황</a></li>
    <li class="breadcrumb-item active" aria-current="page">수당/공제관리</li>
    <li class="breadcrumb-item"><a href="#">급여기준</a></li>
  </ol>
</nav>

<div class="card">
<div class="card-body">
		<div class="container my-5">
		  <h3 class="text-center fw-bold mb-5">📋 수당 및 공제 항목 통합 관리</h3>

		  <!-- 등록 폼 -->
		  <form method="post" action="#" class="row g-3 align-items-end mb-4">
		    <div class="col-md-2">
		      <label class="form-label fw-bold">구분</label>
		      <select class="form-select" name="type" required>
		        <option value="">선택</option>
		        <option value="allowance">수당</option>
		        <option value="deduction">공제</option>
		      </select>
		    </div>
		    <div class="col-md-2">
		      <label class="form-label fw-bold">코드</label>
		      <input type="text" class="form-control" name="code" required>
		    </div>
		    <div class="col-md-2">
		      <label class="form-label fw-bold">명칭</label>
		      <input type="text" class="form-control" name="name" required>
		    </div>
		    <div class="col-md-3">
		      <label class="form-label fw-bold">정의</label>
		      <input type="text" class="form-control" name="desc">
		    </div>
		    <div class="col-md-2">
		      <label class="form-label fw-bold">계산식</label>
		      <input type="text" class="form-control" name="logic">
		    </div>
		    <div class="col-md-1">
		      <button type="submit" class="btn btn-success w-100">등록</button>
		    </div>
		  </form>

		  <!-- 리스트 -->
		  <div class="row">
		    <!-- 수당 리스트 -->
		    <div class="col-md-6">
		      <h5 class="fw-bold text-primary mb-3">📌 수당 리스트</h5>
		      <table class="table table-bordered table-hover text-center align-middle">
		        <thead class="table-light">
		          <tr>
		            <th>코드</th>
		            <th>명칭</th>
		            <th>정의</th>
		            <th>상태</th>
		            <th>V</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach items="${AllowanceList}" var="vo">
		            <tr>
		              <td>${vo.allowanceCode}</td>
		              <td>${vo.allowanceName}</td>
		              <td>${vo.allowanceDesc}</td>
		              <td>사용</td> <!-- 임시 표시 -->
		              <td>
		                <!-- <form action="#" method="post"> -->
		                  <input type="hidden" name="code" value="${vo.allowanceCode}" />
		                  <button type="button" class="btn btn-sm btn-outline-secondary" disabled>V</button>
		                <!-- </form> -->
		              </td>
		            </tr>
		          </c:forEach>
		        </tbody>
		      </table>
		    </div>

		    <!-- 공제 리스트 -->
		    <div class="col-md-6">
		      <h5 class="fw-bold text-danger mb-3">📌 공제 리스트</h5>
		      <table class="table table-bordered table-hover text-center align-middle">
		        <thead class="table-light">
		          <tr>
		            <th>코드</th>
		            <th>명칭</th>
		            <th>정의</th>
		            <th>계산식</th>
		            <th>상태</th>
		            <th>V</th>
		          </tr>
		        </thead>
		        <tbody>
		          <c:forEach items="${deductionList}" var="vo">
		            <tr>
		              <td>${vo.deductionCode}</td>
		              <td>${vo.deductionName}</td>
		              <td>${vo.deductionDesc}</td>
		              <td>${vo.deductionCalcLogic}</td>
		              <td>사용</td> <!-- 임시 표시 -->
		              <td>
		                <!-- <form action="#" method="post"> -->
		                  <input type="hidden" name="code" value="${vo.deductionCode}" />
		                  <button type="button" class="btn btn-sm btn-outline-secondary" disabled>V</button>
		                <!-- </form> -->
		              </td>
		            </tr>
		          </c:forEach>
		        </tbody>
		      </table>
		    </div>
		  </div>
		</div>
	</div>
</div>