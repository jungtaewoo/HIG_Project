package kr.or.ddit.schedule.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mybatis.mappers.schedule.ScheduleMapper;
import kr.or.ddit.schedule.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleMapper dao;

	@Override
	public List<ScheduleVO> ScheduleList() {
		return dao.ScheduleList();
	}

	@Override
	public void ScheduleInsert(ScheduleVO schedule) {
		dao.ScheduleInsert(schedule);
	}

	@Override
	public void ScheduleUpdate(ScheduleVO schedule) {
		dao.ScheduleUpdate(schedule);

	}

	@Override
	public void ScheduleDelete(Long scheduleId) {
		dao.ScheduleDelete(scheduleId);

	}

	@Override
	public List<ScheduleVO> getSchedulesByEmpId(String empId) {
		log.info("🔹 현재 로그인한 empId: {}", empId);
		return dao.getSchedulesByEmpId(empId);

	}

	@Override
	public List<ScheduleVO> getSchedulesByEmpIdWithinRange(Map<String, Object> paramMap) {
		return dao.getSchedulesByEmpIdWithinRange(paramMap);
	}






}
