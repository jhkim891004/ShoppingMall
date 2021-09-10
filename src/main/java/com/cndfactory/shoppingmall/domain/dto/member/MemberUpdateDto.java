package com.cndfactory.shoppingmall.domain.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class MemberUpdateDto {

    @NotNull
    @Min(1)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z\\d_]*$")
    @Size(min = 6, max = 12)
    private String memberId;

    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*()_+\\-=]).*$")
    @Size(min = 8, max = 16)
    private String password;
}
