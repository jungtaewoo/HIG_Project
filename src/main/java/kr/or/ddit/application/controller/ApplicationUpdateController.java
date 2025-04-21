package kr.or.ddit.application.controller;

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
 * 지원서 수정
 * 
 * @author KHT
 * @since 2025. 3. 18.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 18.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/recruit/applicant")
@RequiredArgsConstructor
public class ApplicationUpdateController {
	
	private final ApplicationService service;
	private final EmailService emailService; // 추가
	

	@PostMapping("update")
    @ResponseBody
    public String recruitApplicantUpdate(
    	@RequestBody ApplicationStatusVO appStatus
    ) {
        // service를 호출하여 상태 업데이트
        boolean isUpdated = service.modifyApplicationStatus(appStatus);

        if (isUpdated) {
            // 성공 시 응답
        	
        	// 지원자 상세 정보 가져오기
        	ApplicationVO application = service.readApplicationDetail(appStatus.getAppId());

            if ("서류탈락".equals(appStatus.getCurrentStatus())) {
                emailService.sendRejectionEmail(application);
            } else if ("면접예정".equals(appStatus.getCurrentStatus())) {
                emailService.sendInterviewEmail(application, appStatus.getInterviewDate());
            }
        	
        	
            return "{\"message\": \"평가 저장 및 메일 발송 완료\"}";
            
        } else {
            // 실패 시 응답
            return "{\"message\": \"평가 업데이트 실패\"}";
        }
    }
	
	
	
}
