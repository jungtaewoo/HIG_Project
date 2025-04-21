package kr.or.ddit.annual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.mybatis.mappers.annual.AnnualMangeMapper;

@Service
public class AnnualMangeServiceImpl  implements AnnualMangeService{
	
	@Autowired
	AnnualMangeMapper dao;

	@Override
	public List<AnnualManageVO> annualManageList() {
		return dao.annualManageList();
	}
	   // 항목 추가
    public void insertAnnualItem(AnnualManageVO AnnualManage) {
        dao.insertAnnualItem(AnnualManage);
    }

    // 항목 상태 업데이트
    public void updateAnnualItemStatus(AnnualManageVO AnnualManage) {
        dao.updateAnnualItemStatus(AnnualManage);
    }
	@Override
	public void deleteAnnualItem(String annualCode) {
		dao.deleteAnnualItem(annualCode);
	}
	
}
