package kr.or.ddit.schedule.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.schedule.service.ScheduleService;
import kr.or.ddit.schedule.vo.ScheduleVO;
import kr.or.ddit.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ScheduleController {

	@Autowired
	private ScheduleService service;

	/**
	 * 전체 일정 조회 후 달력에 뿌려줌
	 * @param model
	 * @return
	 */
	@GetMapping("/schedule/list")
	public String scheduleList(Model model) {
		model.addAttribute("scheduleList",service.ScheduleList());
		return "tiles:schedule/scheduleList";
	}

	/**
	 * 일정을 등록
	 * @param schedule
	 * @return
	 */
	@PostMapping("/schedule/insert")
	public String scheduleInsert(@ModelAttribute("schedule") ScheduleVO schedule) {
	    // 시작일과 종료일을 'T' 제거 후 변환
	    String startDateString = schedule.getStartDate().replace("T", " ");
	    String endDateString = schedule.getEndDate().replace("T", " ");

	    // 시작 날짜와 종료 날짜에 시간을 포함하여 저장
	    schedule.setStartDate(startDateString);
	    schedule.setEndDate(endDateString);

	    service.ScheduleInsert(schedule);

	    return "redirect:/schedule/list";
	}

	/**
	 * 일정 수정
	 * @param schedule
	 */
	@PostMapping("/schedule/update")
	public void scheduleUpdate (@ModelAttribute("schedule") ScheduleVO schedule) {

		 // 시작일과 종료일을 'T' 제거 후 변환
		String startDateString = schedule.getStartDate().replace("T", " ");
        String endDateString = schedule.getEndDate().replace("T", " ");

        schedule.setStartDate(startDateString);
        schedule.setEndDate(endDateString);

		service.ScheduleUpdate(schedule);
	}

	/**
	 * 일정 삭제
	 * @param schedule
	 */
	@PostMapping("/schedule/delete")
	public void scheduleDelete(@ModelAttribute("schedule") ScheduleVO schedule) {
		service.ScheduleDelete(schedule.getScheduleId());
	}

//	/**
//	 * 한 사용자의 일정 가져오기
//	 * @param model
//	 * @return
//	 */
//	@GetMapping("/schedule/mainhome")
//	public String schedule(Model model) {
//        // 현재 로그인한 사용자 정보 가져오기
//        AccountVO user = SecurityUtil.getrealUser();
//        if (user == null) {
//            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트 (필요시)
//            return "redirect:/login";
//        }
//
//        // 현재 사용자의 empId를 기준으로 일정 목록 조회 (본인이 등록한 일정)
//        String empId = user.getEmpId();
//        List<ScheduleVO> scheduleList = service.getSchedulesByEmpId(empId);
//        model.addAttribute("scheduleList", scheduleList);
//		return "tiles:schedule/scheduleMainhome";
//	}

	@GetMapping("/schedule/mainhome")
	public String schedule(Model model) {
	    // 현재 로그인한 사용자 정보 가져오기
	    AccountVO user = SecurityUtil.getrealUser();
	    if (user == null) {
	        return "redirect:/login";
	    }

        // 오늘 날짜 기준으로 이번 주의 시작일(월요일)과 종료일(일요일) 계산
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // 날짜를 문자열로 변환 (MyBatis에서 TO_TIMESTAMP로 변환할 수 있도록)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDateTime = startOfWeek.atStartOfDay().format(formatter);
        String endDateTime = endOfWeek.atTime(LocalTime.MAX).format(formatter);

        log.info("이번 주 시작일: {}", startDateTime);
        log.info("이번 주 종료일: {}", endDateTime);

        // 파라미터를 Map으로 구성 (empId, 시작일, 종료일)
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("empId", user.getEmpId());
        paramMap.put("startDateTime", startDateTime);
        paramMap.put("endDateTime", endDateTime);

        // 서비스에서 본인의 이번 주 일정만 조회
        List<ScheduleVO> scheduleList = service.getSchedulesByEmpIdWithinRange(paramMap);
        model.addAttribute("scheduleList", scheduleList);

        return "tiles:schedule/scheduleMainhome";
	}


}
