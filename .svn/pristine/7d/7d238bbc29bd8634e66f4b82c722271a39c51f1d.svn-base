package kr.or.ddit.empappointments.service;

import java.util.List;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.empappointments.vo.AppointmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;

public interface AppointmentService {

	public List<AppointmentVO> readAppointList(); 
	public AppointmentVO readAppoint(String appId);
	public boolean createAppoint(List<AppointmentVO> appointmentList);
	public boolean createAppoint_insertTable(List<AppointmentVO> appointmentList);
	public boolean modifyAppoint(AppointmentVO appoint);
	
	public List<DepartmentVO> readDepartment();
	public List<RankVO> readRank();
	public List<TeamVO> readTeam();
	public List<PositionVO> readPosition();
	public List<JobVO> readJob();
	
	List<TeamVO> getTeamsByDepartment(String departmentId);
	List<EmployeeVO> getEmployeesByTeam(String teamId);
}
