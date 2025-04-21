package kr.or.ddit.messenger.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 *  채팅 파일 업로드를 관리하는 VO
 */
@Data
public class ChatFileVO implements Serializable{
	@NotBlank
	private Long fileId;
	private Long messageId;
	@NotBlank
	private String uploaderId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String filePath;
    private LocalDate uploadDate;
    
	
}
