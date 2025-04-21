package kr.or.ddit.document.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DocumentVO implements Serializable{
	private Long boxId;
	private Long draftId;
	private String empId;
	private String approverId;
	private String docTitle;
	private String docStatus;
	private String docCategory;
	private String docType;
	private Date lastUpdate;
	private Date createDate;
	private Long docFile;
	
	private String empName;  // 기안자 이름
	private String approverName;  // 결재자 이름
}
