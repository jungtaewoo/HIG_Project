package kr.or.ddit.evaluation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.evaluation.service.EvaluationService;
import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.evaluation.vo.EvaluationTypeVO;
import kr.or.ddit.evaluation.vo.EvaluationVO;
import kr.or.ddit.evaluation.vo.EvaluationWrapperVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/evaluation")
@RequiredArgsConstructor
public class EvaluationKPIListController {

    private final EvaluationService service;
    
    // 피평가자 리스트 조회
    @GetMapping("/KPIList")
    public String getEvaluationCandidates(
    		@RequestParam(required = false) String year,
            @RequestParam(required = false) String half,
    		Model model) {
    	
        String evaluatorId = SecurityUtil.getrealUser().getEmpId();
        String name = SecurityUtil.getrealUser().getEmpName(); 
        String empId = SecurityUtil.getrealUser().getEmpId();
        log.info("✅ 평가자 ID: {}", evaluatorId);
        log.info("✅ year: {}, half: {}", year, half);


        List<EvaluationCandidateVO> kpiList = service.getEvaluationTargets(evaluatorId, year, half);
        log.info("✅ 조회된 피평가자 수: {}", kpiList.size());

        model.addAttribute("kpiList", kpiList); // ✅ JSP와 일치하도록 변경
        model.addAttribute("year", year);
        model.addAttribute("half", half);
        model.addAttribute("name", name); // ✅ 추가
        model.addAttribute("empId", empId); // ✅ 추가
        
        if (!kpiList.isEmpty()) {
            String targetEmpId = kpiList.get(0).getEmpId();
            String rankId = service.getRankIdByEmpId(targetEmpId);
            
            log.info("✅ 첫 피평가자의 직급 ID: {}", rankId);
            
            List<EvaluationTypeVO> criteriaList = service.evaluationRank(rankId, year, half);
            
            model.addAttribute("criteriaList", criteriaList);
//            model.addAttribute("year", kpiList.get(0).getEvaluationYear());
//            model.addAttribute("half", kpiList.get(0).getHalfYear());
        }
        
        return "tiles:evaluation/KPIList";
    }
    
    // 평가 저장
    @PostMapping("/saveEvaluation")
    public String saveEvaluation(@ModelAttribute EvaluationWrapperVO wrapperVO) {
    	List<EvaluationVO> list = wrapperVO.getEvaluationList();
        
        if (list != null && !list.isEmpty()) {
            EvaluationVO first = list.get(0); // 첫 항목에 year, half 담겨있음
            String year = first.getEvaluationYear();
            String half = first.getHalfYear();

            service.saveEvaluationList(list);
    	
            return "redirect:/evaluation/KPIList?year=" + year + "&half=" + half;
        }
    
        return "redirect:/evaluation/KPIList"; // 예외적으로 null일 경우
    }
        
}
    

