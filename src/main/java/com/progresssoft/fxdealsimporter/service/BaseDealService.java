package com.progresssoft.fxdealsimporter.service;

import com.progresssoft.fxdealsimporter.dto.DealRequestDto;
import com.progresssoft.fxdealsimporter.dto.DealResponseDto;
import com.progresssoft.fxdealsimporter.mapper.DealMapper;
import com.progresssoft.fxdealsimporter.entity.Deal;
import com.progresssoft.fxdealsimporter.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class BaseDealService implements DealService {

    final DealMapper dealMapper;
    final DealRepository dealRepository;

    @Override
    public DealResponseDto importDeal(DealRequestDto dealRequestDto) {

        Deal deal = dealMapper.toEntity(dealRequestDto);
        Deal importedDeal = dealRepository.save(deal);

        return dealMapper.toResponseEntity(importedDeal);

    }
}
