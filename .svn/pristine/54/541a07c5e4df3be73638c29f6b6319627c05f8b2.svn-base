package kr.or.ddit.salary.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.salary.service.SalaryService;
import kr.or.ddit.salary.vo.SalaryVO;
import kr.or.ddit.spring.config.EmailSalaryUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SalaryPaymentSendEmailController {

    @Autowired
    private AccountService serviceAccount;
    
    @Autowired
    private SalaryService serviceSalary;

    /**
     * html2canvas로 캡처된 급여명세서 이미지를 수신자 이메일로 PDF 전송
     */
    @PostMapping("/salary/send") 
    @ResponseBody 
    public Map<String, Object> sendSalaryImage(@RequestBody Map<String, Object> payload) throws IOException {
        String empId = (String) payload.get("empId");
        String payYear = (String) payload.get("payYear");
        String payMonth = (String) payload.get("payMonth");  
        String base64Image = (String) payload.get("base64Image");

        // 사번으로 이메일 정보 조회
        AccountVO account = serviceAccount.selectedUnAccount(empId);
        String toEmail = account != null && account.getEmployee() != null ? account.getEmployee().getEmail() : null;

        if (toEmail == null || toEmail.isBlank()) {
            log.error(" 수신자 이메일이 없습니다. 사번: {}", empId);
            Map<String, Object> fail = new HashMap<>();
            fail.put("message", "❌ 이메일 정보가 없습니다.");
            return fail;
        }

        log.info("메일확인~~~~~~~~~~ {}", toEmail);

        // 이미지 파일로 저장
        String fileName = empId + "_" + payYear + "_" + payMonth + ".png";
        String imagePath = "C:/salary_images/" + fileName;
        saveBase64Image(base64Image, imagePath);

        // PDF 변환 + 암호화
        String pdfPath = imagePath.replace(".png", ".pdf");
        String password = empId.substring(empId.length() - 4); // 사번 뒷자리 4자리

        try {
            EmailSalaryUtil.generatePasswordPdf(imagePath, pdfPath, password);
            
            // PDF 생성 후 존재 여부 확인
            File pdfFile = new File(pdfPath);
            int retry = 5;
            while (!pdfFile.exists() && retry-- > 0) {
                Thread.sleep(200); // 약간의 대기 후 재확인
            }

            if (!pdfFile.exists()) {
                log.error(" PDF 파일이 존재하지 않습니다: {}", pdfPath);
                Map<String, Object> fail = new HashMap<>();
                fail.put("message", " PDF 파일 생성 실패");
                return fail;
            }

            // 이메일 전송
            EmailSalaryUtil.sendPdfEmail(
                toEmail, 
                "급여명세서 [" + payYear + "년 " + payMonth + "월]",
                "안녕하세요 주식회사 대덕우리전자입니다., 급여명세서를 확인해주세요. <비밀번호 사번 뒷4자리>",
                pdfPath
            );
        } catch (MessagingException e) {
            log.error(" 이메일 전송 실패", e);
            Map<String, Object> fail = new HashMap<>();
            fail.put("message", " 이메일 전송 실패: " + e.getMessage());
            return fail;
        } catch (Exception e) {
            log.error(" PDF 생성 또는 전송 과정 중 오류", e);
            Map<String, Object> fail = new HashMap<>();
            fail.put("message", " 시스템 오류 발생");
            return fail;
        }

        // 응답 반환
        Map<String, Object> result = new HashMap<>();
        result.put("message", "📨 이메일 전송 완료");
        return result;
    }
    

    // 확정된 사원 목록 조회
    @GetMapping("/salary/send/confirm")
    @ResponseBody
    public Map<String, Object> salarySendConfirm(
        @RequestParam("payYear") String payYear,
        @RequestParam("payMonth") String payMonth) {

        Map<String, Object> confirm = new HashMap<>();
        List<SalaryVO> confirmList = serviceSalary.salarySendEmailConfirm(payYear, payMonth);
        
        // 필드 보정
        for (SalaryVO vo : confirmList) {
            vo.setPayYear(payYear);
            vo.setPayMonth(payMonth);
        }

        log.info("년도확인 메일~~~~~~~~~{}{}", payYear,payMonth);
        confirm.put("confirmList", confirmList);
        return confirm;
    }
    
    
    //  메일 전송 (Blob 파일 기반)
    @PostMapping("/salary/send/all")
    @ResponseBody
    public Map<String, Object> sendSalaryFormData(
        @RequestParam("empId") String empId,
        @RequestParam("payYear") String payYear,
        @RequestParam("payMonth") String payMonth,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        Map<String, Object> result = new HashMap<>();

        AccountVO account = serviceAccount.readAccount(empId);
        String toEmail = (account != null && account.getEmployee() != null) ? account.getEmployee().getEmail() : null;

        if (toEmail == null || toEmail.isBlank()) {
            result.put("message", "❌ 이메일 정보가 없습니다.");
            return result;
        }

        String fileName = empId + "_" + payYear + "_" + payMonth + ".png";
        String imagePath = "C:/salary_images/" + fileName;
        File imageFile = new File(imagePath);
        file.transferTo(imageFile);

        String pdfPath = imagePath.replace(".png", ".pdf");
        String password = empId.substring(empId.length() - 4);

        try {
            EmailSalaryUtil.generatePasswordPdf(imagePath, pdfPath, password);
        } catch (Exception e) {
            result.put("message", "❌ PDF 생성 실패");
            return result;
        }

        try {
            EmailSalaryUtil.sendPdfEmail(
                toEmail,
                "급여명세서 [" + payYear + "년 " + payMonth + "월]",
                "안녕하세요, 대덕우리전자입니다.\n" +
                "첨부된 급여명세서를 확인해주시기 바랍니다.\n" +
                "파일은 비밀번호로 보호되어 있으며, 비밀번호는 사번 뒷자리 4자리입니다.\n\n" +
                "감사합니다.",
                pdfPath
            );
        } catch (MessagingException e) {
            result.put("message", "❌ 이메일 전송 실패");
            return result;
        }

        result.put("message", "📨 이메일 전송 완료");
        return result;
    }

    
    // 이전 base64 일괄 발송 API는 비활성화 또는 제거 추천
//    @PostMapping(value = "/salary/send/all", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Map<String, Object> disableOldBatchApi() {
//        Map<String, Object> res = new HashMap<>();
//        res.put("message", "❌ 해당 API는 비활성화되었습니다. 단건 전송 API(/salary/send/single)를 사용해주세요.");
//        return res;
//    }



    /**
     * Base64 이미지를 PNG 파일로 저장
     */
    private void saveBase64Image(String base64Image, String outputPath) throws IOException {
        // "data:image/png;base64,..." 형식에서 데이터 부분만 분리
        String base64Data = base64Image.split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(imageBytes);
        }
    }
}