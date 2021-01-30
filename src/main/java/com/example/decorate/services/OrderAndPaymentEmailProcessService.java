package com.example.decorate.services;

import com.example.decorate.domain.BillingDetails;
import com.example.decorate.domain.OrderHistory;
import com.example.decorate.domain.PaymentHistory;
import com.example.decorate.domain.ShippingDetails;
import com.example.decorate.domain.dto.OrderDetails;
import com.example.decorate.repositorys.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class OrderAndPaymentEmailProcessService {

    private final BillingDetailsRepository billingDetailsRepository;
    private final ShippingDetailsRepository shippingDetailsRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final MailService mailService;

    public void orderEmail(OrderHistory orderHistory) throws MessagingException {
        Long orderId = orderHistory.getId();
        ShippingDetails shippingDetails = this.shippingDetailsRepository.findByOrderId(orderId);
        BillingDetails billingDetails = this.billingDetailsRepository.findByOrderId(orderId);
        PaymentHistory payment = this.paymentHistoryRepository.findByOrderHistory(orderId);
        this.mailService.sendEmailAboutOrder(new OrderDetails(payment, orderHistory, shippingDetails, billingDetails));
    }


    public void orderEmail(PaymentHistory paymentHistory) throws MessagingException {
        Optional<OrderHistory> orderHistory = this.orderHistoryRepository.findByPaymentId(paymentHistory.getPaymentId());
        OrderHistory order = new OrderHistory();
        if (orderHistory.isPresent())
            order = orderHistory.get();
        Long orderId = order.getId();
        ShippingDetails shippingDetails = this.shippingDetailsRepository.findByOrderId(orderId);
        BillingDetails billingDetails = this.billingDetailsRepository.findByOrderId(orderId);
        this.mailService.sendEmailAboutOrder(new OrderDetails(paymentHistory, order, shippingDetails, billingDetails));
    }

}
