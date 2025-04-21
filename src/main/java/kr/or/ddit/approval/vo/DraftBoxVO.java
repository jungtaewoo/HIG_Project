package kr.or.ddit.approval.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.file.vo.FileDetailVO;
import lombok.Data;

/**
 * 문서함 
 */
@Data
public class DraftBoxVO implements Serializable{
	@NotNull
	private Long boxId; // 결재 문서함 ID(PK)
	@NotNull
	private Long draftId; // 기안관리ID
	@NotBlank
	private String empId; // 직원 정보를 저장하는 테이블
	@NotBlank
	private String approverId; // 현재 결재자 ID
	@NotBlank
	private String docTitle; //문서 제목
	@NotBlank
	private String docStatus; // 문서 상태(승인, 반려, 보류)
	@NotBlank
	private String docCategory; // 문서함 유형 메모참조
	private String docType; // 문서함의 유형
	private String lastUpdate; // 최종 업데이트 날짜
	private String createDate; // 문서 등록일
	private String docFile; // 문서함 파일 처리
	
	private List<Long> docFiles; // 첨부파일 ID 리스트
	
	private List<FileDetailVO> fileDetails; // 파일 상세 정보 리스트
	private Long detailId; // 파일 상세 ID
}
