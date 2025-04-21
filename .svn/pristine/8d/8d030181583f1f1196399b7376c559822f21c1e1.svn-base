package kr.or.ddit.empresignations.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.empresignations.service.ResignationService;
import kr.or.ddit.empresignations.vo.ResignationVO;

/**
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

@Controller
@RequestMapping
public class ResignationReadController {
	
	@Inject
	private ResignationService service;
	
	@GetMapping("/employee/resignList")
	public String resignList(Model model) {
		
		List<ResignationVO> resignList = service.readResignationList();
		
		model.addAttribute("resignList",resignList);
		
		return "tiles:employee/resignList";
	}
	
	@GetMapping("employee/resignDetail")
	public String resignSelected(
		@RequestParam("resign") String resignId
		,Model model
		) {
		
		ResignationVO resignation = service.readResignation(resignId);
		
		model.addAttribute("resignation", resignation);
		
		return "tiles:employee/resignDetail";
	}

}
