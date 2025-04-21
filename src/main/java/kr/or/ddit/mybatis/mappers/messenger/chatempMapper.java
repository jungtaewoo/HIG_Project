package kr.or.ddit.mybatis.mappers.messenger;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.messenger.vo.ChatEmpVO;
import kr.or.ddit.messenger.vo.ChatFileVO;
import kr.or.ddit.messenger.vo.ChatMemberVO;
import kr.or.ddit.messenger.vo.ChatMessageVO;
import kr.or.ddit.messenger.vo.ChatRoomVO;

@Mapper
public interface chatempMapper {
	
	//직원 목록 가져오기
	public List<ChatEmpVO> chatempList();
	
	// 직원 정보 삽입(계정생성시 채팅계정생성)
	public int insertChatEmp(AccountVO account);
	
	// 사용자 상태 없데이트 (온라인/오프라인)
	public int updateEmpStatus(@Param("empId") String empId, @Param("status") String status);
	 
	 /**
     * 두 사용자가 참여한 1:1 채팅방이 이미 존재하는지 확인
     * @param empId1 로그인 사용자 ID
     * @param empId2 상대방 사용자 ID
     * @return 존재할 경우 ROOM_ID, 없을 경우 null
     */
     public Integer selectOneToOneChatRoom(@Param("empId1") String empId1, @Param("empId2") String empId2);
    
    /**
     * 새 채팅방 생성 (ROOM_TYPE = '1:1')
     * @param room 채팅방 정보
     * @return 처리된 행 수
     */
     public int insertChatRoom(ChatRoomVO room);
    
    /**
     * 마지막 생성된 채팅방 ID 조회 (ROOM_ID 시퀀스 기반)
     * @return 가장 최근 ROOM_ID
     */
     public Integer selectLastChatRoomId();

    /**
     * CHAT_MEMBER 테이블에 채팅방 참여자 등록
     * @param empId 참여할 사용자 ID
     * @param roomId 채팅방 ID
     * @return 처리된 행 수
     */
     public int insertChatMembers(@Param("empId") String empId, @Param("roomId") int roomId);
     
     /**
     * CHAT_MESSAGE 테이블에 메시지 등록
     * @param message
     * @return
     */
     public int insertMessage(ChatMessageVO message);
     
     /**
     * 로그인한 사원의 채팅방 목록 조회
     * @param empId
     * @return
     */
    public List<ChatRoomVO> selectChatRoomByEmpId(String empId);
    
    /**
     * 채팅방에서 사원 나가기
     * @param empId
     * @param roomId
     * @return
     */
    public int deleteChatMember(@Param("empId") String empId, @Param("roomId") int roomId);
    
    
    Map<String, String> selectEmpNameAndRank(@Param("empId") String empId);
    
    Map<String, String> selectChatempInfo(@Param("roomId") int roomId, @Param("currentUserId") String currentUserId);
    
    List<Map<String, String>> selectChatMembersInfo(@Param("roomId") int roomId);
    
    List<ChatMessageVO>selectMessagesByRoomId(long roomId);
    
    /**
     * 메시지 읽음 여부
     * @param messageId
     * @param empId
     * @return
     */
    int readMessage(@Param("messageId") Long messageId, @Param("empId") String empId);
    
    Map<String, String>selectRoomInfo(Long roomId);
    
//    /**
//     * 파일 등록
//     * @param file
//     * @return
//     */
//    public int insertChatFile(ChatFileVO file);
//    
//    /**
//     * 특정 메시지에 첨부된 파일 조회
//     * @param messageId
//     * @return
//     */
//    public List<ChatFileVO> selectFileByMessageId(Long messageId);
//    
//    /**
//     * 파일 단건조회(다운로드용)
//     * @param fileId
//     * @return
//     */
//    public ChatFileVO selectFileById(Long fileId);
    
    public int updateMessageFile(@Param("messageId") Long messageId, @Param("fileDetailId") Long fileDetailId);
}
