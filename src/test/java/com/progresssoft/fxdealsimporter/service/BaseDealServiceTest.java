package com.progresssoft.fxdealsimporter.service;
import com.progresssoft.fxdealsimporter.HandelException.InvalidCurrencyException;
import com.progresssoft.fxdealsimporter.HandelException.RequestAlreadyExistException;
import com.progresssoft.fxdealsimporter.dto.DealRequestDto;
import com.progresssoft.fxdealsimporter.dto.DealResponseDto;
import com.progresssoft.fxdealsimporter.entity.Deal;
import com.progresssoft.fxdealsimporter.mapper.DealMapper;
import com.progresssoft.fxdealsimporter.repository.DealRepository;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseDealServiceTest {

    @Mock
    private DealMapper dealMapper;

    @Mock
    private DealRepository dealRepository;

    @Mock
    private CurrencyChecker currencyChecker;

    @InjectMocks
    private BaseDealService baseDealService;

    private AutoCloseable closeable;

    private DealRequestDto dealRequest;
    private Deal dealEntity;
    private DealResponseDto dealResponse;

    private final LocalDateTime fixedTimestamp = LocalDateTime.of(2024, 6, 3, 12, 0);

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        dealRequest = new DealRequestDto("DEAL-001", "USD", "EUR", BigDecimal.valueOf(1500.0));

        dealEntity = new Deal(
                "DEAL-001",
                "USD",
                "EUR",
                fixedTimestamp,
                BigDecimal.valueOf(1500.0)
        );

        dealResponse = new DealResponseDto("DEAL-001", "USD", "EUR",fixedTimestamp,BigDecimal.valueOf(1500.0));
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testImportDeal_Success() {

        when(dealRepository.existsById("DEAL-001")).thenReturn(false);
        doNothing().when(currencyChecker).checkCurrencyExchange("USD", "EUR");
        when(dealMapper.toEntity(dealRequest)).thenReturn(dealEntity);
        when(dealRepository.save(dealEntity)).thenReturn(dealEntity);
        when(dealMapper.toResponseEntity(dealEntity)).thenReturn(dealResponse);


        DealResponseDto result = baseDealService.importDeal(dealRequest);


        assertNotNull(result);
        assertEquals("DEAL-001", result.dealId());
        verify(currencyChecker).checkCurrencyExchange("USD", "EUR");
        verify(dealRepository).save(dealEntity);
    }

    @Test
    void testImportDeal_ThrowsExceptionIfExists() {
        when(dealRepository.existsById("DEAL-001")).thenReturn(true);

        RequestAlreadyExistException exception = assertThrows(RequestAlreadyExistException.class, () ->
                baseDealService.importDeal(dealRequest)
        );

        assertEquals("This request already exists", exception.getMessage());
        verify(dealRepository, never()).save(any());
    }

    @Test
    void testImportDeal_CurrencyCheckCalled() {

        when(dealRepository.existsById(any())).thenReturn(false);
        doNothing().when(currencyChecker).checkCurrencyExchange(anyString(), anyString());
        when(dealMapper.toEntity(any())).thenReturn(dealEntity);
        when(dealRepository.save(any())).thenReturn(dealEntity);
        when(dealMapper.toResponseEntity(any())).thenReturn(dealResponse);


        baseDealService.importDeal(dealRequest);


        verify(currencyChecker).checkCurrencyExchange("USD", "EUR");
    }


    @Test
    void givenInvalidCurrencies_whenSave_thenThrowInvalidCurrencyException() {
        doThrow(new InvalidCurrencyException("Invalid from currency format"))
                .when(currencyChecker).checkCurrencyExchange("US", "EUR");

        DealRequestDto invalidCurrencyRequest = new DealRequestDto("DEAL-002", "US", "EUR", BigDecimal.valueOf(1000.0));

        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class, () -> {
            baseDealService.importDeal(invalidCurrencyRequest);
        });

        assertEquals("Invalid from currency format", exception.getMessage());
        verify(currencyChecker).checkCurrencyExchange("US", "EUR");
        verify(dealRepository, never()).save(any());
    }


    @Test
    void givenSameCurrencies_whenSave_thenThrowInvalidCurrencyException() {
        doThrow(new InvalidCurrencyException("fromCurrency [USD] and toCurrency [USD] cannot be the same."))
                .when(currencyChecker).checkCurrencyExchange("USD", "USD");

        DealRequestDto sameCurrencyRequest = new DealRequestDto("DEAL-003", "USD", "USD", BigDecimal.valueOf(1000.0));


        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class, () -> {
            baseDealService.importDeal(sameCurrencyRequest);
        });

        assertTrue(exception.getMessage().contains("cannot be the same"));
        verify(currencyChecker).checkCurrencyExchange("USD", "USD");
        verify(dealRepository, never()).save(any());
    }


}
