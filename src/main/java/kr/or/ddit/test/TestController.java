package kr.or.ddit.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mazer")
public class TestController {
	
	@GetMapping("index")
	public String main() {
		return "tiles:mazer/content";
	}
	
}
