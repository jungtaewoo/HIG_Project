package kr.or.ddit.evaluation.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.evaluation.service.EvaluationResultDashboardService;
import kr.or.ddit.evaluation.vo.EvaluationSummaryVO;
import kr.or.ddit.evaluation.vo.RankScoreVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	
@Controller
@RequestMapping("/evaluation")
public class EvaluationResultDashboardController {

    private final EvaluationResultDashboardService service;

    @GetMapping("/resultDashboard")
    public String resultDashboard(@RequestParam(required = false) String year,
                                  @RequestParam(required = false) String half,
                                  Model model) throws JsonProcessingException {

    	if (year == null || half == null) {
		       LocalDate now = LocalDate.now();
		       year = String.valueOf(now.getYear());
		       half = (now.getMonthValue() <= 6) ? "1" : "2";  // 1~6월 → 상반기("1"), 7~12월 → 하반기("2")
		    }
    	
        EvaluationSummaryVO summary = service.getEvaluationSummary(year, half);
        List<RankScoreVO> rankScoreList = service.getAverageScoreByRank(year, half);

        // ✅ Jackson으로 JSON 직렬화
        ObjectMapper objectMapper = new ObjectMapper();
        String chartJson = objectMapper.writeValueAsString(rankScoreList);

        model.addAttribute("summary", summary);
        model.addAttribute("rankScoreChartJson", chartJson);

        return "tiles:evaluation/resultDashboard";
    }
}
