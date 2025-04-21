package kr.or.ddit.evaluation.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.evaluation.service.EvaluationDashboardService;
import kr.or.ddit.evaluation.vo.EvaluationChartVO;
import kr.or.ddit.evaluation.vo.EvaluationDashboardSummaryVO;
import kr.or.ddit.evaluation.vo.EvaluationDashboardVO;
import kr.or.ddit.evaluation.vo.EvaluationDepartmentSummaryVO;
import kr.or.ddit.evaluation.vo.UnsubmittedEmployeeVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/evaluation")
public class EvaluationDashBoardController {

	@Autowired
	private EvaluationDashboardService service;

	// 대시보드 초화면
	@GetMapping("/evaluationDashboard")
	public String showDashboard(
			@RequestParam(required = false) String evaluationYear,
			@RequestParam(required = false) String halfYear, 
			@RequestParam(required = false) String teamId, 
			Model model) throws JsonProcessingException  {
		
		if (evaluationYear == null || halfYear == null) {
		       LocalDate now = LocalDate.now();
		       evaluationYear = String.valueOf(now.getYear());
		       halfYear = (now.getMonthValue() <= 6) ? "1" : "2";  // 1~6월 → 상반기("1"), 7~12월 → 하반기("2")
		    }
		
		// 대시보드 상세테이블 2 
		List<EvaluationDashboardVO> dashboardList = service.getEvaluationStatus(evaluationYear, halfYear);
		// 대시보드 요약
		EvaluationDashboardSummaryVO summary = service.getEvaluationSummary(evaluationYear, halfYear);
	    // 대시보드 차트
		List<EvaluationChartVO> chartDataList = service.getChartData(evaluationYear, halfYear);
		log.info("============================================================== chartDataList {} " , chartDataList);
		
		String chartDataJson = new ObjectMapper().writeValueAsString(chartDataList); // ✅ chartData를 JSON 문자열로 변환
		log.info("==============================================================chartDataJson {} " , chartDataJson);

	    // 대시보드 상세테이블 1
	    List<EvaluationDepartmentSummaryVO> departmentSummary = service.getDepartmentSummary(evaluationYear, halfYear);
	    
		model.addAttribute("dashboardList", dashboardList);
		model.addAttribute("summary", summary);
		model.addAttribute("evaluationYear", evaluationYear);
		model.addAttribute("halfYear", halfYear);
		model.addAttribute("chartDataJson", chartDataJson);
		log.info("==============================================================chartDataJson {} " , chartDataJson);
		model.addAttribute("departmentSummary", departmentSummary);

		return "tiles:evaluation/evaluationDashBoard";
	}

	@GetMapping("/unsubmittedModal")
	public String getUnsubmittedModalData(
		    @RequestParam String year,
		    @RequestParam String half,
		    @RequestParam String teamId,
		    Model model) {
//		
//		log.info("[미제출자 AJAX 요청] 연도: {}, 반기: {}, 팀ID: {}", year, half, teamId);
//
//		// 모달
//	    if (teamId == null || teamId.trim().isEmpty()) {
//	        log.warn("❌ teamId 누락");
//	        return List.of();
//	    }
//
//	    return service.getUnsubmittedList(year, half, teamId);

//	    // JSP 이동방식
	    List<UnsubmittedEmployeeVO> list = service.getUnsubmittedList(year, half, teamId);
	    
	    model.addAttribute("unsubmittedList", list); // ✅ 이름 통일
	    
	    return "tiles:evaluation/evaluationDashboard_unsubmit"; // JSP 경로
	
	}
	
	/**
	 * // 샘플 jsp @GetMapping("/evaluation/evaluationList") public String
	 * showEvaluationList(Model model) { // 연도별 상/하반기, 직급별 인원수 데이터 //
	 * List<EvaluationSummaryVO> summaryList =
	 * evaluationService.getEvaluationSummary(); // // // 평가 대상자 리스트 //
	 * List<EmployeeEvaluationVO> evaluationTargetList =
	 * evaluationService.getEvaluationTargets(); // //
	 * model.addAttribute("summaryList", summaryList); //
	 * model.addAttribute("targetList", evaluationTargetList);
	 * 
	 * return "tiles:evaluation/evaluationList"; }
	 **/
}
