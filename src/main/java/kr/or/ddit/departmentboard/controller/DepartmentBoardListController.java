package kr.or.ddit.departmentboard.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.departmentboard.service.DepartmentBoardService;
import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;
import kr.or.ddit.security.RealUserWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author LIG
 * @since 2025. 3. 12.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자           수정내용
 *  -----------      -------------    ---------------------------
 *  2025. 3. 12.        LIG             최초 생성
 *  날짜              변경자           수정 사유
 *
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/departmentboard")
public class DepartmentBoardListController {

  @Autowired
  private DepartmentBoardService service;
  
   @GetMapping("list")
   public String DepartmentList(Model model, @ModelAttribute DepartmentBoardVO pvo ,DepartmentBoardVO tvo){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     log.info("sessionAccount++++++++++{}",authentication);
     
       if (authentication != null && authentication.getPrincipal() instanceof RealUserWrapper) {
           RealUserWrapper realUserWrapper = (RealUserWrapper) authentication.getPrincipal();
           log.info("sessionAccount++++++++++{}",realUserWrapper.getRealUser().getDepartment().getDepartmentName());
           
           // 2. realUser가 null인지 확인하고, null이면 로그인 페이지로 리다이렉트
           if (realUserWrapper.getRealUser() == null) {
               model.addAttribute("error", "로그인 정보가 없습니다.");
               return "redirect:/account/accountLoginForm";
           }
    
            // 로그인 된 계정의 부서ID를 추출
//          pvo.setDepartmentId(Long.parseLong(sessionAccount.getName()));
            pvo.setDepartmentId(realUserWrapper.getRealUser().getDepartmentId());
            tvo.setDepartmentId(realUserWrapper.getRealUser().getDepartmentId());
            
            System.out.println("=============================================inkyu");
            System.out.println(pvo);
            System.out.println(tvo);
            
            
            ArrayList<DepartmentBoardVO> list = (ArrayList<DepartmentBoardVO>) service.boardList(pvo);
            ArrayList<DepartmentBoardVO> fix = (ArrayList<DepartmentBoardVO>) service.topFixedList(tvo);
           
//          pvo.setAuthor(sessionAccount.getAccountId());
            model.addAttribute("departmentboardList", list);
            model.addAttribute("topFixedList", fix);
            
            return "tiles:departmentboard/departmentboardList";
       }
       
       // 세션에 sessionAccount가 없는 경우 처리
       model.addAttribute("error", "로그인 정보가 없습니다.");
       return "/account/accountLoginForm";
   }
}
