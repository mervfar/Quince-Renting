package com.quince.rentingapp.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR", strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "CreatedDate")
    @CreatedDate
    private Instant createdDate;

    @Column(name = "LastModifiedDate")
    @LastModifiedDate
    private Instant lastModifiedDate;

    @LastModifiedBy
    private String modifiedBy;


}
