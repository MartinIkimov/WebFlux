package com.example.orderservice.controller;

import com.example.orderservice.dto.PurchaseOrderRequestDto;
import com.example.orderservice.dto.PurchaseOrderResponseDto;
import com.example.orderservice.service.OrderFulfillmentService;
import com.example.orderservice.service.OrderQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

    private final OrderFulfillmentService orderFulfillmentService;
    private final OrderQueryService queryService;

    public PurchaseOrderController(OrderFulfillmentService orderFulfillmentService, OrderQueryService queryService) {
        this.orderFulfillmentService = orderFulfillmentService;
        this.queryService = queryService;
    }

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
        return this.orderFulfillmentService.processOrder(requestDtoMono)
                .map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("user/{userId}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserId(@PathVariable int userId) {
        return this.queryService.getProductsByUserId(userId);
    }


}
