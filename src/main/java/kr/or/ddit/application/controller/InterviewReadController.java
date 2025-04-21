package kr.or.ddit.application.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import kr.or.ddit.application.InterviewExcelDownloadView;
import kr.or.ddit.application.service.ApplicationService;
import kr.or.ddit.application.vo.ApplicationVO;
import lombok.RequiredArgsConstructor;

/**
 * 면접자 조회
 * 
 * @author KHT
 * @since 2025. 3. 19.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      			수정자           수정내용
 *  -----------   	-------------    ---------------------------
 *  2025. 3. 19.     	KHT	          최초 생성
 *
 * </pre>
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/recruit/interview")
public class InterviewReadController {
	
	private final ApplicationService service;

	/**
	 * 면접자 목록 조회
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String recruitInterviewList(Model model) {
		List<ApplicationVO> interviewList = service.readInterviewList();
		model.addAttribute("interviewList",interviewList);
		
		// 총 지원자 성별 비율 조회
		List<Map<String, Object>> totalGenderRatio = service.getTotalGenderRatio();
		model.addAttribute("totalGenderRatio", totalGenderRatio);
		// 합격자 성별 비율 조회
		List<Map<String, Object>> passedGenderRatio = service.getPassedGenderRatio();
		model.addAttribute("passedGenderRatio", passedGenderRatio);
		
		return "tiles:recruitment/recruitInterviewList";
	}
	
	
	// 비동기로 요청되는 합격자 성별 비율 조회
    @GetMapping("passedGenderRatio")
    @ResponseBody
    public List<Map<String, Object>> getPassedGenderRatio() {
        return service.getPassedGenderRatio();
    }
	
    
    // 비동기로 요청되는 면접상태 현황 조회
    @GetMapping("/interviewStatusCount")
    @ResponseBody
    public List<Map<String, Object>> getInterviewStatusCount(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "half", required = false) Integer half) {
        
        if (year == null || half == null) {
            // 기본값: 현재 연도 및 반기 설정
            LocalDate now = LocalDate.now();
            year = now.getYear();
            half = (now.getMonthValue() <= 6) ? 1 : 2; // 1~6월: 상반기(1), 7~12월: 하반기(2)
        }
        
        return service.getInterviewStatusCount(year, half);
    }
    
    
    
    
    // 선택한 합격자 엑셀 다운로드
    @PostMapping("/excelDownload")
    public View downloadExcel(
    	@RequestParam("appIds") List<Long> appIds
    ) {
        // 1. appIds로 필요한 데이터 목록 가져오기
    	List<ApplicationVO> selectedList = service.readInterviewsByAppIds(appIds);

    	 // 2. View 객체 생성해서 return (model은 뷰 내부에서 처리)
        return new InterviewExcelDownloadView(selectedList);
    }
    
}
