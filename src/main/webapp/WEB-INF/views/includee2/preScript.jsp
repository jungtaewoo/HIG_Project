<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>대덕우리전자</title>
<!-- Bootstrap & Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
  <link rel="stylesheet" crossorigin href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/iconly.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/extensions/simple-datatables/style.css">
  <link rel="stylesheet" crossorigin href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/table-datatable.css">
  <link id="theme-css1" rel="stylesheet" crossorigin href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/app.css">
  <link id="theme-css2" rel="stylesheet" crossorigin href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/app-dark.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/extensions/toastify-js/src/toastify.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/template/dist/assets/extensions/dragula/dragula.min.css">

  <link rel="stylesheet" crossorigin href="${pageContext.request.contextPath}/resources/template/dist/assets/compiled/css/ui-widgets-todolist.css">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.38.1/skin-win8/ui.fancytree.min.css">

  <!-- html2canvas 라이브러리 추가 -->
<script src="https://cdn.jsdelivr.net/npm/html2canvas@1.4.1/dist/html2canvas.min.js"></script>


<style>
#qrCodeImage {
   width: 500px !important;
   height: 400px !important;
   max-width: 500px;
   max-height: 400px;
   object-fit: contain; /* 이미지 비율을 유지하면서 크기 맞추기 */
   display: block; /* 인라인 요소일 경우 크기 변형 방지 */
}
</style>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.38.1/jquery.fancytree-all-deps.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" ></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.7.8/axios.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0"></script>

