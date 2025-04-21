package kr.or.ddit.mybatis.mappers.application;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.application.vo.ApplicationStatusVO;
import kr.or.ddit.application.vo.ApplicationVO;

@Mapper
public interface ApplicationMapper {

	//지원서 목록 조회
	public List<ApplicationVO> selectApplicationList(Long recruitId);
	
	//지원서 상세 조회
	public ApplicationVO selectApplicationDetail(Long appId);
	
	//지원서 등록
	public int insertApplication(ApplicationVO application);
	
	//지원서상태 수정
	public int updateApplicationStatus(ApplicationStatusVO appStatus);
	
	//면접자 목록 조회
	public List<ApplicationVO> selectInterviewList();
	
	//면접 평가 수정
	public int updateInterviewResult(ApplicationStatusVO appStatus); 
	
	
	// 총 지원자 성별 비율 조회
	public List<Map<String, Object>> selectTotalGenderRatio();
	
	// 합격자 성별 비율 조회
	public List<Map<String, Object>> selectPassedGenderRatio();
	
	// 면접상태 비율 조회
	public List<Map<String, Object>> selectInterviewStatusCount(@Param("year")int year, @Param("half")int half);
	
	
	
	// 선택된 appId 목록으로 면접자 정보 조회 
	public List<ApplicationVO> selectInterviewsByAppIds(@Param("appIds") List<Long> appIds);

}
