package com.selcuk.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class CSVConsumer {

    private static String KafkaBrokerEndpoint = "localhost:9093";
    private static String KafkaTopic = "mysql_logs";

    private Consumer<String, String> consumerProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBrokerEndpoint);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "log_consumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Collections.singleton(KafkaTopic));
        return consumer;
    }

    public static void main(String[] args) {
        CSVConsumer csvConsumer = new CSVConsumer();
        csvConsumer.readMessages();
    }

    private void readMessages() {
        final Consumer<String, String> consumer = consumerProperties();
        final int threshold = 1000;
        int noRecordsCount = 0;
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(60));

            if(records.count() == 0) {
                noRecordsCount++;
                if(noRecordsCount > threshold) {
                    break;
                }
                else {
                    continue;
                }
            }

            records.forEach(record -> {
                System.out.println(record.value());
            });

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("FINISHED!");

    }
}
