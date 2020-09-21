package com.example.decorate.controller;

import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.domain.dto.order.ItemAndQty;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.service.KeyHolderService;
import com.example.decorate.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "https://secure.test.barion.com/", maxAge = 3600)
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    private PaymentService paymentService;
    private KeyHolderService keyHolderService;

    @Autowired
    public PaymentController(PaymentService paymentService, KeyHolderService keyHolderService) {
        this.paymentService = paymentService;
        this.keyHolderService = keyHolderService;
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

    @CrossOrigin(origins = "https://secure.test.barion.com/pay")
    @PostMapping("/orderRequest")
    public void makeOrder(@RequestBody OrderDto orderDto, HttpServletResponse httpServletResponse) throws JsonProcessingException, URISyntaxException {
        List<Long> ids = new ArrayList<>();
        for (ItemAndQty itemAndQty : orderDto.getItemId()) {
            ids.add(itemAndQty.getId());
        }
        List<ProductListItem> items = this.keyHolderService.getProducts(this.keyHolderService.getKeyholders(ids));
    /*    URI uri = new URI(this.paymentService.processOrder(orderDto, items));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(this.paymentService.processOrder(orderDto, items));*/
    httpServletResponse.setHeader("Location",this.paymentService.processOrder(orderDto, items));

    httpServletResponse.setStatus(302);

    }
}
