package kr.or.ddit.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annual.service.AnnualHistoryService;
import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 직원 리스트 
 */
/**
 * 
 * @author LJW
 * @since 2025. 3. 12.
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 11.     	LJW	          최초 생성
 *
 *      </pre>
 */
@Slf4j
@Controller
@RequestMapping
public class EmpReadController {

	@Inject
	private EmployeeService service;
	@Autowired
	private AnnualHistoryService Aservice;
	
	@GetMapping("/employee/empList")
	public String EmpList(Model model) {
		List<EmployeeVO> empList = service.readEmpList();
		model.addAttribute("empList", empList);
		return "tiles:employee/empList";

	}
	
	@GetMapping("/employee/empDetail")
	public String EmpDetail(
	    @RequestParam("empWho") String empId,
	    Model model) {

	    EmployeeVO employee = service.readEmp(empId);

	    if (employee == null) {
	        model.addAttribute("error", "직원을 찾을 수 없습니다.");
	        return "errorPage"; 
	    }

	    model.addAttribute("employee", employee);
	    return "/employee/empDetail";  // 여기서 반환되는 뷰는 해당 직원의 상세 정보 페이지
	}
	
	@GetMapping("/employee/count")
	@ResponseBody
	public Map<String, Integer> empCount() {
	    Map<String, Integer> result = new HashMap<>();
	    result.put("attendanceCount",Aservice.TodayAnnualCount());
	    result.put("empCount", service.empCount());
	    result.put("resignCount", service.resignCount());
	    result.put("hireCount", service.hireCount());
	    return result;
	}
	
	@GetMapping("/employee/rankPercent")
	@ResponseBody
	public List<Map<String, Object>> rankPercent(){
		List<Map<String, Object>> result = service.rankPercent();
		return result;
	}
	
	@GetMapping("/employee/tunurePercent")
	@ResponseBody
	public List<Map<String, Object>> tunurePercent(){
		List<Map<String, Object>> result = service.tunurePercent();
		return result;
	}
	
	
}
