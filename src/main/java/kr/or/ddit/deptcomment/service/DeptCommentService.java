package kr.or.ddit.deptcomment.service;

import java.util.List;

import kr.or.ddit.deptcomment.vo.DeptCommentVO;

/**
 * 
 * @author LIG
 * @since 2025. 3. 27.
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 27.     	LIG	          최초 생성
 *
 *      </pre>
 */
public interface DeptCommentService {

  /**
   * 댓글 전체 리스트
   * 
   * @param deptnoticeId
   * @return
   */
  public List<DeptCommentVO> commentList(Long deptnoticeId);

  /**
   * 댓글 작성
   * 
   * @param commentInsert
   * @return
   */
  public DeptCommentVO commentInsert(DeptCommentVO commentInsert);

  /**
   * @param deptnoticeId
   * @return
   */
  public DeptCommentVO commentDetail(DeptCommentVO params); // 댓글 전체 리스트
  
  
  /**
   * 댓글 삭제
   * commentId
   * @param commentId
   * @return 
   */
  public Long deleteComment(Long commentId); // 
  
  
  
  
}
