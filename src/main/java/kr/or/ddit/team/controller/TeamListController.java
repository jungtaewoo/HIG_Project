package kr.or.ddit.team.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
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
public class TeamListController {

	@Autowired
	TeamService service;

	@GetMapping("list")
	public String TeamList(Model model) {

		ArrayList<TeamVO> list = (ArrayList<TeamVO>) service.teamManageList();

		model.addAttribute("teamManageList",list);
		System.out.println("" + service.teamManageList());
		return "tiles:team/teamList";
	}

}
























