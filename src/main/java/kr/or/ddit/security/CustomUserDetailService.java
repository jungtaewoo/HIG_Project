package kr.or.ddit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.mybatis.mappers.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private AccountMapper mapper;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    AccountVO account = mapper.selectAccount(username);

	    if (account == null) {
	        log.warn("사용자 없음: {}", username);
	        throw new UsernameNotFoundException(String.format("%s 사용자 없음.", username));
	    }

	    log.info("사용자 조회 성공: {}", account.getAccountId());
	    return new RealUserWrapper(account);
	}
}















