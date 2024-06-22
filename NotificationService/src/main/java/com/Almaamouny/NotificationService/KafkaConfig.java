package com.Almaamouny.NotificationService;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "groupId";

    @Bean
    public ConsumerFactory<String, OrderSuccessful> orderSuccessfulConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());

        JsonDeserializer<OrderSuccessful> deserializer = new JsonDeserializer<>(OrderSuccessful.class);
        deserializer.addTrustedPackages("com.Almaamouny.NotificationService");
        deserializer.setRemoveTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderSuccessful> orderSuccessfulKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderSuccessful> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderSuccessfulConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PaymentSuccess> paymentSuccessConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());

        JsonDeserializer<PaymentSuccess> deserializer = new JsonDeserializer<>(PaymentSuccess.class);
        deserializer.addTrustedPackages("com.Almaamouny.NotificationService");
        deserializer.setRemoveTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentSuccess> paymentSuccessKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PaymentSuccess> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(paymentSuccessConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Integer> integerConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        JsonDeserializer<Integer> deserializer = new JsonDeserializer<>(Integer.class);
        deserializer.setRemoveTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Integer> integerKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Integer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(integerConsumerFactory());
        return factory;
    }

    @Bean
    public NewTopic orderPlacedTopic() {
        return new NewTopic("OrderPlaced", 1, (short) 1);
    }

    @Bean
    public NewTopic paymentConfirmationTopic() {
        return new NewTopic("PaymentConfirmation", 1, (short) 1);
    }

    @Bean
    public NewTopic activateAccountTopic() {
        return new NewTopic("ActivateAccount", 1, (short) 1);
    }
}
