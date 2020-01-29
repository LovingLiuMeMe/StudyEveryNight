package cn.lovingliu.rabbitmq_work.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * @Author：LovingLiu
 * @Description: 消费者1
 * @Date：Created in 2020-01-16
 */
// @Component
// @RabbitListener(queues = "q_rabbit_work")
public class ReceiverWork1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
