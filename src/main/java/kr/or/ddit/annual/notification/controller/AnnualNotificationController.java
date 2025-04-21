package kr.or.ddit.annual.notification.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.annual.notification.service.AnnualNotificationService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/annual/notifications")
@Controller
public class AnnualNotificationController {
	@Inject
	private AnnualNotificationService service;
	
}
