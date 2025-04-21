package kr.or.ddit.application.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.application.service.ApplicationService;
import kr.or.ddit.application.vo.ApplicationVO;
import kr.or.ddit.recruitment.service.RecruitmentService;
import kr.or.ddit.recruitment.vo.RecruitmentVO;

/**
 * 지원서 조회
 * 
 * @author KHT
 * @since 2025. 3. 16.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 16.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/recruit/applicant")
public class ApplicationReadController {
	
	@Autowired
	private ApplicationService service;
	@Autowired
	private RecruitmentService serviceR;

	/**
	 * 지원서 목록 조회
	 * @param recruitId - 지원서를 넣은 채용공고ID
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String recruitApplicantList(
	@RequestParam("recruitId") Long recruitId
	, Model model		
	) {
		List<ApplicationVO> applicationList = service.readApplicationList(recruitId);
		model.addAttribute("applicationList", applicationList);
		
		RecruitmentVO recruit = serviceR.readRecruitBoardDetail(recruitId);
		model.addAttribute("recruit",recruit);
		
		return "tiles:recruitment/recruitApplicantList";
	}
	
	/**
	 * 지원서 상세 조회
	 * @param appId - 지원서ID
	 * @param model
	 * @return
	 */
	@GetMapping("detail")
	public String recruitApplicantDetail(
		@RequestParam("appId") Long appId,
		Model model
	) {
		ApplicationVO applicationDetail = service.readApplicationDetail(appId);
		model.addAttribute("applicationDetail", applicationDetail);
		return "tiles:recruitment/recruitApplicantDetail";
	}
	
	
}
