package kr.or.ddit.departmentboard.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;
import kr.or.ddit.file.service.FileInfoService;
import kr.or.ddit.mybatis.mappers.departmentboard.DepartmentBoardMapper;

/**
 * 
 * @author LIG
 * @since 2025. 3. 12.
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	LIG	          최초 생성
 *
 *      </pre>
 */
@Service
public class DepartmentBoardServiceImpl implements DepartmentBoardService {

  @Autowired
  DepartmentBoardMapper mapper;
  
  @Inject
  private FileInfoService service; //파일

  public void boardInsert(DepartmentBoardVO board, List<Long> fileIds) {
    mapper.boardInsert(board);
  } // 부서 게시판 글 작성

  public DepartmentBoardVO boardDetail(Long deptnoticeId) {
    return mapper.boardDetail(deptnoticeId);
  } // 부서 게시판 글 상세 조회 메서드

  public void incrementViews(Long deptnoticeId) {
    mapper.updateViews(deptnoticeId);
  } // 조회수 증가 메서드

  @Override
  public void modifyBoard(DepartmentBoardVO deptnoticeId) {
    mapper.updateBoard(deptnoticeId);
  } // 게시글 수정 메서드

  @Override
  public List<DepartmentBoardVO> boardList(DepartmentBoardVO pvo) {
    return mapper.boardList(pvo);
  } // 부서 게시판 리스트 메서드
  
  @Override
  public void insertDeftNoFiles(Map<String, Object> paramMap) {
    mapper.insertDeftNoFiles(paramMap);
  }
  
  @Override
  public void delBoard(List<Long> deptnoticeIds) {
    for(Long deptnoticeId : deptnoticeIds) {
      mapper.deleteBoard(deptnoticeId);
    }
  }
  
  @Override
  public List<DepartmentBoardVO> topFixedList(DepartmentBoardVO tvo) {
    return mapper.topFixedList(tvo);
  } // 부서 게시판 상단 고정 게시글 목록 조회 메서드

 

}
