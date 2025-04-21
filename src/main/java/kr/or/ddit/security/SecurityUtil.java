package kr.or.ddit.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.or.ddit.account.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {
	
	public static AccountVO getrealUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof RealUserWrapper)) {
			log.info("현재 로그인한 사용자가 없습니다.");
			return null;
		}
        
        RealUserWrapper realUserWrapper = (RealUserWrapper) authentication.getPrincipal();
        
        if(realUserWrapper.getRealUser() == null) {
        	log.info("realUser가 없습니다.");
        	return null;
        }
        
        return realUserWrapper.getRealUser();
	}

}
