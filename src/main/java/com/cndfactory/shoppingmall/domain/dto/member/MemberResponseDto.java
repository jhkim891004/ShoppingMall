package com.cndfactory.shoppingmall.domain.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {
	private Long id;
	private String memberId;
	private String authority;

	@Builder
	public MemberResponseDto(Long id, String memberId, String authority) {
		this.id = id;
		this.memberId = memberId;
		this.authority = authority;
	}
}
