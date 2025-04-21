package kr.or.ddit.evaluation.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.evaluation.service.EvaluationTypeService;
import kr.or.ddit.evaluation.vo.EvaluationTypeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class EvaluationTypeProcessController {

	@Autowired
	private EvaluationTypeService service;
	
	// 목록 호출 
	@GetMapping("/evaluation/evaluationTypeList")
	public String readEvaluType(
			@RequestParam(defaultValue = "2025") String evaluationYear,
	        @RequestParam(defaultValue = "1") String halfYear,
			Model model) {
		
	    List<EvaluationTypeVO> evaluTypeList = service.evaluTypeList(evaluationYear, halfYear);
		
		log.info("{}============================",evaluTypeList);		
		
		model.addAttribute("evaluTypeList", evaluTypeList);
	    model.addAttribute("evaluationYear", evaluationYear);
	    model.addAttribute("halfYear", halfYear);

		return "tiles:evaluation/evaluationTypeList";
	}
	
	// 항목 추가
	@PostMapping("/evaluation/evaluationTypeInsert")
	public String insertEvalu(EvaluationTypeVO evaluationTypeVO) {
		
		service.insertEvaluType(evaluationTypeVO);
        
		return "redirect:/evaluation/evaluationTypeList"; 
	}
	
	// 삭제 , AJAX 요청 처리
	@ResponseBody
	@PostMapping("evaluation/evaluationTypeDelete")
	public Map<String, Object> evaluDelete(
			@RequestBody Map<String, String> request
		) {
		
		String evaluTypeId = request.get("evaluTypeId");
		
		Map<String, Object> response = new HashMap<>();
		
		try {
	        int result = service.deleteEvaluType(evaluTypeId);

			service.deleteEvaluType(evaluTypeId);  
	        response.put("success", true);
		} catch(Exception e) {
			log.error("삭제오류", e);
			response.put("success", false);
		}
			
		return response;
	}
	
	// 목록 호출 
	@GetMapping("/evaluation/evaluationDetail_Rank")
	public String readEvaluRank(Model model
			) {
		
		LocalDate today = LocalDate.now(); // import java.time.LocalDate;
	    
		String evaluationYear = String.valueOf(today.getYear());

	    String halfYear = today.getMonthValue() <= 6 ? "1" : "2";
		    
		// 평가 항목을 가져와서
		List<EvaluationTypeVO> evaluationList  = service.evaluationRank(evaluationYear, halfYear);
		
		model.addAttribute("evaluationList",evaluationList );

		
		return "tiles:evaluation/evaluationDetail_Rank";
	}

}