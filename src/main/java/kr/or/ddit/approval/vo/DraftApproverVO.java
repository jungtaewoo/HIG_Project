package kr.or.ddit.approval.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.annual.vo.AnnualManageVO;
import lombok.Data;

/**
 * 기안결재자 관리 VO
 * Mapper의 resultMap과 매핑되는 필드들
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 * 2025. 3. 21.     	CHOI            최초 생성
 * 2025. 3. 28.     	CHOI            MyBatis 매핑에 맞춰 VO 수정
 * </pre>
 */
@Data
public class DraftApproverVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 결재자 관련 기본 정보
    @NotNull
    private Long aprId;               // 결재선 순번 (PK) - APR_ID
    @NotNull
    private Long draftId;             // 기안관리ID - DRAFT_ID
    @NotBlank
    private String approverId;        // 결재자ID - APPROVER_ID
    @NotNull
    private Long aprSeq;              // 결재 순서 - APR_SEQ
    private String aprYn;             // 결재 여부 (Y/N) - APR_YN, 기본값 'N'
    private String aprDate;           // 결재일 (SYSDATE) - APR_DATE
    @NotBlank
    private String aprStatus;         // 결재상태 (승인/반려/보류) - APR_STATUS
    private String aprReason;         // 결재 상태 사유 - APR_REASON
    private String empSid;            // 결재자 부재시 대결자 ID - EMP_SID
    @NotBlank
    private String approverName;      // 결재자 이름 - APPROVER_NAME

    
    // 기안자의 직급 이름 
    private String drafterRankName;

    
    
	 // 추가: 결재자의 부서명과 직급명 (매퍼에서 APPROVER_DEPARTMENT_NAME, APPROVER_RANK_NAME 매핑)
	private String approverDepartmentName;
	private String approverRankName;
	
	
	//다음 결재자 이름, 다음결재자 직급 
	private String nextApproverName;
	private String nextApproverRank;
	private String userStatus; // 승인/ 반려 필터링을 위한 매핑 -> 종결함 상태를 나타냄
    
    // 문서(기안) 관련 정보
    private String draftTitle;        // 문서 제목 - DRAFT_TITLE
    private String draftDate;         // 기안일 - DRAFT_DATE
    private String draftStatus;       // 문서 상태 - DRAFT_STATUS
    private String draftEmpId;		  // 기안자 사번 
    private String draftEmpName;      // 기안자 이름 - DRAFT_EMP_NAME
    private String draftDepartment;   // 기안자 부서코드 - DRAFT_DEPARTMENT
    private String draftDepartmentName; // 기안자 부서명 - DRAFT_DEPARTMENT_NAME

    // 파일 정보 (첨부파일 관련)
    private Long detailId;            // 파일 상세 ID - DETAIL_ID
    private Long fileId;              // 파일 ID - FILE_ID
    private String fileName;          // 실제 파일명 - FILE_NAME
    private String fileSavename;      // 저장된 파일명 - FILE_SAVENAME
    private String filePath;          // 파일 경로 - FILE_PATH
    private Long fileSize;            // 파일 크기 - FILE_SIZE
    private String fileType;          // 파일 타입 (MIME) - FILE_TYPE
    private String fileUploadDate;    // 파일 업로드 날짜 - file_upload_date

    // 연차 이력 및 연차 타입 관련 정보
    private String leaveDate;         // 연차 시작일 - LEAVE_DATE
    private String leaveEndDate;      // 연차 종료일 - LEAVE_END_DATE
    private String reason;            // 연차 신청 사유 - REASON
    private String annualCode;        // 연차 코드 - ANNUAL_CODE
    private String annualName;        // 연차 명칭 - ANNUAL_NAME

    // 연차 VO (필요 시 별도 처리)
    private AnnualHistoryVO annualHistory;
    private AnnualManageVO annualType;

    // 다중 결재자 목록 (자기 자신 포함한 결재라인)
    private List<DraftApproverVO> draftApproverList;
}
