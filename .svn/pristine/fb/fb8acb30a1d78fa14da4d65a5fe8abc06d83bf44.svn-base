package kr.or.ddit.mybatis.mappers.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.paging.PaginationInfo;

@Mapper
public interface BoardMapper {

	/** 공지글 등록
	 * @param board 등록할 공지 정보를 가진 vo
	 * @return 등록된 레코드수
	 */
	public int insertBoard(BoardVO board);


	/** 공지글 총 레코드 수
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo<BoardVO> paging);

	/** 공지글 목록 조회
	 * @param paging
	 * @return
	 */
	public List<BoardVO> selectBoardList(PaginationInfo<BoardVO> paging);

	/** 공지글 상세 조회
	 * @param noticeId 조회 대상 primary key
	 * @return 해당 공지글이 없는경우, null 반환
	 */
	public BoardVO selectBoard(Long noticeId);

	/** 조회수 증가
	 * @param noticeId
	 */
	public void updateViews(Long noticeId); // 조회수 증가 메서드 추가


	/** 공지글 수정
	 * @param noticeId
	 * @return
	 */
	public int updateBoard(BoardVO noticeId);

	/** 공지글 삭제
	 * @param noticeId 삭제할 대상의 primary key
	 * @return 삭제된 레코드수
	 */
	public boolean deleteBoard(@Param("noticeId") Long noticeId);

	/** 공지글 상단 고정
	 * @param noticeId
	 * @param importance
	 */
	public void updateBoardImportance(@Param("noticeId") int noticeId, @Param("importance") String importance);

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
