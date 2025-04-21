package kr.or.ddit.mybatis.mappers.annual;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.annual.vo.AnnualVO;

@Mapper
public interface AnnualMangeMapper {
	// 연차 항목 리스트 조회
	public List<AnnualManageVO> annualManageList();

	// 항목 추가
	public void insertAnnualItem(AnnualManageVO AnnualManage);

	// 항목 상태 업데이트
	public void updateAnnualItemStatus(AnnualManageVO AnnualManage);

	// 연차 종류 항목 삭제
	public void deleteAnnualItem(String annualCode);
}
