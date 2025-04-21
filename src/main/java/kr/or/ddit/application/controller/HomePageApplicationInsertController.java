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
import kr.or.ddit.recruitment.service.RecruitmentService;
import kr.or.ddit.recruitment.vo.RecruitmentVO;

/**
 * 지원서 등록
 * 
 * @author KHT
 * @since 2025. 4. 7.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 4. 7.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/homepage/applicant")
public class HomePageApplicationInsertController {
	
	@Autowired
	private ApplicationService service;
	@Autowired
	private RecruitmentService service2;
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
		
	    RecruitmentVO recruit = service2.readRecruitBoardDetail(recruitId);
	    
		model.addAttribute("application", application);
		model.addAttribute("recruit",recruit);
		
		return "biz:homepage/hpRecruitApplicantFormUI";
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
		
		// 바로 채용공고 목록으로 이동하기
//	    if (isRegistered) {
//	    	// 지원서 성공 시 채용공고 목록으로
//	    	return "redirect:/homepage/recruit/list";
//	    }else {
//	    	// 실패 시 폼으로
//	    	return "redirect:/homepage/applicant/registerUI?recruitId=" + application.getRecruitId();
//	    }
	    
		
		// 모달로 확인 모달창 띄우고 목록으로 이동
	    if (isRegistered) {
	        model.addAttribute("showThanksModal", true); 
	    }
	    return "biz:homepage/hpRecruitApplicantFormUI";
		
	}
}
