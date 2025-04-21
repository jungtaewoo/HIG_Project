package kr.or.ddit.department.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@Controller
@RequestMapping("/department")
public class DepartmentDeleteController {

	@Autowired
	private DepartmentService service;

	// ✅ 여러 개의 부서 삭제
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteDepartments(@RequestBody List<String> departmentIds) {
	    log.info("삭제 요청된 부서 ID 목록: {}", departmentIds);

	    Map<String, Object> response = new HashMap<>();

	    try {
	        if (departmentIds == null || departmentIds.isEmpty()) {
	            response.put("success", false);
	            response.put("message", "삭제할 부서를 선택하세요.");
	            return response;
	        }

	        service.deleteDepartments(departmentIds); // 삭제 실행

	        response.put("success", true);
	    } catch (Exception e) {
	        log.error("부서 삭제 중 오류 발생: {}", e.getMessage(), e);
	        response.put("success", false);
	        response.put("message", "삭제 중 오류가 발생했습니다: " + e.getMessage());
	    }

	    return response;
	}

	// 한 개의 부서 삭제
	@PostMapping("/deleteone")
	@ResponseBody
	public Map<String, Object> deleteDepartment(@RequestBody String departmentCode) {

		Map<String, Object> response = new HashMap<>();

		try {
			if (departmentCode == null || departmentCode.isEmpty()) {
				response.put("success", false);
				response.put("message", "삭제할 부서를 선택하세요.");
				return response;
			}

			service.deleteDepartment(departmentCode); // 삭제 실행

			response.put("success", true);
		} catch (Exception e) {
			log.error("부서 삭제 중 오류 발생: {}", e.getMessage(), e);
			response.put("success", false);
			response.put("message", "삭제 중 오류가 발생했습니다: " + e.getMessage());
		}

		return response;
	}

}