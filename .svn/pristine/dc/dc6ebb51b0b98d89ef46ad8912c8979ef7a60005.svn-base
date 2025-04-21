package kr.or.ddit.messenger.vo;

/**
 * 채팅 메시지 관리VO
 */
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.or.ddit.file.vo.FileDetailVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageVO implements Serializable{
	private Long messageId;
	private Long roomId;
	@NotBlank
	private String senderId;
	private String messageText;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime sentAt;
	private String readBy;
	
	private int totalMemberCount;
	private String senderName;
	
	private String roomType;
	private String roomName;
	
	private Long chatFile;
	
	private FileDetailVO fileDetail;
}
