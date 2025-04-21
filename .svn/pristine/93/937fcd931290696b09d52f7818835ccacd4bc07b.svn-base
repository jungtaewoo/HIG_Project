package kr.or.ddit.departmentboard.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.departmentboard.service.DepartmentBoardService;
import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/departmentboard")
public class DepartmentBoardUpdateController {
  
  @Autowired
  private DepartmentBoardService service;


	@GetMapping("update")
	public String DepartmentUpdate( @RequestParam("deptnoticeId") Long deptnoticeId ,
	    Model model) {
	  
	  DepartmentBoardVO vo = service.boardDetail(deptnoticeId);
    model.addAttribute("vo", vo);
    System.out.println(vo);
		return "tiles:departmentboard/departmentboardUpdate";
		
	}
	
	@PostMapping("update/commit")
	public String DepartmentUpdateCommit(@ModelAttribute DepartmentBoardVO vo) {
	  
    System.out.println(vo);
	  
		service.modifyBoard(vo);
		
		return "redirect:/departmentboard/list";
	}
	
	
}
