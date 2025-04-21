package kr.or.ddit.account.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author youngjun
 * @since 2025. 3. 11.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 11.     	youngjun	          최초 생성
 *
 * </pre>
 */
@Slf4j
@Controller
public class AccounReadController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/account/read")
	public String AccountList(Model model,
	@RequestParam(value="accountId",required = false)String accountId){
		//직원전체리스트
		List<AccountVO>accountList = service.accountList();
		//권한그래프
		List<AccountVO> accountRoleCount = service.accountRoleCount();
		//퇴사계정
		List<AccountVO> retireAccount = service.retireAccount();
		//퇴사인원수 그래프
		int cnt = service.disableAccountCount();
		
		model.addAttribute("accountList", accountList);
		model.addAttribute("accountRoleCount",accountRoleCount);
		model.addAttribute("retireAccount",retireAccount);
		model.addAttribute("cnt",cnt);
		return "tiles:account/accountForm";
	}
	
	//계정상세조회
	@GetMapping("/account/read/accountId/{accountId}")
	public String AccountSelected(
		@PathVariable("accountId")String accountId
		,Model model
			){
		AccountVO accountSelect = service.readAccount(accountId);
		model.addAttribute("accountSelect", accountSelect);
		return "account/accountDetail";
	}
	
	//계정상태조회
	@ResponseBody
	@GetMapping("/account/status/{accountId}")
	public AccountVO AccountStatus(
			@PathVariable("accountId")String accountId,
			@RequestParam("accountStatus")String accountStatus
			,Model model
			){
		//사원계정상태 반환 Y,N
		AccountVO AccountStatus = service.readStatusAccount(accountId,accountStatus);
		return AccountStatus;
	}
	
}
