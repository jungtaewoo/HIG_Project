package kr.or.ddit.approval.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.approval.service.ApprovalProcessService;
import kr.or.ddit.approval.vo.DraftApproverVO;
import kr.or.ddit.approval.vo.DraftManageMentVO;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 18.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 18.     	CHOI	         결재 진행 
 *
 * </pre>
 */
@Controller
public class ApprovalWriteController {

	@Inject
	private ApprovalProcessService service;
	/**
	 * 결재 문서 작성 페이지
	 * @return
	 */
	@GetMapping("/approval/write")
	public String writeApprovalForm(Model model) {
		
		return "tiles:approval/approvalwrite";
	}
	

//    /**
//     * 결재 진행 상태 조회 (AJAX 요청)
//     */
//    @GetMapping("/approval/status/{draftId}")
//    @ResponseBody
//    public String getDraftStatus(@PathVariable Long draftId) {
//        return service.getDraftStatus(draftId);
//    }

//    /**
//     * 결재자 추가 (AJAX 요청)
//     */
//    @PostMapping("/approval/addApprover")
//    @ResponseBody
//    public List<DraftApproverVO> addApprover(@RequestBody DraftApproverVO approver) {
//        service.addApprover(approver);
//        return service.getApprovers(approver.getDraftId());
//    }

//    /**
//     * 결재 문서 저장 (기안 상신)
//     */
//    @PostMapping("/approval/submit")
//    @ResponseBody
//    public String submitApproval(@RequestBody DraftManageMentVO draft) {
//        service.submitDraft(draft);
//        return "결재 문서가 상신되었습니다.";
//    }

   
}
