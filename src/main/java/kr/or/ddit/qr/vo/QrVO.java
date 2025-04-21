package kr.or.ddit.qr.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class QrVO implements Serializable{
	
	@NotBlank
	private String token;
	@NotBlank
	private String empId;
	private String createdAt;
	@NotBlank
	private String expiresAt;
}
