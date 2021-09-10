package com.cndfactory.shoppingmall.utils.security.controller;

import com.cndfactory.shoppingmall.domain.dto.member.MemberLoginDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberResponseDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberSaveDto;
import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import com.cndfactory.shoppingmall.utils.security.token.JwtTokenProvider;
import com.cndfactory.shoppingmall.web.service.MemberService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AccountController {
	private final UserDetailsService userDetailsService;
	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/api/v1/signup")
	public ResponseEntity<MemberResponseDto> signUp(@RequestBody @Valid MemberSaveDto dto) {
		return new ResponseEntity<MemberResponseDto>(memberService.save(dto), HttpStatus.OK);
	}

	@PostMapping("/api/v1/signin")
	public ResponseEntity<String> signIn(@RequestBody @Valid MemberLoginDto dto) {

		CustomUserDetails principal = (CustomUserDetails) userDetailsService.loadUserByUsername(dto.getMemberId());
		if(!passwordEncoder.matches(dto.getPassword(), principal.getPassword()))
			throw new BadCredentialsException(ErrorCode.PASSWORD_NOT_MATCH.getMessage());

		List<String> authorities =
				principal.getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList());

		return new ResponseEntity<String>(jwtTokenProvider.createAccessToken(principal.getUsername(), authorities), HttpStatus.OK);
	}

}
