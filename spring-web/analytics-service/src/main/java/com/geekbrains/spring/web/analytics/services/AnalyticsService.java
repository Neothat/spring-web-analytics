package com.geekbrains.spring.web.analytics.services;

import com.geekbrains.spring.web.api.core.OrderDto;
import com.geekbrains.spring.web.api.core.OrderItemDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.analytics.integrations.CoreServiceIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final CoreServiceIntegration coreServiceIntegration;

    public List<ProductDto> getMostBoughtProducts() {
        List<OrderDto> allOrders = coreServiceIntegration.getAllOrders();
        Map<Long, Long> idToQuantityRelation = allOrders.stream()
                .map(OrderDto::getItems)
                .flatMap(Collection::stream)
                .map(OrderItemDto::getProductId)
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));
        return idToQuantityRelation.entrySet().stream()
                .sorted((o1, o2) -> -o1.getValue().compareTo(o2.getValue()))
                .limit(5)
                .map(entry -> coreServiceIntegration.findById(entry.getKey())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + entry.getKey())))
                .collect(Collectors.toList());
    }

    public List<ProductDto> getMostStackableProducts() {
        List<OrderDto> allOrders = coreServiceIntegration.getAllOrders();
        Map<Long, Integer> idToQuantityRelation = allOrders.stream()
                .map(OrderDto::getItems)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(OrderItemDto::getProductId, Collectors.summingInt(OrderItemDto::getQuantity)));
        return idToQuantityRelation.entrySet().stream()
                .sorted((o1, o2) -> -o1.getValue().compareTo(o2.getValue()))
                .limit(5)
                .map(entry -> coreServiceIntegration.findById(entry.getKey())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + entry.getKey())))
                .collect(Collectors.toList());
    }
}
