package kr.or.ddit.approval.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 27.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 27.     	CHOI	          결재 작성 정보 불러오기 위한 VO(연차)
 *
 * </pre>
 */
@Data
public class DraftDetailVO implements Serializable{
	
	private Long draftId;
    private String draftTitle;
    private String draftContent;
    private Date draftDate;
    private String draftStatus;
    private String empId;
    private String empName;
    private String department;

    // 연차 이력 관련
    private Date leaveDate;
    private Date leaveEndDate;
    private String reason;
    private String annualCode;
    private String annualName;  // annual_type 테이블에서 가져온 이름

    // 결재자 관련 (단일 결재자 정보 예시; 다수의 결재자가 있다면 List<DraftApproverVO> 사용)
    private String approverId;
    private Integer aprSeq;
    private String aprStatus;
    private String aprReason;
    //다중 결재자 
    private List<DraftApproverVO> draftApproverList;
    
    
}
