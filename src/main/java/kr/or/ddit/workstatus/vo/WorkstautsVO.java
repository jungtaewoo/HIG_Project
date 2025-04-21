package kr.or.ddit.workstatus.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
/**
 * 
 * 
 * @author 정태우
 * @since 2025. 3. 14.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	정태우	          최초 생성
 *
 * </pre>
 */
@Data
public class WorkstautsVO implements Serializable{
	@NotBlank
	private String statusId;
	@NotBlank
	private String statusName;
	private String statusInfo;
	@NotBlank
	@Size(min=1,max=1)
	private String statusPos;
}
