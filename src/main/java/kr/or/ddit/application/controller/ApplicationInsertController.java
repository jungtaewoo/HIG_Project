package kr.or.ddit.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.application.service.ApplicationService;
import kr.or.ddit.application.vo.ApplicationVO;

/**
 * 지원서 등록
 * 
 * @author KHT
 * @since 2025. 3. 17.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 17.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/recruit/applicant")
public class ApplicationInsertController {
	
	@Autowired
	private ApplicationService service;
	
	/**
	 * 지원서 등록 UI
	 * @param recruitId - 지원서를 넣은 채용공고ID
	 * @param model
	 * @return
	 */
	@GetMapping("registerUI")
	public String recruitApplicantForm(
		@RequestParam("recruitId") Long recruitId
		, Model model
	) {
		ApplicationVO application = new ApplicationVO();
	    application.setRecruitId(recruitId);
		
		model.addAttribute("application", application);
		return "tiles:recruitment/recruitApplicantFormUI";
	}
	
	
	/**
	 * 지원서 등록 process
	 * @param application - ApplicationVO (지원서VO)
	 * @param model
	 * @return
	 */
	@PostMapping("registerProcess")
	public String registerApplication(
		@ModelAttribute ApplicationVO application
	    , Model model
	) {
		boolean isRegistered = service.createApplication(application);
	    if (isRegistered) {
	    	// 지원서 성공 시 채용공고 목록으로
	    	return "redirect:/recruit/board/list";
	    }else {
	    	// 실패 시 폼으로
	    	return "redirect:/recruit/applicant/registerUI?recruitId=" + application.getRecruitId();
	    }
		
	}
}
