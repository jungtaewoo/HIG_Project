package kr.or.ddit.messenger.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
/**
 * 채팅방을 관리하는 VO
 */
@Data
public class ChatRoomVO implements Serializable{

	
	private Long roomId;
	@NotBlank
	private String roomType;
	@NotBlank
	private String roomName;
	private LocalDate createRoom;
	
	private String displayRoomName; // 프론트에 보여줄 이름(상대방기준)
	
	private List<String> empIdList; // 그룹 채팅 참여자 목록
}
