package com.cndfactory.shoppingmall.web.service.member;

import com.cndfactory.shoppingmall.domain.dto.member.MemberResponseDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberSaveDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberUpdateDto;
import com.cndfactory.shoppingmall.domain.entity.member.Member;
import com.cndfactory.shoppingmall.domain.entity.member.MemberRepository;
import com.cndfactory.shoppingmall.utils.exception.BusinessException;
import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 개별 멤버 조회
	 * @param id
	 * @return
	 */
	public MemberResponseDto getOne(Long id) {

		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Member."));

		MemberResponseDto responseDto = MemberResponseDto.builder()
				.id(member.getId())
				.memberId(member.getMemberId())
				.authority(member.getAuthority())
				.build();

		return responseDto;
	}

	/**
	 * 멤버 모두 조회
	 * @param pageable
	 * @return
	 */
	public Page<MemberResponseDto> getAll(Pageable pageable) {
		return memberRepository.findAll(pageable)
				.map(member -> {
					return MemberResponseDto.builder()
							.id(member.getId())
							.memberId(member.getMemberId())
							.build();
				});
	}

	/**
	 * 멤버 저장
	 * @param dto
	 */
	@Transactional
	public MemberResponseDto save(MemberSaveDto dto) {
		if(memberRepository.existsByMemberId(dto.getMemberId())) {
			throw new BusinessException(ErrorCode.USERNAME_IS_EXISTS);
		}
		Member member = dto.toEntity();
		member.encodePassword(passwordEncoder.encode(member.getPassword()));

		memberRepository.save(member);
		MemberResponseDto responseDto = MemberResponseDto.builder()
				.id(member.getId())
				.memberId(member.getMemberId())
				.authority(member.getAuthority())
				.build();

		return responseDto;
	}

	/**
	 * 멤버 수정
	 * @param dto
	 */
	@Transactional
	public void modify(MemberUpdateDto dto) {
		Member member = memberRepository.findById(dto.getId())
				.orElseThrow(() -> new NoSuchElementException("Not Found Member."));

		if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
			member.encodePassword(dto.getPassword());
		}
		member.updateMemberInfo(dto.getMemberId());

		memberRepository.save(member);
	}

	@Transactional
	public void remove(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Not Found Member."));

		memberRepository.delete(member);
	}
}
