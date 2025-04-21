package kr.or.ddit.department.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.team.vo.TeamVO;
import lombok.Data;
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
* </pre>
*/
@Data
public class DepartmentVO implements Serializable{
	@NotNull
	private Long departmentId;
	@NotBlank
	private String departmentName;
	private String departmentLocation;
	private String departmentPhonenumber;
	private String departmentFaxnumber;
	private String numberType;
	private Long parentOrgId;
	private String departmentStatus;
    private String deptHeadName; // 부서장 이름 추가

	private int departmentEmployeeCount;  // 부서 인원 수
	private int departmentArrivedEmployeeCount;  // 출근한 부서 인원 수
	private int departmentAbsentEmployeeCount;  // 미출근부서 인원 수

	// 하나의 부서에 여러개의 팀
	private List<TeamVO> teams;
	// 하나의 부서에 여러개의 계정
	private List<AccountVO> accounts;
	// 하나의 부서에 여러명의 직원
	private List<EmployeeVO> employees;
	// 하나의 부서에 여러개의 직책
	private List<JobVO> jobs;

}
