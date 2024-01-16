package com.jdacoder.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/* All rights reserved by JdaCoder */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true)
public abstract class Audit {

    @Column( name = "created_at", nullable = false, updatable = false )
    @CreationTimestamp
    private Date createdAt;

    @Column( name = "updated_at", nullable = false )
    @UpdateTimestamp
    private Date updatedAt;


}
