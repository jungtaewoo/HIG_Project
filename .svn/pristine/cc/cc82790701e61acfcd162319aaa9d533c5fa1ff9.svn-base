package kr.or.ddit.messenger.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;
/**
 * 채팅방 멤버 정보를 관리하는 VO
 */
@Data
public class ChatMemberVO implements Serializable{
	@NotBlank
	private String empId;
	private Long roomId;
	private LocalDate joinedAt;
	private String role;   //admin, member
	
}
