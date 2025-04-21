package kr.or.ddit.application.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.recruitment.vo.RecruitmentVO;
import lombok.Data;

@Data
public class ApplicationVO implements Serializable{
	@NotNull
	private Long appId;
	@NotNull
	private Long recruitId;
	@NotBlank
	private String appName;
	@NotNull
	private Long appYeardate;
	@NotBlank
	private String appEmail;
	@NotBlank
	private String appGrade;
	@NotBlank
	private String appCareer;
	@NotBlank
	private String appPl;
	private String appFile;
	@NotBlank
	private String appGender;
	
	private ApplicationStatusVO applicationStatus;
	private RecruitmentVO recruitment;
	
	
}
