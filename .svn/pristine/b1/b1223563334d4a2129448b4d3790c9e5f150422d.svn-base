package kr.or.ddit.salary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.salary.service.SalaryService;
import kr.or.ddit.salary.vo.SalaryVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SalaryRegisterController {

	@Autowired
	private SalaryService service;
	
	@GetMapping("/salary/insert/form")
	public String RegisterForm(Model model) {
		List<SalaryVO>salarySummary = service.SalarySummaryByMonth();
		model.addAttribute("salarySummary", salarySummary);
		return"tiles:salary/salaryList";
	}
	
//	급여미리보기
	@GetMapping("/salary/insert/ex/{payYear}/{payMonth}")
	public String exRegister(
			 Model model
			,@PathVariable("payYear")int payYear
			,@PathVariable("payMonth")int payMonth
			){
		
	    // 전월 계산
	    int adjustedYear = (payMonth == 1) ? (payYear - 1) : payYear;
	    int adjustedMonth = (payMonth == 1) ? 12 : (payMonth - 1);
	    
	    SalaryVO salaryEx = service.salaryInsertEx(adjustedYear, adjustedMonth);
	    model.addAttribute("salaryEx", salaryEx);
	    
		log.info("급여미리보기 모달");
		log.info("급여미리보기 모달{}{}",adjustedYear,adjustedMonth);
		model.addAttribute("salaryEx", salaryEx);
		return"salary/salaryEx";
	}
	
	//최종급여등록
//	@Transactional
//	@PostMapping("/salary/insert/register")
//	public String Register(Model model, SalaryVO salary, DeductionVO deducation
//			,@RequestParam("payYear")int payYear, @RequestParam("payMonth")int payMonth
//			){
//		
//		System.out.println("ewrewkrjewkjrkewjrklewjrklewjkrjewkrjewkrj~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//		    log.info("급여 등록 시작");
//		    service.InsertSalary(payYear,payMonth);  // 급여등록
//		    log.info("공제 등록 시작");
//		    service.InsertDeducation(payYear, payMonth);
//		    log.info("수당 등록 시작");
//		    service.InsertAllowance(payYear, payMonth);
//
//		    log.info("수당 총액 반영 시작");
//		    service.allowanceTotalUpdate(payYear, payMonth);
//		    log.info("공제 총액 반영 시작");
//		    service.deducationTotalUpdate(payYear, payMonth);
//		    log.info("실지급액/총지급액 반영 시작");
//		    service.salaryTotalAmountUpdate(payYear, payMonth);
//
//		    log.info("전체 급여 등록 완료");
//			return "redirect:/salary/list";
//
//	}
	
	@PostMapping("/salary/insert/register")
	@ResponseBody
	public ResponseEntity<String> registerSalary(@RequestParam("payYear") int payYear,
	                                             @RequestParam("payMonth") int payMonth) {
	    try {
	        log.info(" 급여 등록 시작");
	        service.InsertSalary(payYear, payMonth);
	        service.InsertDeducation(payYear, payMonth);
	        service.InsertAllowance(payYear, payMonth);
	        service.allowanceTotalUpdate(payYear, payMonth);
	        service.deducationTotalUpdate(payYear, payMonth);
	        service.salaryTotalAmountUpdate(payYear, payMonth);
	        log.info(" 전체 급여 등록 완료");

	        return ResponseEntity.ok("급여 등록이 완료되었습니다.");
	    } catch (Exception e) {
	        log.error(" 급여 등록 오류", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("급여 등록 중 오류가 발생했습니다.");
	    }
	}

}
