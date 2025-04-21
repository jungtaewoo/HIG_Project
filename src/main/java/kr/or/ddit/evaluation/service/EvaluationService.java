package kr.or.ddit.evaluation.service;

import java.util.List;

import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.evaluation.vo.EvaluationTypeVO;
import kr.or.ddit.evaluation.vo.EvaluationVO;

public interface EvaluationService {
	
	/**
	 * 평가하기 -> 내가 평가할 대상 리스트 : 팀장 기준으로 소속 팀원 조회
	 * @param evaluatorId
	 * @return
	 */
	List<EvaluationCandidateVO> getEvaluationTargets(String evaluatorId, String year, String half);

	/**
	 * 평가 결과 저장용
	 * @param list
	 */
    void saveEvaluationList(List<EvaluationVO> list);
    
    /**
     * 평가하기 -> 리스트 -> 직급에 맞는 평가 form 호출 
     * @param rankId
     * @return
     */
    List<EvaluationTypeVO> evaluationRank(String rankId , String year, String half);
    
    /**
     * 피평가자의 직급 조회
     * @param empId
     * @return
     */
    String getRankIdByEmpId(String empId);
}
