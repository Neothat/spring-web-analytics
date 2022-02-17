package com.geekbrains.spring.web.analytics.controllers;

import com.geekbrains.spring.web.analytics.services.AnalyticsService;
import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private AnalyticsService analyticsService;

    @GetMapping("/mostBought")
    public List<ProductDto> getMostBoughtProducts() {
        return analyticsService.getMostBoughtProducts();
    }

    @GetMapping("/mostStackable")
    public List<ProductDto> getMostStackableProducts() {
        return analyticsService.getMostStackableProducts();
    }

    @Autowired
    public void setAnalyticsService(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }
}
