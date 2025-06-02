package com.progresssoft.fxdealsimporter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DealResponseDto(

        @NotBlank String dealId,
        @NotNull String fromCurrencyCode,
        @NotNull String toCurrencyCode,
        @NotNull LocalDateTime dealTimestamp,
        @NotNull @Positive BigDecimal dealAmount
) {
}
