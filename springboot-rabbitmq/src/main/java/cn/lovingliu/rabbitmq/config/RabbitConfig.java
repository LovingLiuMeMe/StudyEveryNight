package cn.lovingliu.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * @Author：LovingLiu
 * @Description: 配置队列
 * @Date：Created in 2020-01-16
 */
// @Configuration
public class RabbitConfig {
    @Bean
    public Queue queue() {
        return new Queue("q_rabbit");
    }
}
