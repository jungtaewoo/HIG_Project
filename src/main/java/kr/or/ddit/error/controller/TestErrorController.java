package kr.or.ddit.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class TestErrorController {
	
	  // 403 에러
    @RequestMapping("/403")
    public String error403() {
        // Tiles에서 정의한 뷰 이름 (예: "error.error_403")
        return "error.error_403";
    }
    
    // 404 에러
    @RequestMapping("/404")
    public String error404() {
        return "error.error_404";
    }
    
    // 500 에러
    @RequestMapping("/500")
    public String error500() {
        return "error.error_500";
    }
}
