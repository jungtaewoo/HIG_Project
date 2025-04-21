package kr.or.ddit.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;
import lombok.RequiredArgsConstructor;

/**
*
* @author KHS
* @since 2025. 3. 11.
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      			수정자           수정내용
*  -----------   	-------------    ---------------------------
*  2025. 3. 11.     	KHS	          최초 생성
*
* </pre>
*/


@Controller
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentUpdateController {

	private final DepartmentService service;
	public static final String MODELNAME = "department";

//	@GetMapping("update")
//	public String DepartmentUpdate() {
//		return "tiles:department/departmentUpdate";
//	}

	@GetMapping("update")
	public String formUI(@RequestParam("departmentId") Long departmentId, Model model) {
	    DepartmentVO department = service.getDepartmentById(departmentId);
	    model.addAttribute("department", department);
	    return "tiles:department/departmentUpdate";
	}


	@PostMapping("update/success")
	public String formProcess(@ModelAttribute DepartmentVO department, RedirectAttributes rttr) {
		 try {
		        boolean result = service.updateDepartment(department);
		        if(result) {
		            rttr.addFlashAttribute("updateSuccess", "부서 수정이 완료되었습니다.");
		        } else {
		            rttr.addFlashAttribute("updateError", "부서 수정 중 오류가 발생했습니다.");
		        }
		    } catch(Exception e) {
		        rttr.addFlashAttribute("updateError", "수정 중 오류가 발생했습니다: " + e.getMessage());
		    }
		    return "redirect:/employee/organization";
	}
}
