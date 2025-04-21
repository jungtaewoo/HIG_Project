package kr.or.ddit.recruitment.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.recruitment.service.RecruitmentService;
import kr.or.ddit.recruitment.vo.RecruitmentVO;

/**
 * 채용공고 조회
 * 
 * @author KHT
 * @since 2025. 4. 4.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 4. 4.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/homepage/recruit")
public class HomePageRecruitBoardReadController {
	
	@Inject
	private RecruitmentService service;

	/** 채용공고 목록 조회
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String recruitBoardList(Model model
		,@RequestParam(value="date",required = false) String date
	) {
		List<RecruitmentVO> recruitBoardList = service.readRecruitBoardList(date);
		model.addAttribute("recruitBoardList", recruitBoardList);
		
		return "biz:homepage/hpRecruitBoardList";
	}
	
	/** 채용공고 상세 조회
	 * 
	 * @param recruitId - 채용공고ID
	 * @param model
	 * @return
	 */
	@GetMapping("detail")
	public String recruitBoardDetail(
		@RequestParam("recruitId") Long recruitId,
		Model model
	) {
		RecruitmentVO recruitBoardDetail = service.readRecruitBoardDetail(recruitId);
		model.addAttribute("recruitBoardDetail", recruitBoardDetail);
//		return "tiles:recruitment/recruitBoardDetail";
		return "biz:homepage/hpRecruitBoardDetail";
	}
	
	
	
	
	
	
	
	
	
}
