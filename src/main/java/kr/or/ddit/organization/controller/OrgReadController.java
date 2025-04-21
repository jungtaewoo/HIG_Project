package kr.or.ddit.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;

/**
 * 직원 조직도
 */
@Controller
@RequestMapping("/employee")
public class OrgReadController {

    private DepartmentService deptservice;

	// 생성자 주입
    @Autowired
    public OrgReadController(DepartmentService deptservice) {
        this.deptservice = deptservice;
    }

	@GetMapping("organization")
	public String EmpChart(Model model, @ModelAttribute("updateSuccess") String updateSuccess, @ModelAttribute("updateError") String updateError) {
		// 조직도 데이터를 가져와 orgDepartmentList로 추가
        List<DepartmentVO> orgDepartmentList = deptservice.getOrg();
        model.addAttribute("orgDeptList", orgDepartmentList);

        // 부서별 직원 수 데이터를 가져와 deptEmpList로 추가
        List<DepartmentVO> deptEmpList = deptservice.getEmployeeCountByDepartment();
        model.addAttribute("deptEmpList", deptEmpList);

        // updateSuccess와 updateError 속성을 model에 추가해서 뷰에서 알림을 표시
        if (updateSuccess != null) {
            model.addAttribute("updateSuccess", updateSuccess);
        }
        if (updateError != null) {
            model.addAttribute("updateError", updateError);
        }


        System.out.println("조직도 데이터: " + orgDepartmentList);
        System.out.println("부서별 직원 수 데이터: " + deptEmpList);

		return "tiles:organization/organizationList";
	}

	// AJAX 요청을 처리하는 메서드
    @GetMapping("getDepartments")
    @ResponseBody
    public List<DepartmentVO> getDepartments() {
        return deptservice.getOrg(); // JSON으로 반환
    }


	// AJAX 요청을 처리하는 메서드
    @GetMapping("getEmployeeCountByDepartment")
    @ResponseBody
    public List<DepartmentVO> getDepartments(Model model) {
        List<DepartmentVO> deptEmpList = deptservice.getEmployeeCountByDepartment();
        model.addAttribute("deptEmpList", deptEmpList);

        return deptEmpList; // JSON으로 반환
    }

}












