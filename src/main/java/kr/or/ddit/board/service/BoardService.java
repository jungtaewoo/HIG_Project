package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.paging.PaginationInfo;

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
public interface BoardService {

	/** 신규 공지 등록
	 * @param board
	 * @return
	 */
	public boolean createBoard(BoardVO board);

	/** 공지글 목록 조회
	 * @param paging
	 * @return
	 */
	public List<BoardVO> readBoardList(PaginationInfo<BoardVO> paging);

	/** 파일 정보를 저장
	 * @param fileName
	 * @param filePath
	 * @return
	 */
	public Long saveFileInfo(String fileName, String filePath);

	/** 공지글 상세 조회
	 * @param noticeId
	 * @return
	 */
	public BoardVO readBoard(Long noticeId);

	/** 조회수 증가
	 * @param noticeId
	 */
	public void incrementViews(Long noticeId);

	/** 공지 수정
	 * @param noticeId
	 * @return
	 */
	public int modifyBoard(BoardVO noticeId);

	/** 공지 삭제
	 * @param noticeId
	 * @return
	 */
	public boolean removeBoard(Long noticeId);

	/**  공지글 상단 고정
	 * @param noticeId
	 * @param importance
	 */
	public void updateBoardimportance(int noticeId, String importance);

	/** 상단 고정 공지 목록
	 * @param paging
	 * @return
	 */
	public List<BoardVO> selectImportanceBoardList(PaginationInfo<BoardVO> paging);


	/** 공지 카테고리 목록 가져오기
	 * @return
	 */
//	public List<BoardCategoryVO> getBoardCategoryList();
}











