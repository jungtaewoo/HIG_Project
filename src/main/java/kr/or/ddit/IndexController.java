package kr.or.ddit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController{
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/")
	public String index(Model model, HttpServletRequest req) {
		List<AccountVO> accountAll = service.accountList();
		model.addAttribute("accountAll", accountAll);
		return "account/accountLoginForm"; 
	}
}
