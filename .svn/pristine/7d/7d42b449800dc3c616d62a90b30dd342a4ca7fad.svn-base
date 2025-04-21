package kr.or.ddit.team.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
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
 *
 * </pre>
 */
@Data
public class TeamVO implements Serializable{

	public TeamVO() {
		super();
	}
	public TeamVO(Long teamId, Long departmentId) {
		super();
		this.teamId = teamId;
		this.departmentId = departmentId;
	}
	@NotNull
	private Long teamId;
	@NotNull
	private Long departmentId;
	@NotBlank
	private String teamName;
	
	private String teamStatus;
	private String teamPhonenumber;
	private String teamFaxnumber;

	private DepartmentVO department;

	private int teamEmployeeCount;  // 팀 인원 수	
	
	
	// 하나의 팀에 여러 팀원
	private List<TeamMemberVO> teamMembers;  // 팀원들 리스트
	// 하나의 팀에 여러개의 계정
	private List<AccountVO> accounts;
	// 하나의 팀에 여러명의 직원
	private List<EmployeeVO> employees;
	// 하나의 팀에 여러개의 직책
	private List<JobVO> jobs;

	private int cnt ;

}
