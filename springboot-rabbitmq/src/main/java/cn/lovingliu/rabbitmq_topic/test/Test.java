package cn.lovingliu.rabbitmq_topic.test;


import cn.lovingliu.rabbitmq_topic.producer.Producer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author：LovingLiu
 * @Description: work 工作队列测试
 * @Date：Created in 2020-01-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private Producer producer;

    @org.junit.Test
    public void send1() throws Exception {
        producer.send1();
    }

    @org.junit.Test
    public void send2() throws Exception {
        producer.send2();
    }
}
