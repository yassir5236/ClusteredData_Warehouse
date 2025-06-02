//package com.progresssoft.fxdealsimporter.service;
//
//import com.progresssoft.fxdealsimporter.model.Entity.Deal;
//import com.progresssoft.fxdealsimporter.repository.DealRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//import static org.mockito.Mockito.*;
//
//public class DealServiceTest {
//
//    private DealRepository mockRepository;
//    private DealService dealService;
//
//    @BeforeEach
//    void setUp() {
//        mockRepository = Mockito.mock(DealRepository.class);
//        dealService = new DealService(mockRepository);
//    }
//
//    @Test
//    void testImportDealOnce() {
//        Deal deal = new Deal(
//                "D-001",
//                "USD",
//                "EUR",
//                LocalDateTime.now(),
//                new BigDecimal("1000.00")
//        );
//
//        when(mockRepository.existsByDealUniqueId("D-001")).thenReturn(false);
//
//        dealService.importDeals(Collections.singletonList(deal));
//
//        verify(mockRepository, times(1)).save(deal);
//    }
//}
