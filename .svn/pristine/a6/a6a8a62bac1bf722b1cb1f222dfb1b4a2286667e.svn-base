package kr.or.ddit.emprecordcards.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.emprecordcards.service.EmpCardService;
import kr.or.ddit.emprecordcards.vo.EmpCardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author LJW
 * @since 2025. 3. 14.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 14.     	LJW	          최초 생성
 *
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping
public class EmpCardReadController {

	@Inject
	private EmpCardService service;
				
	@GetMapping("/employee/empCardList")
	public String EmpCardList(Model model) {
		
		List<EmpCardVO> empCardList = service.readEmpCardList();
		model.addAttribute("empCardList", empCardList);
		log.info("====================={}==========================",empCardList);
		return "tiles:employee/empCardList";
	}
	
	@GetMapping("/employee/empCardDetail")
	public String EmpCardRead(
		@RequestParam("empcard") String empCard
		, Model model 
		) {
		
		EmpCardVO recordCard = service.readEmpCard(empCard);
		
		model.addAttribute("recordCard", recordCard);
		
		return "tiles:employee/empCardDetail";
		
	}
	
}
