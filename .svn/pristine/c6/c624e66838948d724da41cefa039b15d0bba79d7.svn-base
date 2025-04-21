package kr.or.ddit.approval.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.approval.vo.DraftApproverVO;
import kr.or.ddit.approval.vo.DraftBoxVO;
import kr.or.ddit.approval.vo.DraftDetailVO;
import kr.or.ddit.approval.vo.DraftManageMentVO;
import kr.or.ddit.file.vo.FileDetailVO;


public interface ApprovalProcessService {
	
	
	
	/**
	 * 기안자가 본인의 기안자 정보, 문서 카테고리, 최종 결재자, 첨부 파일 여부 볼 수 있음
	 * @param empId 기안자 
	 * @return
	 */
	public List<DraftManageMentVO> writeDraftMangeMent(String empId);
	
	
	/**
     * 기안자가 본인의 상신 상세 정보를 조회한다.
     * @param params - 조회 조건(Map) (예: draftId, empId)
     * @return 기안 상세 정보 (결재자 정보는 리스트로 매핑됨)
     */
    public DraftManageMentVO getMyDraftDetail(Map<String, Object> params);
	
	
    /**
     * 기안자가 본인 문서를 회수 -> 대기, 결재 진행중, 보류 
     * @param params
     */
    public void recallDraft(Map<String, Object> params);
    
    
	
	/**
	 * 기안된 문서 전체 리스트 조회 -> 기안자 정보, 문서 카테고리, 최종 결재자, 첨부 파일 여부
	 * @return
	 */
	public List<DraftManageMentVO> writeDraftMangeMentList();
	
	
    /**
     * 템플릿 제목 조회 메서드 추가
     * @param templateId
     * @return
     */
    public String getTemplateTitle(Long templateId);
    
    
    /**
     * 지정된 사원(empId)의 연차 신청 내역 중,
     * 새롭게 신청할 휴가 기간(leaveStartDate ~ leaveEndDate)과 겹치는 건수를 반환합니다.
     *
     * @param params Map with keys "empId", "leaveStartDate", "leaveEndDate"
     * @return 겹치는 연차 신청 건수
     */
    public boolean hasLeaveConflict(String empId, String leaveStartDate, String leaveEndDate);
    
    /**
     * 연차 관련 정보 
     * @param historyVO
     */
    public void insertAnnualHistory(AnnualHistoryVO historyVO);
    
    
    /**
     * 연차 종류 필드에 들어갈 select 태그 
     * @return
     */
    public List<AnnualManageVO> selectAvailableAnnualTypes();
    
    /**
     * 결재자 이름 검색 
     * @param approverName
     * @return
     */
    public DraftApproverVO selectApproverName(String approverName);

    
    

    
	/**
	 * 기안 문서 기안 
	 * @param draftMentVO 기안 문서 정보
	 * @param approverVO   결재자 리스트
	 * @param draftBoxVO   문서함 정보 
	 */
	public void submitDraft(DraftManageMentVO draftMentVO
			, List<DraftApproverVO> approverVO
			, AnnualHistoryVO historyVO
			, List<DraftBoxVO> draftBoxVO
			);
	
	
	
	@Transactional
    public void updateAnnualHistoryStatus(AnnualHistoryVO historyVO);
	
	
	/**
	 * 결재자가 결재 승인/반려
	 * @param approverVO 결재자 정보 
	 */
	public void approveDraft(DraftApproverVO approverVO);
	
	
	/**
	 * 파일처리 
	 * @param paramMap
	 */
	public void insertDraftMentFile(Map<String, Object> paramMap);




	/**
	 * 파일 상세보기 -> draftId 기안 코드를 통해 
	 * @param draftId
	 * @return
	 */
	public List<FileDetailVO> getFilesByDraftId(Long draftId);
	
	
	
	/**
	 * 기안자가 작성한 문서를 불러오기 
	 * @param draftId
	 * @return
	 */
	public DraftDetailVO getDraftDocDetail(Long draftId);
}
