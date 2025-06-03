package com.progresssoft.fxdealsimporter.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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


    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid format")
    @Column(nullable = false ,length = 3)
    @NotNull
    private String fromCurrencyCode;

    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid format")
    @Column(nullable = false ,length = 3)
    @NotNull
    private String toCurrencyCode;

    @Column(nullable = false)
    @NotNull
    @CreatedDate
    private LocalDateTime dealTimestamp;

    @Positive
    @Column(nullable = false)
    @NotNull
    private BigDecimal dealAmount;


}
