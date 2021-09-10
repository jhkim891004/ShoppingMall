package com.cndfactory.shoppingmall.domain.dto.member;

import com.cndfactory.shoppingmall.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberSaveDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z\\d_]*$")
    @Size(min = 6, max = 12)
    private String memberId;

    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+\\-=]).*$")
    @Size(min = 8, max = 16)
    private String password;

    @NotBlank
    private String authority;

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .password(password)
                .authority(authority)
                .build();
    }

}
