package kr.or.ddit.emprecordcards.service;

import java.util.List;

import kr.or.ddit.emprecordcards.vo.EmpCardVO;

public interface EmpCardService {
	
	public List<EmpCardVO> readEmpCardList();
	
	public EmpCardVO readEmpCard(String cardId);
	
	public boolean createEmpCard(String record);

	public boolean modifyEmpCard(String record);
}
