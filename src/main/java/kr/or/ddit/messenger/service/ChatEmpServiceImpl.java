package kr.or.ddit.messenger.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.validator.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.messenger.vo.ChatEmpVO;
import kr.or.ddit.messenger.vo.ChatFileVO;
import kr.or.ddit.messenger.vo.ChatMessageVO;
import kr.or.ddit.messenger.vo.ChatRoomVO;
import kr.or.ddit.mybatis.mappers.messenger.chatempMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatEmpServiceImpl implements ChatEmpService{
	
	@Autowired
	chatempMapper dao;
	
	@Autowired
	private FileInfoService service;
	
	@Override
	public List<ChatEmpVO> chatempList() {
		return dao.chatempList();
	}

	@Override
	public boolean updateEmpStatus(String empId, String status) {
	    log.info(" DB 상태 업데이트 요청 - empId: {}, status: {}", empId, status);
	    
	    int result = dao.updateEmpStatus(empId, status);
	    
	    if (result > 0) {
	        log.info(" DB 상태 업데이트 성공 (변경된 행 수: {})", result);
	    } else {
	        log.warn(" DB 상태 업데이트 실패 (변경된 행 없음) - empId: {}", empId);
	    }

	    return result > 0;
	}
    //채팅방관련    
	@Override
	public int selectOrinsertOneToOneRoom(String empId1, String empId2) {
		log.info("채팅방 대화 대상자!!~#!@#!@#!@# empId1: {} , empId: {}", empId1, empId2);

		if (empId1.equals(empId2)) {
			return 0;
		}

		Integer existingRoomId = dao.selectOneToOneChatRoom(empId1, empId2);
		if (existingRoomId != null) {
			log.info("기존 채팅방 존재 - ROOM_ID:{}", existingRoomId);
			return existingRoomId;
		}

		Map<String, String> empInfo = dao.selectEmpNameAndRank(empId2);
		for (String key : empInfo.keySet()) {
			log.info("🧪 key: {}, value: {}", key, empInfo.get(key));
		}
		log.info("타겟empId: {}", empId2);
		String targetEmpName = empInfo.get("EMP_NAME");
		String targetRank = empInfo.get("RANK_NAME");
		log.info("타겟empName: {}", targetEmpName);
		log.info("타겟empRank: {}", targetRank);
		ChatRoomVO newRoom = new ChatRoomVO();
		newRoom.setRoomType("1:1");
		newRoom.setRoomName(targetEmpName + " (" + targetRank + ")");

		dao.insertChatRoom(newRoom);

		Integer roomId = dao.selectLastChatRoomId();

		dao.insertChatMembers(empId1, roomId);
		dao.insertChatMembers(empId2, roomId);

		return roomId;
	}

    
	@Override
	public int insertGroupChatRoom(String roomName, List<String> empIdList) {
		ChatRoomVO groupRoom = new ChatRoomVO();
		groupRoom.setRoomType("GROUP");
		groupRoom.setRoomName(roomName);
		dao.insertChatRoom(groupRoom);
		
		Integer roomId = dao.selectLastChatRoomId();
		for(String empId :empIdList) {
			dao.insertChatMembers(empId, roomId);
		}
		return roomId;
	}
	//메시지 관련
	@Override
	public int insertMessage(ChatMessageVO message) {
		return dao.insertMessage(message);
	}
	//로그인한 사원의 채팅방 목록
	@Override
	public List<ChatRoomVO> selectChatRoomByEmpId(String empId) {
	    List<ChatRoomVO> roomList = dao.selectChatRoomByEmpId(empId);

	    for (ChatRoomVO room : roomList) {
	        if ("1:1".equals(room.getRoomType())) {
	        	Map<String, String> info = dao.selectChatempInfo(room.getRoomId().intValue(), empId); // 상대방 정보
	            if (info != null) {
	                String name = info.get("EMP_NAME");
	                String rank = info.get("RANK_NAME");
	                room.setDisplayRoomName(name + " (" + rank + ")");
	            } else {
	                room.setDisplayRoomName("알 수 없음");
	            }
	        } else {
	            // 그룹채팅은 원래 이름 유지
	            room.setDisplayRoomName(room.getRoomName());
	        }
	    }

	    return roomList;
	}
	
	//채팅방에서 사원 나가기
	@Override
	public int deleteChatMember(String empId, int roomId) {
	  int result = dao.deleteChatMember(empId, roomId);
	  log.info("채팅방 나가기 empId :{}, roomId: {}", empId,roomId);
	  return result;
	}
	//사원의 이름과 직급조회
	@Override
	public Map<String, String> selectEmpNameAndRank(String empId) {
		return dao.selectEmpNameAndRank(empId);
	}
	
	@Override
	public Map<String, String> selectChatempInfo(int roomId, String currentUserId) {
		log.info("✅ roomId: {}, currentUserId: {}", roomId, currentUserId);
		return dao.selectChatempInfo(roomId, currentUserId);
	}
	//채팅멤버의 정보조회
	@Override
	public List<Map<String, String>> selectChatMembersInfo(int roomId) {
		 return dao.selectChatMembersInfo(roomId);
	}
	//이전메시지 조회
	@Override
	public List<ChatMessageVO> selectMessagesByRoomId(long roomId) {
		List<ChatMessageVO> messages = dao.selectMessagesByRoomId(roomId);
		for(ChatMessageVO msg :messages) {
			//파일 정보가 있는경우, fileDetail추가
			if(msg.getChatFile() != null) {
				FileDetailVO detail = service.getFileById(msg.getChatFile());
				msg.setFileDetail(detail);
			}
		}
		return messages;
		
	}
	
	//메시지 읽음여부
	@Override
	public int readMessage(Long messageId, String empId) {
		return dao.readMessage(messageId, empId);
	}
	//그룹채팅방 알림시 채팅방, 사원(직급)
	@Override
	public Map<String, String> selectRoomInfo(Long roomId) {
		return dao.selectRoomInfo(roomId);
	}
	
//	//파일생성
//	@Override
//	public int insertChatFile(ChatFileVO file) {
//		return dao.insertChatFile(file);
//	}
//	
//	//특정메시지 파일 조회
//	@Override
//	public List<ChatFileVO> selectFileByMessageId(Long messageId) {
//		return dao.selectFileByMessageId(messageId);
//	}
//
//	//파일 단건조회(다운로드용)
//	@Override
//	public ChatFileVO selectFileById(Long fileId) {
//		return dao.selectFileById(fileId);
//	}

	@Override
	public int updateMessageFile(Long messageId, Long fileDetailId) {
		// TODO Auto-generated method stub
		return dao.updateMessageFile(messageId, fileDetailId);
	}
	
	
	
	
}