package cn.lovingliu.rabbitmq.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 自定义消费者。QueueingConsumer 已被废弃,建议使用继承DefaultConsumer的方式
 * @Date：Created in 2020-01-15
 */
public class MyConsumer extends DefaultConsumer {
    public MyConsumer(Channel channel){
        super(channel);
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("-----------consume message----------");
        System.err.println("consumerTag: " + consumerTag);
        System.err.println("envelope: " + envelope);
        System.err.println("properties: " + properties);
        System.err.println("body: " + new String(body));
    }
}
