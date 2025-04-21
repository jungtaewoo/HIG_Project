package kr.or.ddit.boardcategory.service;

import java.util.List;

import kr.or.ddit.boardcategory.vo.BoardCategoryVO;

public interface BoardCategoryService {
	/** 공지 카테고리 목록 가져오기
	 * @return
	 */
	public List<BoardCategoryVO> getBoardCategoryList();
}
