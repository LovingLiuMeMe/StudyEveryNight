package cn.lovingliu.rabbitmq_fanout.producer;

import cn.lovingliu.rabbitmq_fanout.config.FanoutRabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2020-01-17
 */

@Component
public class Producer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend(FanoutRabbitConfig.EXCHANGE_NAME,"", context);
    }
}
