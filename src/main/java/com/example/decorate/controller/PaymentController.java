package com.example.decorate.controller;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ResponseMessage;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.domain.dto.order.PaymentAndOrderDto;
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

        return new ResponseEntity(this.paymentService.getAtp(), HttpStatus.OK);
    }

    @PostMapping("/processOrder")
    public ResponseEntity processOrder(@Valid @RequestBody OrderDto orderDto) throws JsonProcessingException, MessagingException {
        OrderHistory orderHistory = this.orderService.orderProcessing(orderDto);
        if (orderDto.getPaymentOption().equals(PaymentOptionEnum.CREDIT.getOption()))
            return new ResponseEntity(this.paymentService.processOrder(orderHistory.getProducts(), orderHistory), HttpStatus.CREATED);
        return new ResponseEntity(new ResponseMessage(orderHistory.getOrderId()), HttpStatus.CREATED);
    }

    @GetMapping("/orderNumber/{id}")
    public ResponseMessage getOrderByOrderNumber(@PathVariable("id") String id) {
        return new ResponseMessage(this.orderService.findOrder(id).getOrderId());
    }

    @PostMapping("/checkPaymentStatus")
    public ResponseEntity<PaymentAndOrderDto> complete(@RequestBody String paymentId) throws MessagingException {
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

  /*  @PostMapping("/email")
    public ResponseEntity orderEmail() throws MessagingException {
        OrderHistory order = this.orderService.findOrder(17L);
        ShippingDetails shippingDetails = this.orderService.findShippingDetails(17L);
        BillingDetails billingDetails = this.orderService.findBillingDetails(17L);
        PaymentHistory payment = this.paymentService.findPaymentWithOrderNumber(17L);
        mailService.sendEmailAboutOrder(new OrderDetails(payment, order, shippingDetails, billingDetails));
        return new ResponseEntity(HttpStatus.OK);
    }*/
}
