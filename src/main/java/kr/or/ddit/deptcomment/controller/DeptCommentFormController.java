package kr.or.ddit.deptcomment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.deptcomment.service.DeptCommentService;
import kr.or.ddit.deptcomment.vo.DeptCommentVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author LIG
 * @since 2025. 3. 27.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 27.     	LIG	          최초 생성
 *
 * </pre>
 */
@Slf4j
@RestController
@RequestMapping("/deptcomment")

public class DeptCommentFormController {
  
  @Autowired
  private DeptCommentService service;
  
  @GetMapping("form")
  public Map<String, Object> CommentList(@RequestParam("deptnoticeId") Long deptnoticeId) {
    
    System.out.println("=================================================ig");
    
    
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("DeptCommentVO", service.commentList(deptnoticeId)); 
    return resultMap;
    
    
  
  }
  
  
  
  
//  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//  public Map<String, Object> doPost( @RequestBody PersonVO person ){
//    dao.insertPerson(person);
//    Map<String, Object> resultMap = new HashMap<>();
//    resultMap.put("success", true);
//    resultMap.put("target", person);    
////    Post->Redirect->Get
//    return resultMap;
//  }
  
}














