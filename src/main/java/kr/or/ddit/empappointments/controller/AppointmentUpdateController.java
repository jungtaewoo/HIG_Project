package kr.or.ddit.empappointments.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.empappointments.service.AppointmentService;
import kr.or.ddit.empappointments.vo.AppointmentVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/employee/appointUpdate")
@RequiredArgsConstructor
public class AppointmentUpdateController {
	
	private final AppointmentService service;
	public static final String MODELNAME = "appointment";
	
	@GetMapping
	public String updateFormUI(
			@RequestParam("appwho") String appId
			, Model model ) {
		AppointmentVO appointment = service.readAppoint(appId);
		model.addAttribute(MODELNAME, appointment);
		
		// 옵션에서 부서 불러오기
		List<DepartmentVO> departmentList = service.readDepartment();
		model.addAttribute("departmentList", departmentList);
		// 옵션에서 직급 불러오기
		List<RankVO> rankList = service.readRank();
		model.addAttribute("rankList", rankList);
		// 옵션에서 팀 불러오기
		List<TeamVO> teamList = service.readTeam();
		model.addAttribute("teamList", teamList);
		// 옵션에서 직무 불러오기
		List<PositionVO> positionList = service.readPosition();
		model.addAttribute("positionList", positionList);
		// 옵션에서 직책 불러오기
		List<JobVO> jobList = service.readJob();
		model.addAttribute("jobList", jobList);
		log.info("=================================={}{}{}{}{}{}" + jobList,departmentList,rankList,teamList,positionList);

		return "tiles:employee/appointUpdate";
	}
	
	@PostMapping
	public String updateProcess(
		@ModelAttribute("appointments") List<AppointmentVO> appointmentList 	
		, BindingResult errors
		, HttpSession session
		, RedirectAttributes redirectAttributes
	) {
		String logicalName = null;
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			for (AppointmentVO appointment : appointmentList) {
		         service.modifyAppoint(appointment);
		    }
			logicalName = "tiles:employee/appointList";
			
		} else {
			redirectAttributes.addFlashAttribute("appointments", appointmentList);
			String errorName = BindingResult.MODEL_KEY_PREFIX + "appointments";
			redirectAttributes.addFlashAttribute(errorName, errors);
			logicalName = "redirect:/employee/appointDetail";		
		}	
		return logicalName;
	}
	
}
