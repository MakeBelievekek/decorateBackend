package com.example.decorate.services;

import com.example.decorate.domain.OrderHistory;
import com.example.decorate.domain.PaymentHistory;
import com.example.decorate.domain.PaymentStatus;
import com.example.decorate.domain.Product;
import com.example.decorate.domain.dto.*;
import com.example.decorate.domain.dto.order.PaymentAndOrderDto;
import com.example.decorate.repositorys.OrderHistoryRepository;
import com.example.decorate.repositorys.PaymentHistoryRepository;
import com.example.decorate.repositorys.ShippingOptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@Transactional
public class PaymentService {

    private final RestTemplate restTemplate;
    private final ShippingOptionRepository shippingOptionRepository;
    private final String startUrl = "https://api.test.barion.com/v2/Payment/Start";
    private final String callBack = "https://api.test.barion.com/v2/Payment/GetPaymentState/";
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderAndPaymentEmailProcessService orderAndPaymentEmailProcessService;

    public PaymentService(RestTemplateBuilder restTemplateBuilder,
                          ShippingOptionRepository shippingOptionRepository,
                          PaymentHistoryRepository paymentHistoryRepository,
                          OrderHistoryRepository orderHistoryRepository,
                          OrderAndPaymentEmailProcessService orderAndPaymentEmailProcessService) {
        this.restTemplate = restTemplateBuilder.build();
        this.shippingOptionRepository = shippingOptionRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderAndPaymentEmailProcessService = orderAndPaymentEmailProcessService;
    }

    public List<AptListItem> getAtp() throws JsonProcessingException {

        Map<String, List<String>> citys = new HashMap<>();
        List<AptListItem> items = new ArrayList<>();
        String url = "https://api.csomagvarazslo.hu/v2/apt";
        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(url, String.class);
        JsonNode jsonNode = mapper.readTree(Objects.requireNonNull(responseEntity.getBody()));

        List<Atp> objects = mapper.readValue(jsonNode.toString(), new TypeReference<List<Atp>>() {
        });

        for (Atp valami : objects) {
            if (!citys.containsKey(valami.getCity())) {
                List<String> citysList = new ArrayList<>();
                citysList.add(valami.getName());
                citys.put(valami.getCity(), citysList);
            } else {
                citys.get(valami.getCity()).add(valami.getName());
            }
        }

        for (Map.Entry<String, List<String>> stringListEntry : citys.entrySet()) {
            AptListItem item = new AptListItem();
            item.setCity(stringListEntry.getKey());
            item.setNames(stringListEntry.getValue());
            items.add(item);
        }
        return items;
    }

    public List<Items> getItems(List<Product> products) {
        List<Items> itemArray = new ArrayList<>();
        products.forEach(product -> itemArray.add(new Items(product)));
        System.out.println(itemArray);
        return itemArray;
    }

    public List<OrderHistory> test() {
        return this.orderHistoryRepository.findAll();
    }

    public ResponseEntity processOrder(List<Product> products, OrderHistory orderHistory) throws JsonProcessingException {
        String paymentId = generatePaymentId();
        int totalPrice = orderHistory.getTotalPrice();
        String[] funding = {"All"};
        PaymentData paymentData = new PaymentData();
        paymentData.setPOSKey("5bdaeb94f3a44cdd91a644b73354fc63");
        paymentData.setPaymentType("Immediate");
        paymentData.setGuestCheckout(true);
        paymentData.setFundingSources(funding);
        paymentData.setPaymentRequestId(paymentId);
        paymentData.setOrderNumber(orderHistory.getOrderId());
        paymentData.setShippingAddress(null);
        paymentData.setRedirectUrl("https://textilgarden.hu");
        //paymentData.setRedirectUrl("http://localhost:4200");
        paymentData.setCallbackUrl("https://textilgarden.hu/api/public/payment/barion");

        paymentData.setLocale("hu-HU");
        paymentData.setCurrency("HUF");
        paymentData.setPaymentWindow("00:30:00");
        paymentData.setTransactions(createTransactionArray(getItems(products), totalPrice));

        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(paymentData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(message, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(this.startUrl, httpEntity, String.class);
        JsonNode jsonNode = mapper.readTree(Objects.requireNonNull(responseEntity.getBody()));
        String jsonUrl = jsonNode.get("PaymentId").asText();
        PaymentHistory paymentHistory = this.paymentHistoryRepository.findByOrderHistory(orderHistory.getId());
        paymentHistory.setPaymentId(jsonUrl);
        paymentHistory.setPaymentRequestId(paymentId);
        this.paymentHistoryRepository.save(paymentHistory);

        return responseEntity;
    }

    public List<Transactions> createTransactionArray(List<Items> items, int totalPrice) {
        List<Transactions> transactionsArray = new ArrayList<>();
        Transactions transactions = new Transactions();
        transactions.setPOSTransactionId("tr-25");
        transactions.setPayee("zsolt.preseka@gmail.com");
        transactions.setTotal(totalPrice);
        transactions.setItems(items);
        transactionsArray.add(transactions);
        return transactionsArray;
    }

    public void completeOrder(String paymentId) throws JsonProcessingException {
        String[] payment = paymentId.split("&");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(Objects.requireNonNull(barionProcessing(payment[0]).getBody()));
        String status = jsonNode.get("Status").asText();
        log.info(String.valueOf(jsonNode));
        PaymentHistory paymentHistory = this.paymentHistoryRepository.findByPaymentId(payment[0].split("=")[1]);
        PaymentStatus paymentStatus = status.equals(PaymentStatus.SUCCEEDED.getType()) ? PaymentStatus.SUCCEEDED : PaymentStatus.CANCELED;
        paymentHistory.setStatus(paymentStatus);

        this.paymentHistoryRepository.save(paymentHistory);
    }

    public ResponseEntity<String> barionProcessing(String paymentId) {
        String url = "https://api.test.barion.com/v2/Payment/GetPaymentState/?poskey=5bdaeb94f3a44cdd91a644b73354fc63&" + paymentId;
        return this.restTemplate.getForEntity(url, String.class);
    }

    public PaymentAndOrderDto checkStatus(String paymentId) throws MessagingException {
        PaymentHistory payment = this.paymentHistoryRepository.findByPaymentId(paymentId);
        if (payment.getStatus() == PaymentStatus.SUCCEEDED)
            this.orderAndPaymentEmailProcessService.orderEmail(payment);

        return new PaymentAndOrderDto(payment.getStatus().getType(), payment.getOrderHistory().getOrderId());
    }

    public PaymentHistory findPayment(Long id) {
        if (this.paymentHistoryRepository.findById(id).isPresent())
            return this.paymentHistoryRepository.findById(id).get();
        else
            return null;
    }

    public PaymentHistory findPaymentWithOrderNumber(Long id) {
        return this.paymentHistoryRepository.findByOrderHistory(id);
    }

    public String generatePaymentId() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        return ft.format(dNow);
    }
}
