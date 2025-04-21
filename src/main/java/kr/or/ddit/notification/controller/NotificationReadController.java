package kr.or.ddit.notification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notification")
public class NotificationReadController {
	
	@GetMapping("list")
	public String NotificationList() {
		return "tiles:notification/notificationList";
		
	}
}
