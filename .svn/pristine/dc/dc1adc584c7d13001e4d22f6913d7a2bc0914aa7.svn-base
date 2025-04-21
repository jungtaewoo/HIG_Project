package kr.or.ddit.empappointments.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.empappointments.exception.AppointNotFoundException;
import kr.or.ddit.empappointments.vo.AppointmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.mybatis.mappers.empappointments.AppointmentMapper;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
	
	private final AppointmentMapper dao;
	
	@Override
	public List<AppointmentVO> readAppointList() {
		return dao.selectAppointList();
	}

	@Override
	public AppointmentVO readAppoint(String appId) throws AppointNotFoundException {
		AppointmentVO appoint = dao.selectAppoint(appId);
		if(appoint==null) {
			throw new AppointNotFoundException(appId);
		}
		return appoint;
	}

	@Transactional
	@Override
	public boolean createAppoint(List<AppointmentVO> appointmentList) {
		int totalInsert = 0;
		
		for (AppointmentVO appoint : appointmentList) {
			int newAppId = dao.generateAppId(); // 시퀀스에서 APP_ID 생성
            appoint.setAppId((long) newAppId);  // VO에 할당
			totalInsert += dao.insertAppoint(appoint);
			
		}
		return totalInsert == appointmentList.size(); // 모든 행이 성공적으로 삽입되었는지 확인
	}
	
	@Override
	public boolean createAppoint_insertTable(List<AppointmentVO> appointmentList) {
		int totalInsert = 0;
		for (AppointmentVO appoint : appointmentList) {
			totalInsert += dao.insertAppoint_emptable(appoint);
		}
		return totalInsert == appointmentList.size();
	}

	@Override
	public boolean modifyAppoint(AppointmentVO appoint) {
		int rowcnt = dao.updateAppoint(appoint);
		return rowcnt > 0 ;
	}
	
	@Override
	public List<DepartmentVO> readDepartment() {
		return dao.readDepartment();
	}

	@Override
	public List<RankVO> readRank() {
		return dao.readRank();
	}

	@Override
	public List<TeamVO> readTeam() {
		return dao.readTeam();
	}

	@Override
	public List<PositionVO> readPosition() {
		return dao.readPosition();
	}

	@Override
	public List<JobVO> readJob() {
		return dao.readJob();
	}
	
	@Override
    public List<TeamVO> getTeamsByDepartment(String departmentId) {
        return dao.selectTeamsByDepartment(departmentId);
    }

    @Override
    public List<EmployeeVO> getEmployeesByTeam(String teamId) {
        return dao.selectEmployeesByTeam(teamId);
    }

}
