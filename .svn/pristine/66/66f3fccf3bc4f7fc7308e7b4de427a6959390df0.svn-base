package kr.or.ddit.mybatis.mappers.empcardss;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.emprecordcards.vo.EmpCardVO;

@Mapper
public interface EmpCardMapper {
	
	public List<EmpCardVO> selectRecordList(); 
	
	public EmpCardVO selectRecord(String cardId);
	
	public int insertRecord(String record);
	
	public int updateRecord(String record);
}