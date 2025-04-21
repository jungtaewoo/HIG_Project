package kr.or.ddit.salary.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class PayAccountVO implements Serializable{
	@NotNull
	private Long paId; // 계좌정보 PK
	@NotBlank
	private String paEmp; // 직원ID(예금주)
	@NotBlank
	private String bankName; // 은행명
	@NotBlank
	private String paNumber; // 계좌번호

}
