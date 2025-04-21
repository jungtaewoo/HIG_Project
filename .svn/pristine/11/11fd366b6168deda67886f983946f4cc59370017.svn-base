package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
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
*
* </pre>
*/

@Controller
@RequestMapping("/board")
public class BoardReadController {

    @Inject
    private BoardService service;

    @GetMapping("list")
    public String BoardList(
            @RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
            @ModelAttribute("condition") SimpleCondition condition,
            Model model) {
        PaginationInfo<BoardVO> paging = new PaginationInfo<>(3, 3);
        paging.setCurrentPage(currentPage);
        paging.setSimpleCondition(condition);
        // 중요 공지 조회 (Y)
        List<BoardVO> importanceBoardList = service.selectImportanceBoardList(paging);
        // 중요 공지 조회 (N)
        List<BoardVO> boardList = service.readBoardList(paging);
        // 병합 리스트 생성
        List<BoardVO> mergedList = new ArrayList<>();
        mergedList.addAll(importanceBoardList);
        mergedList.addAll(boardList);

        // scope 저장
        model.addAttribute("mergedList", mergedList);
        model.addAttribute("importanceBoardList", importanceBoardList);
        model.addAttribute("boardList", boardList);
        PaginationRenderer renderer = new DefaultPaginationRenderer();
        String pagingHTML = renderer.renderPagination(paging);
        model.addAttribute("pagingHTML", pagingHTML);

        return "tiles:board/boardList";
    }

    @SuppressWarnings("unused")
	@GetMapping("detail")
    public String BoardDetail(
            @RequestParam("noticeId") Long noticeId,
            Model model) {
    	// 조회수 증가
        service.incrementViews(noticeId);

        // model 확보(logic 사용 - readMember)
        BoardVO board = service.readBoard(noticeId);

        // scope 에 저장 : member (attribute name)
        model.addAttribute("board", board);
        System.out.println("파일 리스트: " + board.getFileDetails());
        System.out.println("조회된 board 객체: " + board);

        if (board == null) {
            model.addAttribute("errorMessage", "게시글 정보를 불러올 수 없습니다.");
            return "errorPage";
        }



        return "tiles:board/boardDetail"; // Tiles 정의 이름 반환
    }
}