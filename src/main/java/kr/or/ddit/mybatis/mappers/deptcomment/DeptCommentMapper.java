package kr.or.ddit.mybatis.mappers.deptcomment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.deptcomment.vo.DeptCommentVO;

/**
 * 
 * @author LIG
 * @since 2025. 3. 27.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 27.     	LIG	          최초 생성
 *
 * </pre>
 */
@Mapper
public interface DeptCommentMapper {
  
  public List<DeptCommentVO> commentList(Long deptnoticeId); // 댓글 전체 리스트
  
  public DeptCommentVO commentDetail(DeptCommentVO params); // 댓글 전체 리스트
  
  public void commentInsert(DeptCommentVO commentInsert); // 댓글 작성
  
  public Long deleteComment(Long commentId); // 댓글 삭제


}
