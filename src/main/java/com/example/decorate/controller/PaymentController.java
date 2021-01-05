package com.example.decorate.controller;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.BarionMessage;
import com.example.decorate.domain.dto.OrderDetails;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.services.MailService;
import com.example.decorate.services.OrderService;
import com.example.decorate.services.PaymentService;
import com.example.decorate.services.ProductKeyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/public/payment")
@Slf4j
public class PaymentController {

    private PaymentService paymentService;
    private ProductKeyService productKeyService;
    private OrderService orderService;
    private MailService mailService;

    @Autowired
    public PaymentController(PaymentService paymentService, ProductKeyService productKeyService, OrderService orderService, MailService mailService) {
        this.paymentService = paymentService;
        this.productKeyService = productKeyService;
        this.orderService = orderService;
        this.mailService = mailService;
    }

    @GetMapping
    public ResponseEntity getResult() {
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
        Long orderDatabaseId = this.orderService.saveOrder(orderDto, orderId);
        List<Long> productIds = this.orderService.getProductIds(orderDto);
        List<ProductKey> keyholders = this.productKeyService.getKeyholders(productIds);
        List<ProductListItem> items = this.productKeyService.getProducts(keyholders);

        return new ResponseEntity(this.paymentService.processOrder(orderDto, items, orderId, paymentId, orderDatabaseId), HttpStatus.OK);
    }

    @PostMapping("/orderRequest")
    public ResponseEntity makeOrder(@Valid @RequestBody OrderDto orderDto) {
        String orderId = this.paymentService.generateOrderId();
        this.orderService.saveOrder(orderDto, orderId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/checkPaymentStatus")
    public ResponseEntity<BarionMessage> complete(@RequestBody String paymentId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.paymentService.checkStatus(paymentId));
    }

    @PostMapping("/barion")
    public ResponseEntity checkBarionPayment(@RequestBody String id) throws JsonProcessingException {
        log.info(id);
        this.paymentService.completeOrder(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity orderEmail() throws MessagingException {
        OrderHistory order = this.orderService.findOrder(17L);
        ShippingDetails shippingDetails = this.orderService.findShippingDetails(17L);
        BillingDetails billingDetails = this.orderService.findBillingDetails(17L);
        PaymentHistory payment = this.paymentService.findPaymentWithOrderNumber(17L);

        //new OrderDetails(payment, order, shippingDetails, billingDetails;
        return new ResponseEntity(mailService.sendEmailAboutOrder(), HttpStatus.OK);
    }
}
