package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.repositorys.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.decorate.domain.PaymentOptionEnum.CASH_ON_DELIVERY;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class OrderService {

    private final BillingDetailsRepository billingDetailsRepository;
    private final ShippingDetailsRepository shippingDetailsRepository;
    private final ShippingOptionRepository shippingOptionRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final ProductKeyService productKeyService;
    private final OrderAndPaymentEmailProcessService orderAndPaymentEmailProcessService;

    public OrderHistory orderProcessing(OrderDto orderDto) throws MessagingException {
        String orderId = generateOrderId();
        List<Product> products = getProductForOrder(orderDto);
        OrderHistory orderHistory = saveOrder(orderDto, orderId, products);
        if (!orderDto.getPaymentOption().equals(PaymentOptionEnum.CREDIT.getOption()))
            this.orderAndPaymentEmailProcessService.orderEmail(orderHistory);
        return orderHistory;
    }

    public OrderHistory saveOrder(OrderDto orderDto, String orderId, List<Product> products) {
        ShippingDetails shippingDetails = new ShippingDetails(orderDto);
        this.shippingDetailsRepository.save(shippingDetails);
        BillingDetails billingDetails = new BillingDetails(orderDto);
        this.billingDetailsRepository.save(billingDetails);
        OrderHistory orderHistory = new OrderHistory(orderDto, products, orderId, billingDetails, shippingDetails);
        int totalPrice = getTotalPrice(shippingDetails, orderDto.getPaymentOption(), products);
        orderHistory.setTotalPrice(totalPrice);
        this.orderHistoryRepository.save(orderHistory);
        shippingDetails.setOrderHistory(orderHistory);
        billingDetails.setOrderHistory(orderHistory);
        this.paymentHistoryRepository.save(new PaymentHistory(orderHistory, PaymentStatus.PENDING));
        return orderHistory;
    }

    public OrderHistory findOrder(Long id) {
        if (this.orderHistoryRepository.findById(id).isPresent())
            return this.orderHistoryRepository.findById(id).get();
        else
            return null;
    }

    public OrderHistory findOrder(String paymentId) {
        System.out.println(paymentId);
        if (this.orderHistoryRepository.findByPaymentId(paymentId).isPresent())
            return this.orderHistoryRepository.findByPaymentId(paymentId).get();
        else
            return null;
    }


    public ShippingDetails findShippingDetails(Long id) {
        return this.shippingDetailsRepository.findByOrderId(id);
    }

    public BillingDetails findBillingDetails(Long id) {
        return this.billingDetailsRepository.findByOrderId(id);
    }

    public int getTotalPrice(ShippingDetails shippingDetails, String paymentOption, List<Product> products) {
        return getShippingPrice(shippingDetails) + getPaymentPrice(paymentOption) + productsPrice(products);
    }

    public int getShippingPrice(ShippingDetails shippingDetails) {
        return this.shippingOptionRepository.findByTypeOfDelivery(shippingDetails.getShipMethod()).getPrice();
    }

    public int getPaymentPrice(String paymentOption) {
        return paymentOption.equals(CASH_ON_DELIVERY.getOption()) ? CASH_ON_DELIVERY.getPrice() : 0;
    }

    public int productsPrice(List<Product> products) {
        return products.stream().mapToInt(product -> product.getQuantity() * product.getPrice()).sum();
    }

    public String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    public List<Product> getProductForOrder(OrderDto orderDto) {
        List<Product> products = new ArrayList<>();
        orderDto.getItemId()
                .forEach(itemAndQty -> {
                    ProductListItem prod = this.productKeyService.getProd(itemAndQty.getId());
                    products.add(new Product(prod, itemAndQty.getQty()));
                });
        return products;
    }
}
