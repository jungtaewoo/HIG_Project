package kr.or.ddit.empresignations.exception;

public class ResignNotFoundException extends RuntimeException {
	
	public ResignNotFoundException(String leaveId) {
		super(String.format("%s 퇴사기록이 존재하지 않습니다.", leaveId));
	}
}
