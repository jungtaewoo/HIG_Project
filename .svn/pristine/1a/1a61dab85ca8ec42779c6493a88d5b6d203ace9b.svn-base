package kr.or.ddit.empresignations.controller;

import java.security.Provider.Service;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.empappointments.service.AppointmentService;
import kr.or.ddit.empappointments.vo.AppointmentVO;
import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.empresignations.service.ResignationService;
import kr.or.ddit.empresignations.vo.ResignationVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;


/**
 * @author LJW
 * @since 2025. 3. 21.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 21.     	LJW	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/employee/resignFormUIController")
public class ResignationInsertController {

	@Inject
	private EmployeeService empService;
	
	@Inject
	private AppointmentService service;
	
	private static final String MODELNAME = "resignation";
	
	@GetMapping
	public String formUI (Model model) {
		
		List<EmployeeVO> empList = empService.readEmpList();
		model.addAttribute("empList", empList);
				
		List<DepartmentVO> departmentList = service.readDepartment();
		model.addAttribute("departmentList", departmentList);
		
		List<TeamVO> teamList = service.readTeam();
		model.addAttribute("teamList", teamList);
		
		return "tiles:employee/resignFormUI";
		
	}
	
	
	@PostMapping
	public String forUIProcess (
			@ModelAttribute(MODELNAME) AppointmentVO appointment
			,RedirectAttributes redirectAttributes
		) {
		
		// 다음은 퇴사 희장자에게 
		
		return "redirect:/employee/resignList";
	}
}
