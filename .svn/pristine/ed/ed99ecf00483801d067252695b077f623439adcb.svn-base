/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 4. 8.     	KHS            최초 생성
 *
 * </pre>
 */

document.addEventListener("DOMContentLoaded", function(){
  var currentPath = location.pathname;

  var contextPath = '${pageContext.request.contextPath}';

  var trimmedPath = currentPath.replace(contextPath, '');

  /*
  console.log("📍 location.pathname:", currentPath);
  console.log("📍 contextPath:", contextPath);
  console.log("📍 trimmedPath:", trimmedPath);
  */
  $("#navmenu a").each(function(){
    var href = $(this).attr("href");

    if (href) {
      var trimmedHref = href.replace(contextPath, '');
      console.log("🔍 비교용 trimmedHref:", trimmedHref);

      if (trimmedHref === trimmedPath) {
        console.log("✅ 매칭됨! ACTIVE 추가:", href);
        $(this).addClass("active");
        $(this).closest("li").addClass("active");
      } else {
        console.log("❌ 매칭 안됨:", trimmedHref, "!=", trimmedPath);
      }
    }
  });
});
