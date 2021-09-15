package com.cndfactory.shoppingmall.utils.jwt;

import com.cndfactory.shoppingmall.domain.entity.member.Member;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

//@Component
public class JwtUtil {
	@Value("${jwt.secure.key}")
	private static String key;
	@Value("${jwt.token.header}")
	private static String header;

	static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000L;			// 1시간
	private static final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;	// 1주일

	static final String ACCESS_TOKEN_NAME = "accessToken";
	static final String REFRESH_TOKEN_NAME = "refreshToken";

	@PostConstruct
	private void init() {
		key = Base64.getEncoder().encodeToString(key.getBytes());
	}

	Claims extractAllClaims(String token) throws ExpiredJwtException {
		// TODO. build() ignore..
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}

	String getMemberId(String token) {
		return extractAllClaims(token).get("memberId", String.class);
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = extractAllClaims(token).getExpiration();
		return expiration.before(new Date());
	}

	String generateToken(Member member) {
		return doGenerateToken(member.getMemberId(), ACCESS_TOKEN_VALID_TIME);
	}

	private String generateRefreshToken(Member member) {
		return doGenerateToken(member.getMemberId(), REFRESH_TOKEN_VALID_TIME);
	}

	private String doGenerateToken(String memberId, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("memberId", memberId);

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	private boolean validateToken(String token, CustomUserDetails userDetails) {
		final String memberId = getMemberId(token);

		return (memberId.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
