package kr.or.ddit.meetingroom.service;

import java.util.List;

import kr.or.ddit.meetingroom.vo.MeetingReservationVO;
import kr.or.ddit.meetingroom.vo.MeetingRoomVO;

public interface MeetingService {

    // 회의실 목록 조회
    List<MeetingRoomVO> getAllMeetingRooms();

    // 예약 등록
    int createReservation(MeetingReservationVO vo);

    // 중복 체크
    boolean isDuplicateReservation(MeetingReservationVO vo);

    // 특정 회의실의 예약 목록
    List<MeetingReservationVO> getReservationsByRoom(int roomId);
}