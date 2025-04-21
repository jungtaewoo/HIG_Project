package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.boardcategory.vo.BoardCategoryVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.file.vo.FileDetailVO;
import kr.or.ddit.role.vo.RoleVO;
import lombok.Data;
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
*  2025. 3. 21.     	KHS	          파일업로드 관련 추가
* </pre>
*/
@Data
public class BoardVO implements Serializable{
	@NotNull
	private Long noticeId;
	@NotBlank
	private String empId;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private String categoryName;
	@NotBlank
	private String importance;
	private Date createdAt;
	private String updatedAt;
	private int viewCount;
	@NotNull
	private Long categoryId;
	private Long noticeFile;

	private String jobName;

	private Long oneFile;
	private Long detailId; // 파일 상세 ID

	private BoardCategoryVO category;
	private RoleVO role;
	private EmployeeVO emp;
    private List<Long> noticeFiles; // 첨부파일들
	private List<FileDetailVO> fileDetails; // 파일 상세 정보 리스트
}
