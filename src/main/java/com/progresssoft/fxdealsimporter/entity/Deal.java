package com.progresssoft.fxdealsimporter.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

@Table(name="deals")
public class Deal {
    @Id
    private String dealId;

    @Column(nullable = false)
    private String fromCurrencyCode;

    @Column(nullable= false)
    private String toCurrencyCode;

    @Column(nullable = false)
    @NotNull
    @CreatedDate
    private LocalDateTime dealTimestamp;

    @Column(nullable = false)
    private BigDecimal dealAmount;


}
