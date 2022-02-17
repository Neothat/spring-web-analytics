package com.geekbrains.spring.web.analytics.services;

import com.geekbrains.spring.web.analytics.integrations.CoreServiceIntegration;
import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final CoreServiceIntegration coreServiceIntegration;

    public List<ProductDto> getMostBoughtProducts() {
        return coreServiceIntegration.getMostPurchased();
    }

    public List<ProductDto> getMostStackableProducts() {
        return coreServiceIntegration.getMostStackable();
    }
}
