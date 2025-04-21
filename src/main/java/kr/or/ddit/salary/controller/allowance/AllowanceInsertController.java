package kr.or.ddit.salary.controller.allowance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//수당등록
@Controller
@RequestMapping("/Allowance/insert")
public class AllowanceInsertController {
	
	@GetMapping
	public String AllowanceForm() {
		return "tiles:salary/AllowanceInsert";
	}

}
