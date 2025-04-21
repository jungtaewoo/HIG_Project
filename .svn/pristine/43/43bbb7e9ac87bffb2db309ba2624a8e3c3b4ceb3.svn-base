package kr.or.ddit.messenger.vo;
/**
 * 메신저 세팅 VO
 */
import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ChatSettingVO implements Serializable{
	
	private Long notifyId;
	@NotBlank
	private String receiverId;
	private Long roomId;
	private String isRead;
	private LocalDate createAt;
	
}
