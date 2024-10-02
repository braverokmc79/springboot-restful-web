package net.macaronics.springboot.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration  // 스프링에서 설정 클래스를 나타냄
public class SecurityConfiguration {

	// 비밀번호 암호화를 위한 PasswordEncoder 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Spring Security 필터 체인을 구성하는 메서드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable);  // CSRF 보호 기능을 비활성화
		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));  // H2 콘솔 접속을 위해 동일 출처에서만 iframe 사용 허용
		
        // 로그인, 로그아웃, 예외 처리 설정
        http.formLogin(login -> login
                        .loginPage("/user/login")  // 로그인 페이지 설정
                        .defaultSuccessUrl("/", true)  // 로그인 성공 시 리다이렉트될 기본 URL 설정
                        .usernameParameter("username")  // 로그인 시 사용할 사용자 이름 파라미터 설정
                        .failureUrl("/user/login?error=true"))  // 로그인 실패 시 리다이렉트될 URL 설정
        .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // 로그아웃 요청 경로 설정
                              .logoutSuccessUrl("/"))  // 로그아웃 성공 시 리다이렉트될 URL 설정
        .exceptionHandling(exception -> exception.authenticationEntryPoint(new Http403ForbiddenEntryPoint()));  // 인증되지 않은 사용자의 접근을 처리하는 예외 핸들링
		
        // 권한에 따른 요청 URL 접근 허용/제한 설정
        http.authorizeHttpRequests(request -> request
	                .requestMatchers("/webjars/**","/css/**", "/js/**", "/img/**","/images/**").permitAll()  // 정적 리소스 접근 허용
	                .requestMatchers("/" ,  "/api/**").permitAll()  // 메인 페이지 접근 허용	                
	                .requestMatchers("/actuator/**").permitAll()  // 애플리케이션의 모니터링과 관리	                
	                .requestMatchers("/api-docs","/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**").permitAll()
	                
	                .requestMatchers("/user/login", "/user/register", "/h2-console/**").permitAll()  // 로그인, 회원가입, H2 콘솔 접근 허용
	                .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
	        );
				  
		return http.build();
	}
}
