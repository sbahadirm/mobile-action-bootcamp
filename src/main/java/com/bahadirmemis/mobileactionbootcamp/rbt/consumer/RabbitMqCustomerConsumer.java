//package com.bahadirmemis.mobileactionbootcamp.rbt.consumer;
//
//import com.bahadirmemis.mobileactionbootcamp.cus.dto.CusCustomerDto;
//import com.bahadirmemis.mobileactionbootcamp.cus.entity.CusCustomer;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author Bahadır Memiş
// * @since 1.0.0
// */
//@Component
//public class RabbitMqCustomerConsumer {
//
//    @RabbitListener(queues = "${mobileaction.rabbitmq.queue}")
//    public void consumeCustomer(CusCustomerDto cusCustomerDto){
//        System.out.println("Received customer: " + cusCustomerDto);
//    }
//}
