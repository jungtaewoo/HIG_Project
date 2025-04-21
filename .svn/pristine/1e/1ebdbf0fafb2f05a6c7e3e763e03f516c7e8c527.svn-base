package kr.or.ddit.departmentboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.departmentboard.service.DepartmentBoardService;
import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;

/**
 * 
 * @author LIG
 * @since 2025. 3. 12.
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	LIG	          최초 생성
 *
 *      </pre>
 */
@Controller
@RequestMapping("/departmentboard")
public class DepartmentBoardDetailController {

  @Autowired
  private DepartmentBoardService service;

  @GetMapping("detail")
  public String detail(@RequestParam("deptnoticeId") Long deptnoticeId, Model model,
      HttpSession session) {

    // 조회수 증가
    service.incrementViews(deptnoticeId);

    DepartmentBoardVO vo = service.boardDetail(deptnoticeId);
    model.addAttribute("vo", vo);
    return "tiles:departmentboard/departmentboardDetail";
  }
}