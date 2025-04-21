package kr.or.ddit.team.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.team.service.TeamService;
import kr.or.ddit.team.vo.TeamVO;
import kr.or.ddit.workstatus.vo.WorkstautsVO;

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
*================================ 현재 미사용 컨트롤러 =================================
* </pre>
*/

@Controller
@RequestMapping("/team")
public class TeamItemController {

	@Autowired
	TeamService service;

	@GetMapping("List")
	public String TeamList(Model model) {
		model.addAttribute("teamManageList",service.teamManageList());
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("" + service.teamManageList());
		return "tiles:team/teamItem";
	}

	@PostMapping("Insert")
	@ResponseBody
	public void TeamInsert(@RequestBody TeamVO teamManage) {
		service.insertTeamItem(teamManage);
	}

	@PostMapping("Update")
	@ResponseBody
	public void TeamUpdate(@RequestBody TeamVO teamManage) {
		service.updateTeamStatusItem(teamManage);
	}

	@PostMapping("Delete")
	@ResponseBody
	public ResponseEntity<String> TeamDelete(@RequestBody Map<String, String>  teamCodeMap) {
		String teamCode = teamCodeMap.get("teamId");
//	    service.deleteTeamItem(teamCode);
	    return ResponseEntity.ok("삭제 완료");
	}
}
