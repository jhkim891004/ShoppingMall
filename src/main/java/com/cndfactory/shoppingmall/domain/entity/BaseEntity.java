package com.cndfactory.shoppingmall.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

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

    public void addRegistInfo(String regId, LocalDateTime regDatetime) {
        this.regId = regId;
        this.regDatetime = regDatetime;
    }

}
