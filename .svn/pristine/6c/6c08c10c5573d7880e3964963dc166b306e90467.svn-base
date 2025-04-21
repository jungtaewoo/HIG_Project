package kr.or.ddit.meetingroom.vo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MeetingReservationVO {
    private int reservationId;
    private String empId;
    private int roomId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    
    private String purpose;
    private LocalDateTime createdAt;

    // JOIN용 필드
    private String empName;
    private String roomName;
}