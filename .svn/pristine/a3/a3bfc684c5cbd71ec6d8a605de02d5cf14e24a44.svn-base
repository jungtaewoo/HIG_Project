package kr.or.ddit.messenger.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.messenger.vo.ChatEmpVO;
import kr.or.ddit.messenger.vo.ChatFileVO;
import kr.or.ddit.messenger.vo.ChatMessageVO;
import kr.or.ddit.messenger.vo.ChatRoomVO;

public interface ChatEmpService {
	List<ChatEmpVO> chatempList();
	
	boolean updateEmpStatus(String empId, String Status);
	
	int selectOrinsertOneToOneRoom(String empId1, String empId2);
	
	int insertMessage(ChatMessageVO message);
	
	public List<ChatRoomVO> selectChatRoomByEmpId(String empId);

	int insertGroupChatRoom(String roomName, List<String> empIdList);
	
	public int deleteChatMember(String empId, int roomId);
	
	Map<String, String> selectEmpNameAndRank(@Param("empId") String empId);
	
	Map<String, String> selectChatempInfo(int roomId, String currentUserId);
	
	List<Map<String, String>> selectChatMembersInfo(int roomId);
	
	List<ChatMessageVO> selectMessagesByRoomId(long roomId);
	
	int readMessage(Long messageId, String empId);
	
	Map<String, String>selectRoomInfo(Long roomId);
	
//    int insertChatFile(ChatFileVO file);
//
//    List<ChatFileVO> selectFileByMessageId(Long messageId);
//
//    ChatFileVO selectFileById(Long fileId);
    
    int updateMessageFile(Long messageId, Long fileDetailId);

}
