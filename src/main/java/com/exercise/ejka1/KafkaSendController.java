package com.exercise.ejka1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class KafkaSendController {

    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/add/{topic}")
    public ResponseEntity<String> addIdCustomer(@PathVariable String topic, @RequestBody String body)
    {
        kafkaMessageProducer.sendMessage(topic,body);
        return new ResponseEntity<>("Mensaje enviado: "+body, HttpStatus.OK);
    }
}
