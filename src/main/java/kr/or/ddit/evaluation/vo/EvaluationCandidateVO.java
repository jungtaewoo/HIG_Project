package kr.or.ddit.evaluation.vo;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.department.vo.DepartmentVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.rank.vo.RankVO;
import kr.or.ddit.team.vo.TeamVO;
import lombok.Data;

@Data
public class EvaluationCandidateVO {

	@NotBlank(message = "사번은 필수입니다.")
	private String empId;

	@NotBlank(message = "평가 연도는 필수입니다.")
	private String evaluationYear;

	@NotBlank(message = "상/하반기 구분은 필수입니다.")
	private String halfYear;
	
    @NotBlank(message = "평가 대상 여부를 선택해주세요.")
	private String isTarget;
	private String evaluationStatus;
	private String evaluatorId;
	private String evaluatorName;

	private Long jobId;
	private Long teamId;
	private Long departmentId;

	private EmployeeVO employee;
	private DepartmentVO department;
	private TeamVO team;
	private RankVO rank;

	private boolean selected = false;

	public boolean isSelected() {
	    return selected;
	}
	public void setSelected(boolean selected) {
	    this.selected = selected;
	}


	private String name;
	private String teamName;
	private String rankName;
	private String departmentName;
	private String jobName;
	

}
