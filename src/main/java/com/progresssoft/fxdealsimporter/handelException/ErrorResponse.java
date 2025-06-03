package com.progresssoft.fxdealsimporter.handelException;

import java.time.LocalDateTime;

public record ErrorResponse(
        int code,
        LocalDateTime timestamp,
        String message,
        String description,
        Object errors
){}
