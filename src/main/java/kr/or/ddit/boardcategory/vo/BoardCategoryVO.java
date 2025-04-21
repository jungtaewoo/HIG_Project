package kr.or.ddit.boardcategory.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.board.vo.BoardVO;
import lombok.Data;

@Data
public class BoardCategoryVO implements Serializable{
	@NotNull
	private Long categoryId;
	@NotBlank
	private String categoryName;
	private String description;
	private String createdAt;
	private String updatedAt;

	private List<BoardVO> board;
}
