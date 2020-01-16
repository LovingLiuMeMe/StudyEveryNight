package cn.lovingliu.rabbitmq_work.consumer;

import cn.lovingliu.common.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author：LovingLiu
 * @Description: 消费者1
 * @Date：Created in 2020-01-16
 */
public class Customer_1 {
    /**
     * 队列名称
     */
    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("001");
        /** 1.获取连接 */
        Connection newConnection = ConnectionUtil.getConnection();
        /** 2.获取通道 */
        final Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        /** 保证一次只分发一次 限制发送给同一个消费者 不得超过一条消息 */
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
                try {
                    Thread.sleep(1000);// 阻塞事件更长,代表该消息处理事件更长
                } catch (Exception e) {

                } finally {
                    /** 手动回执消息 */
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        /** 3.监听队列 */
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }
}
