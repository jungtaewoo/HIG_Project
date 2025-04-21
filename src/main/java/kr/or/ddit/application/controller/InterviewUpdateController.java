package kr.or.ddit.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.application.service.ApplicationService;
import kr.or.ddit.application.service.EmailService;
import kr.or.ddit.application.vo.ApplicationStatusVO;
import kr.or.ddit.application.vo.ApplicationVO;
import lombok.RequiredArgsConstructor;

/**
 * 면접자 수정
 * 
 * @author KHT
 * @since 2025. 3. 20.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 20.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/recruit/interview")
public class InterviewUpdateController {

	private final ApplicationService service;
	private final EmailService emailService;
	
	/**
	 * 면접자 수정
	 * @param appStatus - ApplicationStatusVO (지원서관리 VO)
	 * @return
	 */
    @PostMapping("update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateInterviewEvaluation(
    	@RequestBody ApplicationStatusVO appStatus
    ) {
        // 평가 정보 업데이트
        boolean isUpdated = service.modifyInterviewResult(appStatus);

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        
        if (isUpdated) {
        	response.put("success", true);
            response.put("message", "면접 평가가 업데이트되었습니다.");
            
            try {
                // 지원서 상세 정보 가져오기 (메일 전송에 필요)
                ApplicationVO application = service.readApplicationDetail(appStatus.getAppId());
                String status = appStatus.getCurrentStatus(); // 예: "합격", "불합격"

                // 상태에 따라 메일 전송
                if ("합격".equals(status)) {
                    emailService.sendPassEmail(application);
                } else if ("불합격".equals(status)) {
                    emailService.sendFailEmail(application);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.put("emailError", "메일 전송 중 오류 발생");
            }
            
            return ResponseEntity.ok(response);
            
        } else {
        	response.put("success", false);
            response.put("message", "업데이트 중 오류가 발생했습니다.");
            return ResponseEntity.status(500).body(response);
        }
        
        
    }
}
