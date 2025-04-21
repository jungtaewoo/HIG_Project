package kr.or.ddit.messenger.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.messenger.service.ChatEmpService;
import kr.or.ddit.messenger.vo.ChatEmpVO;
import kr.or.ddit.messenger.vo.ChatFileVO;
import kr.or.ddit.messenger.vo.ChatMessageVO;
import kr.or.ddit.messenger.vo.ChatRoomVO;
import kr.or.ddit.mybatis.mappers.messenger.chatempMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/messenger", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MessengerRestController {
	@Autowired
	private chatempMapper dao;
	
	@Autowired
	private ChatEmpService service;
	
	@Autowired 
	private FileInfoService fileService;

	private final SimpMessagingTemplate msgTemplate;
	//직원 목록
	@GetMapping("empList")
	public List<ChatEmpVO> chatemplist(){
		List<ChatEmpVO>list = service.chatempList();
		list.forEach(vo-> System.out.println("emp ID!@#!@#!@#!@#굿" + vo.getEmpId()));
		System.out.println("직원목록 요청받음");
		return service.chatempList();
	}
	
	// 직원 상태 업데이트(온라인/오프라인) WebSocket 실시간 반영
	@PostMapping("updateStatus")
	public String updateEmpStatus(@RequestParam("empId")String empId,
								  @RequestParam("status")String status) {
		int result =dao.updateEmpStatus(empId, status);
		if(result>0) {
       
			
            msgTemplate.convertAndSend("/topic/onlineStatus", empId + "상태가" + status);
            log.info("상태업데이트 뎌ㅐㅅ음?!@#!@#@!$!@$!@$@!$@!", status);		
			return "상태 업데이트 성공";
			
			
		}else {
			return "상태 업데이트 실패";
		}
	}
	//채팅방 생성 혹은 기존에 있는 채팅방 가져오기
	 @PostMapping("/selectOrInsertRoom")
	    public ResponseEntity<Integer> getOrCreateRoom(
	            @RequestParam("empId1") String empId1,
	            @RequestParam("empId2") String empId2) {

	        int roomId = service.selectOrinsertOneToOneRoom(empId1, empId2);
	        return ResponseEntity.ok(roomId); // 방 번호 리턴
	    }
	 //로그인한 사원의 채팅방 목록조회
	 @GetMapping("/chatRoom")
	 public List<ChatRoomVO>selectChatRoomByEmpId(@RequestParam("empId")String empId){
		 return service.selectChatRoomByEmpId(empId);
	 }
	 
	 //그룹채팅 생성
	@PostMapping("/insertGroupChat")
	public ResponseEntity<Integer> insertGroupChatRoom(@RequestBody ChatRoomVO room){
		String roomName = room.getRoomName();
		List<String> empIdList = room.getEmpIdList(); //참여자 목록
		
		if(empIdList == null || empIdList.size()<2) {
			return ResponseEntity.badRequest().body(-1); // 참여자 2명미만이면 오류류류
		}
		
		log.info("그룹채팅 생성 요청 : roomName={}, 참여자 ={}", roomName, empIdList);
		
		int roomId = service.insertGroupChatRoom(roomName, empIdList);
		return ResponseEntity.ok(roomId);
	}
	
	//채팅방에서 사원나가기
	@PostMapping("/deleteChatMember")
	public ResponseEntity<?> deleteChatMember(@RequestParam("empId") String empId,
												   @RequestParam("roomId") int roomId){
		Map<String, Object> map = new HashMap<>();
		
		System.out.println("========================deleteChatMember");
		System.out.println("empId:"+empId);
		System.out.println("roomId:"+roomId);
		
		int cnt =service.deleteChatMember(empId, roomId);
		System.out.println("cnt:"+cnt);
		map.put("cnt", cnt);
		
		if(cnt>0) {
			System.out.println("======================== True ...");
	        return ResponseEntity.ok(map);
		} else {
			System.out.println("======================== False ...");
			return ResponseEntity.badRequest().body("실패");
		}
		
				
	}
	//이전 메시지 조회
	@GetMapping("/messages")
	public List<ChatMessageVO> PreviousMessages(@RequestParam("roomId") Long roomId){
		log.info("이전 메시지 roomId:{}",roomId);
		return service.selectMessagesByRoomId(roomId);
	}
	@PostMapping("/message/read")
	public ResponseEntity<?> readMessage(
			@RequestParam("messageId") Long messageId,
			@RequestParam("empId") String empId){
		
		int result = service.readMessage(messageId, empId);
		
		if(result>0) {
			return ResponseEntity.ok("읽음완료");
			
		}else {
			return ResponseEntity.badRequest().body("읽음실패");
		}
	}
	//파일 업로드
	@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam("roomId") Long roomId,
	    @RequestParam("senderId") String senderId) {

	    try {
	        // 1. 메시지 객체 생성
	        ChatMessageVO message = new ChatMessageVO();        
	        message.setRoomId(roomId);
	        message.setSenderId(senderId);
	        message.setMessageText("[파일을 보냈습니다.]");
	        service.insertMessage(message);
	        Long messageId = message.getMessageId();

	        // 2. 파일 저장
	        List<MultipartFile> files = List.of(file);
	        List<Long> detailIds = fileService.saveFiles(files.toArray(new MultipartFile[0]));
	        Long fileDetailId = detailIds.get(0);

	        // 3. DB 업데이트 + VO에 세팅
	        service.updateMessageFile(messageId, fileDetailId);
	        message.setChatFile(fileDetailId);

	        // 4. 파일 상세 정보 포함 (카드 UI용)
	        var fileDetail = fileService.getFileById(fileDetailId);
	        message.setFileDetail(fileDetail);

	        // 5. 사용자 정보, 채팅방 정보 설정
	        Map<String, String> empInfo = service.selectEmpNameAndRank(senderId);
	        message.setSenderName(empInfo.get("EMP_NAME") + " (" + empInfo.get("RANK_NAME") + ")");
	        Map<String, String> roomInfo = service.selectRoomInfo(roomId);
	        message.setRoomType(roomInfo.get("ROOM_TYPE"));
	        message.setRoomName(roomInfo.get("ROOM_NAME"));
	        message.setSentAt(LocalDateTime.now());

	        // 6. WebSocket 실시간 전송
	        msgTemplate.convertAndSend("/topic/chat/" + roomId, message);
	        msgTemplate.convertAndSend("/topic/chat/global", message);

	        return ResponseEntity.ok("파일 업로드 및 메시지 등록 성공");

	    } catch (Exception e) {
	        log.error("파일 업로드 예외 발생", e);
	        return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
	    }
	}


	@GetMapping("/downloadFile")
	public ResponseEntity<?> downloadFile(@RequestParam("fileId") Long fileId) {
	    FileDetailVO fileDetail = fileService.getFileById(fileId); // FileDetailVO로 가져오기
	    if (fileDetail == null) {
	        return ResponseEntity.notFound().build();
	    }

	    File file = new File(fileDetail.getFilePath());
	    if (!file.exists()) {
	        return ResponseEntity.notFound().build();
	    }

	    try {
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	        return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileDetail.getFileName(), "UTF-8") + "\"")
	            .contentLength(file.length())
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	    } catch (IOException e) {
	        log.error("파일 다운로드 실패", e);
	        return ResponseEntity.status(500).body("파일 다운로드 실패: " + e.getMessage());
	    }
	}
}
