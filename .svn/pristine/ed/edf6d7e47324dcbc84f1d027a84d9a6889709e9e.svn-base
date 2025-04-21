package kr.or.ddit.salary.controller;

import java.time.chrono.JapaneseEra;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.salary.service.SalaryService;
import kr.or.ddit.salary.vo.SalaryVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SalaryUpdateController {

	@Autowired
	private SalaryService service;

//	@Transactional
//	@PostMapping("/salary/update/total")
//	public String totalSalaryUpdate(@RequestParam("pyear")int pyear, @RequestParam("pmonth")int pmonth
//			,@ModelAttribute("salary")SalaryVO salary
//			){
//		
//		return "redirect:/salary/list";
//	}

	// 확정인원 카운트 년,월
//	@GetMapping("/salary/finalConfirmCount")
//	@ResponseBody
//	public SalaryVO getConfirmCount() {
//		return service.salaryInfo();
//	}

//	// 급여상세에서 버튼 활성화
//	@PostMapping("/edit")
//	public String slaryUpdate() {
//		return "salary/salarDetail";
//	}
	
	

	// 급여리스트에서 클릭 기존코드
	@PostMapping("/salary/final")
	@ResponseBody
	public Map<String , String> salaryFinal(@RequestParam("empId") String empId,
			@RequestParam("salaryId") Long salaryId
			,@RequestParam(value="payYear",required = false) String payYear
			,@RequestParam(value="payMonth",required = false) String payMonth
			){
//		List<Long>salaryIdList = Collections.singletonList(salaryId);
		
		int cnt = service.togglePayStatus(empId,salaryId);
		
		SalaryVO salaryInfo= service.salaryInfo(payYear,payMonth); // 확정인원
		
		Map<String, String> finalMapDate = service.getPayStatus(empId, salaryId);//확정일
		String finalDate = finalMapDate.get("CONFIRMDATE");
		
		Map<String, String> result = new HashMap<>();
		result.put("cnt",cnt+""); 
		result.put("finalCount",salaryInfo.getConfirmedCount()+""); //확정인원 섬머리부분
		result.put("PayStatus",salaryInfo.getPayStatus()); //확정상태값 (확정/확정대기)
		result.put("finalDate",finalDate);
		return result;
	}
	
	@PostMapping("/salary/final/all")
	@ResponseBody
	public Map<String, Object> finalAll(@RequestBody Map<String, Object> param
			,@RequestParam(value="payYear",required = false) String  payYear
			,@RequestParam(value="payMonth",required = false) String  payMonth
			){
		
		log.info("일괄확정 컨털~~:{}{}{}", param,payYear, payMonth);
	    List<Map<String, String>> salaryList = (List<Map<String, String>>) param.get("salaryList");
	    List<Long>salaryIdList = salaryList.stream()
	    		.map(item -> Long.parseLong(String.valueOf(item.get("salaryId"))))
	    		.collect(Collectors.toList());
		
		
	     payYear = String.valueOf(param.get("payYear"));
	     payMonth = String.valueOf(param.get("payMonth"));

	    int count =  service.togglePayStatusAll(salaryIdList, payYear, payMonth);
	    log.info("일괄확정 컨털2~~:{}{}{}", param,payYear, payMonth);
	    

//	    for (Map<String, String> item : salaryList) {
//	        String empId = item.get("empId");
//	        Long salaryId = Long.parseLong(item.get("salaryId"));
//	        int result = service.togglePayStatus(salaryIdList, payYear, payMonth);
//	        if (result > 0) count++;
//	    }

	    SalaryVO finalCount = service.salaryInfo(payYear, payMonth); //여기로 전달
	    log.info("확정 인원수: {}", finalCount.getConfirmedCount());

	    Map<String, Object> result = new HashMap<>();
	    result.put("count", count);
	    result.put("finalCount", finalCount.getConfirmedCount() + "");
	    return result;
	}
	
	@PostMapping("/salary/request")
	@ResponseBody
	public Map<String, Object>salaryRequestToggle(
			  @RequestParam("empId") String empId,
			  @RequestParam("salaryId") Long salaryId,
			  @RequestParam(value="date",required = false) String  payYear,
			  @RequestParam(value="date",required = false) String  payMonth
			){
		int cnt = service.togglePayRequest(empId, salaryId); //토글
		Map<String, String>reqMapDate = service.getPayStatus(empId, salaryId); //요청일
		String reqDate = reqMapDate.get("TRANSFER_REQUEST_DATE");
		
		SalaryVO reqCount = service.salaryInfo(payYear, payMonth);
		
		Map<String, Object> request = new HashMap<>();
		request.put("cnt", cnt);
		request.put("reqDate", reqDate);
		request.put("reqCount", reqCount.getPaymentRequestCount());
		
		return request;
	}
	
	@PostMapping("/salary/requestAll")
	@ResponseBody
	public Map<String, Object> requestAll(
	    @RequestBody Map<String, Object> param,
	    @RequestParam(value = "payYear", required = false) String payYear,
	    @RequestParam(value = "payMonth", required = false) String payMonth
	) {
	    // 파라미터에서 payYear, payMonth 추출
	    payYear = String.valueOf(param.get("payYear"));
	    payMonth = String.valueOf(param.get("payMonth"));
	    // salaryList 파싱 → List<SalaryVO>로 변환
	    List<Map<String, String>> requestList = (List<Map<String, String>>) param.get("salaryList");
	    
	    List<SalaryVO> salaryIdList = requestList.stream().map(item -> {
	        SalaryVO vo = new SalaryVO();
	        vo.setEmpId(String.valueOf(item.get("empId"))); // empId는 항상 문자열로 처리
	        vo.setSalaryId(Long.parseLong(String.valueOf(item.get("salaryId")))); // Long이든 String이든 문자열로 변환 후 파싱
	        return vo;
	    }).collect(Collectors.toList());

	    log.info("일괄 승인 요청 날짜 확인: {}년 {}월", payYear, payMonth);
	    int cnt = service.togglePayRequestAll(salaryIdList, payYear, payMonth);

	    // 응답 구성
	    SalaryVO salaryInfo = service.salaryInfo(payYear, payMonth);
	    Map<String, Object> resultReq = new HashMap<>();
	    resultReq.put("reqPayCount", String.valueOf(salaryInfo.getPaymentRequestCount()));
	    resultReq.put("cnt", cnt);

	    return resultReq;
	}


}


