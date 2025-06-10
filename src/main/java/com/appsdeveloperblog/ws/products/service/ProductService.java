package com.appsdeveloperblog.ws.products.service;

import com.appsdeveloperblog.ws.products.rest.CreateProductRestModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    // Dạng của <key,value>
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public String createProduct(CreateProductRestModel productRestModel) throws Exception {
        String productId = UUID.randomUUID().toString();
        // TODO: lưu vào DB trước khi publish events

        // Tạo event
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, productRestModel.getTitle(),
                productRestModel.getPrice(), productRestModel.getQuantity());

        // topic, key, value
        SendResult<String, ProductCreatedEvent> future =
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();


        log.info("******* Returning product id");

        return productId;
    }
}
