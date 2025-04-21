package kr.or.ddit.mybatis.mappers.schedule;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.schedule.vo.ScheduleVO;

@Mapper
public interface ScheduleMapper {
	// 전체 스케줄 조회
	public List<ScheduleVO> ScheduleList();
	// 스케줄 등록
	public void ScheduleInsert(ScheduleVO schedule);
	// 스케줄 수정
	public void ScheduleUpdate(ScheduleVO schedule);
	// 스케줄 삭제
	public void ScheduleDelete(Long scheduleId);

	// 내 스케줄 조회
	public List<ScheduleVO> getSchedulesByEmpId(String empId);

	public List<ScheduleVO> getSchedulesByEmpIdWithinRange(Map<String, Object> paramMap);

}
