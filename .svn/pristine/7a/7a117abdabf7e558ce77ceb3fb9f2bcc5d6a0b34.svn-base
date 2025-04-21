package kr.or.ddit.meetingroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.meetingroom.vo.MeetingReservationVO;
import kr.or.ddit.meetingroom.vo.MeetingRoomVO;
import kr.or.ddit.mybatis.mappers.meetingroom.MeetingMapper;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public List<MeetingRoomVO> getAllMeetingRooms() {
        return meetingMapper.selectAllRooms();
    }

    @Override
    public int createReservation(MeetingReservationVO vo) {
        return meetingMapper.insertReservation(vo);
    }

    @Override
    public boolean isDuplicateReservation(MeetingReservationVO vo) {
        return meetingMapper.checkDuplicateReservation(vo) > 0;
    }

    @Override
    public List<MeetingReservationVO> getReservationsByRoom(int roomId) {
        return meetingMapper.selectReservationsByRoom(roomId);
    }
}