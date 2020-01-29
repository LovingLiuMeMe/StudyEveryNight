package cn.lovingliu.rabbitmq_work.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author：LovingLiu
 * @Description: 生产者
 * @Date：Created in 2020-01-16
 */
// @Component
public class Producer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + i + " " + date;
        System.out.println("Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.rabbitTemplate.convertAndSend("q_rabbit_work", context);
    }
}
