package cn.lovingliu.rabbitmq_fanout.consumer;

import cn.lovingliu.rabbitmq_fanout.config.FanoutRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 消费者B
 * @Date：Created in 2020-01-17
 */
@Component
@RabbitListener(queues = FanoutRabbitConfig.QUEUE_NAME_B)
public class ReceiverFanoutB {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("BReceiver  : " + hello + "/n");
    }
}
