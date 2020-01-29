package cn.lovingliu.rabbitmq_fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：LovingLiu
 * @Description: 配置队列,绑定交换机
 * @Date：Created in 2020-01-16
 */
@Configuration
public class FanoutRabbitConfig {
    public final static String QUEUE_NAME_A = "q_rabbit_fanout_a";
    public final static String QUEUE_NAME_B = "q_rabbit_fanout_b";
    public final static String QUEUE_NAME_C = "q_rabbit_fanout_c";
    public final static String EXCHANGE_NAME = "my_fanout_exchange";


    @Bean
    public Queue aQueue() {
        return new Queue(QUEUE_NAME_A);
    }

    @Bean
    public Queue bQueue() {
        return new Queue(QUEUE_NAME_B);
    }

    @Bean
    public Queue cQueue() {
        return new Queue(QUEUE_NAME_C);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingExchangeA(Queue aQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(aQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue bQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(bQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue cQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(cQueue).to(fanoutExchange);
    }
}
