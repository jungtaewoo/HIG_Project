package kr.or.ddit.employee.exception;

public class EmpNotFoundException extends RuntimeException {
	
	public EmpNotFoundException(String empId) {
		super(String.format("%s 사원이 존재하지 않습니다.", empId));
	}
}
