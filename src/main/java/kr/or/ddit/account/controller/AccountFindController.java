package kr.or.ddit.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;

/**
 * 
 * @author youngjun
 * @since 2025. 3. 12.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	youngjun	          최초 생성
 *
 * </pre>
 */
@Controller
public class AccountFindController {
	
	@Autowired
	private AccountService service;

	@GetMapping("/account/find")
	public String accountFind() {
		return "/account/accountFind";
	}
	
	@GetMapping("/account/find/getId")
	@ResponseBody
    public AccountVO accountFindGetAccountId(@RequestParam("empName")String empName, @RequestParam("juminFront")String juminFront) {
		return service.findAccountId(empName, juminFront);
    }

}
