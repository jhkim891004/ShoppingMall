package com.cndfactory.shoppingmall.web.service;

import com.cndfactory.shoppingmall.domain.dto.member.MemberResponseDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberSaveDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberUpdateDto;
import com.cndfactory.shoppingmall.domain.entity.member.Member;
import com.cndfactory.shoppingmall.domain.entity.member.MemberRepository;
import com.cndfactory.shoppingmall.web.service.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MemberServiceTest {

	@InjectMocks
	MemberService memberService;

	@Mock
	MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@DisplayName("멤버_조회_성공")
	@Test
	void getOne_Success_FindOne() {
		Member member = Member.builder()
				.memberId("tester")
				.password("cndfactory@)21")
				.build();

		given(memberRepository.findById(anyLong())).willReturn(Optional.of(member));

		MemberResponseDto dto = memberService.getOne(1L);

		assertThat(member.getMemberId()).isEqualTo(dto.getMemberId());
		verify(memberRepository, times(1)).findById(anyLong());

	}

	@DisplayName("멤버_조회_실패")
	@Test
	void getOne_Fail_FindOne() {
		given(memberRepository.findById(anyLong())).willReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> memberService.getOne(1L));

		verify(memberRepository, times(1)).findById(anyLong());
	}

	@DisplayName("모든_멤버_조회_성공")
	@Test
	void getAll_Success_FindAll() {
		List<Member> members = new ArrayList<>();
		for(int i=0; i<10; i++) {
			members.add(Member.builder().memberId("tester"+i).password("cndfactory@)21").build());
		}
		Pageable pageable = PageRequest.of(1, 10);

		Page<Member> memberList = new PageImpl<Member>(members, pageable, members.size());
		given(memberRepository.findAll(pageable)).willReturn(memberList);

		Page<MemberResponseDto> dtos = memberService.getAll(pageable);

		assertThat(dtos.getContent().size()).isEqualTo(members.size());
		verify(memberRepository, times(1)).findAll(any(Pageable.class));
	}

	@DisplayName("멤버_저장_성공")
	@Test
	void save_Success_Insert() {
		MemberSaveDto member = new MemberSaveDto("tester", "cndfactory@)21", "ADMIN");

		memberService.save(member);

		verify(memberRepository, times(1)).save(any(Member.class));
	}

	@DisplayName("멤버_수정_성공")
	@Test
	void modify_Success_Update() {
		Member member = Member.builder()
				.memberId("tester")
				.password("cndfactory@)21")
				.build();

		MemberUpdateDto dto = new MemberUpdateDto(1L, "tester2", "cndfactory@)21");

		given(memberRepository.findById(1L)).willReturn(Optional.of(member));

		memberService.modify(dto);

		verify(memberRepository, times(1)).findById(1L);
		verify(memberRepository, times(1)).save(member);
		assertThat(member.getMemberId()).isEqualTo(dto.getMemberId());
	}
}