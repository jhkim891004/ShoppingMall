package com.cndfactory.shoppingmall.utils.security.token;

import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Value("${jwt.secure.key}")
	private String key;
	@Value("${jwt.token.header}")
	private String header;

//	private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000L;			// 1시간
	private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;			// 1시간
	private static final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;	// 1주일

	private final UserDetailsService userDetailsService;

	@PostConstruct
	public void init() {
		key = Base64.getEncoder().encodeToString(key.getBytes());
	}

	/**
	 * JWT 토큰 생성
	 * @param memberId - 사용자 아이디
	 * @param roles - 사용자 인가 정보
	 * @return - JWT 토큰
	 */
	public String createAccessToken(String memberId, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(memberId);	// JWT Payload 에 저장되는 정보 단위
		claims.put("roles", roles);
		Date now = new Date();
		Date expiration = new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	/**
	 * JWT 갱신 토큰 생성
	 * @param value
	 * @return
	 */
	public String createRefreshToken(String value) {
		Claims claims = Jwts.claims();
		claims.put("value", value);
		Date now = new Date();
		Date expiration = new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	/**
	 * JWT 토큰에서 인증 정보 조회
	 * @param request
	 * @return
	 */
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = this.resolveToken(request);

		if(token == null) {
			return null;
		}

		try {
			this.validateToken(token);
		} catch(ExpiredJwtException e) {
			request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getCode());
			return null;
		} catch(Exception e) {
			request.setAttribute("exception", ErrorCode.ACCESS_DENIED.getCode());
			return null;
		}

		CustomUserDetails principal =
				(CustomUserDetails) userDetailsService.loadUserByUsername(this.getMemberId(token));
		return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
	}

	/**
	 * 토큰에서 사용자 아이디 조회
	 * 토큰이 만료되었을 경우 ExpiredJwtException 을 발생
	 * @param token - JWT 토큰
	 * @return 회원정보
	 */
	public String getMemberId(String token) {
		String memberId = "";
		try {
			memberId = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		} catch(ExpiredJwtException e) {
			e.printStackTrace();
		}
		return memberId;
	}

	/**
	 * JWT 토큰 조회
	 * @param request - 요청 정보
	 * @return 토큰값
	 */
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader(header);
	}

	/**
	 * JWT 토큰 유효성 검사
	 * @param token - JWT 토큰
	 * @return boolean
	 */
	public boolean validateToken(String token) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		return !claims.getBody().getExpiration().before(new Date());
	}

}
