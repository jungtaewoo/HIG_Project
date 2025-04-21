package kr.or.ddit.application.service;

import kr.or.ddit.application.vo.ApplicationVO;

public interface EmailService {
	
	void sendInterviewEmail(ApplicationVO application, String interviewDate);
    void sendRejectionEmail(ApplicationVO application);
	
	void sendPassEmail(ApplicationVO application);
    void sendFailEmail(ApplicationVO application);
    
}