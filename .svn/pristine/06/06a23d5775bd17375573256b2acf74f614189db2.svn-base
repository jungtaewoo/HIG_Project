package kr.or.ddit.mybatis.mappers.account;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.employee.vo.EmployeeVO;
import kr.or.ddit.passwordrest.vo.PasswordResetVO;

/**
 * 
 * @author youngjun
 * @since 2025. 3. 12.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자           수정내용
 *  -----------      -------------    ---------------------------
 *  2025. 3. 10.        youngjun         최초 생성
 *  2025. 3. 12.        youngjun         EMPLOYEE(직원등록 기입력된 데이터조회), 아직 계정등록안된 직원만 불러옴
 *  2025. 3. 16.        youngjun         직원계정 수정, 직원테이블과 컬럼엮여있어 동시업데이트 진행
 *  2025. 3. 21.        kyu                 비밀번호 재설정 기능 추가
 * </pre>
 */
@Mapper
public interface AccountMapper {
   
   /**
    * 직원 계정리스트 반환 
    * @return
    */
   public List<AccountVO>accountList();
   /**
    * 계정상세조회
    * @param accountId
    * @return
    */
   public AccountVO selectAccount(@Param("accountId") String accountId);
   
   /**
    * 계정등록 메서드 (직원등록 선행되며, 직원등록에 저장된 데이터 활용)
    * @param account
    * @return
    */
   public int accountInsert(AccountVO account);
   
   /**
    * 계정에 미등록된 신규사원 조회 메서드(직원등록은 선행되야함 employee)
    * @return
    */
   public List<AccountVO>findUnregisteredAccountList();

   /**
    * 계정에 등록되어 있지 않은 직원 선택
    * @param empId
    * @return
    */
   public AccountVO selectedUnAcount(String empId);
   
   
   /**
    * 직원의 계정수정, 직원정보 테이블과 엮여있어 동시진행
    * @param account
    * @param employee
    * @return
    */
   public boolean updateAccount(AccountVO account);
   
   /**
    * 직원의 계정수정, 직원테이블과 엮여 동시진행 (updateAccount와)
    * @return
    */
   public boolean updateAccountEmp(EmployeeVO employee);
   
   // 비밀번호 재설정 토큰 저장
   public int insertPasswordReset(PasswordResetVO token);
   
   // 토큰 조회
   public PasswordResetVO selectPasswordReset(String token);
   
   // 사용한 토큰 삭제
   public int deletePasswordReset(String token);
   
   // 만료된 토큰 삭제
   public int deleteExpired(@Param("now")Date now);
   
   // 비밀번호 변경용 
   public int updateAccountPassword(@Param("accountId") String accountId, @Param("password") String password);
   
   public String accountfindEmail(String accountId);
   
   public int chatempInsert(AccountVO account);
   
   /**
    * 계정권한별 인원수
 * @param account
 * @return
 */
public List<AccountVO> accountRoleCount();
   
   /**
    * 사번조회, 이름 주민번호 앞자리
 * @param empName
 * @param juminFront
 * @return
 */
public AccountVO findAccountId(@Param("empName") String empName, @Param("juminFront")String juminFront);

/**
 * 퇴사계정수
 * @return
 */
public int disableAccountCount(); 

/**
 * 퇴사계정관리
 * @return
 */
public List<AccountVO>retireAccount();

/**
 * 계정상태 확인
 * @param accountId
 * @return
 */
public AccountVO readStatusAccount(@Param("accountId")String accountId, @Param("accountStatus")String accountStatus );


/**
 * 계정활성화/비활성화 토글
 * @param accountId
 * @return
 */
public int toggleAccountStatus(String accountId);

/**
 * 계정비활성처리
 * @param accountId
 * @return
 */
public int retireAccountUpdate(String accountId);
}
