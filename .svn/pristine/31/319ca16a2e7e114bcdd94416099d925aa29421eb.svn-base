package kr.or.ddit.recruitment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.recruitment.service.RecruitmentService;
import kr.or.ddit.recruitment.vo.RecruitmentVO;

/**
 * 채용공고 등록
 * 
 * @author KHT
 * @since 2025. 3. 13.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 13.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/recruit/board")
public class RecruitBoardInsertController {
	
	@Autowired
	private RecruitmentService service;

	/**
	 * 채용공고 등록 UI
	 * @return
	 */
	@GetMapping("registerUI")
	public String recruitBoardForm() {
		return "tiles:recruitment/recruitBoardFormUI";
	}
	
	/**
	 * 채용공고 등록 process
	 * @param recruitBoard - RecruitmentVO (채용공고VO)
	 * @param model
	 * @return
	 */
	@PostMapping("registerProcess")
	@ResponseBody
	public Map<String, Object> recruitBoardRegister(
		@RequestBody RecruitmentVO recruitBoard
		, Model model
	) {
		Map<String, Object> response = new HashMap<>();
		boolean isRegistered = service.createRecruitBoard(recruitBoard);

        if (isRegistered) {
        	response.put("success", true);
        } else {
        	response.put("success", false);
        	response.put("message", "등록에 실패했습니다.");
        }
        return response;
	}
	
}
