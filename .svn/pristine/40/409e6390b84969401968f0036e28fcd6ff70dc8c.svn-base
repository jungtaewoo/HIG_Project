package kr.or.ddit.annual.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.employee.vo.EmployeeVO;
import lombok.Data;
@Data
public class AnnualHistoryVO implements Serializable{
	@NotNull
	private Long historyId; //사용 기록 (시퀀스)
	@NotBlank
	private String empId; //사용한 사번
	@NotBlank
	private String leaveDate; //연차 시작일
	@NotBlank
	private String leaveEndDate; //연차 종료일
	@NotBlank
	private String status; // 승인 & 대기 & 반려 
	private String reason; //신청 이유
	private String rejectReason; //반려 사유
	@NotBlank
	private String requestDate; //요청 날짜
	@NotBlank
	private String annualCode; //연차 종류
	
	private Long draftId; //기안자 코드 
	
	private double leaveDay; //사용일
	
	private AnnualManageVO annualType;
	private EmployeeVO employee;
	
	private int todayAnnualCount; // 오늘자 휴가자
}
