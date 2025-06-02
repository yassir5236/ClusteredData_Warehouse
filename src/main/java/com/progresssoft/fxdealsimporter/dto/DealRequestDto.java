package com.progresssoft.fxdealsimporter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DealRequestDto(

        @NotBlank(message ="Deal ID is required")
        String dealId,

        @NotNull(message="From currency is required")
        String fromCurrencyCode,

        @NotNull(message="To currency  is required")
        String toCurrencyCode,

        @NotNull(message="Deal amount is required")
        @Positive(message = "Deal amount must be positive")
        BigDecimal dealAmount
) {
}
