package kr.or.ddit.evaluation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.evaluation.vo.EvaluationSummaryVO;
import kr.or.ddit.evaluation.vo.RankScoreVO;
import kr.or.ddit.mybatis.mappers.evaluation.EvaluationResultDashboardMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EvaluationResultDashboardServiceImpl implements EvaluationResultDashboardService {

    private final EvaluationResultDashboardMapper mapper;

	@Override
    public EvaluationSummaryVO getEvaluationSummary(String year, String half) {
        return mapper.getEvaluationSummary(year, half);
    }

    @Override
    public List<RankScoreVO> getAverageScoreByRank(String year, String half) {
        return mapper.getAverageScoreByRank(year, half);
    }

}
