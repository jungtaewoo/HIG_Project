package kr.or.ddit.team.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.team.service.TeamService;
import kr.or.ddit.team.vo.TeamVO;
import lombok.extern.slf4j.Slf4j;

/**
*
* @author KHS
* @since 2025. 3. 13.
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      			수정자           수정내용
*  -----------   	-------------    ---------------------------
*  2025. 3. 13.     	KHS	          최초 생성
*
* </pre>
*/

@Slf4j
@Controller
@RequestMapping("/team")
public class TeamInsertController {

	@Autowired
	TeamService service;

	@Autowired
	private DepartmentService deptservice;

	@GetMapping("register")
	public String TeamInsert(Model model) {

        // 모든 부서 정보 조회
        List<DepartmentVO> departmentList = deptservice.getDepartmentList();

        model.addAttribute("departmentList", departmentList);

		return "tiles:team/teamFormUI";
	}

    // 팀 등록 처리 (POST)
    @PostMapping("register/commit")
    @ResponseBody
    public Map<String, Object> registerTeam(
    		@ModelAttribute TeamVO team, HttpSession session) {
        log.info("팀 등록 요청: {}", team);

	    Map<String, Object> result = new HashMap<>();
	    try {
	    	service.createTeamWithMembers(team);
	        result.put("success", true);
	        result.put("redirectUrl", "/employee/organization");  // 조직도 페이지로 리다이렉트
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("success", false);
	        result.put("message", "등록 중 오류가 발생했습니다.");
	    }
	    return result;  // 수정해야함 등록후 조직도로 리다이렉트 되게 ---------------------------------------------------------------------------------------------
    }


    //아래는 동기방식 팀추가 컨트롤러
//    // 팀 등록 처리 (POST)
//    @PostMapping("register/commit")
//    public String registerTeam(TeamVO team) {
//    	log.info("팀 등록 요청: {}", team);
//
//    	// 팀과 팀 멤버(팀장 여부 포함)를 등록
//    	service.createTeamWithMembers(team);
//
//    	return "redirect:/team/list"; // 팀 목록 페이지로 이동
//    }
}