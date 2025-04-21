package kr.or.ddit.deptcomment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.deptcomment.vo.DeptCommentVO;
import kr.or.ddit.mybatis.mappers.deptcomment.DeptCommentMapper;

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
@Service
public class DeptCommentServiceImpl implements DeptCommentService {

  @Autowired
  DeptCommentMapper mapper;

  @Override
  public List<DeptCommentVO> commentList(Long deptnoticeId) {
    return mapper.commentList(deptnoticeId);
  } // 댓글 조회

  @Override
  public DeptCommentVO commentInsert(DeptCommentVO commentInsert) {
    mapper.commentInsert(commentInsert);

    return commentInsert;
  } // 댓글 작성

  @Override
  public DeptCommentVO commentDetail(DeptCommentVO params) {
    return mapper.commentDetail(params);
  }

  @Override
  public Long deleteComment(Long commentId) {
    return mapper.deleteComment(commentId);
     
  } // 댓글 삭제
  
  
}
