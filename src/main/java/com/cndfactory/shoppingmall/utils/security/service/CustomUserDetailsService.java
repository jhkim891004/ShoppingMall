package com.cndfactory.shoppingmall.utils.security.service;

import com.cndfactory.shoppingmall.domain.dto.member.MemberLoginDto;
import com.cndfactory.shoppingmall.domain.entity.member.Member;
import com.cndfactory.shoppingmall.domain.entity.member.MemberRepository;
import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberId(memberId)
				.orElseThrow(() -> new UsernameNotFoundException("가입되지 않은 아이디입니다."));

		return CustomUserDetails.builder()
				.username(member.getMemberId())
				.password(member.getPassword())
				.authority(member.getAuthority())
				.isEnabled(true)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.build();
	}
}
