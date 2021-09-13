package com.cndfactory.shoppingmall.domain.entity;

import com.cndfactory.shoppingmall.utils.security.domain.CustomUserDetails;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements AuditorAware<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(updatable = false)
    private String regId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDatetime;

    @LastModifiedBy
    @Column
    private String modId;

    @LastModifiedDate
    @Column
    private LocalDateTime modDatetime;

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return Optional.of("system");
        }
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return Optional.of(user.getUsername());
    }

}
