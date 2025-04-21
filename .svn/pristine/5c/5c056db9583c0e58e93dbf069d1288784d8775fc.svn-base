package kr.or.ddit.document.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.document.vo.DocumentVO;
import kr.or.ddit.mybatis.mappers.document.DocumentMapper;
import kr.or.ddit.paging.PaginationInfo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
	
	@Inject
	private final DocumentMapper dao;

	@Override
	public List<DocumentVO> readDocumentList(PaginationInfo<DocumentVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectDocumentList(paging);
	}

}
