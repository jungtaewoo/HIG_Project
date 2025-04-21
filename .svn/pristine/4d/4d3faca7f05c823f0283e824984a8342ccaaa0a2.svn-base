package kr.or.ddit.workstatus.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import kr.or.ddit.workstatus.service.WorkStatusService;
import kr.or.ddit.workstatus.vo.WorkstautsVO;

@Controller
@RequestMapping("/workstauts")
public class WorkstatusController {

	@Autowired
	WorkStatusService service;
	
	/**
	 * 업무 상태 종류 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String WorkStatusList(Model model) {
		model.addAttribute("workStatusManageList", service.workStatusManageList());
		return "tiles:workstatus/workstatusMange";
	}
	
	/**
	 * 업무 상태 등록 
	 * @param workstautsManage
	 * @return
	 */
	@PostMapping("Insert")
	@ResponseBody
	public ResponseEntity<Map<String, String>> workStatusManageInsert(@RequestBody WorkstautsVO workstautsManage) {
	    service.insertWorkStatusItem(workstautsManage);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "Work status item inserted successfully.");
	    
	    return ResponseEntity.ok(response); 
	}
	
	/**
	 * 업무 상태 사용여부 업데이트
	 */
	@PostMapping("Update")
	@ResponseBody
	public void workStatusManageUpdate(@RequestBody WorkstautsVO workstautsManage) {
		service.updateWorkStatusItemStatus(workstautsManage);
	}
	
	/**
	 *  업무 상태 삭제
	 * @param workstautsCodeMap
	 * @return
	 */
	@PostMapping("Delete")
	@ResponseBody
	public ResponseEntity<String> workStatusManageDelete(@RequestBody Map<String, String> workstautsCodeMap) {
		String workstautsCode = workstautsCodeMap.get("statusId");
		service.deleteWorkStatusItem(workstautsCode);
		return ResponseEntity.ok("삭제 완료");
	}
	
	/**
	 * 사용자가 업무상태 변경시 쓰는 메서드
	 * @param statusId
	 * @param empId
	 * @param workDate
	 */
	@PostMapping("updateWorkStatus")
	@ResponseBody
	public void updateWorkStatus(@RequestParam("statusId") String statusId, @RequestParam("empId") String empId, @RequestParam("workDate") String workDate) {
		service.updateWorkStatus(statusId, empId ,workDate);
	};
}
