package com.bahadirmemis.mobileactionbootcamp.log.entity;

import com.bahadirmemis.mobileactionbootcamp.gen.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Entity
@Table(name = "LOG_ERROR")
@Getter
@Setter
public class LogError extends BaseEntity {

    @Id
    @SequenceGenerator(name = "AccAccount", sequenceName = "ACC_ACCOUNT_ID_SEQ")
    @GeneratedValue(generator = "AccAccount")
    private Long id;

    @Column(name = "ID_CUS_CUSTOMER")
    private Long cusCustomerId;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private HttpStatus httpStatus;

    @Column(name = "BODY", length = 4000)
    private String body;

    @Column(name = "HEADERS", length = 4000)
    private String headers;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ERROR_DATE")
    private Date errorDate;
}
