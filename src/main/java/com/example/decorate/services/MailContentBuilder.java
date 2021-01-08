package com.example.decorate.services;

import com.example.decorate.domain.dto.OrderDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }

    public String buildOrder(OrderDetails orderDto) {
        Context context = new Context();
        context.setVariable("lastName", orderDto.getOrderHistory().getLastName());
        context.setVariable("firstName", orderDto.getOrderHistory().getFirstName());
        context.setVariable("products", orderDto.getOrderHistory().getProducts());
        context.setVariable("totalPrice", orderDto.getOrderHistory().getTotalPrice());
        context.setVariable("shipMethod", orderDto.getShippingDetails().getShipMethod());
        context.setVariable("paymentType", orderDto.getOrderHistory().getPaymentType());
        context.setVariable("shipInfo", orderDto.getShippingDetails().getShipInfo());
        context.setVariable("subtotal", orderDto.getSubTotalPrice());
        context.setVariable("billCity", orderDto.getBillingDetails().getCity());
        context.setVariable("billAddress", orderDto.getBillingDetails().getAddress1());
        context.setVariable("billZip", orderDto.getBillingDetails().getZip());
        context.setVariable("phone", orderDto.getBillingDetails().getPhoneNumber());
        context.setVariable("email", orderDto.getOrderHistory().getEmail());
        context.setVariable("shipCity", orderDto.getShippingDetails().getCity());
        context.setVariable("shipAddress", orderDto.getShippingDetails().getAddress());
        context.setVariable("shipZip", orderDto.getShippingDetails().getZip());
        return templateEngine.process("order", context);
    }


}
