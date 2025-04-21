package kr.or.ddit.mybatis.mappers.attendance;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import kr.or.ddit.attendance.vo.AttendanceVO;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.team.vo.TeamVO;
import kr.or.ddit.workstatus.vo.WorkstautsVO;

@Mapper
public interface attendanceMapper {
	// 모두의 출퇴 기록을 보기 위한 메서드
	public List<AttendanceVO> attendanceList();
	
	// 모두의 출퇴 기록에서 부서나 팀이나 날짜로서 검색하고싶은 메서드
	public List<AttendanceVO> attendanceList(
			@Param("departmentId") String departmentId
		, @Param("teamId") String teamId
		, @Param("date")String date
	);
	// 개인의 출퇴 기록을 보기위한 메서드
	public List<AttendanceVO> attendanceDetail(
		@Param("empId") String empId
		,@Param("startDate") String startDate
		,@Param("endDate") String endDate
	);
	
	// 한명의 근태 기록을 보기위한 메서드 2
	public List<AttendanceVO> myAttendance(@Param("empId")String empId, @Param("workDate")String workDate);
	
	//QR 스캔시 출근 기록 업데이트
	public void attendanceInsert(AttendanceVO attendance);
	
	//QR controller에서 직원 한명의 오늘 날짜 출근 기록을 조회 
	public AttendanceVO findTodayAttendance(String empId);
	
	//task 어드바이스에서 모든 직원의 오늘 날짜 출근 기록을 조회 
	public List<AttendanceVO> findTodayAttendanceList();
	
	//QR controller에서 직원 한명의 출근 기록이 있다면 퇴근 기록을 업데이트 할때 사용
	public void updateWorkEnd(AttendanceVO attendance);
	
	// 부서 목록을 조회시 사용
	public List<DepartmentVO> departmentList(String teamId);
	
	// 팀 목록을 조회시 사용
	public List<TeamVO> teamList(String teamId);
	
	// 업무 상태 목록 조회시 사용
	public List<WorkstautsVO> workStatusList();
	
	// 직원의 업무 상태 조회
	public AttendanceVO workStatusEmployee(String empId);
	
	public EmployeeVO Employee(String empId);
	
	// QR스캔 시 메인 프로필 출퇴기록 업데이트
	public AttendanceVO updateAttendance(String empId);
	
	public void insertAbsentStatus( AttendanceVO attendance);
	
	
	public AttendanceVO getMax();
	
}
