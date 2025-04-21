package kr.or.ddit.passwordrest.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetVO implements Serializable{
   
   @NotBlank
   private String token;
   @NotBlank
   private String accountId;
   @NotBlank
   private Date expirationDate;

}