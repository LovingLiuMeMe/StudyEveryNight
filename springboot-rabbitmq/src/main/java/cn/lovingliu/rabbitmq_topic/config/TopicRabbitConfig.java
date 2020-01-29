package cn.lovingliu.rabbitmq_topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

/**
 * @Author：LovingLiu
 * @Description: 配置队列,绑定交换机
 * @Date：Created in 2020-01-16
 */
// @Configuration
public class TopicRabbitConfig {
    public final static String QUEUE_NAME_1 = "q_rabbit_topic_1";
    public final static String QUEUE_NAME_2 = "q_rabbit_topic_2";
    public final static String EXCHANGE_NAME = "my_exchange";

    @Bean
    public Queue queue1() {
        return new Queue(TopicRabbitConfig.QUEUE_NAME_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(TopicRabbitConfig.QUEUE_NAME_2);
    }

    /**
     * 声明一个Topic类型的交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    /**
     * 绑定Q到交换机,并且指定routingKey
     * @param queue1
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("topic.1.*");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("topic.2.*");
    }
}
