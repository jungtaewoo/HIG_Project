package kr.or.ddit.rank.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RankVO implements Serializable{
	@NotBlank
	private String rankId;
	@NotBlank
	private String rankName;
	private String rankStatus;
}
