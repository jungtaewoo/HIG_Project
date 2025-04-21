package kr.or.ddit.empresignations.vo;

import java.io.Serializable;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;
import lombok.Data;

@Data
public class ResignationVO implements Serializable{
	private Long   resignId;
	private String empId;
	private String departmentId;
	private String teamId;
	private String jobId;
	private String rankId;
	private String positionId;
	private String idStatus;
	private String resignDate;
	private String resignReason;
	private String resignType;
	private String resignInterview;
	private String resignInterviewContent;
	
	EmployeeVO employee;
	DepartmentVO department;
	TeamVO team;
	RankVO rank;
	JobVO job;
	PositionVO position;
	
}
