package kr.or.ddit.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/unAccount")
public class UnRegisterAccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping("/list")
	public String unRegisterList(Model model) {
		List<AccountVO>unAccount = service.unRegisterAccountList();
		model.addAttribute("unAccount", unAccount);
		return"account/unRegisterAccount";
		
	}
	
	@GetMapping("unNew/{empId}")
	public String newUnAccount(
			Model model,
			@PathVariable("empId")String empId
		){
		AccountVO unAccount = service.selectedUnAccount(empId);
		log.info("accountId 미등록자+++++++++++++++++++++++ {} : ",empId);
		model.addAttribute("unAccount", unAccount);
		
		
		
		return"tiles:account/accountRegister";
	}
}
