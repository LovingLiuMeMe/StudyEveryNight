package cn.lovingliu.rabbitmq.producer;


import cn.lovingliu.common.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * @Author：LovingLiu
 * @Description: 生产者发送消息
 * @Date：Created in 2020-01-15
 */
public class Send {
    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] args) throws Exception {
        /** 1.获取连接 */
        Connection newConnection = ConnectionUtil.getConnection();
        /** 2.创建通道 */
        Channel channel = newConnection.createChannel();
        /** 3.创建队列声明 */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // channel.queueDeclare(QUEUE_NAME, true, false, false, null); // 持久化
        String msg = "我是生产者生成的消息";
        System.out.println("生产者发送消息:" + msg);
        /** 4.发送消息 */
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        // channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes()); // 持久化
        channel.close();
        newConnection.close();
    }
}
