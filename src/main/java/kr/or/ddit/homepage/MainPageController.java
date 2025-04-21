package kr.or.ddit.homepage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainPageController{

	@GetMapping("/home")
	public String index(Model model, HttpServletRequest req) {
		return "biz:homepage/content";
	}

	@GetMapping("/about")
	public String about(Model model, HttpServletRequest req) {
		return "biz:homepage/about";
	}

	@GetMapping("/service")
	public String service(Model model, HttpServletRequest req) {
		return "biz:homepage/service";
	}

	@GetMapping("/product")
	public String product(Model model, HttpServletRequest req) {
		return "biz:homepage/product";
	}
}
