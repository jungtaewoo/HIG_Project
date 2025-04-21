package kr.or.ddit.account.service;

import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.account.exception.UserNotFoundException;
import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.passwordrest.vo.PasswordResetVO;

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
 *  2025. 3. 11.        youngjun             최초 생성
 *  2025. 3. 16.        youngjun         직원계정 수정, 직원테이블과 컬럼엮여있어 동시업데이트 진행
 *  2025. 3. 21.            kyu         비밀번호 재설정 
 *
 * </pre>
 */
public interface AccountService {
   
   /**
    * 계정리스트 전체조회
    * @return
    */
   public List<AccountVO>accountList();
   
   
   /**
    * 계정상세조회
    * @param accountId
    * @return
    */
   public AccountVO readAccount(String accountId)throws UserNotFoundException;
   
   /**
    * 계정등록메서드
    * @param account
    * @return
    */
   public boolean accountInsert(AccountVO account);
   
   /**
    * 계정 상태 인증 (id,비밀번호정보)
    * @param account
    * @return
    * @throws AuthenticationException
    */
//   public AccountVO authenticate(AccountVO account) throws AuthenticationException;
   
   
   /**
    * 계정에 미등록된 신규사원 조회 메서드(직원등록은 선행되야함 employee)
    * @return
    */
   public List<AccountVO>unRegisterAccountList();

   /**
    * 직원에 등록되지 않은 직원 선택
    * @param empId
    * @return
    */
   public AccountVO selectedUnAccount(String empId);
   
   /**
    *  직원의 계정수정, 직원정보 테이블과 엮여있어 동시진행
    * @param account
    * @param employee
    * @return
    */
   public boolean updateAccount(AccountVO account);
   
   /**
    *  직원의 계정수정, 직원정보 테이블과 엮여있어 동시진행
    * @param account
    * @param employee
    * @return
    */
   public boolean updateAccountEmp(EmployeeVO employee);
   
   
   //비밀번호 재설정 토큰 생성 및 저장(생성된 토큰 리턴함)
   public String insertPasswordReset(String accountId);
   
   //토큰으로 정보 가져오기
   public PasswordResetVO selectPasswordReset(String token);
   
   //사용한 토큰 삭제(true: 성공)
   public boolean deletePasswordReset(String token);
   
   //만료된 토큰 정리
   public int cleanUpExpirer();
   
   //비밀번호 재설정(토큰 확인후 변경)
   public boolean resetPassword(String token, String newPassword);
   
   //사원의 이메일 
   public String accountfindEmail(String accountId);
   
   //사원계정찾기 이름과 주민번호 앞자리
   public AccountVO findAccountId(@Param("empName") String empName, @Param("juminFront")String juminFront);
   
   //비밀번호 '1234'비교
   public boolean isInitialPassword(String accountId);
   
   public List<AccountVO> accountRoleCount();
   
   /**
    * 퇴사계정관리
    * @return
    */
   public List<AccountVO>retireAccount();
   

	/**
	 * 퇴사계정수
	 * @return
	 */
	public int disableAccountCount(); 
	
	
	/**
	 * 계정상태 확인
	 * @param accountId
	 * @return
	 */
	public AccountVO readStatusAccount(String accountId,String accountStatus);
	
	/**
	 * 계정활성화/비활성화 토글
	 * @param accountId
	 * @return
	 */
	public int toggleAccountStatus(String accountId);

   
}
