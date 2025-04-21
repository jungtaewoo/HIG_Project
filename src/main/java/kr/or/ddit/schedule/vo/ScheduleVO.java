package kr.or.ddit.schedule.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ScheduleVO implements Serializable{
	@NotNull
	private Long scheduleId;
	@NotBlank
	private String empId;
	@NotBlank
	private String scheduleTitle;
	@NotBlank
	private String scheduleContext;
	@NotBlank
	private String startDate;
	@NotBlank
	private String endDate;
	@NotBlank
	private String scheduleVisibility;
	private String colorCode;
	
	private String empName;
}
