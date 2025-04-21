package kr.or.ddit.approval.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.approval.common.AuthUtil;
import kr.or.ddit.approval.service.ApprovalProcessService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author CHOI
 * @since 2025. 4. 3.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자           수정내용
 *  -----------      -------------    ---------------------------
 *  2025. 4. 3.      CHOI            상신함 기안자 기능 (기안회수, 재상신(수정))
 *
 * </pre>
 */
@Slf4j
@RestController 
public class ApprovalDrafterController {
    
    @Inject
    private ApprovalProcessService service;
    
    /**
     * 기안 회수 기능 
     * @param params - 요청 바디에 포함된 { draftId, empId }
     * @return 회수 결과 메시지
     */
    @PostMapping("/approval/drafter/recall")
    public ResponseEntity<String> recallDraft(@RequestBody Map<String, Object> params){
        try {
            // params에서 draftId와 empId를 가져옴
            Long draftId = Long.valueOf(params.get("draftId").toString());
            String empId = (params.get("empId") != null && !params.get("empId").toString().isEmpty()) 
                    ? params.get("empId").toString() 
                    : null;
            
            // 로그인된 사용자 ID
            String loggedInId = AuthUtil.getLoggedInUserId();
            if(empId == null) {
                empId = loggedInId;
            }
            
            // 새 map에 필요한 파라미터를 세팅
            Map<String, Object> map = new HashMap<>();
            map.put("draftId", draftId);
            map.put("empId", empId);
            
            log.info("로그인한 사용자 ID: {}", loggedInId);
            // 새로 만든 map을 서비스에 전달
            service.recallDraft(map);
            log.info("회수 요청 성공 -> {} , empId : {}", map, empId);
            return ResponseEntity.ok("기안 회수 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("기안 회수 실패");
        }
    }
}
