package kr.or.ddit.messenger.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.messenger.service.ChatEmpService;
import kr.or.ddit.messenger.vo.ChatMessageVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatMessageController {
	@PostConstruct
	public void init() {
		log.info("Chatmessage 컨트롤러 빈 초기화 됐닌ㄴㄴㄴㄴ~#!@???????");
	}
	
	@Autowired SimpMessagingTemplate msgTemplate;
	
	@Autowired ChatEmpService service;
	
	 @MessageMapping("/chat.send")
	 public void sendMessage(ChatMessageVO message){
		 log.info("==================메시지 수신 : {}===================", message);
		 System.out.println("메시지 수신됨???::::" + message);
		 
		 //senderId -> 이름 + 직급 조회
		 Map<String, String> empInfo= service.selectEmpNameAndRank(message.getSenderId());
		 
		 String empName = empInfo.get("EMP_NAME");
		 String rankName = empInfo.get("RANK_NAME");
		 
	     String senderName = empName + " (" + rankName + ")";
	     message.setSenderName(senderName);
		 
	     Map<String, String> roomInfo = service.selectRoomInfo(message.getRoomId());
	     message.setRoomType(roomInfo.get("ROOM_TYPE"));
	     message.setRoomName(roomInfo.get("ROOM_NAME"));
	     
	     message.setSentAt(LocalDateTime.now());
	     
		 service.insertMessage(message);
		 
		 msgTemplate.convertAndSend("/topic/chat/" + message.getRoomId(),message);
		 msgTemplate.convertAndSend("/topic/chat/global",message);
		 
		 
	 }

}
