package kr.or.ddit.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author youngjun
 * @since 2025. 3. 16.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 16.     	youngjun	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping
@Slf4j
public class AccountUpdateController {

    @Autowired
    private AccountService service;

    /**
     * 계정 수정 폼 조회 (모달 내 삽입)
     */
    @GetMapping("/account/update/{accountId}")
    public String UpdateFormUI(Model model, @PathVariable("accountId") String accountId,
    		@ModelAttribute("account")AccountVO account,
    		@ModelAttribute("employee")EmployeeVO employee
    		){
        	account = service.readAccount(accountId);
        	log.info("수정ID값 account{} : ",account );
        	log.info("수정ID값 accountId{} : ",accountId );


        model.addAttribute("account", account);
        model.addAttribute("employee", employee);
        return "account/accountUpdateForm"; 
    }

    /**
     * 계정 정보 수정 처리 (계정,직원테이블 동시)
     */
    @Transactional
    @PostMapping("/account/update/save")
    @ResponseBody
    public Map<String, Object> updateSave(
        @ModelAttribute("account") AccountVO account,
        @ModelAttribute("employee") EmployeeVO employee) {

        Map<String, Object> response = new HashMap<>();

        try {
            log.info("수정된 account 값: {}", account);
            log.info("수정된 employee 값: {}", employee);
            
            log.info("들어오는값 account:{}",account);
            log.info("들어오는값 employee:{}",employee);
            
            // 성공 응답
            response.put("success",service.updateAccount(account));
            response.put("success",service.updateAccountEmp(employee));
        } catch (Exception e) {
            log.error("업데이트 중 오류 발생", e);
            response.put("success", false);
            response.put("message", "업데이트 실패: " + e.getMessage());
        }

        return response; // JSON
    }
    
    @PostMapping("/account/statusToggle/{accountId}")
    @ResponseBody
    public AccountVO toggleAccount(@PathVariable("accountId")String accountId,
    		@RequestParam("accountStatus")String accountStatus) {
    	service.toggleAccountStatus(accountId);
    	
    	 String newAccountStatus = "N";
    	    if ("N".equals(accountStatus)) {
    	        newAccountStatus = "Y";
    	    }
    	    
    	AccountVO updatedAccount = service.readStatusAccount(accountId,newAccountStatus);
    	return updatedAccount;
    }
}


