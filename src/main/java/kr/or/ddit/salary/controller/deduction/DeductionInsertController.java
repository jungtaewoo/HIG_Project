package kr.or.ddit.salary.controller.deduction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//공제등록
@Controller
@RequestMapping("/Deduction/insert")
public class DeductionInsertController {
	
	@GetMapping
	public String DeductionForm() {
		return"tiles:salary/DeductionInsert";
	}

}
