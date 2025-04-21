package kr.or.ddit.team.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.department.service.DepartmentService;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.team.service.TeamService;
import kr.or.ddit.team.vo.TeamMemberVO;
import kr.or.ddit.team.vo.TeamVO;

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

@Controller
@RequestMapping("/team")
public class TeamUpdateController {

	@Autowired
    private TeamService service;

	@Autowired
	private DepartmentService deptservice;

	// 팀 수정 화면 표시 (팀 ID를 통해 기존 정보를 조회)
    @GetMapping("update")
    public String updateTeamForm(@RequestParam("teamId") Long teamId, Model model) {
    	// 팀 정보 조회
        TeamVO team = service.selectTeam(teamId);
        // 모든 부서 정보 조회
        List<DepartmentVO> departmentList = deptservice.getDepartmentList();


        model.addAttribute("team", team);
        model.addAttribute("departmentList", departmentList);


        return "tiles:team/teamUpdate";
    }

    // 팀 수정 처리 (POST)
    @PostMapping("update/commit")
    public String updateTeam(@ModelAttribute TeamVO team, RedirectAttributes redirectAttributes) {
        try {
            service.updateTeamWithMembers(team);  // void 타입이라 반환값 없음
            redirectAttributes.addFlashAttribute("updateSuccess", "팀 수정이 완료되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("updateError", "팀 수정 중 오류가 발생했습니다. " + e.getMessage());
        }
        return "redirect:/employee/organization";
    }


}








