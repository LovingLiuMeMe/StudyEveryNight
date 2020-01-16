package cn.lovingliu.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author：LovingLiu
 * @Description: 获取rabbitMq连接
 * @Date：Created in 2020-01-15
 */
public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("49.235.110.134");
        factory.setPort(5672);
        factory.setVirtualHost("test001_host");
        factory.setUsername("root");
        factory.setPassword("root");

        Connection connection = factory.newConnection();
        return connection;
    }
}
