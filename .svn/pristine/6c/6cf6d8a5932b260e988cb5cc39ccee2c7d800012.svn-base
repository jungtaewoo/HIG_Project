$(document).ready(function() {

  $('#exampleModal').on('hidden.bs.modal', function () {
    $('#btnForgotPassword').show();
    $('#modalBodyContainer').empty();
  });

  $('#btnForgotPassword').on('click', function() {
    $('#modalBodyContainer').load(
      contextPath + '/account/forgotPassword',
      function(response, status, xhr) {
        if (status === "error") {
          $('#modalBodyContainer').html("<p>비밀번호 찾기 페이지를 불러오는 중 오류가 발생했습니다.</p>");
        } else {
          $('#btnForgotPassword').hide();
        }
      }
    );
  });
});
