package kr.or.ddit.deptcomment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.deptcomment.service.DeptCommentService;
import kr.or.ddit.deptcomment.vo.DeptCommentVO;
import kr.or.ddit.security.RealUserWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DeptCommentInsertController {

  @Autowired
  private DeptCommentService service;

  @PostMapping("insertComment")
  public DeptCommentVO CommentInsert(

      @RequestBody DeptCommentVO commentInsert, Authentication authentication

  ) {

    /*
     * try { RealUserWrapper realUserWrapper = (RealUserWrapper)
     * authentication.getPrincipal(); AccountVO sessionAccount =
     * realUserWrapper.getRealUser();
     * 
     * if (sessionAccount != null) { // 로그인 된 계정의 부서ID를 추출
     * commentInsert.setDepartmentId(sessionAccount.getDepartmentId());
     * commentInsert.setAuthor(sessionAccount.getAccountId());
     * 
     * } else { // 세션에 sessionAccount가 없는 경우 처리 model.addAttribute("error",
     * "로그인 정보가 없습니다."); return "/account/accountLoginForm"; }
     */

    System.out.println("====================찍어보는중==============================");
    System.out.println(commentInsert);
    commentInsert.setAccountId(authentication.getName());

    log.info("댓글 입력: {}", commentInsert);

    DeptCommentVO result = service.commentInsert(commentInsert);

    System.out.println("====================찍어보는중==============================");
    System.out.println(commentInsert);

    return service.commentDetail(result); // 저장된 댓글을 반환
  }

}
