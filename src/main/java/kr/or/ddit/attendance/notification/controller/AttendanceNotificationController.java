package kr.or.ddit.attendance.notification.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.attendance.notification.service.AttendanceNotificationService;

/**
 * 근태 알림 관리
 */
@RequestMapping("/attendance/notification")
@Controller
public class AttendanceNotificationController {
	@Inject
	private AttendanceNotificationService service;
	
	
	/**
	 * 근태 알림 리스트
	 * @return
	 */
	@GetMapping("list")
	public String attendanceNoList() {
		return "tiles:attendance/notification/attNoList";
		
	}
}
