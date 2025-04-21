package kr.or.ddit.deptcomment.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringExclude;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;
import kr.or.ddit.rank.vo.RankVO;
import lombok.Data;

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
@Data
public class DeptCommentVO implements Serializable {
  
  @ToStringExclude
  @NotNull
  private Long commentId; // 댓글 ID
  private Long deptnoticeId; // 부서 게시판 고유 식별자(fk)
  @NotBlank
  private String accountId; // 작성자
  private String createAt; // 작성 날짜
  @NotBlank
  private String commentContent; // 댓글 내용
  private String commentStatus; // 댓글 상태 (Y,N 디폴트값 N) Y일 경우 활성화 N일 경우 비활성화 
  private Long parentComment; // 부모 댓글 ID(대댓글)
  
  private String name;
  private RankVO rank;
  private DepartmentBoardVO debo;
}
