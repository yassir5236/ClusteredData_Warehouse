package com.progresssoft.fxdealsimporter.service;


import com.progresssoft.fxdealsimporter.dto.DealRequestDto;
import com.progresssoft.fxdealsimporter.dto.DealResponseDto;

public interface DealService {
    DealResponseDto importDeal (DealRequestDto dealRequestDto);
}
