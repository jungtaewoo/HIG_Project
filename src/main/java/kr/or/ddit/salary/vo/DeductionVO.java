package kr.or.ddit.salary.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class DeductionVO implements Serializable{
	@NotNull
	private Long deductionId; // 공제 내역 ID
	@NotNull
	private Long salaryId; // 급여지급 ID 고유식별자
	@NotBlank
	private String empId; // 직원 정보를 저장하는 테이블
	@NotNull
	private Long deductionCode; // 공제코드(FK)
	@NotNull
	private Long deductionAmount; // 공제금액
	
	private String createAt; // 공제 내역 생성일
	
	private DeductionCodeVO deductionCodeVO;
	
}
