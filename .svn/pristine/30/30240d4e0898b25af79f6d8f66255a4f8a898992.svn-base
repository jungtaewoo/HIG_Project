package kr.or.ddit.mybatis.mappers.document;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.document.vo.DocumentVO;
import kr.or.ddit.paging.PaginationInfo;

@Mapper
public interface DocumentMapper {
	
	public int selectTotalRecord(PaginationInfo<DocumentVO> paging);
	
	public List<DocumentVO> selectDocumentList(PaginationInfo<DocumentVO> paging);
	
	

}
