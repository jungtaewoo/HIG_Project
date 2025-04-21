package kr.or.ddit.mybatis.mappers.evaluation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.evaluation.vo.EvaluationTypeVO;
import kr.or.ddit.evaluation.vo.EvaluationVO;

@Mapper
public interface EvaluationMapper {

	/**
	 * 평가하기 -> 내가 평가할 대상 리스트 : 팀장 기준으로 소속 팀원 조회
	 * @param evaluatorId
	 * @param evaluationYear
	 * @param halfYear
	 * @return
	 */
	List<EvaluationCandidateVO> selectEvaluationTargets(
		    @Param("evaluatorId") String evaluatorId,
		    @Param("evaluationYear") String evaluationYear,
		    @Param("halfYear") String halfYear
		);	
	/**
	 * 평가 결과 저장용
	 * @param vo
	 */
	void insertEvaluation(EvaluationVO vo);
	
	/**
	 * 평가하기 -> 리스트 -> 직급에 맞는 평가 form 호출
	 * @param rankId
	 * @return
	 */
	List<EvaluationTypeVO> selectEvaluationByRank(
			@Param("rankId") String rankId,
			@Param("year") String year,
			@Param("half") String half);
	
	/**
	 * 피평가자의 직급 조회
	 * @param empId
	 * @return
	 */
	String selectRankIdByEmpId(String empId);
	
	/**
	 * 평가 정보 저장 (evaluationCandidate 테이블 Y값 ) 
	 * @param empId
	 * @param evaluationYear
	 * @param halfYear
	 */
	void updateEvaluationStatus(
			@Param("empId") String empId,
            @Param("evaluationYear") String evaluationYear,
            @Param("halfYear") String halfYear);

}
