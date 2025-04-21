package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.boardcategory.service.BoardCategoryService;
import kr.or.ddit.boardcategory.vo.BoardCategoryVO;
import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
/**
*
* @author KHS
* @since 2025. 3. 11.
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      			수정자           수정내용
*  -----------   	-------------    ---------------------------
*  2025. 3. 11.     	KHS	          최초 생성
*  2025. 3. 21.     	KHS	          파일업로드 적용
* </pre>
*/
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardUpdateController {

	@Inject
	private BoardService service;
	@Inject
	private BoardCategoryService cateservice;
	@Inject
	private FileInfoService fileService;

	public static final String MODELNAME = "board";


	@GetMapping("update")
	public String formUI(@RequestParam("noticeId") Long noticeId, Model model
			) {
		// 모든 공지 카테고리 조회
    	List<BoardCategoryVO> categoryList = cateservice.getBoardCategoryList();

    	model.addAttribute("categoryList", categoryList);

	    BoardVO board = service.readBoard(noticeId);
	    model.addAttribute("board", board);
	    System.out.println(board);

	    return "tiles:board/boardUpdate";
	}


	@PostMapping("update/success")
	public String formProcess(HttpSession session,
			@ModelAttribute BoardVO board, MultipartFile[] files, Model model,
			@RequestParam(value = "importance", defaultValue = "N") String importance,
			@RequestParam(value = "categoryId") String categoryId
			) {

	    System.out.println("최종 CategoryId 값: " + board.getCategoryId());
		// importance 값이 없으면 기본값 N 설정
        board.setImportance(importance);
        // 디버깅 출력
        System.out.println("최종 importance 값: " + board.getImportance());

//        AccountVO sessionAccount = (AccountVO) session.getAttribute("sessionAccount");
		AccountVO user = SecurityUtil.getrealUser();

		board.setEmpId(user.getEmpId());

        System.out.println("최종 oneFile 값: " + board.getOneFile());
        System.out.println(board);
        System.out.println("=============================================hs-1");
        System.out.println(user);

        try {
            // 세션에서 empId 설정
//            if (sessionAccount != null) {
//                board.setEmpId(sessionAccount.getAccountId());
//                System.out.println("empId 확인: " + board.getEmpId());
//            } else {
//                model.addAttribute("error", "로그인 정보가 없습니다.");
//                return "/account/accountLoginForm";
//            }

            // 파일 업로드 및 그룹화된 파일 ID 처리
            Long fileId = null;  // 그룹화된 파일 ID
            List<Long> fileIds = new ArrayList<>();

            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        if (fileId == null) {
                            fileId = fileService.createFileGroup(); // 새로운 FILE_ID 생성
                            System.out.println("새로운 파일 그룹 생성, fileId: " + fileId);
                        }
                        Long detailId = fileService.saveFileWithGroup(file, fileId);
                        System.out.println("저장된 detailId: " + detailId);
                        fileIds.add(detailId);
                    }
                }
            }

            if (fileIds.isEmpty()) {
                System.out.println("파일 ID: " + fileId);  // 여기에 로그 추가
                board.setOneFile(fileId);
                fileIds = new ArrayList<>();
            }

            // BoardVO에 파일 정보 설정
            board.setNoticeFiles(fileIds);
            // 대표 파일(oneFile) 설정
            if (!fileIds.isEmpty()) {
                board.setOneFile(fileId);
            }

            System.out.println("fileIds 리스트: " + fileIds);
            System.out.println("최종 oneFile 값: " + board.getOneFile());
            System.out.println(board);

            int cnt = service.modifyBoard(board);
            System.out.println("=============================================hs");
            System.out.println(cnt);
            System.out.println(board);



        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/board/list";
        }
        return "redirect:/board/list";
	}




    @PostMapping("/updatePin")
    public String updateNoticeImportance(@RequestParam("noticeId") int noticeId,
                                         @RequestParam("importance") String importance) {
        // 중요 공지로 설정 (IMPORTANCE = 'Y') 또는 해제 (IMPORTANCE = 'N')
        service.updateBoardimportance(noticeId, importance);
        return "redirect:/board/list";
    }

}
