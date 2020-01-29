package cn.lovingliu.rabbitmq_topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;

/**
 * @Author：LovingLiu
 * @Description: 消费者1
 * @Date：Created in 2020-01-16
 */
// @Component
// @RabbitListener(queues = TopicRabbitConfig.QUEUE_NAME_1)
public class ReceiverTopic1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
}
