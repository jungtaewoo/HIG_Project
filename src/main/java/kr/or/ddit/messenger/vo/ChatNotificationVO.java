package kr.or.ddit.messenger.vo;


/**
 * 메신저 알림관리 VO
 */
import java.io.Serializable;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class ChatNotificationVO implements Serializable{
   private Long notifyId;
   private Long receiverId;
   private Long roomId;
   private Long messageId;
   private String isRead;
   private LocalDate createAt;
   
}