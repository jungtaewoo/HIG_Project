package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
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
* </pre>
*/
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardDeleteController {

	private final BoardService service;


	@GetMapping("delete")
	public String BoardDelete(@RequestParam("noticeId") Long noticeId, Model model,
							  RedirectAttributes redirectAttributes
			) {
	    boolean isDeleted = service.removeBoard(noticeId);

	    if (isDeleted) {
	    	redirectAttributes.addFlashAttribute("noticeDelete", "공지사항이 삭제되었습니다!");
	        return "redirect:/board/list"; // 삭제 후 목록으로 이동
	    } else {
	        model.addAttribute("error", "삭제에 실패했습니다.");
	        return "tiles:board/boardDetail"; // 실패 시 게시글 상세 페이지 유지
	    }
	}

}