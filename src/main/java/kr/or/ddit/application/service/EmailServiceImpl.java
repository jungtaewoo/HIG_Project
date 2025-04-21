package kr.or.ddit.application.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.application.vo.ApplicationVO;
import kr.or.ddit.spring.config.EmailUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailUtil emailUtil;

    // 공통 이메일 설정
    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;
    private static final String USERNAME = "cgh797979@gmail.com"; // 실제 Gmail 주소 
    private static final String PASSWORD = "iqzcwupgjtvasoks";    // 실제 앱 비밀번호  
    private static final String FROM_NAME = "대덕우리전자 인사팀";     // 발신자 이름
    
    // 면접 안내 메일
    @Override
    public void sendInterviewEmail(ApplicationVO application, String interviewDate) {
        String subject = application.getAppName() + "님, 면접 일정 안내드립니다.";
        String content = "<h3>" + application.getAppName() + "님</h3>"
                       + "<p>서류 전형에 합격하셨습니다. 아래 일정을 참고해 면접에 참석해 주세요.</p>"
                       + "<p><strong>면접일자: " + interviewDate + "</strong></p>"
                       + "<p>자세한 내용은 개별 연락드릴 예정입니다.</p>";

        emailUtil.sendEmail(HOST, PORT, USERNAME, PASSWORD, FROM_NAME,
                application.getAppEmail(), subject, content);
    }
    
    // 서류탈락 메일
    @Override
    public void sendRejectionEmail(ApplicationVO application) {
    	String subject = application.getAppName() + "님, 서류 전형 결과를 알려드립니다.";
    	String content = "<h3>" + application.getAppName() + "님</h3>"
    			+ "<p>아쉽게도 이번 채용에서 서류 전형 <strong>불합격</strong>되셨습니다.</p>"
    			+ "<p>지원해 주셔서 진심으로 감사드립니다.</p>";
    	
    	emailUtil.sendEmail(HOST, PORT, USERNAME, PASSWORD, FROM_NAME,
    			application.getAppEmail(), subject, content);
    }
    
    // 합격 메일
    @Override
    public void sendPassEmail(ApplicationVO application) {
        String subject = application.getAppName() + "님, 면접 결과를 알려드립니다.";
        String content = "<h3>" + application.getAppName() + "님, 축하드립니다!</h3>"
                       + "<p>귀하는 면접 결과 <strong>합격</strong>하셨습니다.</p>"
                       + "<p>추후 절차는 개별 연락드릴 예정입니다.</p>";

        emailUtil.sendEmail(HOST, PORT, USERNAME, PASSWORD, FROM_NAME, application.getAppEmail(), subject, content);
    }

    // 불합격 메일
    @Override
    public void sendFailEmail(ApplicationVO application) {
        String subject = application.getAppName() + "님, 면접 결과를 알려드립니다.";
        String content = "<h3>" + application.getAppName() + "님</h3>"
                       + "<p>아쉽게도 이번 채용에서 <strong>불합격</strong>되셨습니다.</p>"
                       + "<p>귀하의 지원에 진심으로 감사드립니다.</p>";

        emailUtil.sendEmail(HOST, PORT, USERNAME, PASSWORD, FROM_NAME, application.getAppEmail(), subject, content);
    }
}
