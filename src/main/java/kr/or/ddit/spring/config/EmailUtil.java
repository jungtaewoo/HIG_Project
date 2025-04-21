package kr.or.ddit.spring.config;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailUtil {

    /**
     * 메일 서버 설정과 함께 이메일을 보냄
     * @param host SMTP 서버 주소 (예: smtp.gmail.com)
     * @param port SMTP 포트 (예: 587)
     * @param username 메일 계정 아이디
     * @param password 앱 비밀번호 또는 로그인 비번
     * @param fromName 발신자 이름
     * @param toEmail 수신자 이메일
     * @param subject 제목
     * @param htmlContent HTML 본문
     */
    public void sendEmail(
            String host,
            int port,
            String username,
            String password,
            String fromName,
            String toEmail,
            String subject,
            String htmlContent
    ) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(host);
            mailSender.setPort(port);
            mailSender.setUsername(username);
            mailSender.setPassword(password);
            mailSender.setDefaultEncoding("UTF-8");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.debug", true);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(username, fromName);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println("📧 이메일 전송 성공: " + toEmail);

        } catch (MessagingException e) {
            System.err.println("❌ 메시지 오류로 전송 실패");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ 기타 오류로 전송 실패");
            e.printStackTrace();
        }
    }
}
