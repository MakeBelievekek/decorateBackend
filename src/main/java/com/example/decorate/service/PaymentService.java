package com.example.decorate.service;

import com.example.decorate.domain.ShippingDetails;
import com.example.decorate.domain.dto.*;
import com.example.decorate.domain.dto.order.ItemAndQty;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.domain.dto.order.ShippingOrderDto;
import com.example.decorate.domain.dto.order.UserOrderDto;
import com.example.decorate.repository.ShippingOptionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class PaymentService {

    private final RestTemplate restTemplate;
    private ShippingOptionRepository shippingOptionRepository;
    private final String startUrl = "https://api.test.barion.com/v2/Payment/Start";

    public PaymentService(RestTemplateBuilder restTemplateBuilder, ShippingOptionRepository shippingOptionRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.shippingOptionRepository = shippingOptionRepository;
    }

    public String generatePaymentId() {
        //  String uniqueID = UUID.randomUUID().toString();
        //  return UUID.randomUUID();
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        return datetime;
    }

    public String generateOrderId() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
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

    public List<Items> getItems(OrderDto orderDto, List<ProductListItem> products) {
        List<Items> itemArray = new ArrayList<>();
        for (ProductListItem product : products) {
            for (ItemAndQty itemAndQty : orderDto.getItemId()) {
                if (itemAndQty.getId().equals(product.getId())) {
                    itemArray.add(new Items(product, itemAndQty.getQty()));
                }
            }
        }
        return itemArray;
    }

    public ShippingDetails createShippingAddress(ShippingOrderDto shippingOrderDto, UserOrderDto userOrderDto) {

        ShippingDetails shippingAddress = new ShippingDetails(shippingOrderDto, userOrderDto);
        return shippingAddress;
    }

    public List<Transactions> createTransactionArray(List<Items> items) {
        int totalPrice = 0;
        List<Transactions> transactionsArray = new ArrayList<>();
        Transactions transactions = new Transactions();
        transactions.setPOSTransactionId("tr-25");
        transactions.setPayee("zsolt.preseka@gmail.com");
        for (Items item : items) {
            totalPrice += item.getItemTotal();
        }

        transactions.setTotal(totalPrice);
        transactions.setItems(items);
        transactionsArray.add(transactions);
        return transactionsArray;
    }

    public String processOrder(OrderDto orderDto, List<ProductListItem> products) throws JsonProcessingException {
        String paymentId = generatePaymentId();
        String[] funding = {"All"};
        String url = "https://api.test.barion.com/v2/Payment/Start";

        PaymentData paymentData = new PaymentData();
        paymentData.setPOSKey("5bdaeb94f3a44cdd91a644b73354fc63");
        paymentData.setPaymentType("Immediate");
        paymentData.setGuestCheckout(true);
        paymentData.setFundingSources(funding);
        paymentData.setPaymentRequestId(paymentId);
        paymentData.setOrderNumber(generateOrderId());
        paymentData.setShippingDetails(createShippingAddress(orderDto.getShipping(), orderDto.getUser()));
        paymentData.setRedirectUrl("http://localhost:4200/");
        paymentData.setCallbackUrl("http://localhost:4200/basket");
        paymentData.setLocale("hu-HU");
        paymentData.setCurrency("HUF");
        paymentData.setPaymentWindow("00:30:00");
        paymentData.setTransactions(createTransactionArray(getItems(orderDto, products)));

        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(paymentData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(message, headers);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url, httpEntity, String.class);


        JsonNode jsonNode = mapper.readTree(Objects.requireNonNull(responseEntity.getBody()));
        String jsonUrl = jsonNode.get("GatewayUrl").asText();

        return jsonUrl;
    }

 /*   public ResponseEntity<String> payment() throws JsonProcessingException {

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
    }*/


}
