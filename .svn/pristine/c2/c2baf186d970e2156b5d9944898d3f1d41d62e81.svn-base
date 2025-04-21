package kr.or.ddit.salary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mybatis.mappers.salary.SalaryMapper;
import kr.or.ddit.salary.vo.AllowanceCodeVO;
import kr.or.ddit.salary.vo.DeductionCodeVO;
import kr.or.ddit.salary.vo.SalaryVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private SalaryMapper mapper;

	@Override
	public List<SalaryVO> salaryList(String payYear, String payMonth) {
		return mapper.salaryList(payYear, payMonth);
	}

	@Override
	public SalaryVO salarySelected(String empId, String payYear, String payMonth) {
		return mapper.salarySelected(empId, payYear, payMonth);
	}

	@Override
	public List<AllowanceCodeVO> AllowanceList() {
		return mapper.AllowanceList();
	}

	@Override
	public List<DeductionCodeVO> DeductionList() {
		return mapper.DeductionList();
	}

	@Override
	public boolean InsertSalary(int payYear, int payMonth) {
		return mapper.InsertSalary(payYear, payMonth) > 0;
	}

	@Override
	public boolean InsertDeducation(int payYear, int payMonth) {
		return mapper.InsertDeducation(payYear, payMonth) > 0;
	}

	@Override
	public SalaryVO salaryInfo(String payYear, String payMonth) {
		
		  if (payYear == null || payMonth == null) {
		        LocalDate now = LocalDate.now();
		        payYear = String.valueOf(now.getYear());
		        payMonth = String.format("%02d", now.getMonthValue());
		    }

		log.info("체크 {} {}", payYear, payMonth);
//	    int monthInt = Integer.parseInt(payMonth); // 
		String dateSelect = String.format("%s-%s-01", payYear, payMonth);
		log.info("체킁2 {}", dateSelect);
		return mapper.salaryInfo(payYear, payMonth, dateSelect); // 순서도 올바름
	}

	@Override
	public int InsertAllowance(int payYear, int payMonth) {

//		String date = String.format("%s-%02d-01", payYear, payMonth);
//		log.debug("String.format : {}", date);
//		log.info("String.format : {}", date);

		return mapper.InsertAllowance(payYear, payMonth);
	}

	@Override
	public int deducationTotalUpdate(int payYear, int payMonth) {
		return mapper.deducationTotalUpdate(payYear, payMonth);
	}

	@Override
	public int allowanceTotalUpdate(int payYear, int payMonth) {
		return mapper.allowanceTotalUpdate(payYear, payMonth);
	}

	@Override
	public int salaryTotalAmountUpdate(int payYear, int payMonth) {
		return mapper.salaryTotalAmountUpdate(payYear, payMonth);
	}

	@Override
	public List<SalaryVO> SalarySummaryByMonth() {
		return mapper.SalarySummaryByMonth();
	}

	@Override
	public SalaryVO salaryInsertEx(int payYear, int payMonth) {
		return mapper.salaryInsertEx(payYear, payMonth);
	}

	@Override
	public List<SalaryVO> departMentAvgSalry(String payYear, String payMonth) {
		return mapper.departMentAvgSalry(payYear, payMonth);
	}

	@Override
	public List<SalaryVO> rankAvgSalary(String payYear, String payMonth) {
		return mapper.rankAvgSalary(payYear, payMonth);
	}

	@Override
	public Map<String, String> getPayStatus(String empId, Long salaryId) {
		return mapper.getPayStatus(empId, salaryId);
	}

	@Override
	public int togglePayStatus(String empId, Long salaryId) {
		return mapper.togglePayStatus(empId,salaryId);
	}
	
	@Override
	public int togglePayStatusAll(List<Long>salaryIdList,String payYear, String payMonth) {
		return mapper.togglePayStatusAll(salaryIdList, payYear,  payMonth);
	}

	@Override
	public SalaryVO salarySelectedSummaryMonth(String payYear, String payMonth) {
		return mapper.salarySelectedSummaryMonth(payYear, payMonth);
	}

//	@Override
//	public List<SalaryVO> getSalaryList(int payYear, int payMonth) {
//		return mapper.getSalaryList(payYear, payMonth);
//	}

//	@Override
//	public List<SalaryVO> finalSalaryList() {
//		return mapper.finalSalaryList();
//	}

   /**
    *이체요청 토글 (요청완료<->요청대기)
    */
   @Override
   public int togglePayRequest(String empId, Long salaryId) {
       int result = mapper.togglePayRequest(empId, salaryId);
       Map<String, String> status = mapper.getPayStatus(empId, salaryId);
       log.info("paymentRequest 상태 확인상단: {}", status.get("PAYMENT_REQUEST"));
       log.info("payStatus 상태 확인상단: {}", status.get("PAY_STATUS"));
       log.info("승인 처리 서비스 상단");
       
       // mapper에서 '승인'으로 상태가 바뀐 사원일 경우만 후속 처리
       if ("승인".equals(status.get("PAYMENT_REQUEST"))) {
           log.info("승인상태, 3초후 지급완료 처리");

           new Thread(() -> {
               try {
                   Thread.sleep(3000);
                   mapper.salaryPaid(empId, salaryId); // 이 메서드는 파라미터 받도록 수정 필요
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }).start();
       }
       log.info("paymentRequest 상태 확인하단: {}", status.get("PAYMENT_REQUEST"));
       log.info("payStatus 상태 확인하단: {}", status.get("PAY_STATUS"));
       return result;
   }


	@Override
	public List<SalaryVO> salarySendEmailConfirm(String payYear, String payMonth) {
		return mapper.salarySendEmailConfirm(payYear, payMonth);
	}

	@Override
	public List<Map<String, Object>> MinMaxSalaryByDept() {
		return mapper.MinMaxSalaryByDept();
	}

	@Override
	public int salaryPaid(String empId, Long salaryId) {
		return mapper.salaryPaid(empId, salaryId);
	}

	/**
	 *일괄 승인요청 토글ㄴ
	 */
	@Override
	public int togglePayRequestAll(List<SalaryVO> salaryIdList, String payYear, String payMonth) {
	    int result = mapper.togglePayRequestAll(salaryIdList, payYear, payMonth);

	    // 승인된 건 전체를 한 번에 지급완료 처리
	    mapper.updateSalaryPaidBatch(salaryIdList);

	    return result;
	}

}
