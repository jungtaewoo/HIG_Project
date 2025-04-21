package kr.or.ddit.document.service;

import java.util.List;

import kr.or.ddit.document.vo.DocumentVO;
import kr.or.ddit.paging.PaginationInfo;

public interface DocumentService {
	
	public List<DocumentVO> readDocumentList(PaginationInfo<DocumentVO> paging);
	
	

}
