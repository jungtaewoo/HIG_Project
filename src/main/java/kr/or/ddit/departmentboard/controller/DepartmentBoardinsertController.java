package kr.or.ddit.departmentboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.departmentboard.service.DepartmentBoardService;
import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;
import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.security.RealUserWrapper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/departmentboard")
public class DepartmentBoardinsertController {
  
  @Inject
  private DepartmentBoardService service;
  
  @Autowired
  private FileInfoService fileService;

	@GetMapping("insert")
	public String DepartmentInsert(Model model) {
	  model.addAttribute("insert", new DepartmentBoardVO());
		return "tiles:departmentboard/departmentboardInsert";
	}
	@Transactional
	@PostMapping("insert/commit")
  public String DepartmentInsert(HttpSession session,
      Authentication authentication,
      @ModelAttribute DepartmentBoardVO insert, MultipartFile[] files, Model model
  ) {
	  
	  try {
	   RealUserWrapper realUserWrapper = (RealUserWrapper) authentication.getPrincipal();
    AccountVO sessionAccount = realUserWrapper.getRealUser();
      
    if (sessionAccount != null) {
          // 로그인 된 계정의 부서ID를 추출
      insert.setDepartmentId(sessionAccount.getDepartmentId());
      insert.setAuthor(sessionAccount.getAccountId());
          
      } else {
          // 세션에 sessionAccount가 없는 경우 처리
          model.addAttribute("error", "로그인 정보가 없습니다.");
          return "/account/accountLoginForm";
      }
    
       // 2. 파일 업로드 및 그룹화된 파일 ID 처리
          Long fileId = null;  // 그룹화된 파일 ID
          List<Long> fileIds = new ArrayList<>();
      
          if (files != null && files.length > 0) {
              for (MultipartFile file : files) { 
                  if (!file.isEmpty()) {
                      if (fileId == null) {
                          fileId = fileService.createFileGroup(); // 새로운 FILE_ID 생성
                      }
                      Long detailId = fileService.saveFileWithGroup(file, fileId);
                      fileIds.add(detailId);
                  }
              }
          }
      
          // 3. 파일이 없을 경우 예외 처리
          if (fileIds.isEmpty()) {
              fileIds = new ArrayList<>();
          }
      
          //  4. draftTemplate에 파일 정보 설정
          insert.setDeptFiles(fileIds);
      
          // 5. 대표 파일 설정 (fileIds가 비어있지 않을 경우)
          if (!fileIds.isEmpty()) {
            insert.setOneFile(fileId != null ? fileId.toString() : null);
          }
    
 
      // 서비스 계층을 통해 데이터베이스에 저장
      service.boardInsert(insert, fileIds);
      
	  } catch (Exception e){
	    e.printStackTrace();
	    return "redirect:/departmentboard/list";
	  }
    return "redirect:/departmentboard/list";
  }
	
	
	
}