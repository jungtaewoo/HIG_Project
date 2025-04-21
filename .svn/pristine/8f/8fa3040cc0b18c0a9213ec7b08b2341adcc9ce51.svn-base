package kr.or.ddit.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.mybatis.mappers.messenger.chatempMapper;
import kr.or.ddit.validate.InsertGroup;
import lombok.extern.slf4j.Slf4j;

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
 *  2025. 3. 12.     	youngjun	     최초 생성
 *
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/account/insert")
public class AccountInsertController {
	
	@Autowired
	private AccountService service;
	
	//계정등록
	@GetMapping("/formUI")
	public String RegisterFormUI() {
		
		return"tiles:account/accountRegister";
	}
	
	@PostMapping("/save")
	public String AccountInsert(
	    RedirectAttributes redirectAttributes,
		@ModelAttribute("account")AccountVO account
		,Errors errors
		,Model model
		){
	    log.warn(" POST /account/insert/save 호출됨");
	    log.warn(" account: {}", account);
		
	    try {
	        boolean result = service.accountInsert(account);
	        log.warn("✅ result 계정삽입: {}", result);

	        if (!result) {
	        	redirectAttributes.addFlashAttribute("message", "등록을 진행할 수 없습니다.");
	            return "redirect:/account/insert/formUI";
	        }
	        return "redirect:/account/read";
	    } catch (Exception e) {
	        log.error("❌ 계정 등록 중 예외 발생!", e);
	        model.addAttribute("message", "서버 오류로 인해 등록에 실패했습니다.");
	        return "redirect:/account/insert/formUI";
	    }
		
	}

}
