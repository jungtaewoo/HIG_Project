package kr.or.ddit.department.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

/**
 * 부서 등록, 팀 등록
 */
@Controller
@RequestMapping("/department")
public class DepartmentInsertController {

	@Inject
	private DepartmentService service;

	/**
	 * 부서 등록
	 * @return
	 */
	@GetMapping("register")
	public String DepartmentForm() {
		return "tiles:department/departmentFormUI";
	}


	@PostMapping("register/commit")
	@ResponseBody
	public Map<String, Object> DepartmentInsert(
	        @ModelAttribute DepartmentVO dept, HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        service.insertDepartment(dept);
	        result.put("success", true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("success", false);
	        result.put("message", "등록 중 오류가 발생했습니다.");
	    }
	    return result;
	}


//    아래는 동기방식 부서추가 컨트롤러
//    @PostMapping("register/commit")
//    public String DepartmentInsert(HttpSession session,
//    		@ModelAttribute DepartmentVO dept, Model model
//    ) {
//
//        // 서비스 계층을 통해 데이터베이스에 저장
//        service.insertDepartment(dept);
//    	return "redirect:/department/list";
//
//	}
}
