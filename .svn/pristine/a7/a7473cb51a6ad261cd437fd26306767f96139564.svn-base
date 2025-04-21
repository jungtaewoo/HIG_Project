package kr.or.ddit.approval.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.approval.common.AuthUtil;
import kr.or.ddit.approval.service.ApprovalProcessService;
import kr.or.ddit.approval.service.ApproverService;
import kr.or.ddit.approval.vo.ApprovalStatusCountVO;
import kr.or.ddit.approval.vo.DraftApproverVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.qr.controller.QrWebSocket;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 20.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   결재자 기준(결재 처리)
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 20.     	CHOI	          결재자가 해당 문서를 승인/반려/보류처리 
 *
 * </pre>
 */
@Slf4j
@RestController
public class ApprovalApproverController {
	
	@Inject
	private ApprovalProcessService service;
	
	@Inject
	private ApproverService aprService;
	
	
	
	 /**
     * 결재라인 등록 
     * 조직도 모달을 통해 선택한 결재 후보들의 정보를 JSON 배열로 전달받습니다.
     */
	@PostMapping("/approval/insertApprovalLine")
	public ResponseEntity<?> insertApprovalLine(@RequestBody List<DraftApproverVO> approverList){
		try {
            log.info("결재라인 등록 요청, 후보 수: {}", approverList.size());
            List<DraftApproverVO> insertedList = aprService.insertApprovalLine(approverList);
            return ResponseEntity.ok(insertedList);
        } catch (Exception e) {
            log.error("결재라인 등록 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결재라인 등록 실패");
        }
	}
	/**
	 * 결재라인 리스트 
	 * @param empVO
	 * @return
	 */
	@GetMapping("/approval/employee/getLineApprovers")
	public List<Map<String, Object>> getLineApprovers(@RequestParam("empId") String empId) {
	    List<EmployeeVO> empList = aprService.getLineApprovers(empId);
	    
	    // 부서별 그룹화 (LinkedHashMap을 사용하여 순서 유지)
	    Map<String, Map<String, Object>> deptMap = new LinkedHashMap<>();
	    
	    // 본인 부서 노드 열기용 
	    EmployeeVO userInfo = aprService.getEmployeeById(empId); 
	    
	    
	    for (EmployeeVO emp : empList) {
	        String deptIdStr = String.valueOf(emp.getDepartmentId());
	        // 부서 노드 생성 (없으면)
	        if (!deptMap.containsKey(deptIdStr)) {
	            Map<String, Object> deptNode = new HashMap<>();
	            deptNode.put("title", emp.getDepartmentName());  // 부서명만 표시
	            deptNode.put("key", "dept_" + deptIdStr);
	            deptNode.put("folder", true);  // 폴더로 처리
	            Map<String, Object> deptData = new HashMap<>();
	            deptData.put("departmentId", emp.getDepartmentId());
	            deptData.put("departmentName", emp.getDepartmentName());
	            deptNode.put("data", deptData);
	            deptNode.put("children", new ArrayList<Map<String, Object>>());
	            deptMap.put(deptIdStr, deptNode);
	        }
	        
	        // 현재 부서 노드
	        Map<String, Object> deptNode = deptMap.get(deptIdStr);
	        // 팀별 그룹화 : EmployeeVO에 teamId와 teamName이 있어야 함.
	        String teamIdStr = String.valueOf(emp.getTeamId());
	        List<Map<String, Object>> deptChildren = (List<Map<String, Object>>) deptNode.get("children");
	        
	        Map<String, Object> teamNode = null;
	        // 이미 추가된 팀 노드가 있는지 검색 (키: "team_" + teamId)
	        for (Map<String, Object> child : deptChildren) {
	            if (child.get("key") != null && child.get("key").equals("team_" + teamIdStr)) {
	                teamNode = child;
	                break;
	            }
	        }
	        if (teamNode == null) {
	            teamNode = new HashMap<>();
	            teamNode.put("title", emp.getTeamName());  // 팀명만 표시
	            teamNode.put("key", "team_" + teamIdStr);
	            teamNode.put("folder", true);
	            Map<String, Object> teamData = new HashMap<>();
	            teamData.put("teamId", emp.getTeamId());
	            teamData.put("teamName", emp.getTeamName());
	            teamNode.put("data", teamData);
	            teamNode.put("children", new ArrayList<Map<String, Object>>());
	            deptChildren.add(teamNode);
	        }
	        
	     // 사원 노드 생성 (title에는 사원명만 설정하고, 직급은 별도로 rankName으로 전달)
	        Map<String, Object> empNode = new HashMap<>();
	        empNode.put("title", emp.getName());
	        empNode.put("rankName", emp.getRankName());
	        // key에 empId 사용
	        empNode.put("key", emp.getEmpId());
	        Map<String, Object> empData = new HashMap<>();
	        empData.put("empId", emp.getEmpId());  // 반드시 넣기!
	        empData.put("jobName", emp.getJobName() != null ? emp.getJobName() : emp.getJobId());
	        empData.put("departmentId", emp.getDepartmentId());
	        empData.put("departmentName", emp.getDepartmentName());
	        empData.put("teamId", emp.getTeamId());
	        empData.put("teamName", emp.getTeamName());
	        empNode.put("data", empData);
	        empNode.put("children", new ArrayList<Map<String, Object>>());

	        
	        // 해당 팀 노드의 자식 리스트에 사원 노드 추가
	        List<Map<String, Object>> teamChildren = (List<Map<String, Object>>) teamNode.get("children");
	        teamChildren.add(empNode);
	    }
	    
	    // 4) 만약 empList가 비어서 deptMap이 완전히 비어있으면,
	    //    기안자의 부서 노드를 하나 강제로 추가
	    if(deptMap.isEmpty()) {
	        String deptIdStr = String.valueOf(userInfo.getDepartmentId());
	        Map<String, Object> deptNode = new HashMap<>();
	        deptNode.put("title", userInfo.getDepartmentName());
	        deptNode.put("key", "dept_" + deptIdStr);
	        deptNode.put("folder", true);
	        Map<String, Object> deptData = new HashMap<>();
	        deptData.put("departmentId", userInfo.getDepartmentId());
	        deptData.put("departmentName", userInfo.getDepartmentName());
	        deptNode.put("data", deptData);
	        deptNode.put("children", new ArrayList<Map<String, Object>>());
	        deptMap.put(deptIdStr, deptNode);
	    }
	    
	    
	    // 최종적으로 부서 노드들의 리스트 반환
	    return new ArrayList<>(deptMap.values());
	}

	
	/**
	 * 시연용 -> 즐겨찾는 결재라인 리스트 
	 * @param empId
	 * @return
	 */
	@GetMapping("/approval/employee/getFavoriteApprovers")
	public List<Map<String, Object>> getFavoriteApprovers(@RequestParam("empId") String empId) {
	    // 1) DB에서 가져온 직원 목록
	    List<EmployeeVO> empList = aprService.getFavoriteApprovers(empId);
	    
	    // 2) 부서별(=deptMap)로 폴더 구성
	    Map<String, Map<String, Object>> deptMap = new LinkedHashMap<>();
	    EmployeeVO userInfo = aprService.getEmployeeById(empId);

	    // === (A) 부서 / 팀 / 사원 구조 생성 ===
	    for (EmployeeVO emp : empList) {
	        String deptIdStr = String.valueOf(emp.getDepartmentId());
	        // 부서 노드가 없으면 생성
	        if (!deptMap.containsKey(deptIdStr)) {
	            Map<String, Object> deptNode = new LinkedHashMap<>();
	            deptNode.put("title", emp.getDepartmentName());     // 부서명
	            deptNode.put("key", "dept_" + deptIdStr);
	            deptNode.put("folder", true);

	            Map<String, Object> deptData = new HashMap<>();
	            deptData.put("departmentId", emp.getDepartmentId());
	            deptData.put("departmentName", emp.getDepartmentName());
	            deptNode.put("data", deptData);

	            deptNode.put("children", new ArrayList<Map<String, Object>>());
	            deptMap.put(deptIdStr, deptNode);
	        }

	        // 현재 부서 노드
	        Map<String, Object> deptNode = deptMap.get(deptIdStr);

	        // 팀 처리
	        String teamIdStr = String.valueOf(emp.getTeamId());
	        List<Map<String, Object>> deptChildren = (List<Map<String, Object>>) deptNode.get("children");

	        // 이미 생성된 팀 노드가 있는지 검색
	        Map<String, Object> teamNode = null;
	        for (Map<String, Object> child : deptChildren) {
	            if (("team_" + teamIdStr).equals(child.get("key"))) {
	                teamNode = child;
	                break;
	            }
	        }
	        if (teamNode == null) {
	            teamNode = new LinkedHashMap<>();
	            teamNode.put("title", emp.getTeamName());         // 팀명
	            teamNode.put("key", "team_" + teamIdStr);
	            teamNode.put("folder", true);

	            Map<String, Object> teamData = new HashMap<>();
	            teamData.put("teamId", emp.getTeamId());
	            teamData.put("teamName", emp.getTeamName());
	            teamNode.put("data", teamData);

	            teamNode.put("children", new ArrayList<Map<String, Object>>());
	            deptChildren.add(teamNode);
	        }

	        // 사원 노드 생성
	        Map<String, Object> empNode = new LinkedHashMap<>();
	        empNode.put("title", emp.getName());         // 사원명
	        empNode.put("key", emp.getEmpId());
	        empNode.put("folder", false);
	        empNode.put("rankName", emp.getRankName());

	        Map<String, Object> empData = new HashMap<>();
	        empData.put("empId", emp.getEmpId());
	        empData.put("departmentId", emp.getDepartmentId());
	        empData.put("departmentName", emp.getDepartmentName()); // 각 사원의 부서명
	        empData.put("teamId", emp.getTeamId());
	        empData.put("teamName", emp.getTeamName());
	        empData.put("rankName", emp.getRankName());
	        empNode.put("data", empData);

	        empNode.put("children", new ArrayList<>()); // 사원 노드 자식 없음

	        // 팀 노드 children에 사원 추가
	        List<Map<String, Object>> teamChildren = (List<Map<String, Object>>) teamNode.get("children");
	        teamChildren.add(empNode);
	    }

	    // (B) empList가 비어 있을 경우 (옵션)
	    if (empList.isEmpty()) {
	        String deptIdStr = String.valueOf(userInfo.getDepartmentId());
	        Map<String, Object> deptNode = new LinkedHashMap<>();
	        deptNode.put("title", userInfo.getDepartmentName());
	        deptNode.put("key", "dept_" + deptIdStr);
	        deptNode.put("folder", true);

	        Map<String, Object> deptData = new HashMap<>();
	        deptData.put("departmentId", userInfo.getDepartmentId());
	        deptData.put("departmentName", userInfo.getDepartmentName());
	        deptNode.put("data", deptData);

	        deptNode.put("children", new ArrayList<Map<String, Object>>());
	        deptMap.put(deptIdStr, deptNode);
	    }

	    // === (C) "즐겨찾기" 노드를 생성하고, 최종 결과 배열의 맨 위에 삽입 ===
	    // 즐겨찾기 노드는 title만 고정하고, 나머지 속성은 동적으로 처리 (예: dynamicDeptData)
	    // 만약 서버나 다른 곳에서 동적 데이터를 받아온다면 그 객체를 활용할 수 있으나, 
	    // 여기서는 예시로 직접 작성합니다.
	    Map<String, Object> dynamicDeptData = new HashMap<>();
	    dynamicDeptData.put("key", "dept_fav_RD");
	    dynamicDeptData.put("folder", true);
	    // 동적 자식 배열 (예시로 사원 노드들이 들어있는 배열을 넣을 수도 있음)
	    // 여기서는 부서별 노드들을 그대로 사용하려면, customDeptNode를 별도로 만들 필요 없이
	    // 즐겨찾기로 사용할 부서 노드를 직접 선택할 수 있습니다.
	    // 예시에서는 customDeptNode에 동적 속성들을 가져오고, title은 "즐겨찾기"로 고정합니다.
	    dynamicDeptData.put("children", new ArrayList<>()); // 필요시 동적 데이터를 채움

	    // customDeptNode: dynamicDeptData의 모든 속성을 가져오되, title은 "즐겨찾기"로 설정
	    Map<String, Object> customDeptNode = new HashMap<>(dynamicDeptData);
	    customDeptNode.put("title", "즐겨찾기");
	    // 만약 동적 데이터에 다른 값(key, folder, children)이 있다면 그대로 사용됩니다.

	    // 최종 결과 리스트: customDeptNode가 맨 위, 그 다음에 deptMap의 부서 노드들
	    List<Map<String, Object>> result = new ArrayList<>();

	    // customDeptNode를 최상위에 삽입
	    result.add(customDeptNode);

	    // 그리고 deptMap의 값을 모두 넣되, key가 "dept_fav_RD"인 노드가 아니라면 추가
	    for (Map<String, Object> deptNode : deptMap.values()) {
	        // 만약 기존에 같은 key("dept_fav_RD")가 존재하면 추가하지 않음
	        if (!"dept_fav_RD".equals(deptNode.get("key"))) {
	            result.add(deptNode);
	        }
	    }

	    return result;
	}


	
	
	
	/**
     * 결재 진행함 목록을 조회하는 엔드포인트
     * 예시 URL: /approval/approver/processList?approverId=현재결재자ID
     */
	@GetMapping("/approval/approver/processList")
	public ResponseEntity<List<DraftApproverVO>> approverProcessList(@RequestParam String approverId){
		List<DraftApproverVO> draftAprList = aprService.approverProcessList(approverId);
		log.info("결재진행함 리스트 {} => ", draftAprList);
		return ResponseEntity.ok(draftAprList);
	}
	
	/**
     * 종결함 목록을 조회하는 엔드포인트
     * 예시 URL: /approval/approver/complitedList?approverId=현재결재자ID
     */
	@GetMapping("/approval/approver/complitedList")
	public ResponseEntity<List<DraftApproverVO>> approverComplitedList(@RequestParam String approverId){
		List<DraftApproverVO> draftAprList = aprService.approverComplitedList(approverId);
		log.info("종결함 리스트 {} => ", draftAprList);
		return ResponseEntity.ok(draftAprList);
	}
	
	/**
     * 대기/승인/반려 카운트 (로그인 사용자 기준)
     * GET /approval/approver/statusCount
     */
    @GetMapping("/approval/approver/statusCount")
    public ResponseEntity<List<Map<String, Object>>> selectApproverCountsGroup(
    		@RequestParam(value="empId", required=false) String empId) {
    	 if(empId == null || empId.isEmpty()){
             empId = AuthUtil.getLoggedInUserId();
             if(empId == null){
                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
             }
        }

        // empId 기반으로 승인/반려/대기 건수 조회
        List<Map<String, Object>> countGroup = aprService.selectApproverCountsGroup(empId);

        return ResponseEntity.ok(countGroup);
    }
    
    /**
     * 결재자 일 때 대기/승인/반려/결재중 건수 조회 
     * @param approverId -> 결재자 id -> empId 매핑
     * @return
     */
    @GetMapping("/approver/chart")
    public ResponseEntity<?> selectApproverChart(@Param("approverId") String approverId){
    	if(approverId == null || approverId.isEmpty()) {
    		
    		approverId = AuthUtil.getLoggedInUserId();
    	} if(approverId == null) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    	}
    	
    	List<ApprovalStatusCountVO> statusVO = aprService.selectApproverChart(approverId);
    	return ResponseEntity.ok(statusVO);
    }

	
	/**
	 * 결재 승인
	 * @param approverVO
	 * @return
	 */
	@PostMapping("/approval/draft/approve")
	public ResponseEntity<String> approveDraft(@RequestBody DraftApproverVO approverVO){
		try {
			log.info("[결재 승인 요청] - draftId: {}, approverId : {}", approverVO.getDraftId(), approverVO.getApproverId());
			approverVO.setAprStatus("승인"); //승인 상태 설정
			service.approveDraft(approverVO);
			
			return ResponseEntity.ok("결재가 승인되었습니다.");
		} catch (Exception e) {
			log.error("[결재 승인 실패] - {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결재 승인 실패");
		}
	}
	
	/**
	 * 결재 반려
	 * @param approverVO
	 * @return
	 */
	@PostMapping("/approval/draft/reject")
	public ResponseEntity<String> rejectDraft(@RequestBody DraftApproverVO approverVO){
		try {
			log.info("[결재 반려 요청] - draftId: {}, approverId : {}", approverVO.getDraftId(), approverVO.getApproverId());
			approverVO.setAprStatus("반려");
			service.approveDraft(approverVO);
			
			return ResponseEntity.ok("결재가 반려되었습니다.");
			
		} catch (Exception e) {
			log.error("[결재 반려 실패] - {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결재 반려 실패");
		}
		
		
	}
	
	/**
	 * 결재 보류
	 * @param approverVO
	 * @return
	 */
	@PostMapping("/approval/draft/hold")
	public ResponseEntity<String> holdDraft(@RequestBody DraftApproverVO approverVO) {
	    try {
	        log.info("[결재 보류 요청] - draftId: {}, approverId : {}", approverVO.getDraftId(), approverVO.getApproverId());
	        approverVO.setAprStatus("보류");  // 보류 상태 설정
	        service.approveDraft(approverVO);
	        
	        return ResponseEntity.ok("결재가 보류되었습니다.");
	    } catch (Exception e) {
	        log.error("[결재 보류 실패] - {}", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결재 보류 실패");
	    }
	}

	
}
