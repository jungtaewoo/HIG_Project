package kr.or.ddit.salary.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.CustomRootContextConfig;
import kr.or.ddit.salary.vo.AllowanceCodeVO;
import kr.or.ddit.salary.vo.DeductionCodeVO;
import kr.or.ddit.salary.vo.SalaryVO;

@CustomRootContextConfig
class SalaryServiceTest {
	
	@Autowired
	private SalaryService service;

	@Disabled
	@Test
	void 급여리스트조회() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>salaryList = service.salaryList();
//			assertNotEquals(0, salaryList);
		});
	}

	@Test
	void 급여상세조회() {
//		assertDoesNotThrow(()-> service.salarySelected("2023050907", 2025, 4));
	}

	@Test
	void 수당리스트조회() {
		assertDoesNotThrow(()->{
			List<AllowanceCodeVO>allowanceList = service.AllowanceList();
			assertNotEquals(0, allowanceList);
		});
	}

	@Test
	void 공제리스트조회() {
		assertDoesNotThrow(()->{
			List<DeductionCodeVO>deductionList = service.DeductionList();
			assertNotEquals(0, deductionList);
		});
	}

	@Test
	void 급여일괄등록() {
	 assertDoesNotThrow( ()-> service.InsertSalary(2025, 9) );
	}

	@Test
	void 공제등록() {
		assertDoesNotThrow( ()-> service.InsertDeducation(2025, 9) );
	}

	@Test
	void 수당등록() {
		assertDoesNotThrow( ()-> service.InsertAllowance(2025, 9) );
	}

	@Test
	void 공제액급여반영() {
		assertDoesNotThrow( ()-> service.deducationTotalUpdate(2025, 3) );
		
	}

	@Test
	void 수당액급여반영() {
		assertDoesNotThrow( ()-> service.allowanceTotalUpdate(2025, 3) );
	}

	@Test
	void 총지급액수정() {
		assertDoesNotThrow( ()-> service.salaryTotalAmountUpdate(2025, 3) );
	}

	@Test
	void 급여평균총액정보() {
//		assertDoesNotThrow(()->{
//			SalaryVO info = service.salaryInfo();
//			assertNotEquals(0, info);
//		});
	}
	

	@Test
	void 상단노출급여정보() {
		assertDoesNotThrow(()->{
			List<SalaryVO>salary = service.SalarySummaryByMonth();
			assertNotEquals(0, salary);
		});
	}

	@Test
	void 특정년월조회() {
//		assertDoesNotThrow(()->{
//			SalaryVO info = service.salarySelectedSummaryMonth(2025,3);
//		});
	}


	@Test
	void 부서평균급여() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>salaryDept = service.departMentAvgSalry(2025,3);
//			assertNotEquals(0, salaryDept);
		});
	}

	@Test
	void 직급별평균급여() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>salaryRank = service.rankAvgSalary(2025,3);
//			assertNotEquals(0, salaryRank);
		});
	}

	@Test
	void 기간별리스트() {
		assertDoesNotThrow(()-> service.getPayStatus("2025", (long) 3));
	}

	@Test
	void 확정미확정토글() {
		assertDoesNotThrow(()-> service.togglePayStatus("2025", (long) 3));
	}

	@Test
	void 기간별급여조회() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>getSalaryList = service.getSalaryList(2025,3);
//			assertNotEquals(0, getSalaryList);
		});
	}

	@Test
	void 급여확정된사원조회() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>finalSalaryList = service.finalSalaryList();
//			assertNotEquals(0, finalSalaryList);
		});
		
	}

}
