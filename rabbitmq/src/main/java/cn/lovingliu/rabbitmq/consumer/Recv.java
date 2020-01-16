package cn.lovingliu.rabbitmq.consumer;

import cn.lovingliu.common.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * @Author：LovingLiu
 * @Description: 消费者消费消息
 * @Date：Created in 2020-01-15
 */
public class Recv{
    /** 队列名称 */
    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] argv) throws Exception {
        /** 1.获取连接 */
        Connection newConnection = ConnectionUtil.getConnection();
        /** 2.获取通道 */
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        /** 3.监听队列 */
        channel.basicConsume(QUEUE_NAME, true, new MyConsumer(channel));
    }
}
