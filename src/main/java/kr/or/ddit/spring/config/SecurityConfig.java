package kr.or.ddit.spring.config;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import kr.or.ddit.account.service.AccountService;
import kr.or.ddit.account.vo.AccountVO;
import kr.or.ddit.messenger.service.ChatEmpService;
import kr.or.ddit.security.CustomUserDetailService;
import kr.or.ddit.security.RealUserWrapper;
import kr.or.ddit.spring.config.EmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final ChatEmpService chatEmpService;
    private final CustomUserDetailService userDetailsService;
    private final AccountService accountService;
    private final EmailUtil emailUtil;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(auth -> auth
               .requestMatchers("/", "/login", "/resources/**").permitAll()
               .requestMatchers("/account/login/**", "/account/find/**", "/password/**", "/account/forgotPassword/**").permitAll()
               .requestMatchers("/WEB-INF/views/**").denyAll() // 직접 접근 방지
               .requestMatchers("/account/**").hasAnyRole("HR_ADMIN", "HR_MANAGER")
               .requestMatchers("/salary/**").hasAnyRole("HR_ADMIN", "HR_MANAGER")
           )
           .headers(headers -> headers.frameOptions().sameOrigin()) // iframe 허용
           .formLogin(login -> login
               .loginPage("/") // 로그인 폼 경로
               .loginProcessingUrl("/login/loginProcess")
               .usernameParameter("accountId")
               .passwordParameter("password")
               .successHandler((request, response, authentication) -> {
                   HttpSession session = request.getSession();
                   RealUserWrapper realUser = (RealUserWrapper) authentication.getPrincipal();
                   AccountVO account = realUser.getRealUser();

                   if (accountService.isInitialPassword(account.getAccountId())) {
                       String email = accountService.accountfindEmail(account.getAccountId());
                       if (email != null && !email.isBlank()) {
                           String token = accountService.insertPasswordReset(account.getAccountId());
                           String resetLink = "http://localhost:80/password/reset?token=" + token;

                           String subject = "초기 비밀번호 재설정 안내";
                           String htmlContent = "<h3>비밀번호 재설정 안내</h3>"
                                   + "<p>초기 비밀번호로 인해 비밀번호를 재설정해야 합니다.</p>"
                                   + "<a href='" + resetLink + "'>비밀번호 재설정하기</a>";

                           emailUtil.sendEmail(
                               "smtp.gmail.com", 587,
                               "honeynut7789@gmail.com", "icqi jmax ovzf pibx",
                               "인사관리 시스템",
                               email, subject, htmlContent
                           );
                       }

                       session.invalidate(); // 세션 초기화
                       response.sendRedirect("/?initPw=true");
                       return;
                   }

                   session.setAttribute("sessionAccount", account);
                   response.sendRedirect("/account/login/home");
               })
               .failureUrl("/?error=true")
               .permitAll()
           )
           .logout(logout -> logout
               .logoutUrl("/account/login/logout")
               .logoutSuccessHandler((request, response, authentication) -> {
                   if (authentication != null && authentication.getPrincipal() instanceof RealUserWrapper) {
                       AccountVO account = ((RealUserWrapper) authentication.getPrincipal()).getRealUser();
                       chatEmpService.updateEmpStatus(account.getEmpId(), "오프라인");
                   }
                   response.sendRedirect("/");
               })
               .invalidateHttpSession(true)
               .deleteCookies("JSESSIONID", "remember-me")
               .permitAll()
           )
           .sessionManagement(session -> session
               .maximumSessions(1)
               .expiredUrl("/account/login?expired")
           );

        return http.build();
    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
}
