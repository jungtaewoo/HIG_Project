package kr.or.ddit.messenger.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.department.vo.DepartmentVO;
import lombok.Data;

/**
 * 메신저 사용자 관리를 위한 VO
 */

@Data
public class ChatEmpVO implements Serializable{
	@NotBlank
	private String empId;
	/*
	 * @NotBlank//테스트로 타입변환 long -> String 계정등록시 같이 삽입 private String deptId;
	 */
	@NotBlank
	private String empName;
	@NotBlank
	private String tmId;
	@NotBlank
	private String rankId;
	private String status;
	private LocalDate lastLogin;
	
	private String teamName;
	private String rankName;
	
	private DepartmentVO department;
}
