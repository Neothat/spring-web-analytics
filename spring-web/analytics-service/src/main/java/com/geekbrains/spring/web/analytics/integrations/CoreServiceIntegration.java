package com.geekbrains.spring.web.analytics.integrations;

import com.geekbrains.spring.web.api.core.OrderDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {

    private final WebClient coreServiceWebClient;

    public List<OrderDto> getAllOrders() {
        return coreServiceWebClient.get()
                .uri("/api/v1/orders/allOrders")
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    public Optional<ProductDto> findById(Long id) {
        return Optional.ofNullable(coreServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block());
    }
}
