package kr.or.ddit.signature.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import lombok.Data;

@Data
public class SignatureVO implements Serializable{
	
	@NotBlank
	private long signId;
	@NotBlank
	private String empId;
	@NotBlank
	private String signImagePath;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;
	
	private EmployeeVO employee;
}
