package kr.or.ddit.mybatis.mappers.approval;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.approval.vo.DraftApproverVO;
import kr.or.ddit.approval.vo.DraftBoxVO;
import kr.or.ddit.approval.vo.DraftDetailVO;
import kr.or.ddit.approval.vo.DraftManageMentVO;
import kr.or.ddit.file.vo.FileDetailVO;

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
 *  2025. 3. 18.     	CHOI	          전자결재 진행 Mapper
 *
 * </pre>
 */
@Mapper
public interface ApprovalProcessMapper {

	/**
	 * 기안자가 본인의 기안자 정보, 문서 카테고리, 최종 결재자, 첨부 파일 여부 볼 수 있음
	 * @param empId 기안자 
	 * @return
	 */
	public List<DraftManageMentVO> writeDraftMangeMent(@Param("empId") String empId);
	
	
	
	/**
     * 기안자가 본인의 상신 상세 정보를 조회한다.
     * @param params - 조회 조건(Map) (예: draftId, empId)
     * @return 기안 상세 정보 (결재자 정보는 리스트로 매핑됨)
     */
    public DraftManageMentVO getMyDraftDetail(Map<String, Object> params);
	
	
    
    /**
     * 기안자가 해당 문서가 대기, 결재 진행중, 보류 일 때 해당 문서를 회수 가능함 
     * @param params
     */
    public void recallDraft(Map<String, Object> params);
    
    
	
	/**
	 * 기안자가 결재 문서  전체 목록 조회 -> 기안된 문서 리스트 조회, 기안자 정보, 문서 카테고리 확인, 최종 결재자 확인, 첨부파일 확인
	 * @return
	 */
	public List<DraftManageMentVO> writeDraftMangeMentList();
	
	
	/**
     * 지정된 사원(empId)의 연차 신청 내역 중,
     * 새롭게 신청할 휴가 기간(leaveStartDate ~ leaveEndDate)과 겹치는 건수를 반환합니다.
     *
     * @param params Map with keys "empId", "leaveStartDate", "leaveEndDate"
     * @return 겹치는 연차 신청 건수
     */
    public int checkLeaveConflict(Map<String, Object> params);
	
	
	
	/**
	 * 기안 문서 등록
	 * @param draft 
	 */
	public void insertDraftManagement(DraftManageMentVO draft);
	
	/**
	 * 템플릿 제목 가져오기 
	 * @param templateId
	 * @return
	 */
	public String selectTemplateTitle(Long templateId);
	
	
	/**
	 * 결재자 이름 가져오기 
	 * @param approverName
	 * @return
	 */
	public DraftApproverVO selectApproverName(String approverName);
	
	/**
	 * 연차 관련 정보 넣기 
	 * @param historyVO
	 */
	public void insertAnnualHistory(AnnualHistoryVO historyVO);
	
	/**
     * 연차 종류 필드에 들어갈 select 태그 
     * @return
     */
    public List<AnnualManageVO> selectAvailableAnnualTypes();
	
	/**
	 * 결재자 정보 등록
	 * @param approver
	 */
	public void insertDraftApprover(DraftApproverVO approver);

	
	/**
	 * 결재자 이름 id 매핑 
	 * @param approverName 결재자 이름 
	 * @return
	 */
	public String selectEmpIdByName(String approverName);
	
	/**
	 * 문서함 등록
	 * @param draftBox
	 */
	public void insertDraftBox(DraftBoxVO draftBox);

	/**
	 * 결재 승인/반려/보류 처리
	 * @param approver
	 */
	public void updateDraftApprover(DraftApproverVO approver);

	/**
	 * 남은 결재자 수 확인 (마지막 결재자인지 체크)
	 * @param draftId
	 * @return
	 */
	public int countPendingApprovals(@Param("draftId") Long draftId);

	/**
	 * 문서 상태 업데이트 (최종 승인/반려)
	 * @param draftId
	 * @param draftStatus
	 */
	public void updateDraftManagementStatus(@Param("draftId") Long draftId, @Param("draftStatus") String draftStatus);

	/**
	 * 문서함 상태 업데이트
	 * @param draftId
	 * @param docStatus
	 */
	public void updateDraftBoxStatus(@Param("draftId") Long draftId, @Param("docStatus") String docStatus);
	
	
	/**
	 * 연차 기록 필드 업데이트 
	 * @param historyVO
	 */
	public void updateAnnualHistoryStatus(AnnualHistoryVO historyVO);
	
	/**
	 * 
	 * 상세보기 -> 파일 처리 기능 
	 * @param paramMap
	 */
	public void insertDraftMentFile(Map<String, Object> paramMap);
	
	/**
	 * 파일 상세보기 -> draftId 기안 코드를 통해 
	 * @param draftId
	 * @return
	 */
	public List<FileDetailVO> getFilesByDraftId(@Param("draftId") Long draftId);

	
	/**
	 * 기안자가 작성한 문서를 불러오기 
	 * @param draftId
	 * @return
	 */
	public DraftDetailVO getDraftDocDetail(@Param("draftId") Long draftId);
	
}
