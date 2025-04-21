package kr.or.ddit.departmentboard.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringExclude;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.file.vo.FileDetailVO;
import lombok.Data;

@Data
public class DepartmentBoardVO implements Serializable{
  // 부서 게시판을 위한 VO
  
  @ToStringExclude
  @NotNull
  private Long deptnoticeId; // 부서게시판 고유 식별자
  @NotNull
  private Long departmentId; // 부서ID
  @NotBlank
  private String title; // 제목
  @NotBlank
  private String content; // 내용
  @NotBlank
  private String author; // 작성자
  
  private Long viewCount; // 조회수
  
  private String importance; // 게시글 중요도
  
  private Long deptFile; // 첨부파일
  private List<Long> deptFiles; // 첨부파일들
  private String oneFile;
  
  
  private Date noticeDate; // 작성일자
  
  private DepartmentVO department;
  
  private AccountVO account;
  
  private List<FileDetailVO> fileDetails; // 파일 상세 정보 리스트
  private Long detailId; // 파일 상세 ID
  
}
