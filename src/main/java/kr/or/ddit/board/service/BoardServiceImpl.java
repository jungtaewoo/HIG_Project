package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.mybatis.mappers.board.BoardMapper;
import kr.or.ddit.paging.PaginationInfo;
import lombok.RequiredArgsConstructor;

/**
*
* @author KHS
* @since 2025. 3. 11.
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      			수정자           수정내용
*  -----------   	-------------    ---------------------------
*  2025. 3. 11.     	KHS	          최초 생성
*
* </pre>
*/

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	@Inject
	private final BoardMapper dao;



	@Override
	public List<BoardVO> readBoardList(PaginationInfo<BoardVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectBoardList(paging);
	}


	@Override
	public BoardVO readBoard(Long noticeId) {
		return dao.selectBoard(noticeId);
	}

    @Override
    public Long saveFileInfo(String fileName, String filePath) {
        // 파일 정보를 데이터베이스에 저장하고 파일 ID를 반환하는 로직 구현
        // 예시로 파일 ID를 1로 반환
        return 1L;
    }

    @Override
    @Transactional
    public void incrementViews(Long noticeId) {
        dao.updateViews(noticeId);
    }


	@Override
	public int modifyBoard(BoardVO noticeId) {
		return dao.updateBoard(noticeId);
	}


	@Override
	public boolean removeBoard(Long noticeId) {
		return dao.deleteBoard(noticeId);
	}


	@Override
	public boolean createBoard(BoardVO board) {
		int rowcnt = dao.insertBoard(board);
		return rowcnt > 0;
	}


	@Override
	public void updateBoardimportance(int noticeId, String importance) {
		dao.updateBoardImportance(noticeId, importance);

	}


	@Override
	public List<BoardVO> selectImportanceBoardList(PaginationInfo<BoardVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		return dao.selectImportanceBoardList(paging);
	}




//	@Override
//	public List<BoardCategoryVO> getBoardCategoryList() {
//		return dao.getBoardCategoryList();
//	}



}
