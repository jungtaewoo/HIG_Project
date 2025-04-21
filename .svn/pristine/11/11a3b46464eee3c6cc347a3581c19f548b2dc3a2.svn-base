package kr.or.ddit.workstatus.service;

import java.util.List;

import kr.or.ddit.workstatus.vo.WorkstautsVO;

public interface WorkStatusService {
	// 업무 항목 리스트 조회
		public List<WorkstautsVO> workStatusManageList();

		// 항목 추가
		public void insertWorkStatusItem(WorkstautsVO workstautsManage);

		// 항목 상태 업데이트
		public void updateWorkStatusItemStatus(WorkstautsVO workstautsManage);

		// 업무 종류 항목 삭제
		public void deleteWorkStatusItem(String workstautsCode);
		
		
		public void updateWorkStatus(String statusId , String empId, String workDate);
}
