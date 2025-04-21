package kr.or.ddit.job.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JobVO implements Serializable{
	@NotBlank
	private String jobId;
	@NotBlank
	private String jobName;
	private String jobStatus;

}
