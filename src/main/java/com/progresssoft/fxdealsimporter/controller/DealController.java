package com.progresssoft.fxdealsimporter.controller;

import com.progresssoft.fxdealsimporter.dto.DealRequestDto;
import com.progresssoft.fxdealsimporter.dto.DealResponseDto;
import com.progresssoft.fxdealsimporter.service.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
@Slf4j
public class DealController {
    private final DealService service;


    @PostMapping("/import")
    public ResponseEntity<DealResponseDto> importDeal(@RequestBody DealRequestDto dealRequestDto) {
        DealResponseDto importedDeal = service.importDeal(dealRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(importedDeal);
    }
}
