package kr.or.ddit.account.controller.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.salary.service.SalaryService;
import kr.or.ddit.salary.vo.SalaryVO;
import kr.or.ddit.team.service.TeamService;
import kr.or.ddit.team.vo.TeamVO;

@ControllerAdvice(basePackages = {"kr.or.ddit.account.controller", "kr.or.ddit.salary.controller"})
public class AccountAdviceController {
	//부서,팀,직급,직책
	@Autowired
	private DepartmentService Dservice;
	@Autowired
	private TeamService Tservice;
	@Autowired
	private EmployeeService Eservice;
	@Autowired
	private SalaryService Sservie;
	
	@ModelAttribute("departmentList")
	public List<DepartmentVO>departmentList(){
		return Dservice.getDepartmentList();
	}
	
	@ModelAttribute("teamList")
	public List<TeamVO>teamList(){
		return Tservice.teamManageList();
	}
	
	@ModelAttribute("empList")
	public List<EmployeeVO>empList(){
		return Eservice.readEmpList();
	}
	
	@ModelAttribute("totalCount")
	public SalaryVO totalCount(
			@RequestParam(value="date",required = false)String payYear,
			@RequestParam(value="date",required = false)String payMonth
			){
		return Sservie.salaryInfo(payYear,payMonth);
	}
	
//   //부서평균급여
//   @ModelAttribute("departMentAvg")
//   public List<SalaryVO>departMentAvgSalary(Model model,
//		   @RequestParam("payYear")int payYear,@RequestParam("payYear")int payMonth) {
//      return Sservie.departMentAvgSalry(payYear,payMonth);
//   }
//	   
//직급평균급여s
//@ModelAttribute("rankAvg")
//public List<SalaryVO>rankAvgSalary(@RequestParam("payYear")int payYear,@RequestParam("payYear")int payMonth ) {
//	return Sservie.rankAvgSalary(payYear,payMonth);
//}
	
}
