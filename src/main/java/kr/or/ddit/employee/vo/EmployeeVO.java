package kr.or.ddit.employee.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.annual.vo.AnnualHistoryVO;
import kr.or.ddit.annual.vo.AnnualVO;
import kr.or.ddit.attendance.vo.AttendanceVO;
import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.departmentboard.vo.DepartmentBoardVO;
import kr.or.ddit.document.vo.DocumentVO;
import kr.or.ddit.empcertificates.vo.EmpCertificateVO;
import kr.or.ddit.job.vo.JobVO;
import kr.or.ddit.position.vo.PositionVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.role.vo.RoleVO;
import kr.or.ddit.salary.vo.SalaryVO;
import kr.or.ddit.schedule.vo.ScheduleVO;
import kr.or.ddit.team.vo.TeamVO;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.workstatus.vo.WorkstautsVO;
import lombok.Data;
/**
 * 
 * 
 * @author 이진우
 * @since 2025. 3. 12.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 12.     	이진우	          최초 생성
 *  2025. 3. 12.     	정태우	          List 및 VO 매핑
 *  2025. 3. 20.     	이영준	          급여관계형성 추가
 *
 * </pre>
 */
import lombok.ToString;

@Data
public class EmployeeVO implements Serializable{
   
	@NotBlank(groups = UpdateGroup.class)
	@Size(min = 3, max = 11)
	private String empId;
	private Long departmentId;
	private Long jobId;
	private Long positionId;
	private Long rankId;
	@NotBlank(groups = InsertGroup.class)
	private String name;
	@NotBlank(groups = InsertGroup.class,message = "입사일자는 필수입니다." )
	private String hireDate;
	private Long salary;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String address;
	@NotBlank
	private String addressDetail;
	private String finalLevel;
	private String phoneNumber;
	@Size(min = 6, max = 6, groups = InsertGroup.class)
	private String juminFront;
	@Size(min = 7, max = 7, groups = InsertGroup.class)
	private String juminBack;
	private String empStatus;
	
	private int tenureYear;
	
	
	private String departmentName;
	private String jobName;
	private String rankName;
	private Long teamId;
	private String teamName;
	
	private int empCount;
	private int resignCount;
	private int hireCount;
	
	
	private DepartmentVO department;
	private TeamVO team;
	private PositionVO position;
	private JobVO job;
	private RankVO rank;
	private AccountVO account;
	private WorkstautsVO Workstauts;
	private RoleVO role;
	
	private List<AttendanceVO> attendanceList;
	private List<EmpCertificateVO> empCertificate;
	private List<DocumentVO> document;
	private List<ScheduleVO> schedule;
	private List<AnnualHistoryVO> annualHistory;
	private List<DepartmentBoardVO> departmentBoard;
	
	//25.3.20 영준추가
	private SalaryVO salaryDetial; //급여상세
	private List<SalaryVO>salaryList;//급여전체조회

	//25.4.10 영규추가 
	// 퇴사,휴직 관련 필드 추가
	private String retireDate;     // 퇴사일
	private String retireReason;   // 퇴사 사유
	private String leaveStartDate; // 휴직 시작일
	private String leaveEndDate;   // 휴직 종료일
	private String leaveReason;    // 휴직 사유
	@ToString.Exclude
	private Long empImg;
	// 실제 이미지 저장 경로
	private String empPath;
}
