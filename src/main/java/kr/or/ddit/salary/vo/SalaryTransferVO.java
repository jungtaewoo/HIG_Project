package kr.or.ddit.salary.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class SalaryTransferVO implements Serializable{
	@NotNull
	private Long transferId; // 급여이체 고유 (PK)
	@NotBlank
	private String empId; // 직원 정보를 저장하는 테이블
	@NotNull
	private Long salaryId; // 급여지급 ID 고유식별자
	@NotNull
	private Long paId; // 계좌정보 PK
	@NotBlank
	private String bankName; // 은행명
	@NotBlank
	private String paNumber; // 계좌번호
	@NotBlank
	private String paEmp; // 직원ID(예금주)
	@NotNull
	private Long transferPrice; // 이체 금액(실지급액)
	@NotBlank
	private String transferDate; // 급여지급날짜
	@NotBlank
	private String transferStatus; // 이체 상태(확정, 대기 ,진행중)
	private String pdfPath; // PDF 다운로드 경로
	private String createAt; // 디폴트 : SYSDATE

}
