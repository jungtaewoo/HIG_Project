package kr.or.ddit.meetingroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.meetingroom.service.MeetingService;
import kr.or.ddit.meetingroom.vo.MeetingReservationVO;
import kr.or.ddit.meetingroom.vo.MeetingRoomVO;
import kr.or.ddit.security.SecurityUtil;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    // 1. 회의실 목록 보기
    @GetMapping("/roomList")
    public String roomList(Model model) {
        List<MeetingRoomVO> rooms = meetingService.getAllMeetingRooms();
        model.addAttribute("rooms", rooms); 
        return "tiles:meeting/roomList";
    }

    // 2. 예약 폼 이동
    @GetMapping("/reserveForm")
    public String reserveForm(@RequestParam("roomId") int roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "tiles:meeting/reserveForm"; 
    }

    // 3. 예약 등록 처리
    @PostMapping("/reserve")
    public String reserve(
            @ModelAttribute MeetingReservationVO reservationVO,
            Model model
    ) {
    	AccountVO account = SecurityUtil.getrealUser();
    	
    	reservationVO.setEmpId(account.getEmpId());
    	
        // 중복 예약 체크
        boolean isDuplicated = meetingService.isDuplicateReservation(reservationVO);
        if (isDuplicated) {
            model.addAttribute("error", "해당 시간에 이미 예약이 존재합니다.");
            return "tiles:meeting/reserveForm";
        }

        // 예약 등록
        meetingService.createReservation(reservationVO);
        return "redirect:/meeting/roomList";
    }

    // 4. 회의실별 예약 현황 보기 (선택)
    @GetMapping("/reservations")
    public String reservationList(@RequestParam("roomId") int roomId, Model model) {
        List<MeetingReservationVO> list = meetingService.getReservationsByRoom(roomId);
        model.addAttribute("reservations", list);
        return "tiles:meeting/reservationList"; // 예: 예약 리스트 출력용 JSP
    }
}