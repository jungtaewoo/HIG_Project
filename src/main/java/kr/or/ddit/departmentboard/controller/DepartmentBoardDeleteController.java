package kr.or.ddit.departmentboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.departmentboard.service.DepartmentBoardService;

@RestController
@Controller
@RequestMapping("/departmentboard")
public class DepartmentBoardDeleteController {

  @Inject
  private DepartmentBoardService service;

//비동기 삭제 처리 

  @PostMapping("/delete")
//  @DeleteMapping("/departmentboard/delete")
  public ResponseEntity<String> delBoard(
      @RequestBody List<Long> deptnoticeId

  ) {

    System.out.println("===========================================ingu");

    if (deptnoticeId == null || deptnoticeId.isEmpty()) {
      return ResponseEntity.badRequest().body("{\"message\": \"삭제할 항목이 없습니다.\"}");
    }

    // 삭제 처리
    service.delBoard(deptnoticeId);

    // 성공적으로 삭제된 경우
    return ResponseEntity.ok("{\"message\": \"삭제 완료\"}"); // JSON 응답 반환

  }

}
