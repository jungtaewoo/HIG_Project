package kr.or.ddit.approval.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.approval.service.ApprovalService;
import kr.or.ddit.approval.service.ApproverService;
import kr.or.ddit.approval.vo.DraftTemplateVO;
import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.team.vo.TeamVO;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 15.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 13.     	CHOI	          최초 생성
 *  2025. 3. 15.        CHOI 			  등록 처리
 *
 * </pre>
 */
@Controller
@RequestMapping("/approval")
public class ApprovalFormController {

    @Autowired
    private ApprovalService service;

    
    @Autowired
    private ApproverService aprService;
    
    @Autowired
    private FileInfoService fileService;

    
    /**
     * 팀 리스트 전역적으로 사용
     * @return
     */
    @ModelAttribute("teamList")
    public List<TeamVO> getTeamList(){
    	return service.getTeamList();
    }
    
    @ModelAttribute("categoryList")
    public List<String> getCategoryList(){
		return service.getAllTemplateCategories();
    	
    }
    /**
     *  양식 추가 폼 리스트 조회(관리자)
     */
    @GetMapping("list")
    public String ApprovalDocForm(Model model) {
        model.addAttribute("DraftTemplateList", service.draftTemplateList());
        return "tiles:approval/approvalForm";
    }
    
    /**
     * 결재 양식 리스트(일반 사원)
     * @param model
     * @return
     */
    @GetMapping("templateList")
    public String getApprovalDocForm(Model model) {
    	List<DraftTemplateVO> draftTemplates = service.draftTemplateListEmp();
    	System.out.println("draftTemplates:" + draftTemplates);
    	model.addAttribute("draftEmpTemplate",draftTemplates);
		return "tiles:approval/approvalEmpFomList"; 
    }
    
    /**
     * 양식 폼 동적 불러오기
     * @param templateId 각 템플릿 정보
     * @return ? -> 모든 vo를 바로 받을 수 있음 
     */
    @GetMapping("template/{templateId}")
    @ResponseBody
    public ResponseEntity<?> getTemplate(@PathVariable Long templateId) {
        // 1. 파일 정보 조회
        FileDetailVO filedetail = service.getTemplateFileByTemplateId(templateId);

        // 2. 파일 정보가 없을 경우 404 에러 반환
        if (filedetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("템플릿 파일을 찾을 수 없습니다.");
        }

        // 3. 정상적인 경우 파일 정보 반환 (JSON 응답)
        return ResponseEntity.ok(filedetail);
    }

    /**
     *  양식 상세 조회
     */
    @GetMapping("{templateId}")
    public String ApprovalDocDetail(
        @PathVariable("templateId") Long templateId, 
        Model model, 
        HttpSession session
    ) {
        DraftTemplateVO draftVo = service.draftTemplateDetail(templateId);
        model.addAttribute("template", draftVo);

        return "tiles:approval/approvalFormDetail";
    }

    
    /**
     *  양식 등록 폼 -> select로 팀 목록 뽑기 위한 작업 
     */
    @GetMapping("processForm")
    public String ApprovalDocProcess(Model model) {
        return "tiles:approval/approvalProcessForm";
    }

    /**
     *  양식 등록 처리 (파일 업로드 포함)
     */
    @Transactional
    @PostMapping("ApprovalFormProcess")
    public String ApprovalFromProcess(
        DraftTemplateVO draftTemplate, 
        MultipartFile[] files, 
        HttpSession session
    ) {
        try {
            // 1. 현재 로그인한 사용자 정보 가져오기
            AccountVO account = (AccountVO) session.getAttribute("sessionAccount");
            draftTemplate.setEmpId(account.getEmpId());
            draftTemplate.setTemplateCreate(new Date()); // 현재 시간 설정
            
            
            // 2. 선택한 팀 정보 가져오기
            List<TeamVO> teamList = service.getTeamList();
            for (TeamVO team : teamList) {
                if (team.getTeamId().equals(draftTemplate.getTeamId())) {
                    draftTemplate.setTeamName(team.getTeamName()); //팀 이름 바인딩
                    break;
                }
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
            draftTemplate.setTemplateFile(fileIds);

            // 5. 대표 파일 설정 (fileIds가 비어있지 않을 경우)
            if (!fileIds.isEmpty()) {
                draftTemplate.setTemplateFiles(fileId != null ? fileId.toString() : null);
            }

            // 6. 결재 양식 저장
            service.insertDraftTemplate(draftTemplate, fileIds);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/approval/processForm?error=true";
        }

        return "redirect:/approval/list";
    }
}
