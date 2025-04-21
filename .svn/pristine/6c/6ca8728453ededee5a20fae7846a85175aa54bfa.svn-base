package kr.or.ddit.annual.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.annual.service.AnnualMangeService;
import kr.or.ddit.annual.vo.AnnualManageVO;
import kr.or.ddit.workstatus.vo.WorkstautsVO;

/**
 * 연차 관리 등록 수정 삭제
 */
@RequestMapping("/annual")
@Controller
public class AnnualManageController {
	
	@Autowired
	AnnualMangeService service;
	
	/**
	 * 연차 종류 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String AnnualList(Model model) {
		model.addAttribute("annualMangeList",service.annualManageList());  
		return "tiles:annual/annualMange";
	}
	
	/**
	 * 연차 종류 등록
	 * @param AnnualManage
	 * @return
	 */
	@PostMapping("Insert")
	@ResponseBody
	public ResponseEntity<Map<String, String>> AnnualInsert(@RequestBody AnnualManageVO AnnualManage) {
		service.insertAnnualItem(AnnualManage);
	    Map<String, String> response = new HashMap<>();
	    response.put("status", "success");
	    
	    return ResponseEntity.ok(response); 
	}
	
	/**
	 * 연차 종류 사용여부 변경
	 * @param AnnualManage
	 */
	@PostMapping("Update")
	@ResponseBody
	public void AnnualUpdate(@RequestBody AnnualManageVO AnnualManage) {
		service.updateAnnualItemStatus(AnnualManage);
	}
	
	/**
	 * 연차 종류 삭제 
	 * @param annualCodeMap
	 * @return
	 */
	@PostMapping("Delete")
	@ResponseBody
	public ResponseEntity<String> AnnualDelete(@RequestBody Map<String, String>  annualCodeMap) {
		String annualCode = annualCodeMap.get("annualCode");
	    service.deleteAnnualItem(annualCode);
	    return ResponseEntity.ok("삭제 완료");
	}
	
}
