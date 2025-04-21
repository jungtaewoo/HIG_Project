package kr.or.ddit.annual.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annual.service.AnnualHistoryService;
import kr.or.ddit.annual.vo.AnnualHistoryVO;

@Controller
public class AnnaulHistoryContoroller {
	@Autowired
	AnnualHistoryService service;

//	@GetMapping("/annaulHistoryList")
//	public String annualHistory(Model model){
//		model.addAttribute("historyList",service.TotalHisrotyList());
//		return "tiles:annual/annualHistoryList";
//	}

	/**
	 * 한명의 연차 내역 조회
	 * @param empId
	 * @return
	 */
	@GetMapping("/MyAnnualHistory")
	public Map<String, Object> MyAnnualHistory( @RequestParam("empId") String empId) {
		Map<String, Object> result = new HashMap<>();
		result.put("empHistoryList", service.EmpHisrotyDetail(empId));
		return result;
	}

	@GetMapping("/annual/history")
	@ResponseBody
	public Map<String, Integer> annualCount() {
	    Map<String, Integer> result = new HashMap<>();
	    result.put("vacCount", service.ThisMonthAnnualHistory());

	    return result;
	}
}
