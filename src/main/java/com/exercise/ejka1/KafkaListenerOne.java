package com.exercise.ejka1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerOne {

    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    @Value(value="${server.port}")
    String port;

    @KafkaListener(topics = "topic1", groupId = "mygroup")
    public void listenTopic1(String message) {
        System.out.println("App in port "+port+":");
        System.out.println("Recieved message of topic1 in listener: " + message);
        // Response sending message in topic2
        System.out.println("Sending the same message in topic2");
        kafkaMessageProducer.sendMessage("topic2","Received message of topic1: "+message);
    }

    @KafkaListener(topics = "topic2", groupId = "mygroup")
    public void listenTopic2(String message) {
        System.out.println("App in port "+port+":");
        System.out.println("Recieved message of topic2 in listener: "+message);
    }
}
