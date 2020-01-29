package cn.lovingliu.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * @Author：LovingLiu
 * @Description: 消费者
 * @Date：Created in 2020-01-16
 */
// @Component
// @RabbitListener(queues = "q_rabbit")
public class Receiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
