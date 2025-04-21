package kr.or.ddit.boardcategory.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.boardcategory.vo.BoardCategoryVO;
import kr.or.ddit.mybatis.mappers.boardcategory.BoardCategoryMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardCategoryServiceImpl implements BoardCategoryService {

	@Inject
	private final BoardCategoryMapper dao;

	@Override
	public List<BoardCategoryVO> getBoardCategoryList() {
		return dao.getBoardCategoryList();
	}


}
