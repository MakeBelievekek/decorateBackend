package com.example.decorate.controller;

import com.example.decorate.domain.OrderHistory;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.domain.dto.order.ItemAndQty;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.services.ProductKeyService;
import com.example.decorate.services.OrderService;
import com.example.decorate.services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/public/payment")
@Slf4j
public class PaymentController {

    private PaymentService paymentService;
    private ProductKeyService productKeyService;
    private OrderService orderService;

    @Autowired
    public PaymentController(PaymentService paymentService, ProductKeyService productKeyService, OrderService orderService) {
        this.paymentService = paymentService;
        this.productKeyService = productKeyService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity getResult() throws JsonProcessingException {
        //  return new ResponseEntity(this.paymentService.payment(), HttpStatus.ACCEPTED);
        return new ResponseEntity(this.paymentService.generatePaymentId(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/atp")
    public ResponseEntity getAllAtp() throws JsonProcessingException {

        return new ResponseEntity(this.paymentService.getAtp(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/orderRequestBarion")
    public ResponseEntity makeOrderWithBarion(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        String paymentId = this.paymentService.generatePaymentId();
        String orderId = this.paymentService.generateOrderId();
        Long orderDatabaseId;
        List<Long> ids = new ArrayList<>();
        for (ItemAndQty itemAndQty : orderDto.getItemId()) {
            ids.add(itemAndQty.getId());
        }
        List<ProductListItem> items = this.productKeyService.getProducts(this.productKeyService.getKeyholders(ids));
        orderDatabaseId = this.orderService.saveOrder(orderDto, orderId);
        return new ResponseEntity(this.paymentService.processOrder(orderDto, items, orderId, paymentId, orderDatabaseId), HttpStatus.OK);
    }

    @PostMapping("/orderRequest")
    public ResponseEntity makeOrder(@Valid @RequestBody OrderDto orderDto) {
        String orderId = this.paymentService.generateOrderId();
        this.orderService.saveOrder(orderDto, orderId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/test")
    public List<OrderHistory> test() {

        return this.paymentService.test();
    }

    @PostMapping("/paymentComplete")
    public ResponseEntity complete(@RequestBody String paymentId) {
        this.paymentService.completeOrder(paymentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/barion")
    public ResponseEntity checkBarionPayment(@RequestParam(name = "paymentId") String id) {
        System.out.println("első állomás");
        System.out.println(id);
        return new ResponseEntity(this.paymentService.barionProcessing(id), HttpStatus.OK);
    }
}
