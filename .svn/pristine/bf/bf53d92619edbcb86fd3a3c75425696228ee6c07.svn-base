package kr.or.ddit.empappointments.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;
import kr.or.ddit.validate.InsertGroup;
import lombok.Data;

@Data
public class AppointmentVO implements Serializable{
	@NotBlank(groups = InsertGroup.class, message = "필수항목입니다")
	private Long appId;
	@NotBlank(groups = InsertGroup.class, message = "직원 선택은 필수입니다.")
	private String empId;
	@NotBlank(groups = InsertGroup.class, message = "입사일자는 필수입니다.")
	private String appDate;
	@NotBlank(groups = InsertGroup.class, message = "발령구분은 필수입니다.")
	private String appType;
	@NotBlank(groups = InsertGroup.class, message = "발령사유는 필수입니다.")
	private String appReason;
	private String prevJobId;
	private String prevRankId;
	private String prevDepartmentId;
	private String newJobId;
	private String newRankId;
	private String newDepartmentId;
	private String prevTeamId;
	private String prevPositionId;
	private String newTeamId;
	private String newPositionId;

	private EmployeeVO employee;
	private TeamVO team;
	private PositionVO position;
	private JobVO job;
	private RankVO rank;
	private DepartmentVO department;

	private String prevRankName;
	private String newRankName;
	private String prevPositionName;
	private String newPositionName;
	private String prevDepartmentName;
	private String newDepartmentName;
	private String prevJobName;
	private String newJobName;
	private String prevTeamName;
	private String newTeamName;
	

}

