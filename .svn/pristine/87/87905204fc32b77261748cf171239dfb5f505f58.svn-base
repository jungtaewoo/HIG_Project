package kr.or.ddit.mybatis.mappers.evaluation;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.rank.vo.RankVO;

@Mapper
public interface EvaluationCandidateMapper {
	
    // 평가 대상자 리스트 조회
    public List<EvaluationCandidateVO> readCandidateList(@Param("rank") String rank, 
											            @Param("year") String year, 
											            @Param("half") String half);

    // 여러 후보자 업데이트
    public int insertCandidate(EvaluationCandidateVO candidate);
    
    public int existsCandidate(EvaluationCandidateVO candidate);
    
    List<RankVO> readAllRanks();

    List<EvaluationCandidateVO> readCandidateListForInsert(
    		@Param("rank") String rank,
    		@Param("year") String year,
    		@Param("half") String half);
    
    List<EvaluationCandidateVO> readCandidateListForHistory(
    		@Param("rank") String rank,
    		@Param("year") String year,
    		@Param("half") String half);
    
    // 팀장찾기
    String findEvaluatorByJobAndTeam(
    		@Param("jobId") Long jobId
    	   ,@Param("teamId") Long teamId);

    
    // 부서장찾기
    String findEvaluatorByJobAndDepartment(
    		@Param("jobId") Long jobId
    	   ,@Param("departmentId") Long departmentId);
    
}