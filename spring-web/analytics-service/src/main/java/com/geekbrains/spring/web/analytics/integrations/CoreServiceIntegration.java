package com.geekbrains.spring.web.analytics.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {

    private final WebClient coreServiceWebClient;

    public List<ProductDto> getMostPurchased() {
        return coreServiceWebClient.get()
                .uri("/api/v1/products/mostPurchased")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    public List<ProductDto> getMostStackable() {
        return coreServiceWebClient.get()
                .uri("/api/v1/products/mostStackable")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }
}
