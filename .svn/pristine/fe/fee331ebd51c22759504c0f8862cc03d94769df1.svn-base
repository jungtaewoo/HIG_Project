package kr.or.ddit.departmentboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;

/**
 * 
 * @author LIG
 * @since 2025. 3. 12.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	LIG	          최초 생성
 *
 * </pre>
 */
public interface DepartmentBoardService {
  
  public List<DepartmentBoardVO> boardList(DepartmentBoardVO vo); // 부서게시판 목록 조회
  
  public void boardInsert(DepartmentBoardVO board, List<Long> fileIds); // 부서게시판 글 작성
  
  public DepartmentBoardVO boardDetail(Long deptnoticeId); // 부서게시판 상세 조회
  
  public void incrementViews(Long deptnoticeId); // 조회수 증가

  public void modifyBoard(DepartmentBoardVO deptnoticeId); // 부서 게시판 글 수정
  
  public void insertDeftNoFiles(Map<String, Object> paramMap); // 첨부파일 등록
  
  public void delBoard(List<Long> deptnoticeId); // 게시글 삭제
  
  public List<DepartmentBoardVO> topFixedList(DepartmentBoardVO vo); // 부서게시판 상단 고정 게시글 목록 조회


}