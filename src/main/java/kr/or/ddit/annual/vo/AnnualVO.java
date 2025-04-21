package kr.or.ddit.annual.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.employee.vo.EmployeeVO;
import lombok.Data;

@Data
public class AnnualVO implements Serializable{
	@NotNull
	private Long annualId;
	@NotBlank
	private String empId;
	@NotNull
	private float totalAnnual;
	@NotNull
	private float usedAnnual;
	@NotNull
	private float remainingAnnual;
	@NotBlank
	private String lastUpdated;
	
	
	private AnnualManageVO annualManage; 
	private EmployeeVO employee;
	 
}
