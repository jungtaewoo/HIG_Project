package kr.or.ddit.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.ddit.account.vo.AccountVO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
@ToString
@Slf4j
public class RealUserWrapper implements UserDetails{
	private final AccountVO realUser;
	public RealUserWrapper(AccountVO realUser) {
		super();
		this.realUser = realUser;
	}
	
	public AccountVO getRealUser() {
		return realUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roles = realUser.getAuthorities();
		List<GrantedAuthority> authorites = new ArrayList<>();
		for(String role : roles) {
			authorites.add(new SimpleGrantedAuthority(role));
		}
		return authorites;
	}
	
	@Override
	public String getPassword() {
		return realUser.getPassword();
	}
	@Override
	public String getUsername() {
		return realUser.getAccountId();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return realUser.isAccountStatus();
	}
	
}











