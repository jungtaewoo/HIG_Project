package kr.or.ddit.document.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.document.service.DocumentService;
import kr.or.ddit.document.vo.DocumentVO;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;

/**
 * 
 * 
 * @author KHT
 * @since 2025. 3. 12.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/document/box")
public class DocumentReadController {
	
	@Inject
	private DocumentService service;
	
	//테스트용 전체문서함
	@GetMapping("all")
	public String DocAllList(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("condition") SimpleCondition condition,
			Model model
		) {
		PaginationInfo<DocumentVO> paging = new PaginationInfo<>(3, 3);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(condition);
		
		List<DocumentVO> documentList = service.readDocumentList(paging);
		model.addAttribute("documentList", documentList);
		PaginationRenderer renderer = new DefaultPaginationRenderer();
		String pagingHTML = renderer.renderPagination(paging);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "tiles:document/documentList";	
	}
	
	//기결함
	@GetMapping("approvedDoc")
	public String approvedList() {
		return "tiles:document/approvedDoc";
	}
	
	//미결함
	@GetMapping("pendingDoc")
	public String pendingDocList() {
		return "tiles:document/pendingDoc";
	}
	
	//예결함
	@GetMapping("preApprovalDoc")
	public String preApprovalList() {
		return "tiles:document/preApprovalDoc";
	}
		
	//후결함
	@GetMapping("postApprovalDoc")
	public String postApprovalList() {
		return "tiles:document/postApprovalDoc";
	}
	
	//반려함
	@GetMapping("rejectedDoc")
	public String rejectedDocList() {
		return "tiles:document/rejectedDoc";
	}
	
	//보류함
	@GetMapping("holdedDoc")
	public String holdedDocList() {
		return "tiles:document/holdedDoc";
	}
	
	//수신참조함
	@GetMapping("referencedDoc")
	public String referencedDocList() {
		return "tiles:document/referencedDoc";
	}
	
	//이관문서함
	@GetMapping("transferredDoc")
	public String transferredDocList() {
		return "tiles:document/transferredDoc";
	}
	
	//부서문서함-기안완료함
	@GetMapping("deptCompletedDoc")
	public String deptCompletedDocList() {
		return "tiles:document/deptCompletedDoc";
	}
	
	//부서문서함-부서참조함
	@GetMapping("deptReferencedDoc")
	public String deptReferencedDocList() {
		return "tiles:document/deptReferencedDoc";
	}
	
	
	
}
