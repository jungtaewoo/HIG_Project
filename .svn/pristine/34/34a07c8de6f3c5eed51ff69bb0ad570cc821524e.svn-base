package kr.or.ddit.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import kr.or.ddit.messenger.service.ChatEmpService;
import kr.or.ddit.messenger.vo.ChatEmpVO;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy._Proxy_;

@Slf4j
@Controller
public class StatusUpdateController {
	
	@Autowired
	private ChatEmpService service;
	
	@Autowired
	private SimpMessagingTemplate msgTemplate;
	
	@MessageMapping("/status")
	public void updateStatus(ChatEmpVO chatempVO) {
		log.info("웹소켓 업데이트 요청 {}", chatempVO);
		service.updateEmpStatus(chatempVO.getEmpId(), chatempVO.getStatus());
		msgTemplate.convertAndSend("/topic/onlineStatus",chatempVO.getEmpId() + "사원이" + chatempVO.getStatus() + "상태입니다");
	}
}
