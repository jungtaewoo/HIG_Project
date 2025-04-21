package kr.or.ddit.approval.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.file.vo.FileDetailVO;
import lombok.Data;

/**
 * 기안관리 
 */
@Data
public class DraftManageMentVO implements Serializable{
	@NotNull
	private Long draftId; // 기안관리ID
	@NotNull
	private Long templateId; // 기안양식 ID(PK)
	@NotBlank
	private String empId; // 직원 정보를 저장하는 테이블
	@NotBlank
	private String draftTitle; // 기안 제목
	private String draftContent; // 기안 내용
	private String draftFile; // 첨부파일 테이블 값을 입력 첨부파일ID
	@NotBlank
	private String draftDate; // 기안일시
	private String draftUrgent; // 긴급여부 (Y,N)
	private Long departmentId; // 부서 ID
	private String draftStatus; // 문서상태(대기,결재중,완료) ,기본값 : 대기
	private String finalApprover; // 마지막 결재자 
	private boolean hasAttachment;
	// 직원 1 : 1 매핑 
	private EmployeeVO employee;
	
	// 문서함 목록 (1:N)
	private List<DraftBoxVO> draftBoxList;
	
	// 결재자 목록 (1:N)
	private List<DraftApproverVO> draftapproverList;
	// 연차 폼에 넣을 데이터 
	private AnnualHistoryVO annualHistory; // 연차 이력 
	private AnnualManageVO annualType; // 연차 타입 
	
	 // 여기가 중요
    private String draftEmpName;       // 기안자 이름
    private String draftDepartment;    // 기안자 부서코드(혹은 부서명)
    private String draftDepartmentName; //부서 이름 
	private String approverDepartmentName; //결재자 부서 이름
	private String approverRankName;  // 결재자 직급
	
	// 연차 이력에 포함되는 필드들
    private String leaveDate; // 연차 시작일
    private String leaveEndDate; // 연차 종료일
    private String reason; // 연차 신청 사유
    private String annualCode; // 연차 코드
    private String annualName; // 연차 명칭
    
    private List<AnnualManageVO> annualTypes; // 연차 타입 SELECT리스트 
    
    
    // 파일 정보 필드 추가
    private Long detailId; //첨부파일의 상세 아디
    private Long fileId;           // 파일 ID
    private String fileName;       // 실제 파일명
    private String fileSavename;   // 저장된 파일명
    private String filePath;       // 파일 경로
    private Long fileSize;         // 파일 크기
    private String fileType;       // 파일 타입(MIME)
    private String fileUploadDate; // 파일 업로드 날짜(문자열 or Date/LocalDateTime)
	// 참조자 목록 (1:N)
	private List<DraftParVO> draftParList;
	
	private String templateFiles; // 단일 파일
	private List<Long> templateFile; // 첨부파일 ID 리스트
	
	private List<FileDetailVO> fileDetails; // 파일 상세 정보 리스트
	
	
}
