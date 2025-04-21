package kr.or.ddit.salary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salary/transfer")
public class SalaryTransferController {
	
	@GetMapping("list")
	public String salaryTransferList() {
		return"tiles:salary/transferList";
	}
	
	@GetMapping("request")
	public String salaryRequest() {
		return"tiles:salary/transferRequest";
	}
	
	@PostMapping("approve")
	public String salaryApprove() {
		  return "redirect:/salary/transferList";
	}
	

}
