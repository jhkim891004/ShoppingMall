package com.cndfactory.shoppingmall.domain.entity.member;

import com.cndfactory.shoppingmall.domain.dto.member.MemberResponseDto;
import com.cndfactory.shoppingmall.domain.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Column
    private String memberId;
    @Column
    private String password;
    @Column
    private String authority;

    public void updateMemberInfo(String memberId) {
        this.memberId = memberId;
    }

    public void encodePassword(String password) {
        this.password = password;
    }

    public MemberResponseDto toDto() {
        return MemberResponseDto.builder()
                .id(this.getId())
                .memberId(this.memberId)
                .build();
    }
    @Builder
    public Member(String memberId, String password, String authority) {
        this.memberId = memberId;
        this.password = password;
        this.authority = authority;
    }
}
