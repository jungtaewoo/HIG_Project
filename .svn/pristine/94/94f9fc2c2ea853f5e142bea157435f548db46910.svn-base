package kr.or.ddit.application.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.application.vo.ApplicationStatusVO;
import kr.or.ddit.application.vo.ApplicationVO;

public interface ApplicationService {
	
	// 지원서 목록 조회
	public List<ApplicationVO> readApplicationList(Long recruitId);
	
	// 지원서 상세 조회
	public ApplicationVO readApplicationDetail(Long appId);
	
	// 지원서 등록
	public boolean createApplication(ApplicationVO application);
	
	// 지원서상태 수정
	public boolean modifyApplicationStatus(ApplicationStatusVO appStatus);
	
	// 면접자 목록 조회
	public List<ApplicationVO> readInterviewList();
	
	// 면접 평가 수정
	public boolean modifyInterviewResult(ApplicationStatusVO appStatus);
	
	
	// 총 지원자 성별 비율 조회
	public List<Map<String, Object>> getTotalGenderRatio();
	// 합격자 성별 비율 조회
	public List<Map<String, Object>> getPassedGenderRatio();
	// 면접상태 비율 조회
	public List<Map<String, Object>> getInterviewStatusCount(int year, int half);
	
	
	// 선택된 appId 목록으로 면접자 정보 조회
	public List<ApplicationVO> readInterviewsByAppIds(List<Long> appIds);

}
