/** 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 21.     	정태우            최초 생성
 *
 * </pre>
 */
document.addEventListener("DOMContentLoaded", function() {
    let themaChange = document.querySelector("#themaChange");
    let themecss1 = document.querySelector("#theme-css1");
    let themecss2 = document.querySelector("#theme-css2");

    // JSP에서 전달된 contextPath 변수 사용
    let contextPath = window.contextPath;

    let sidebarDirection = localStorage.getItem("sidebarDirection");
    if (sidebarDirection === "rtl") {
        themecss1.setAttribute(
            "href",
            contextPath + "/resources/template/dist/assets/compiled/css/app.rtl.css"
        );
        themecss2.setAttribute(
            "href",
            contextPath + "/resources/template/dist/assets/compiled/css/app-dark.rtl.css"
        );
    } else {
        themecss1.setAttribute(
            "href",
            contextPath + "/resources/template/dist/assets/compiled/css/app.css"
        );
        themecss2.setAttribute(
            "href",
            contextPath + "/resources/template/dist/assets/compiled/css/app-dark.css"
        );
    }

    themaChange.addEventListener('click', function() {
        let isRTL = themecss1.getAttribute("href").includes("app.rtl.css");

        if (isRTL) {
            themecss1.setAttribute(
                "href",
                contextPath + "/resources/template/dist/assets/compiled/css/app.css"
            );
            themecss2.setAttribute(
                "href",
                contextPath + "/resources/template/dist/assets/compiled/css/app-dark.css"
            );
            localStorage.setItem("sidebarDirection", "ltr"); // 왼쪽 정렬로 저장

        } else {
            themecss1.setAttribute(
                "href",
                contextPath + "/resources/template/dist/assets/compiled/css/app.rtl.css"
            );
            themecss2.setAttribute(
                "href",
                contextPath + "/resources/template/dist/assets/compiled/css/app-dark.rtl.css"
            );
            localStorage.setItem("sidebarDirection", "rtl"); // 오른쪽 정렬로 저장
        }
    });
});
