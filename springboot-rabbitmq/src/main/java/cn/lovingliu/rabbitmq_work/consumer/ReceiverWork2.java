package cn.lovingliu.rabbitmq_work.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * @Author：LovingLiu
 * @Description: 消费者2
 * @Date：Created in 2020-01-16
 */
// @Component
// @RabbitListener(queues = "q_rabbit_work")
public class ReceiverWork2 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }
}
