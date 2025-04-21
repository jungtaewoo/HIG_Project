package kr.or.ddit.evaluation.service;

import java.util.List;

import kr.or.ddit.evaluation.vo.EvaluationChartVO;
import kr.or.ddit.evaluation.vo.EvaluationDashboardSummaryVO;
import kr.or.ddit.evaluation.vo.EvaluationDashboardVO;
import kr.or.ddit.evaluation.vo.EvaluationDepartmentSummaryVO;
import kr.or.ddit.evaluation.vo.UnsubmittedEmployeeVO;

public interface EvaluationDashboardService {
    // 대쉬보드 상세 1
	List<EvaluationDashboardVO> getEvaluationStatus(String evaluationYear, String halfYear);
	// 대쉬보드 요약
    EvaluationDashboardSummaryVO getEvaluationSummary(String evaluationYear, String halfYear);
    // 대쉬보드 차트
    List<EvaluationChartVO> getChartData(String evaluationYear, String halfYear);
    // 대쉬보드 상세 2
    List<EvaluationDepartmentSummaryVO> getDepartmentSummary(String evaluationYear, String halfYear);
    // 미평가자 목록
    List<UnsubmittedEmployeeVO> getUnsubmittedList(String evaluationYear, String halfYear, String teamId);

}
