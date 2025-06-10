package com.appsdeveloperblog.ws.products;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createTopic(){
        return TopicBuilder.name("product-created-events-topic")
                .partitions(3) // xử lý song song
                .replicas(3) // 3 rep tức phải có ít nhất 3 broker hoạt động
                .configs(Map.of("min.insync.replicas","2"))
                //Đảm bảo ít nhất 2 trong số 3 replica phải đồng bộ trước khi nhận dữ liệu (an toàn dữ liệu khi mất 1 broker).
                .build();
    }
}
