package kr.or.ddit.account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.attendance.service.AttendanceService;
import kr.or.ddit.attendance.vo.AttendanceVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.messenger.service.ChatEmpService;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.schedule.service.ScheduleService;
import kr.or.ddit.schedule.vo.ScheduleVO;
import kr.or.ddit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author youngjun
 * @since 2025. 3. 12.
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자            수정내용
 *  -----------      -------------    ---------------------------
 *  2025. 3. 12.        youngjun        최초 생성
 *  2025. 3. 14.        태우             로그인 후 컨텐츠 안에서 동적인 현재 시간 받아오기 위함
 *  2025. 3. 14.        규				로그인 후 실시간 ChatEmp 상태 온라인 설정
 *  2025. 3. 25.        현				메인페이지에서 공지글이 나오게 boardList 추가
 *  2025. 3. 31.        현				메인페이지에서 본인일정이 나오게 scheduleList 추가
 *      </pre>
 */
@Slf4j
@Controller
@RequestMapping("/account/login")
@RequiredArgsConstructor
public class AccountLoginController {

	private final SimpMessagingTemplate msgTemplate;
	public static final int ROLE_ID_ADMIN = 93;
	public static final int ROLE_ID_MANAGER = 92;

	@Autowired
	private AccountService service;
	@Autowired
	private ChatEmpService cservice;
	@Autowired
	private AttendanceService Aservice;
	@Autowired
	private BoardService boardservice;
	@Autowired
	private ScheduleService Sservice;
	
	@GetMapping("/home")
   public String accountLogin(Model model
			, @RequestParam(value="workDate", required = false) String workDate
	) throws ParseException {

	   AccountVO sessionAccount  = SecurityUtil.getrealUser();
	   if(sessionAccount ==null ) {
		   return "redirect:/";
	   }

	   log.info("{}==============",sessionAccount.getRoleId());
	   PaginationInfo<BoardVO> paging = new PaginationInfo<>(3, 3);

       // 🔹 공지사항 목록 추가
       List<BoardVO> boardList = boardservice.readBoardList(paging);
       model.addAttribute("boardList", boardList);

	   String empId = sessionAccount.getEmpId();
	   log.info("🔹 현재 로그인한 empId: {}", empId);
	   
	   AttendanceVO Attendance = Aservice.findTodayAttendance(empId);

	   model.addAttribute("employee",Aservice.Employee(empId));
	   model.addAttribute("myAttendance",Aservice.myAttendance(empId,workDate));

	   List<AttendanceVO> attendanceList = Aservice.myAttendance(empId, workDate);
	   log.info("📊 myAttendance 데이터: {}--------------------------------------------------------------------------------------", attendanceList);
	   log.info("📊 myAttendance 데이터: {}", attendanceList);
	   model.addAttribute("myAttendance", attendanceList);


       // 오늘 날짜 기준으로 이번 주의 시작일(월요일)과 종료일(일요일) 계산
       LocalDate today = LocalDate.now();
       LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
       LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

       // 날짜를 문자열로 변환 (MyBatis에서 TO_TIMESTAMP로 변환할 수 있도록)
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String startDateTime = startOfWeek.atStartOfDay().format(formatter);
       String endDateTime = endOfWeek.atTime(LocalTime.MAX).format(formatter);

       // 파라미터를 Map으로 구성 (empId, 시작일, 종료일)
       Map<String, Object> paramMap = new HashMap<>();
       paramMap.put("startDateTime", startDateTime);
       paramMap.put("endDateTime", endDateTime);
       

	   if(Attendance == null) {				//IF 타면 출근 안타면 퇴근
		   AttendanceVO ad = new AttendanceVO();		  
		   ad.setStatusId("STAT_03");	//근무중 상태코드
		   model.addAttribute("workStatus",ad);
		   model.addAttribute("workStatusList",Aservice.workStatusList());

		   if(sessionAccount.getRoleId() == ROLE_ID_ADMIN ||sessionAccount.getRoleId() == ROLE_ID_MANAGER) {
			   List<ScheduleVO> scheduleList = Sservice.getSchedulesByEmpIdWithinRange(paramMap);
			   model.addAttribute("scheduleList", scheduleList);
			   return "tiles:mazer/content1";
		   }
		   // 메인화면에 현재 사용자의 empId를 기준으로 일정 목록 조회 (현재 날짜 기준으로 일주일 이내 것만)
		   paramMap.put("empId", sessionAccount.getEmpId());
		   List<ScheduleVO> scheduleList = Sservice.getSchedulesByEmpIdWithinRange(paramMap);
		   model.addAttribute("scheduleList", scheduleList);
		   return "tiles:mazer/content";
	   }

	   String workStartTime = Attendance.getWorkStartTime();
	   String workEndTime = Attendance.getWorkEndTime();

	    // null 체크
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedStartTime = null;
	    String formattedEndTime = null;

	    if (workStartTime != null) {
	        Date startDate = inputFormat.parse(workStartTime);
	        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
	        formattedStartTime = outputFormat.format(startDate); // 시간 부분만 추출
	    }

	    if (workEndTime != null) {
	        Date endDate = inputFormat.parse(workEndTime);
	        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
	        formattedEndTime = outputFormat.format(endDate); // 시간 부분만 추출
	    }

	    Attendance.setWorkStartTime(formattedStartTime);
	    Attendance.setWorkEndTime(formattedEndTime);

	   List<AttendanceVO> AttendanceList = new ArrayList<>();
	   AttendanceList.add(Attendance);
	   sessionAccount.setAttendance(AttendanceList);
	   model.addAttribute("workStatus",Aservice.workStatusEmployee(empId));
	   model.addAttribute("workStatusList",Aservice.workStatusList());
	   if(sessionAccount.getRoleId() == ROLE_ID_ADMIN ||sessionAccount.getRoleId() == ROLE_ID_MANAGER) {
		   List<ScheduleVO> scheduleList = Sservice.getSchedulesByEmpIdWithinRange(paramMap);
		   model.addAttribute("scheduleList", scheduleList);
		   return "tiles:mazer/content1";
	   }
	   paramMap.put("empId", sessionAccount.getEmpId());
	   List<ScheduleVO> scheduleList = Sservice.getSchedulesByEmpIdWithinRange(paramMap);
	   model.addAttribute("scheduleList", scheduleList);
	   return "tiles:mazer/content";
   }

	@GetMapping("logout")
	public String accountLogout() {
		return "redirect:/account/accountLoginForm";
	}
}
