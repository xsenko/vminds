package com.selcuk.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Stream;

public class CSVProducer {

    private static String KafkaBrokerEndpoint = "localhost:9093";
    private static String KafkaTopic = "mysql_logs";
    private static String CsvFile = null;

    private Producer<String, String> ProducerProperties() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBrokerEndpoint);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "CSVProducer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // make idempotent
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        return new KafkaProducer<String, String>(properties);
    }

    public static void main(String[] args) {

        if(args != null) {
            CsvFile = args[0];
        }

        CSVProducer csvProducer = new CSVProducer();
        csvProducer.publishMessage();
    }

    private void  publishMessage(){
        final Producer<String, String> CsvProducer = ProducerProperties();
        try{
            Stream<String> fileStream = Files.lines(Paths.get(CsvFile));
            fileStream.forEach(line -> {
                if (!line.isEmpty()) {
                    final ProducerRecord<String, String> csvRecord = new ProducerRecord<>(KafkaTopic, UUID.randomUUID().toString(), line);
                    // send to kafka async
                    CsvProducer.send(csvRecord, ((recordMetadata, recordException) -> {
                        if(recordMetadata != null) {
                            System.out.println("CsvData: -> "+ csvRecord.key()+" | "+ csvRecord.value());
                        }
                        else {
                            System.out.println("Error Sending Csv Record -> "+ csvRecord.value());
                        }
                    }));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CsvProducer.flush();
            CsvProducer.close();
        }
    }

}
