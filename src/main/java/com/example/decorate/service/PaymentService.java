package com.example.decorate.service;

import com.example.decorate.domain.dto.Items;
import com.example.decorate.domain.dto.PaymentData;
import com.example.decorate.domain.dto.ShippingAddress;
import com.example.decorate.domain.dto.Transactions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class PaymentService {

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<String> payment() throws JsonProcessingException {

        String url = "https://api.test.barion.com/v2/Payment/Start";
        ObjectMapper mapper = new ObjectMapper();
        String[] funding = {"All"};
        List<Items> itemArray = new ArrayList<>();
        Items items = new Items();
        items.setName("Digital Camera");
        items.setDescription("Canon D500");
        items.setQuantity(1);
        items.setSKU("cn-d500-fxc3");
        items.setUnit("pcs");
        items.setItemTotal(300);
        items.setUnitPrice(300);
        itemArray.add(items);
        System.out.println(itemArray.toString());
        System.out.println(items.toString());

        List<Transactions> transactionsArray = new ArrayList<>();

        Transactions transactions = new Transactions();
        transactions.setPOSTransactionId("tr-25");
        transactions.setPayee("zsolt.preseka@gmail.com");
        transactions.setTotal(400);
        transactions.setItems(itemArray);
        transactionsArray.add(transactions);
        System.out.println(transactions.toString());

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCity("Vienna");
        shippingAddress.setCountry("AT");
        shippingAddress.setRegion(null);
        shippingAddress.setFullName("Mario Van");
        shippingAddress.setStreet("szel utca 19");
        shippingAddress.setZip("1035");

        PaymentData paymentData = new PaymentData();
        paymentData.setPOSKey("5bdaeb94f3a44cdd91a644b73354fc63");
        paymentData.setPaymentType("Immediate");
        paymentData.setGuestCheckout(true);
        paymentData.setFundingSources(funding);
        paymentData.setPaymentRequestId("payment-25");
        paymentData.setOrderNumber("order-25");
        paymentData.setPayerHint("joseph-schmidt@example.com");
        paymentData.setShippingAddress(shippingAddress);
        paymentData.setRedirectUrl("http://shop.example.com/danke-fur-den-kauf");
        paymentData.setCallbackUrl("http://shop.example.com/api/callback");
        paymentData.setLocale("hu-HU");
        paymentData.setCurrency("HUF");
        paymentData.setPaymentWindow("00:30:00");
        paymentData.setTransactions(transactionsArray);

        String message = mapper.writeValueAsString(paymentData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(message, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url, httpEntity, String.class);

        JsonNode jsonNode = mapper.readTree(Objects.requireNonNull(responseEntity.getBody()));
        String jsonUrl = jsonNode.get("GatewayUrl").asText();


        return responseEntity;
    }


    public String getPlainJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String funding[] = {"All"};
        List<Items> itemArray = new ArrayList<>();
        Items items = new Items();
        items.setName("Digital Camera");
        items.setDescription("Canon D500");
        items.setQuantity(1);
        items.setSKU("cn-d500-fxc3");
        items.setUnit("pcs");
        items.setItemTotal(300);
        items.setUnitPrice(300);
        itemArray.add(items);
        System.out.println(itemArray.toString());
        System.out.println(items.toString());

        List<Transactions> transactionsArray = new ArrayList<>();

        Transactions transactions = new Transactions();
        transactions.setPOSTransactionId("tr-25");
        transactions.setPayee("eshop@example.com");
        transactions.setTotal(400);
        transactions.setItems(itemArray);
        transactionsArray.add(transactions);
        System.out.println(transactions.toString());

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCity("Budapest");
        shippingAddress.setCountry("Vienna");
        shippingAddress.setRegion("");
        shippingAddress.setFullName("Mario van");
        shippingAddress.setStreet("szél utca 19");
        shippingAddress.setZip("1035");

        PaymentData paymentData = new PaymentData();
        paymentData.setPOSKey("5bdaeb94f3a44cdd91a644b73354fc63");
        paymentData.setPaymentType("Immediate");
        paymentData.setGuestCheckout(true);
        paymentData.setFundingSources(funding);
        paymentData.setPaymentRequestId("payment-25");
        paymentData.setOrderNumber("order-25");
        paymentData.setPayerHint("joseph-schmidt@example.com");
        paymentData.setShippingAddress(shippingAddress);
        paymentData.setRedirectUrl("http://shop.example.com/danke-fur-den-kauf");
        paymentData.setCallbackUrl("http://shop.example.com/api/callback");
        paymentData.setLocale("hun-HUN");
        paymentData.setCurrency("HUF");

        paymentData.setPaymentWindow("00:30:00");
        paymentData.setTransactions(transactionsArray);
        return mapper.writeValueAsString(paymentData);
    }

    public Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            // you can change format of date
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }


}
