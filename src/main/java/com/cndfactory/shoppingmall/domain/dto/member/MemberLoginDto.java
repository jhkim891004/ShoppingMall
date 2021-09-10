package com.cndfactory.shoppingmall.domain.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberLoginDto {
	private String memberId;
	private String password;
}
