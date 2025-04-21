package kr.or.ddit.mybatis.mappers.salary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.CustomRootContextConfig;
import kr.or.ddit.salary.vo.AllowanceCodeVO;
import kr.or.ddit.salary.vo.DeductionCodeVO;
import kr.or.ddit.salary.vo.SalaryVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CustomRootContextConfig
class SalaryMapperTest {

	@Autowired
	private SalaryMapper mapper;
	
	
	@Disabled
	@Test
	void 급여리스트조회() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>salaryList = mapper.salaryList();
//			assertNotEquals(0, salaryList);
		});
	}

	@Test
	void 급여상세조회() {
//		assertDoesNotThrow(()-> mapper.salarySelected("2023050907", 2025, 3));
	}
	

	@Test
	void 수당리스트조회() {
		assertDoesNotThrow(()->{
			List<AllowanceCodeVO>allowanceList = mapper.AllowanceList();
			assertNotEquals(0, allowanceList);
		});
	}

	@Test
	void 공제리스트조회() {
		assertDoesNotThrow(()->{
			List<DeductionCodeVO>deductionList = mapper.DeductionList();
			assertNotEquals(0, deductionList);
		});
	}

	@Test
	void 급여일괄등록() {
	 assertDoesNotThrow( ()-> mapper.InsertSalary(2025, 9) );
	}

	@Test
	void 공제등록() {
		assertDoesNotThrow( ()-> mapper.InsertDeducation(2025, 9) );
	}

	@Test
	void 수당등록() {
		assertDoesNotThrow( ()-> mapper.InsertAllowance(2025, 9) );
	}

	@Test
	void 공제액급여반영() {
		assertDoesNotThrow( ()-> mapper.deducationTotalUpdate(2025, 3) );
		
	}

	@Test
	void 수당액급여반영() {
		assertDoesNotThrow( ()-> mapper.allowanceTotalUpdate(2025, 3) );
	}

	@Test
	void 총지급액수정() {
		assertDoesNotThrow( ()-> mapper.salaryTotalAmountUpdate(2025, 3) );
	}

	@Test
	void 급여평균총액정보() {
		
		assertDoesNotThrow(()->{
//			SalaryVO info = mapper.salaryInfo();
//			assertNotEquals(0, info);
		});
	}
	

	@Test
	void 상단노출급여정보() {
		assertDoesNotThrow(()->{
			List<SalaryVO>salary = mapper.SalarySummaryByMonth();
			assertNotEquals(0, salary);
		});
	}
//
	@Test
	void 특정년월조회() {
//		assertDoesNotThrow(()->{
//			SalaryVO info = mapper.salarySelectedSummaryMonth(2025,3);
//		});
	}


	@Test
	void 부서평균급여() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>salaryDept = mapper.departMentAvgSalry(2025,3);
//			assertNotEquals(0, salaryDept);
		});
	}

	@Test
	void 직급별평균급여() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>salaryRank = mapper.rankAvgSalary(2025,3);
//			assertNotEquals(0, salaryRank);
		});
	}

	@Test
	void 기간별리스트() {
		assertDoesNotThrow(()-> mapper.getPayStatus("2025", (long) 3));
	}

	@Test
	void 확정미확정토글() {
		assertDoesNotThrow(()-> mapper.togglePayStatus("2025", (long) 3));
	}

	@Test
	void 기간별급여조회() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>getSalaryList = mapper.getSalaryList(2025,3);
//			assertNotEquals(0, getSalaryList);
		});
	}

	@Test
	void 급여확정된사원조회() {
		assertDoesNotThrow(()->{
//			List<SalaryVO>finalSalaryList = mapper.finalSalaryList();
//			assertNotEquals(0, finalSalaryList);
		});
		
	}

}
