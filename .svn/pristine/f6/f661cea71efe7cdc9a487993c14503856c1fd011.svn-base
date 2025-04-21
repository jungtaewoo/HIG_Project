package kr.or.ddit.approval.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.approval.common.AuthUtil;
import kr.or.ddit.approval.service.ApprovalProcessService;
import kr.or.ddit.approval.vo.DraftManageMentVO;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 20.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 20.     	CHOI	          비동기 처리 시 타일즈 적용을 위한 뷰 컨트롤러
 *
 * </pre>
 */
@Controller
public class ApprovalViewController {
	
	@Autowired
	private ApprovalProcessService service;
	/**
	 * 기안자 기준, 상신함 
	 * @return
	 */
	@GetMapping("/approval/mydrafts")
	public String approvalProcessPage() {
		return "tiles:approval/approvalProcess"; 
	}
	
	/**
	 * 본인 상신 문서 상세보기 
	 * @return
	 */
	@GetMapping("/approval/myDraftDetailView")
	public String approvalProcessDetailPage() {
		return "tiles:approval/approvalProcessDetailView"; 
	}
	
	
	/**
	 * 결재자 기준 Page
	 * @return
	 */
	@GetMapping("/approval/approverDrafts")
	public String approverProcessPage() {
		return "tiles:approval/approverProcess";
	}
	
	
	/**
	 * 결재 진행함 page
	 * @return
	 */
	@GetMapping("/approval/approver/processListView")
	public String approverProcessListPage() {
		return "tiles:approval/approverProcessList";
	}
	
	
	/**
	 * 결재 종결함 page
	 * @return
	 */
	@GetMapping("/approval/approver/complitedListView")
	public String approverComplitedListPage() {
		return "tiles:approval/approverComplitedList";
	}
	
	
	/**
	 * 결재 문서 상세 보기 
	 * @param draftId
	 * @param model
	 * @return
	 */
	@GetMapping("/approval/draft/detailView")
	public String approverProcessBoxDetail(@RequestParam("draftId") Long draftId, Model model) {
		// 필요한 경우 모델에 draftId를 추가할 수 있음
        model.addAttribute("draftId", draftId);
		return "tiles:approval/approverProcessDetail";
	}
	
	

	
	
	
	/**
	 * 기안 상신 Page
	 * @return
	 */
	@GetMapping("/approval/draft")
	public String approvalDraftPage() {
		return "tiles:approval/approvalDraft";
	}
}
