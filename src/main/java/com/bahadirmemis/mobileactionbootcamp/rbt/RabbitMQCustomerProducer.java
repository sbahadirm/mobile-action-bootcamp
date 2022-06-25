//package com.bahadirmemis.mobileactionbootcamp.rbt;
//
//import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
///**
// * @author Bahadır Memiş
// * @since 1.0.0
// */
//@Service
//@RequiredArgsConstructor
//public class RabbitMQCustomerProducer {
//
//    private final AmqpTemplate amqpTemplate;
//
//    @Value("${mobileaction.rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${mobileaction.rabbitmq.routingkey}")
//    private String routingKey;
//
//    public void produce(CusCustomerDto cusCustomerDto){
//
//        amqpTemplate.convertAndSend(exchange, routingKey, cusCustomerDto);
//
//        System.out.println("MESSAGE SENT: " + cusCustomerDto);
//    }
//
//}
