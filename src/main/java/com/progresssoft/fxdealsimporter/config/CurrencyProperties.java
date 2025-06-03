package com.progresssoft.fxdealsimporter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "valid")
public class CurrencyProperties {
    private List<String> currencies;

    public Set<String> getCurrencySet() {
        return currencies.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toUnmodifiableSet());
    }
}
