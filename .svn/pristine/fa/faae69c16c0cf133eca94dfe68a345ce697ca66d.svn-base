package kr.or.ddit.approval.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.approval.service.ApprovalService;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 16.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 16.     	CHOI	          최초 생성
 *  2025. 3. 16.        CHOI              비동기 삭제 처리 
 * </pre>
 */
@Controller
public class ApprovalDeleteController {
	
	@Inject
	private ApprovalService service;
	
	//비동기 삭제 처리 
	@DeleteMapping("/approval/delete")
	public ResponseEntity<String> deleteTemplate(@RequestBody List<Long> templateIds) {
	    if (templateIds == null || templateIds.isEmpty()) {
	        return ResponseEntity.badRequest().body("{\"message\": \"삭제할 항목이 없습니다.\"}");
	    }

	    

	    
	    // 삭제 처리
	    service.deleteDraftTemplates(templateIds);

	    // 성공적으로 삭제된 경우
	    return ResponseEntity.ok("{\"message\": \"삭제 완료\"}");  // JSON 응답 반환
	}
	
	


}
