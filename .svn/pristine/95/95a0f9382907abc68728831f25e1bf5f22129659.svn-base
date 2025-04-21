package kr.or.ddit.team.controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.team.service.TeamService;
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
public class TeamDetailController {

	@Inject
	private TeamService service;

    @GetMapping("detail")
    public String TeamDetail(@RequestParam("teamId") Long teamId, Model model) {

        // 팀 객체 조회
        TeamVO team = service.selectTeam(teamId);
        System.out.println("--------------------------------------------------------------");
        System.out.println("--------------------" + team);
        System.out.println("--------------------------------------------------------------");

        // 팀원 리스트가 null인 경우 빈 리스트로 초기화 (검증 로직)
        if(team != null && team.getEmployees() == null) {
            team.setEmployees(new ArrayList<>());
        }

        // 필요하다면 다른 컬렉션들도 초기화할 수 있습니다.
        // 예: 팀 내 다른 리스트가 null인 경우

        // 모델에 저장
        model.addAttribute("team", team);

        return "tiles:team/teamDetail";
    }
}
