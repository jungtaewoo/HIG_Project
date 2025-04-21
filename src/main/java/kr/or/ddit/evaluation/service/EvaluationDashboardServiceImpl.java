package kr.or.ddit.evaluation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.evaluation.vo.EvaluationChartVO;
import kr.or.ddit.evaluation.vo.EvaluationDashboardSummaryVO;
import kr.or.ddit.evaluation.vo.EvaluationDashboardVO;
import kr.or.ddit.evaluation.vo.EvaluationDepartmentSummaryVO;
import kr.or.ddit.evaluation.vo.UnsubmittedEmployeeVO;
import kr.or.ddit.mybatis.mappers.evaluation.EvaluationDashboardMapper;

@Service
public class EvaluationDashboardServiceImpl implements EvaluationDashboardService {

	@Autowired
	private EvaluationDashboardMapper dao;
	
	// 대쉬보드 상세테이블 1
	@Override
	public List<EvaluationDashboardVO> getEvaluationStatus(String evaluationYear, String halfYear) {
		return dao.getDepartmentTeamEvaluationStatus(evaluationYear, halfYear);
	}
	// 대쉬보드 요약 
	public EvaluationDashboardSummaryVO getEvaluationSummary(String evaluationYear, String halfYear) {
		return dao.getEvaluationSummary(evaluationYear, halfYear);
	}
	// 대쉬보드 차트
	@Override
	public List<EvaluationChartVO> getChartData(String evaluationYear, String halfYear) {
		return dao.getDepartmentEvaluationChart(evaluationYear, halfYear);
	}
	// 대쉬보드 상세테이블 2
	@Override
	public List<EvaluationDepartmentSummaryVO> getDepartmentSummary(String evaluationYear, String halfYear) {
	    return dao.getDepartmentSummary(evaluationYear, halfYear);
	}
	// 미평가자 목록
	@Override
	public List<UnsubmittedEmployeeVO> getUnsubmittedList(String evaluationYear, String halfYear, String teamId) {
	    return dao.getUnsubmittedList(evaluationYear, halfYear, teamId);
	}

}
