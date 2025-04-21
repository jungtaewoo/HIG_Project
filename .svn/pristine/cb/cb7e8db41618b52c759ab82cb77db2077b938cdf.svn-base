package kr.or.ddit.annual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.annual.vo.AnnualVO;
import kr.or.ddit.mybatis.mappers.annual.AnnualMapper;

@Service
public class AnnualServiceImpl implements AnnualService{
	
	@Autowired
	AnnualMapper dao;

	@Override
	public List<AnnualVO> annualList(String teamId) {
		return dao.annualList(teamId);
	}

	@Override
	public List<AnnualHistoryVO> annualDetail(String empId, String date) {
		return dao.annualDetail(empId,date);
	}
	
	public AnnualVO annaulCount(String empId) {
		return dao.annaulCount(empId);
	}
}
