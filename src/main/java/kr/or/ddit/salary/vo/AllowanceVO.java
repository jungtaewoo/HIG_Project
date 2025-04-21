package kr.or.ddit.salary.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AllowanceVO implements Serializable{
	@NotNull
	private Long allowanceCode; // 수당코드(PK)
	@NotNull
	private Long allowanceId; // 수당ID
	@NotNull
	private Long salaryId; // 급여ID
	@NotBlank
	private String empId; // 직원ID
	@NotNull
	private Long allowanceAmount; // 수당금액
	private String createdAt; // 수당생성일
	
	private AllowanceCodeVO allowanceCodeVO;
	private SalaryVO salary;

}
