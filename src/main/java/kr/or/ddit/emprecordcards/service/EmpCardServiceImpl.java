package kr.or.ddit.emprecordcards.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.emprecordcards.exception.EmpCardNotFoundException;
import kr.or.ddit.emprecordcards.vo.EmpCardVO;
import kr.or.ddit.mybatis.mappers.empcardss.EmpCardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpCardServiceImpl implements EmpCardService {

	private final EmpCardMapper dao;

	@Override
	public List<EmpCardVO> readEmpCardList() {
		return dao.selectRecordList();
	}

	@Override
	public EmpCardVO readEmpCard(String cardId) throws EmpCardNotFoundException {
		EmpCardVO record = dao.selectRecord(cardId);
		if(record==null) {
			throw new EmpCardNotFoundException(cardId);			
		}
		return record;
	}

	@Override
	public boolean createEmpCard(String record) {
		int rowcnt = dao.insertRecord(record);
		return rowcnt > 0;
	}

	@Override
	public boolean modifyEmpCard(String record) {
		int rowcnt = dao.updateRecord(record);
		return rowcnt > 0;
	}
	
	

}
