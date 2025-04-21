package kr.or.ddit.recruitment.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.recruitment.service.RecruitmentService;
import kr.or.ddit.recruitment.vo.RecruitmentVO;

/**
 * 채용공고 수정
 * 
 * @author KHT
 * @since 2025. 3. 14.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 14.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/recruit/board")
public class RecruitBoardUpdateController {
	
	@Autowired
	private RecruitmentService service;

	/**
	 * 채용공고 수정 UI
	 * @param recruitId - 채용공고ID
	 * @param model
	 * @return
	 */
	@GetMapping("updateUI")
	public String recruitBoardForm2(@RequestParam("recruitId") Long recruitId, Model model) {
		// 수정할 게시물 정보를 조회하여 폼에 전달
        RecruitmentVO recruitBoard = service.readRecruitBoardDetail(recruitId);
        
        // DB에서 "YYYY/MM/DD" 형식으로 저장된 날짜를 Date 객체로 변환
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy/MM/dd");  // DB에서 저장된 형식
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd"); // 출력할 형식

        String formattedEndDate = "";
        if (recruitBoard.getRecruitEnddate() != null) {
            formattedEndDate = sdfOutput.format(recruitBoard.getRecruitEnddate());
        }
        String formattedStartDate = "";
        if (recruitBoard.getRecruitStartdate() != null) {
        	formattedStartDate = sdfOutput.format(recruitBoard.getRecruitStartdate());
        }
        // 모델에 날짜 변환된 값을 추가
        model.addAttribute("formattedEndDate", formattedEndDate);
        model.addAttribute("formattedStartDate", formattedStartDate);
        
        model.addAttribute("recruitBoard", recruitBoard);
		return "tiles:recruitment/recruitBoardUpdate";
	}
	
	/**
	 * 채용공고 수정 process
	 * @param recruitBoard - RecruitmentVO (채용공고VO)
	 * @return
	 */
	@PostMapping("updateProcess")
	public String recruitBoardUpdate(
		@ModelAttribute RecruitmentVO recruitBoard
		
	) {
		service.modifyRecruitBoard(recruitBoard);
		return "redirect:/recruit/board/list";
	}
	
}
