package kr.or.ddit.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;

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
public class DepartmentListController {

	@Autowired
	private DepartmentService service;

	@GetMapping("list")
	public String DepartmentList(Model model) {
		List<DepartmentVO> departmentList = service.getDepartmentList();
		model.addAttribute("departmentList", departmentList);
		return "tiles:department/departmentList";
	}
}
