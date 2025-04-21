package kr.or.ddit.certificate.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CertificateVO implements Serializable{
	@NotNull
	private Long certificateId;
	@NotBlank
	private String certificateName;
	@NotBlank
	private String issuedBy;
	@NotBlank
	private String issueDate;
	private String expirationDate;
	private String certificateImg;
}
