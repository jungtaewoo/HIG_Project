package kr.or.ddit.mybatis.mappers.meetingroom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.meetingroom.vo.MeetingReservationVO;
import kr.or.ddit.meetingroom.vo.MeetingRoomVO;

@Mapper
public interface MeetingMapper {
    List<MeetingRoomVO> selectAllRooms();
    int insertReservation(MeetingReservationVO vo);
    int checkDuplicateReservation(MeetingReservationVO vo);
    List<MeetingReservationVO> selectReservationsByRoom(int roomId);
}