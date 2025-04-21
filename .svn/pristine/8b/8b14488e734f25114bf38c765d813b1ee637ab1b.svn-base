package kr.or.ddit.salary.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DeductionCodeVO implements Serializable{
	@NotNull
	private Long deductionRate; // 공제요율
	@NotBlank
	private String deductionCalcBase; // 공제대상
	private Long deductionCode; // 공제코드(PK)
	private String deductionName; // 공제명
	private String deductionDesc; // 공제설명
	private String deductionCalcLogic; // 계산식

}
