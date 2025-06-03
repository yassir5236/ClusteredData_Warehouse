package com.progresssoft.fxdealsimporter.service;

import com.progresssoft.fxdealsimporter.handelException.RequestAlreadyExistException;
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
    final CurrencyChecker currencyChecker;

    @Override
    public DealResponseDto importDeal(DealRequestDto dealRequestDto) {

        currencyChecker.checkCurrencyExchange(dealRequestDto.fromCurrencyCode(), dealRequestDto.toCurrencyCode());

        log.info("Importing deal: from={}, to={}, amount={}",
                dealRequestDto.fromCurrencyCode(),
                dealRequestDto.toCurrencyCode(),
                dealRequestDto.dealAmount());


        if (dealRepository.existsById(dealRequestDto.dealId())) {
            log.warn("Duplicate  ID detected: {}. Operation aborted.", dealRequestDto.dealId());
            throw new RequestAlreadyExistException("This request already exists");
        }

        Deal deal = dealMapper.toEntity(dealRequestDto);
        Deal importedDeal = dealRepository.save(deal);

        return dealMapper.toResponseEntity(importedDeal);

    }



}
