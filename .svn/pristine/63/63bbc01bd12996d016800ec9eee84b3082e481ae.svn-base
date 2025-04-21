package kr.or.ddit.mybatis.mappers.workstatus;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.workstatus.vo.WorkstautsVO;

@Mapper
public interface WorkStatusMapper {
	
	// 업무 항목 리스트 조회
	public List<WorkstautsVO> workStatusManageList();

	// 항목 추가
	public void insertWorkStatusItem(WorkstautsVO workstautsManage);

	// 항목 상태 업데이트
	public void updateWorkStatusItemStatus(WorkstautsVO workstautsManage);

	// 업무 종류 항목 삭제
	public void deleteWorkStatusItem(String workstautsCode);
	
	public void updateWorkStatus(@Param("statusId") String statusId ,@Param("empId") String empId, @Param("workDate") String workdDate);
	
}
