package com.bahadirmemis.mobileactionbootcamp.rbt;

import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RabbitMQMailProducer {

    private final AmqpTemplate amqpTemplate;

    @Value("${mobileaction.rabbitmq.mail.exchange}")
    private String exchange;

    @Value("${mobileaction.rabbitmq.mail.routingkey}")
    private String routingKey;

    public void produce(MailSendRequestDto mailSendRequestDto){

        amqpTemplate.convertAndSend(exchange, routingKey, mailSendRequestDto);

        System.out.println("MESSAGE SENT: " + mailSendRequestDto);
    }

}
