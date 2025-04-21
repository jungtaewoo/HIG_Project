package kr.or.ddit.schedule.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.schedule.vo.ScheduleVO;

public interface ScheduleService {
	public List<ScheduleVO> ScheduleList();
	public void ScheduleInsert(ScheduleVO schedule);
	public void ScheduleUpdate(ScheduleVO schedule);
	public void ScheduleDelete(Long scheduleId);
	public List<ScheduleVO> getSchedulesByEmpId(String empId);
	public List<ScheduleVO> getSchedulesByEmpIdWithinRange(Map<String, Object> paramMap);

}
