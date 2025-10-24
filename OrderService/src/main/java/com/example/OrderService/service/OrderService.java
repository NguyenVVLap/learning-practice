package com.example.OrderService.service;

import com.example.OrderService.dto.OrderDto;
import com.example.OrderService.dto.Product;
import com.example.OrderService.entity.Order;
import com.example.OrderService.entity.OrderDetail;
import com.example.OrderService.feignclient.ProductClient;
import com.example.OrderService.repository.OrderDetailRepository;
import com.example.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private KafkaService kafkaService;

    public List<Order> getAllOrderV1() {
        return orderRepository.findAll();
    }

    public List<OrderDto> getAllOrderV2() {
        List<Order> listOrder = orderRepository.findAll();
        List<OrderDto> listOrderDto = new ArrayList<>();
        for (Order order : listOrder) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setListOrderDetail(order.getListOrderDetail());
            orderDto.setTotalPrice(order.totalPrice());
            listOrderDto.add(orderDto);
        }
        return listOrderDto;
    }

    public ResponseEntity<Product> feignToProduct(long id) {
        return productClient.getProductsWithPageSortByCategory(id);
    }

    public ResponseEntity<Product> getProductWithWebClient(long id) {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8081")
//                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081/products/" + id))
                .build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();
//        WebClient.RequestBodySpec bodySpec = uriSpec.uri(URI.create("/resource"));
        // Define body
//        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(
//                Mono.just(new Foo("name")), Foo.class);

        // Define header
//        WebClient.ResponseSpec responseSpec = headersSpec.header(
//                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
//                .acceptCharset(StandardCharsets.UTF_8)
//                .ifNoneMatch("*")
//                .ifModifiedSince(ZonedDateTime.now())
//                .retrieve();

        // Get response
//        Mono<String> response = headersSpec.exchangeToMono(response -> {
//            if (response.statusCode().equals(HttpStatus.OK)) {
//                return response.bodyToMono(String.class);
//            } else if (response.statusCode().is4xxClientError()) {
//                return Mono.just("Error response");
//            } else {
//                return response.createException()
//                        .flatMap(Mono::error);
//            }
//        });
        return productClient.getProductsWithPageSortByCategory(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        List<OrderDetail> listOrderDetail = order.getListOrderDetail().stream()
                .peek(orderDetail -> orderDetail.setOrder(newOrder)).toList();
        orderDetailRepository.saveAll(order.getListOrderDetail());
        kafkaService.sendMessage("orderService", "Order created");
    }
}
