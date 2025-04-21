package kr.or.ddit.mybatis.mappers.departmentboard;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface DepartmentBoardMapper {
  
  public List<DepartmentBoardVO> boardList(DepartmentBoardVO pvo); // 부서게시판 목록 조회
  
  public void boardInsert(DepartmentBoardVO board); // 부서게시판 글 작성
  
  public DepartmentBoardVO boardDetail(Long deptnoticeId); // 부서게시판 상세 조회
  
  public void updateViews(Long deptnoticeId); // 조회수 증가 메서드 추가
  
  public void updateBoard(DepartmentBoardVO deptnoticeId); // 게시글 수정
  
  public void insertDeftNoFiles(Map<String, Object> paramMap); // 첨부파일 등록
  
  public void deleteBoard(@Param("deptnoticeId") Long deptnoticeId); // 게시글 삭제
  
  public List<DepartmentBoardVO> topFixedList(DepartmentBoardVO tvo); // 상단 고정 게시글 리스트
  

}
