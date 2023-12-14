package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateTopic() {
        Map<String, String> configurations = new HashMap<>();
        //delete: message after a time / compact: mantein the most actual message
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //time to retain message (miliseconds) - default -1 (never delete)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //1 day
        //maximum size of segment inside topic (bytes) - default 1Gb
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // 1Gb
        //max size of each message - default 1Mb
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "10485760"); //10 Mb

        return TopicBuilder.name("topic-in-spring-boot")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
