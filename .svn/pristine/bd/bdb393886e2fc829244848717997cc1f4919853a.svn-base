package kr.or.ddit.approval.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.file.vo.FileDetailVO;
import lombok.Data;

/**
 * 기안템플릿양식
 */
@Data
public class DraftTemplateVO implements Serializable {
    
    @NotNull(message = "템플릿 ID는 필수입니다.")
    private Long templateId; // 기안양식 ID(PK)
    
    private List<Long> templateIds; // 기안양식 ID(PK) -> 여러개 삭제 시 사용 

    @NotBlank(message = "등록자 ID는 필수입니다.")
    private String empId; // 기안양식 등록자 ID

    @NotBlank(message = "양식 제목은 필수입니다.")
    @Size(max = 100, message = "양식 제목은 100자 이내여야 합니다.")
    private String templateTite; // 기안양식 제목

    @NotBlank(message = "양식 내용은 필수입니다.")
    private String templateContent; // 기안양식 내용

    @NotBlank(message = "카테고리는 필수입니다.")
    private String templateCategory; // 기안양식 카테고리

    @NotBlank(message = "삭제 여부는 필수입니다.")
    private String temlpateDeleted = "N"; // 기안양식 삭제 여부 (Y, N) 기본값: N

    @PastOrPresent(message = "등록일시는 현재 또는 과거여야 합니다.")
    private Date templateCreate; // 기안양식 최초 등록일시

    private String aprStatus; // 결재 상태

    private String templateFiles; // 단일 파일

    @NotEmpty(message = "첨부 파일이 최소 1개 이상 필요합니다.")
    private List<Long> templateFile; // 첨부파일 ID 리스트

    private List<FileDetailVO> fileDetails; // 파일 상세 정보 리스트
    private Long detailId; // 파일 상세 ID
    
    
    //insert에서 select로 TeamName을 뽑음
    private Long teamId;
    private String teamName;
    
    
    // 역할명을 가져옴
    private String roleName;
    
    // 등록자 정보 (1:1 관계)
    private EmployeeVO employee;

    // 기안 관리 정보 (1:N)
    private List<DraftManageMentVO> draftManageMentList;

    // 결재자 목록 (1:N)
    private List<DraftApproverVO> approverList;

    // 문서함 목록 (1:N)
    private List<DraftBoxVO> draftBoxList;

    // 참조자 목록 (1:N)
    private List<DraftParVO> draftParList;
}
