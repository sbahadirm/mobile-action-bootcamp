package com.bahadirmemis.mobileactionbootcamp.rbt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rabbitmq")
public class RabbitMQTestController {

//    private final RabbitMQCustomerProducer rabbitMQCustomerProducer;
    private final RabbitMQMailProducer rabbitMQMailProducer;

//    @PostMapping("/customers")
//    public void produce(@RequestBody CusCustomerDto cusCustomerDto){
//
//        rabbitMQCustomerProducer.produce(cusCustomerDto);
//    }

    @PostMapping("/e-mail")
    public void produce(@RequestBody MailSendRequestDto mailSendRequestDto){

        rabbitMQMailProducer.produce(mailSendRequestDto);
    }
}
