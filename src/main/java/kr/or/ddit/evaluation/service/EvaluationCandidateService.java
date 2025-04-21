package kr.or.ddit.evaluation.service;

import java.util.List;

import kr.or.ddit.evaluation.exception.DuplicateEvaluationCandidateException;
import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.rank.vo.RankVO;

public interface EvaluationCandidateService {
    
    // 전체 후보자 리스트 조회
    public List<EvaluationCandidateVO> selectAll(String rank, String year, String half);
    
    // 여러 직원 정보를 업데이트
    public void updateAll(List<EvaluationCandidateVO> candidates) throws DuplicateEvaluationCandidateException;
    
    public List<RankVO> getAllRanks();
    
 // 등록용 - 평가 정보 없어도 포함됨 (LEFT JOIN)
    List<EvaluationCandidateVO> selectAllForInsert(String rank, String year, String half);

    // 과거용 - 평가 정보 있는 직원만 (INNER JOIN)
    List<EvaluationCandidateVO> selectAllForHistory(String rank, String year, String half);


}
