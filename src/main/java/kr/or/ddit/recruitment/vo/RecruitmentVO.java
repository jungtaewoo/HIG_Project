package kr.or.ddit.recruitment.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.position.vo.PositionVO;
import lombok.Data;

@Data
public class RecruitmentVO implements Serializable{

	private Long recruitId;
	private String recruitTitle;
	private String recruitWorkplace;
	private String recruitHiretype;
	private Long recruitSalary;
	private String recruitWorkdetail;
	private String recruitPq;
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 날짜 형식 지정
	private Date recruitEnddate;
	private String recruitContact;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 날짜 형식 지정
	private Date recruitStartdate;
	private String recruitPosition;
	private int recruitHirenum;
	
	private int applicantCount; // 지원자 수
	private int waitingCount;   // 대기자 수
	private int passCount;      // 합격자 수
}
