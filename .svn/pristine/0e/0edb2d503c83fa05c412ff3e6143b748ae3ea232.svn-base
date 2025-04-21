package kr.or.ddit.salary.controller.allowance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.salary.service.SalaryService;
import kr.or.ddit.salary.vo.AllowanceCodeVO;
import kr.or.ddit.salary.vo.AllowanceVO;
import kr.or.ddit.salary.vo.DeductionCodeVO;

//수당조회
@Controller
public class AllowanceReadController {

	@Autowired
	private SalaryService service;
	
	//수당에 등록된 직원리스트 조회
//	@GetMapping("/Allowance/empList")
//	public String AllowanceEmpList(Model model) {
//		List<AllowanceVO>empAllowanceList = service.EmpAllowanceList();
//		model.addAttribute("empAllowanceList", empAllowanceList);
//		return "tiles:salary/AllowanceForm";
//	}
	
	//수당에 등록된 특정 직원조회
//	@GetMapping("/Allowance/empList/{empId}")
//	public String AllowanceEmpListSelected(Model model, @PathVariable("empId")String empId) {
//		AllowanceVO allowanceEmpSelected = service.EmpAllowanceSelected(empId);
//		return "tiles:salary/AllowanceForm";
//	}
	
	//수당리스트 조회
	@GetMapping("/Allowance/list")
	public String AllowanceList(Model model) {
		List<AllowanceCodeVO>AllowanceList = service.AllowanceList();
		List<DeductionCodeVO> deductionList = service.DeductionList(); // 반환 타입이 List<DeductionVO>
		model.addAttribute("AllowanceList", AllowanceList);
	    model.addAttribute("deductionList", deductionList); // 바로 모델에 바인딩
		return "tiles:salary/AllowanceForm";
	}
}
