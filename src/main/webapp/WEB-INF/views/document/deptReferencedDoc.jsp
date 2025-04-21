<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-heading">
	<div class="page-title">
		<div class="row">
			<div class="col-12 col-md-6 order-md-1 order-last">
				<h3>부서참조함</h3>
				<p class="text-subtitle text-muted">소속부서가 참조에 포함된 문서내역</p>
			</div>
			<div class="col-12 col-md-6 order-md-2 order-first">
				<nav aria-label="breadcrumb"
					class="breadcrumb-header float-start float-lg-end">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
						<li class="breadcrumb-item active" aria-current="page">DataTable</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<section class="section">
		<div class="card">
			<div class="card-header">Simple Datatable</div>
			<div class="card-body">
				<table class="table table-striped" id="table1">
					<thead>
						<tr>
							<th>기안일</th>
							<th>결재양식</th>
							<th>제목</th>
							<th>기안자</th>
							<th>부서</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>2025.03.05</td>
							<td>보고서</td>
							<td>보고서</td>
							<td>김사원</td>
							<td>1팀</td>
						</tr>
						<tr>
							<td>2025.03.05</td>
							<td>보고서</td>
							<td>보고서</td>
							<td>박사원</td>
							<td>4팀</td>
						</tr>
						<tr>
							<td>2025.03.03</td>
							<td>보고서</td>
							<td>보고서</td>
							<td>이사원</td>
							<td>3팀</td>
						</tr>
						<tr>
							<td>2025.03.02</td>
							<td>보고서</td>
							<td>보고서</td>
							<td>최사원</td>
							<td>2팀</td>
						</tr>


					</tbody>
				</table>
			</div>
		</div>

	</section>
</div>