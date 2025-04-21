package kr.or.ddit.emprecordcards.exception;

public class EmpCardNotFoundException extends RuntimeException {
	
	public EmpCardNotFoundException(String cardId) {
		super(String.format("%s 인사기록이 존재하지 않습니다.", cardId));
	}
}
