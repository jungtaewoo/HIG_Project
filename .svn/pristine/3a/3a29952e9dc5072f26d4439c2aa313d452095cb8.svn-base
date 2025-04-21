package kr.or.ddit.messenger.controller;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.messenger.service.ChatEmpService;
import kr.or.ddit.messenger.vo.ChatRoomVO;
import kr.or.ddit.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class MessengerViewController {
@Autowired
private ChatEmpService service;

	
	@GetMapping("/messenger/Main")
	public String messengerMain() {
		return "messenger/messengerMain";
		
	}
	@GetMapping("/messenger/room")
	public String enterChatRoom(@RequestParam("roomId") int roomId, Model model, HttpSession session) {
	    AccountVO account = SecurityUtil.getrealUser();
	    String currentUserId = account != null ? account.getEmpId() : null;
	    log.info("채팅방 입장 - roomId: {}, 로그인 유저 ID: {}", roomId, currentUserId);

	    // 채팅방 유형 확인
	    List<ChatRoomVO> chatRooms = service.selectChatRoomByEmpId(currentUserId);
	    ChatRoomVO thisRoom = chatRooms.stream()
	            .filter(room -> room.getRoomId() == roomId)
	            .findFirst()
	            .orElse(null);

	    if (thisRoom != null && "1:1".equals(thisRoom.getRoomType())) {
	        Map<String, String> chatempInfo = service.selectChatempInfo(roomId, currentUserId);
	        model.addAttribute("chatempInfo", chatempInfo);
	    } else {
	        // 그룹 채팅인 경우 전체 참여자 목록을 조회
	        List<Map<String, String>> memberList = service.selectChatMembersInfo(roomId);
	        model.addAttribute("chatMemberList", memberList);
	    }

	    model.addAttribute("roomId", roomId);
	    return "messenger/chatRoom";
	}

	
}
