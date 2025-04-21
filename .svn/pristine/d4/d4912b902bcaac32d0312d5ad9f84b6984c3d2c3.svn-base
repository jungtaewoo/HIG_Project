package kr.or.ddit.mybatis.mappers.evaluation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.evaluation.vo.EvaluationTypeVO;

@Mapper
public interface evaluationTypeMapper {
	
	/**
	 * 인사평가 유형 리스트 조회 
	 * @param evaluationYear
	 * @param halfYear
	 * @return
	 */
	public List<EvaluationTypeVO> evaluTypeList(
			@Param("evaluationYear") String evaluationYear,
            @Param("halfYear") String halfYear);
	
	/**
	 *  인사 평가 항목 리스트 조회(직급별)
	 * @return
	 */
	public List<EvaluationTypeVO> evaluationRank(
			@Param("evaluationYear") String evaluationYear, 
			@Param("halfYear") String halfYear);	
	
	/**
	 *  인사 평가 항목 추가
	 * @param evaluType
	 */
	public void insertEvaluType(EvaluationTypeVO evaluType);
	
	/** 
	 *  항목 삭제
	 * @param evaluTypeId
	 * @return
	 */
	public int deleteEvaluType(String evaluTypeId);

	/**
	 *  지난 평가 항목 삭제 방어
	 * @param evaluTypeId
	 * @return
	 */
//    EvaluationTypeVO selectEvaluTypeById(String evaluTypeId); // 추가

	
}
