package kr.or.ddit.evaluation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.evaluation.exception.DuplicateEvaluationCandidateException;
import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.mybatis.mappers.evaluation.EvaluationCandidateMapper;
import kr.or.ddit.rank.vo.RankVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaluationCandidateServiceImpl implements EvaluationCandidateService {

    @Autowired
    private EvaluationCandidateMapper mapper;

    
    @Override
    public List<EvaluationCandidateVO> selectAll(String rank, String year, String half) {
        return mapper.readCandidateList(rank, year, half);
    }

    @Transactional
    @Override
    public void updateAll(List<EvaluationCandidateVO> candidates) {
    	for (EvaluationCandidateVO candidate : candidates) {
            if (mapper.existsCandidate(candidate) > 0) {
                throw new DuplicateEvaluationCandidateException();
            }
            
            
            log.info(" ================ 머가 나오 노 candidates:{}",candidates);
            
            // ✅ 평가자 자동 지정
            Long jobId = candidate.getJobId();
            
            String evaluatorId = null;
            
            if (jobId != null) {
                if (jobId <= 1020) {
                    // 인턴, 사원, 선임 → 팀장 평가
                    evaluatorId = mapper.findEvaluatorByJobAndTeam(1030L, candidate.getTeamId());
                } else if (jobId == 1030L) {
                    // 팀장 → 본부장 평가
                    evaluatorId = mapper.findEvaluatorByJobAndDepartment(1040L, candidate.getDepartmentId());
                }
                // 본부장은 평가자 없음 처리 (optionally log)
            }
            log.info("▶ 평가자 지정됨: {} " + evaluatorId + " ← for empId: {} " + candidate.getEmpId());
            log.info("▶ 대상자: {}", candidate.getEmpId());
            log.info(" - jobId: {}", candidate.getJobId());
            log.info(" - teamId: {}", candidate.getTeamId());
            log.info(" - departmentId: {}", candidate.getDepartmentId());
            
            candidate.setEvaluatorId(evaluatorId); // 세팅

            int result = mapper.insertCandidate(candidate);
            if (result == 0) {
                throw new RuntimeException("Insert 실패");
            }
        }
    }

    @Override
    public List<RankVO> getAllRanks() {
        return mapper.readAllRanks();
    }
    
    @Override
    public List<EvaluationCandidateVO> selectAllForInsert(String rank, String year, String half) {
    	return mapper.readCandidateListForInsert(rank, year, half);
    }
    
    @Override
    public List<EvaluationCandidateVO> selectAllForHistory(String rank, String year, String half) {
    	return mapper.readCandidateListForHistory(rank, year, half);
    }
}
