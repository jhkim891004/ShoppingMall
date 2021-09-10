package com.cndfactory.shoppingmall.config;

import com.cndfactory.shoppingmall.utils.security.filter.JwtAuthenticationFilter;
import com.cndfactory.shoppingmall.utils.security.handler.WebAccessDeniedHandler;
import com.cndfactory.shoppingmall.utils.security.handler.WebAuthenticationEntryPoint;
import com.cndfactory.shoppingmall.utils.security.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 인증 관련 핸들러 추가
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		WebAuthenticationEntryPoint authenticationEntryPoint = new WebAuthenticationEntryPoint();
		return authenticationEntryPoint;
	}

	/**
	 * 인가 관련 핸들러 추가
	 * @return
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		WebAccessDeniedHandler accessDeniedHandler = new WebAccessDeniedHandler();
		return accessDeniedHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		http.formLogin().disable();

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
				.antMatchers("/api/v1/signin", "/api/v1/signup").permitAll()
				.antMatchers("/api/v1/members").hasRole("ADMIN")
				.antMatchers("/api/v1/members/**").hasRole("ADMIN")
				.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
				.antMatchers("/api/v1/customer/**").hasRole("CUSTOMER")
				.antMatchers("/**").authenticated();

		http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
			@Override
			protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
				WebSecurityExpressionRoot root =
						(WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
				root.setDefaultRolePrefix("");

				return root;
			}
		});
	}
}
