package kr.or.ddit.attendance.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.team.vo.TeamVO;
import kr.or.ddit.workstatus.vo.WorkstautsVO;
import lombok.Data;

@Data
public class AttendanceVO implements Serializable{
	@NotNull
	private Long attendanceId;
	@NotBlank
	private String empId;
	private String statusId;
	private String workDate;
	private String workStartTime;
	private String workEndTime;
	private Long workingHours;
	private Long workingMinutes;
	private Long overtimeHours;
	private Long overtimeMinutes;
	private Long nightWorkHours;
	private Long nightWorkMinutes;
	@NotBlank
	private String attendanceStatus;
	private String workType;
	
	private EmployeeVO employee;
	private WorkstautsVO workstauts;
	
	private List<DepartmentVO> departmentList;
	private List<TeamVO> teamList;
	private List<WorkstautsVO> workStatusList;
	
	private int departmentEmployeeCount;
}
