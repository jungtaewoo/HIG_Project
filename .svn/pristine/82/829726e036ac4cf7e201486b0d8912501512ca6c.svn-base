package kr.or.ddit.salary.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.spring.config.EmailUtil;

@Service
public class PaystubEmailService {

	public void send(String empId, String payYear, String payMonth, MultipartFile file) {
		// 별도 스레드로 비동기 처리
		new Thread(() -> {
			try {
				File tempFile = File.createTempFile("paystub_" + empId, ".png");
				file.transferTo(tempFile);

				String email = getEmployeeEmail(empId);
				String subject = payYear + "년 " + payMonth + "월 급여명세서";
				String body = "첨부된 명세서를 확인해 주세요.";

//				EmailUtil.sendEmailWithAttachment(email, subject, body, tempFile.getAbsolutePath());

				tempFile.delete();
				System.out.println("✅ " + empId + " 이메일 전송 완료");

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("❌ " + empId + " 전송 실패: " + e.getMessage());
			}
		}).start();
	}

	private String getEmployeeEmail(String empId) {
		// 사원 이메일 조회 로직 (DB 등)
		return "test@example.com";
	}
}
