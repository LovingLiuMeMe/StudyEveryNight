package cn.lovingliu.rabbitmq.test;

import cn.lovingliu.rabbitmq.producer.Producer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2020-01-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private Producer producer;

    @org.junit.Test
    public void oneToOne() throws Exception {
        producer.send();
    }
}
