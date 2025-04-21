package kr.or.ddit.mybatis.mappers.approval;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.approval.vo.ApprovalStatusCountVO;
import kr.or.ddit.approval.vo.DraftApproverVO;
import kr.or.ddit.approval.vo.DraftManageMentVO;
import kr.or.ddit.employee.vo.EmployeeVO;

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
 *  2025. 3. 18.     	CHOI	          결재자 관리
 *
 * </pre>
 */
@Mapper
public interface ApproverMapper {
	
	/**
	 * 결재자가 본인의 결재 상태 정보를 볼 때 
	 * @param aprId 결재자
	 * @return
	 */
	public List<DraftApproverVO> getMyApprovalInfo(@Param("aprId") String aprId);
	
	
	/**
     * 주어진 결재자 이름에 해당하는 모든 emp_id를 조회합니다.
     *
     * @param approverName 결재자 이름
     * @return emp_id 목록
     */
    public List<String> selectEmpIdsByName(@Param("approverName") String approverName);
	
	/**
	 * 결재라인 리스트 
	 * @param empId
	 * @return
	 */
	public List<EmployeeVO> getLineApprovers(@Param("empId") String empId);
	
	
	/**
	 * 즐겨찾는 결재라인 리스트 
	 * @param empId
	 * @return
	 */
	public List<EmployeeVO> getFavoriteApprovers(@Param("empId") String empId);	
	/**
	 * 본인 부서 노드 열 때 사용 
	 * @param empId
	 * @return
	 */
	public EmployeeVO getEmployeeById(@Param("empId") String empId);
	
	
	/**
	 * 결재자 draftId 찾기
	 * @param draftId
	 * @return
	 */
	public List<DraftApproverVO> selectApproversByDraftId(@Param("draftId") Long draftId);
	
	
	
	/**
	 * 대기/승인/반려 카운트 
	 * @return
	 */
	public List<Map<String, Object>> selectApproverCountsGroup(@Param("empId") String empId);
	
	
	/**
	 * 결재자 입장에서 -> 대기/승인/반려/결재중 문서 확인 차트용 
	 * @param approverId
	 * @return
	 */
	public List<ApprovalStatusCountVO> selectApproverChart(@Param("approverId") String approverId);
	
	 /**
     * 결재 진행함 목록 조회
     * (보류 상태 또는 승인 상태이지만 후속 결재자가 남아있는 경우)
     */
    public List<DraftApproverVO> approverProcessList(@Param("approverId") String approverId);
	
    
    /**
     * 종결함 -> 최종 승인, 반려된 문서 조회(남은 결재라인 x) 
     * @param approverId
     * @return
     */
    public List<DraftApproverVO> approverComplitedList(@Param("approverId") String approverId);
    
    
	/**
	 * 결재자가 결재 대기 상세정보를 볼 때 
	 * @param draftId
	 * @return
	 */
	public List<DraftApproverVO> getMyApprovalInfoDetail(@Param("draftId") Long draftId);
}
