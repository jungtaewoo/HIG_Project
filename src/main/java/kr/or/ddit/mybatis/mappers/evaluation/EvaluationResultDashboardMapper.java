package kr.or.ddit.mybatis.mappers.evaluation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.evaluation.vo.EvaluationSummaryVO;
import kr.or.ddit.evaluation.vo.RankScoreVO;

@Mapper
public interface EvaluationResultDashboardMapper {
	
	// 평가 결과 피드바
	EvaluationSummaryVO getEvaluationSummary(
			@Param("year") String year, 
			@Param("half") String half);

    List<RankScoreVO> getAverageScoreByRank(
    		@Param("year") String year, 
    		@Param("half") String half);

	
}
