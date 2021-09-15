package com.cndfactory.shoppingmall.utils.jwt;

import com.cndfactory.shoppingmall.domain.entity.member.Member;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import com.cndfactory.shoppingmall.utils.security.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
//@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private final CustomUserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final CookieUtil cookieUtil;
	private final RedisUtil redisUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		final Cookie jwtToken = cookieUtil.getCookie(request, JwtUtil.ACCESS_TOKEN_NAME);

		String memberId = null;
		String jwt = null;
		String refreshJwt = null;
		String refreshUname = null;

		try {
			if(jwtToken != null) {
				jwt = jwtToken.getValue();
				memberId = jwtUtil.getMemberId(jwt);
			}

			if(memberId != null) {
				CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(memberId);

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		} catch(ExpiredJwtException e) {
			Cookie refreshToken = cookieUtil.getCookie(request, JwtUtil.REFRESH_TOKEN_NAME);
			if(refreshToken != null) {
				refreshJwt = refreshToken.getValue();
			}
		} catch(Exception e) {

		}

		try {
			if(refreshJwt != null) {
				refreshUname = redisUtil.getData(refreshJwt);

				if(refreshJwt.equals(jwtUtil.getMemberId(refreshJwt))) {
					CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(refreshUname);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

					Member member = Member.builder()
							.memberId(refreshUname)
							.build();
					String newToken = jwtUtil.generateToken(member);

					Cookie newAccessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, newToken);
					response.addCookie(newAccessToken);
				}
			}
		} catch(ExpiredJwtException e) {
			log.error("Throw ExpiredJwtException", e);
		}

		filterChain.doFilter(request, response);
	}
}
