package kr.or.ddit.mybatis.mappers.empappointments;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.empappointments.vo.AppointmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;

@Mapper
public interface AppointmentMapper {
	
	/**
	 * 인사발령 리스트 조회
	 * 
	 * @return
	 */
	public List<AppointmentVO> selectAppointList();

	/**
	 * 인사발령 디테일
	 * 
	 * @param appId
	 * @return
	 */
	public AppointmentVO selectAppoint(String appId);

	
	
    // APP_ID 자동 생성 시퀀스 
    public int generateAppId();
	/**
	 * 인사발령 등록
	 * 
	 * @param appoint
	 * @return
	 */
	public int insertAppoint(AppointmentVO appoint);

	/**
	 * 인사발령 -> 직원테이블 반영을 위한 메소드
	 * 
	 * @param appoint
	 * @return
	 */
	public int insertAppoint_emptable(AppointmentVO appoint);

	/**
	 * 인사발령 정보 수정을 위한 메소드
	 * 
	 * @param appoint
	 * @return
	 */
	public int updateAppoint(AppointmentVO appoint);

	/**
	 * update,formUI 부서 선택을 위한 메소드
	 * 
	 * @return
	 */
	public List<DepartmentVO> readDepartment();

	/**
	 * update,formUI 직급 선택을 위한 메소드
	 * 
	 * @return
	 */
	public List<RankVO> readRank();

	/**
	 * update,formUI 팀 선택을 위한 메소드
	 * 
	 * @return
	 */
	public List<TeamVO> readTeam();

	/**
	 * update,formUI 직무 선택을 위한 메소드
	 * 
	 * @return
	 */
	public List<PositionVO> readPosition();

	/**
	 * update,formUI 직책 선택을 위한 메소드
	 * 
	 * @return
	 */
	public List<JobVO> readJob();

	/**
	 * 	부서 선택 시 팀 리스트 조회
	 * @param departmentId
	 * @return
	 */
    List<TeamVO> selectTeamsByDepartment(String departmentId);

    /**
     * 팀 선택 시 직원 리스트 조회	
     * @param teamId
     * @return
     */
    List<EmployeeVO> selectEmployeesByTeam(String teamId);

    
}
