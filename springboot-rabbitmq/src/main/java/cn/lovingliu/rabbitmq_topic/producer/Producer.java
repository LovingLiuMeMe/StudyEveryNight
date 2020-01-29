package cn.lovingliu.rabbitmq_topic.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：LovingLiu
 * @Description: 生产者
 * @Date：Created in 2020-01-16
 */
// @Component
public class Producer {
    private static final String EXCHANGE_NAME = "my_exchange";
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, queue 1 message";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, "topic.1.message", context);
    }


    public void send2() {
        String context = "hi, queue 2 message";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(EXCHANGE_NAME, "topic.2.messages", context);
    }
}
