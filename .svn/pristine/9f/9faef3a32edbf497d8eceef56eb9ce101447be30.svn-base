package kr.or.ddit.account.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.mybatis.mappers.account.AccountMapper;
import kr.or.ddit.mybatis.mappers.employee.EmployeeMapper;
import kr.or.ddit.mybatis.mappers.messenger.chatempMapper;
import kr.or.ddit.passwordrest.vo.PasswordResetVO;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author youngjun
 * @since 2025. 3. 16.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자           수정내용
 *  -----------      -------------    ---------------------------
 *  2025. 3. 11.        youngjun   최초생성
 *  2025. 3. 11.        youngjun   직원의 계정수정, 직원정보 테이블과 엮여있어 동시진행
 *
 * </pre>
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;
	
   @Autowired
   private AccountMapper mapper;
   @Autowired
   private chatempMapper chatMapper;
   @Autowired
   private EmployeeMapper empMapper;
   
    @Autowired
   private AuthenticationManagerBuilder builder;
    
    @Autowired
    private PasswordEncoder encoder;

   @Override
   public List<AccountVO> accountList() {
      return mapper.accountList();
   }

   @Override
   @Transactional
   public boolean accountInsert(AccountVO account) {
	   // 초기 비밀번호 "1234" 암호화
	   String encodedPw = passwordEncoder.encode("1234");
	   account.setPassword(encodedPw);
	   
	    int result1 = mapper.accountInsert(account);
	    int result2 = mapper.chatempInsert(account);
	    

	    return result1 > 0 && result2 > 0;
   }
   
   @Override
   public AccountVO readAccount(String accountId) {
	   System.out.println("로그인한 사용자 확인(serviceImpl)" + accountId);
      return mapper.selectAccount(accountId);
   }
   
   @Override
   public List<AccountVO> unRegisterAccountList() {
      return mapper.findUnregisteredAccountList();
   }
   

   @Override
   public AccountVO selectedUnAccount(String empId) {
      return mapper.selectedUnAcount(empId);
   }

   @Override
   public boolean updateAccount(AccountVO account) {
      
//      boolean empEdit = mapper.updateAccountEmp(employee);
      
//      if(empEdit) {
//         return mapper.updateAccount(account);
//      }
      
      return mapper.updateAccount(account);
   }
   
   @Override
   public boolean updateAccountEmp(EmployeeVO employee) {
   	return mapper.updateAccountEmp(employee);
   }

   @Override
   public String insertPasswordReset(String accountId) {
      String token = UUID.randomUUID().toString();
       Date expiration =  Date.from(LocalDateTime.now().plusMinutes(5)
               .atZone(ZoneId.systemDefault())
               .toInstant());
       PasswordResetVO resetVO =new PasswordResetVO(token, accountId, expiration); 
       mapper.insertPasswordReset(resetVO);
      return token;
   }

   @Override
   public PasswordResetVO selectPasswordReset(String token) {      
      return mapper.selectPasswordReset(token);
   }

   @Override
   public boolean deletePasswordReset(String token) {
      
      return mapper.deletePasswordReset(token)>0;
   }

   @Override
   public int cleanUpExpirer() {
      return mapper.deleteExpired(new Date());
   }

   @Override
   public boolean resetPassword(String token, String newPassword) {
      PasswordResetVO resetVO = mapper.selectPasswordReset(token);
      if(resetVO == null || resetVO.getExpirationDate().before(new Date())) {
         return false;
      }
      
      String encodedPw = passwordEncoder.encode(newPassword);
      mapper.updateAccountPassword(resetVO.getAccountId(), encodedPw);
      mapper.deletePasswordReset(token);
      
      return true;
   }

   @Override
   public String accountfindEmail(String accountId) {
      return mapper.accountfindEmail(accountId);
   }

@Override
public AccountVO findAccountId(String empName, String juminFront) {
	return mapper.findAccountId(empName, juminFront);
}

@Override
public boolean isInitialPassword(String accountId) {
    AccountVO account = mapper.selectAccount(accountId);
    if (account == null) return false;
    return passwordEncoder.matches("1234", account.getPassword());
}

@Override
public List<AccountVO> accountRoleCount() {
	return mapper.accountRoleCount();
}

@Override
public List<AccountVO> retireAccount() {
	return mapper.retireAccount();
}

@Override
public int disableAccountCount() {
	return mapper.disableAccountCount();
}

@Override
public AccountVO readStatusAccount(String accountId,String accountStatus) {
	return mapper.readStatusAccount(accountId,accountStatus);
}

@Override
public int toggleAccountStatus(String accountId) {
	return mapper.toggleAccountStatus(accountId);
}


}
