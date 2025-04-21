package kr.or.ddit.evaluation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.evaluation.vo.EvaluationCandidateVO;
import kr.or.ddit.evaluation.vo.EvaluationTypeVO;
import kr.or.ddit.evaluation.vo.EvaluationVO;
import kr.or.ddit.mybatis.mappers.evaluation.EvaluationMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper mapper;

    // 평가 대상자 조회
    @Override
    public List<EvaluationCandidateVO> getEvaluationTargets(String evaluatorId, String year, String half) {
        return mapper.selectEvaluationTargets(evaluatorId, year, half);
    }

    // 평가 저장 + 상태값 'Y'로 변경
    @Override
    public void saveEvaluationList(List<EvaluationVO> list) {
        if (list == null || list.isEmpty()) return;

        String empId = list.get(0).getEmpId();
        String evaluatorEmpId = list.get(0).getEvaluatorEmpId();
        String year = list.get(0).getEvaluationYear();
        String half = list.get(0).getHalfYear();

        double totalScore = 0.0;
        double totalWeight = 0.0;

        for (EvaluationVO vo : list) {
            // 공통 필드 세팅
            vo.setEmpId(empId);
            vo.setEvaluatorEmpId(evaluatorEmpId);
            vo.setEvaluationYear(year);
            vo.setHalfYear(half);

            // Null-safe 처리
            if (vo.getEvaluationComments() == null) {
                vo.setEvaluationComments("");
            }

            totalScore += vo.getEvaluationScore() * vo.getEvaluationWeight();
            totalWeight += vo.getEvaluationWeight();
        }

        double finalScore = 0.0;
        if (totalWeight > 0.0) {
            finalScore = Math.round((totalScore / totalWeight) * 10.0) / 10.0;
        }

        for (int i = 0; i < list.size(); i++) {
            EvaluationVO vo = list.get(i);

            if (i == 0) {
                vo.setEvaluationFinalScore(finalScore);
                if (vo.getEvaluationFinalComments() == null) {
                    vo.setEvaluationFinalComments("");
                }
            } else {
                vo.setEvaluationFinalScore(0.0); // null로 두면 insert 오류 위험
                vo.setEvaluationFinalComments("");
            }

            if (vo.getEvaluationComments() == null) {
                vo.setEvaluationComments("");
            }

            mapper.insertEvaluation(vo);
        }

        mapper.updateEvaluationStatus(empId, year, half);
    }

    // 직급 기준 평가 항목 조회
    @Override
    public List<EvaluationTypeVO> evaluationRank(String rankId , String year, String half) {
        return mapper.selectEvaluationByRank(rankId, year, half);
    }

    // 피평가자 직급 조회
    @Override
    public String getRankIdByEmpId(String empId) {
        return mapper.selectRankIdByEmpId(empId);
    }
}
