package kr.or.ddit.salary.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AllowanceCodeVO implements Serializable{
	@NotNull
	private Long allowanceCode; // 수당 코드(PK)
	@NotBlank
	private String allowanceName; // 수당명
	private String allowanceDesc; // 수당설명
	private Long allowanceRate; // 수당요율

}
