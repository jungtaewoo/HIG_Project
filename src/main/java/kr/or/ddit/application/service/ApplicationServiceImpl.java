package kr.or.ddit.application.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.application.vo.ApplicationStatusVO;
import kr.or.ddit.application.vo.ApplicationVO;
import kr.or.ddit.mybatis.mappers.application.ApplicationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationMapper dao;
	
	// 지원서 목록 조회
	@Override
	public List<ApplicationVO> readApplicationList(Long recruitId) {
		return dao.selectApplicationList(recruitId);
	}

	// 지원서 상세 조회
	@Override
	public ApplicationVO readApplicationDetail(Long appId) {
		return dao.selectApplicationDetail(appId);
	}

	// 지원서 등록
	@Override
	public boolean createApplication(ApplicationVO application) {
		return dao.insertApplication(application) > 0;
	}

	// 지원서상태 수정
	@Override
	public boolean modifyApplicationStatus(ApplicationStatusVO appStatus) {
		return dao.updateApplicationStatus(appStatus) > 0;
	}

	// 면접자 목록 조회
	@Override
	public List<ApplicationVO> readInterviewList() {
		return dao.selectInterviewList();
	}

	// 면접 평가 수정
	@Override
	public boolean modifyInterviewResult(ApplicationStatusVO appStatus) {
		return dao.updateInterviewResult(appStatus) > 0;
	}

	
	// 총 지원자 성별 비율 조회
    @Override
    public List<Map<String, Object>> getTotalGenderRatio() {
        List<Map<String, Object>> result = dao.selectTotalGenderRatio();
        // GENDER_COUNT 값을 Integer로 변환
        for (Map<String, Object> map : result) {
            Object genderCount = map.get("GENDER_COUNT");
            if (genderCount instanceof String) {
                map.put("GENDER_COUNT", Integer.parseInt((String) genderCount));
            }
        }
        return result;
    }
    
    // 합격자 성별 비율 조회
    @Override
    public List<Map<String, Object>> getPassedGenderRatio() {
        List<Map<String, Object>> result = dao.selectPassedGenderRatio();
        // GENDER_COUNT 값을 Integer로 변환
        for (Map<String, Object> map : result) {
            Object genderCount = map.get("GENDER_COUNT");
            if (genderCount instanceof String) {
                map.put("GENDER_COUNT", Integer.parseInt((String) genderCount));
            }
        }
        return result;
    }
    
    
    // 면접 상태 비율 조회
    @Override
    public List<Map<String, Object>> getInterviewStatusCount(int year, int half) {
        return dao.selectInterviewStatusCount(year, half);
    }


    
    // 선택된 appId 목록으로 면접자 정보 조회 (엑셀용)
    @Override
    public List<ApplicationVO> readInterviewsByAppIds(List<Long> appIds) {
    	return dao.selectInterviewsByAppIds(appIds);
    }
    
}
