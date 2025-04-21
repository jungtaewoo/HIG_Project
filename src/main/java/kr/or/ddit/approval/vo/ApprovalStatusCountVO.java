package kr.or.ddit.approval.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ApprovalStatusCountVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userStatus; // ex) 대기, 진행중, 승인중, 반려
	private int cnt; // 차트용 결재 관련 건수 
}
