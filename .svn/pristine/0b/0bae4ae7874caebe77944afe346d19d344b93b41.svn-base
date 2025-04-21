package kr.or.ddit.workstatus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mybatis.mappers.workstatus.WorkStatusMapper;
import kr.or.ddit.workstatus.vo.WorkstautsVO;

@Service
public class WorkStatusServiceImpl implements WorkStatusService{
	
	@Autowired
	WorkStatusMapper dao;
	
	@Override
	public List<WorkstautsVO> workStatusManageList() {
		return dao.workStatusManageList();
	}

	@Override
	public void insertWorkStatusItem(WorkstautsVO workstautsManage) {
		dao.insertWorkStatusItem(workstautsManage);
		
	}

	@Override
	public void updateWorkStatusItemStatus(WorkstautsVO workstautsManage) {
		dao.updateWorkStatusItemStatus(workstautsManage);
		
	}

	@Override
	public void deleteWorkStatusItem(String workstautsCode) {
		dao.deleteWorkStatusItem(workstautsCode);
		
	}

	@Override
	public void updateWorkStatus(String statusId, String empId, String workDate) {
		dao.updateWorkStatus(statusId, empId, workDate);
		
	}
	
}
