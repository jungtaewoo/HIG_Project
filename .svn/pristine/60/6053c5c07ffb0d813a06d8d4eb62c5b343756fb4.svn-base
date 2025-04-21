package kr.or.ddit.annual.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * @author CHOI
 * @since 2025. 3. 26.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 26.     	CHOI	          js 매핑 -> cd, nm
 *
 * </pre>
 */
@Data
public class AnnualManageVO implements Serializable{
	@NotBlank
	private String annualCode;
	@NotBlank
	private String annualName;
	private String annualInfo;
	@NotBlank
	@Size(min=1,max=1)
	private String annualStatus;
	
	
	private String cd = "";
	private String nm = "";
	
	
}
