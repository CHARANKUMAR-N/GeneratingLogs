package com.logs.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logs.service.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class LogController {

    private final OrderService orderService;

    public LogController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/generate-error")
    public String generateError() {
        orderService.generateError();
        orderService.generateMoreErrors();
        return "Error generated and stored in Elasticsearch";
    }
}