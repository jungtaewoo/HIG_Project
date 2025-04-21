package kr.or.ddit.contract.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.employee.vo.EmployeeVO;
import lombok.Data;

@Data
public class ContractVO implements Serializable{

	@NotBlank
	private long contractId;
	@NotBlank
	private String empId;
	@NotBlank
	private String contractType;
	@NotBlank
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@NotBlank
	private String workType;
	@NotBlank
	private long baseSalary;
	private Long overtimePay;
	@NotBlank
	private long weeklyHours;
	@NotBlank
	private String contractStatus;
	private LocalDate createAt;
	private String workPlace;
	private Long signId;
	
	//03.23급여 기능으로 영준추가 3개
	private Long positionAllowance; // 직책수당(테스
	private Long transportAllowance; // 교통비(테스트)
	private Long foodAllowance; // 식대(테스트)
	private Long annualSalary; // 연봉
	
	private EmployeeVO employee;
	private String name;
	
	private String signImagePath;
}
