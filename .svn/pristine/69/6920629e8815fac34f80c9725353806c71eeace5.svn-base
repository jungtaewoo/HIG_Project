package kr.or.ddit.approval.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.approval.service.ApprovalProcessService;
import kr.or.ddit.approval.service.ApprovalService;
import kr.or.ddit.approval.vo.DraftApproverVO;
import kr.or.ddit.approval.vo.DraftManageMentVO;
import kr.or.ddit.approval.vo.DraftTemplateVO;
import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.vo.EmployeeVO;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 20.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 20.     	CHOI	          기안자 기준(결재 처리)
 *
 * </pre>
 */
@RestController
public class ApprovalDraftController {
	
	//결재 관련 
	@Inject
	private ApprovalProcessService service;
	
	//결재 양식 관련 서비스 
	@Inject
	private ApprovalService temService;
	
	// 직원 정보 
	@Inject
	private EmployeeService empService;
	
	// 부서 관련 정보 
	@Inject
	private DepartmentService deboService;
	
	/**
     *  로그인한 사용자의 empId 가져오기
     */
    @GetMapping("/approval/draft/getEmpId")
    public ResponseEntity<String> getLoggedInEmpId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ResponseEntity.ok(((UserDetails) principal).getUsername());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
	


	/**
	 * 기안 문서 상신
	 * -> 백단 -> 서버쪽 
	 * @param draftMentVO
	 * @return
	 */
    @PostMapping("/approval/draft/submit")
    public ResponseEntity<String> submitApproval(@RequestBody DraftManageMentVO draftMentVO) {
        try {
            // 템플릿 제목을 자동으로 가져오는 로직 추가
            String templateTitle = service.getTemplateTitle(draftMentVO.getTemplateId());
            
            // 제목이 존재하지 않으면 기본 제목 설정
            if (templateTitle == null || templateTitle.isEmpty()) {
                templateTitle = "기본 문서 제목";  // 기본 제목
            }

            draftMentVO.setDraftTitle(templateTitle);  // 템플릿 제목을 DRAFT_TITLE에 설정
            
            System.out.println("=============상신 요청 데이터: " + draftMentVO);
            
//            draftMentVO.getAnnualHistory(), 연차 상세 정보 작성할 때 씀 
            // 기안 문서 상신 로직 처리
            service.submitDraft(draftMentVO, draftMentVO.getDraftapproverList(), draftMentVO.getAnnualHistory(), draftMentVO.getDraftBoxList());
            return ResponseEntity.ok("결재 문서가 상신되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결재 상신 실패");
        }
    }
    
   
	
	
	
	/**
	 * 관리자 또는 특정 사용자가 특정 기안자의 문서를 조회
	 * -> 로그인 세션 정보 없는 조회
	 * @param empId
	 * @return
	 */
	@GetMapping("/approval/draft/my")
	public ResponseEntity<List<DraftManageMentVO>> getDraftsByEmpId(@RequestParam String empId) {
	    return ResponseEntity.ok(service.writeDraftMangeMent(empId));
	}
	
	
	/**
	 * 연차 신청 ->프론트단 -> js 
	 * 템플릿 ID에 해당하는 데이터를 JSON으로 반환
	 * @return 템플릿 내부 input name 값에 맞는 데이터 키로 매핑
	 */
	@GetMapping("/approval/draft/data/{templateId}")
	public ResponseEntity<?> getDraftData(@PathVariable("templateId") Long templateId, HttpSession session) {
	    try {
	        AccountVO accountVO = (AccountVO) session.getAttribute("sessionAccount");

	        // 세션이 없는 경우 에러 처리
	        if (accountVO == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("세션이 만료되었습니다.");
	        }

	        Map<String, Object> map = new HashMap<>();
	        map.put("empId", accountVO.getEmpId());  // 기안자 ID
	        map.put("draftUser", accountVO.getEmpName()); // 기안자 이름
	        map.put("departmentId", accountVO.getDepartmentId()); // 부서 ID

	        // 부서명 조회 추가
	        DepartmentVO deptVO = deboService.getDepartmentById(accountVO.getDepartmentId());
	        if (deptVO != null) {
	            map.put("draftDept", deptVO.getDepartmentName()); // 부서명을 삽입
	        } else {
	            map.put("draftDept", "부서 정보 없음");
	        }

	        // 직원 상세 정보 조회
	        EmployeeVO empVO = empService.readEmp(accountVO.getEmpId());
	        if (empVO != null) {
	            map.put("position", empVO.getPosition()); // 직급
	            map.put("email", empVO.getEmail()); // 이메일 추가
	        }

	        // 결재 관련 정보 (추가 필요 시)
	        map.put("draftDate", LocalDate.now().toString()); // 오늘 날짜
	        
	        
	        //문서번호 매핑 
	        map.put("docNo", null); // 시퀀스로 자동 생성 

	        //템플릿 vo에서 상세정보 조회 가져오기 
	        DraftTemplateVO temVO = temService.draftTemplateDetail(templateId);
	        map.put("templateId", temVO.getTemplateId());
	        
	        //기안자가 결재 라인을 추가함 -> 해당 결재자 정보 
	        DraftApproverVO aprVO = new DraftApproverVO();
	        //결재 선 순번
	        map.put("aprId", aprVO.getAprId());
	        //결재자 ID 
	        map.put("approverId",aprVO.getApproverId());
	        //결재자명
	        map.put("approverName", aprVO.getApproverName());
	        // 결재 순서 
	        map.put("aprSeq", aprVO.getAprSeq());
	        //결재 사유 
	        map.put("aprReason", aprVO.getAprReason()); 
	        
	        // 연차 타입 목록
	        List<AnnualManageVO> annualCodes = service.selectAvailableAnnualTypes();
	        map.put("annualCode", annualCodes);
	        
	        return ResponseEntity.ok(map);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("데이터 조회 실패");
	    }
	}
	
	
	


	
}
