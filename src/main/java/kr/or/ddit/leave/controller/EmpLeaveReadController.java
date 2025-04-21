package kr.or.ddit.leave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 퇴사자 관리 
 */
@Controller
@RequestMapping("/leave")
public class EmpLeaveReadController {
	
	@GetMapping("list")
	public String LeaveList() {
		return "tiles:leave/leaveList";
		
	}
}
