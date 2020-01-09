package cn.lovingliu.example.service.impl;

import cn.lovingliu.example.pojo.Stu;
import cn.lovingliu.example.service.StuService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 测试
 * @Date：Created in 2020-01-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class StuServiceImplTest {
    @Autowired
    private StuService stuService;

    @Test
    void insert() {
        Stu stu = new Stu();
        stu.setAge(18);
        stu.setName("lili");
        int count = stuService.insert(stu);
        System.out.println("count: "+count);
    }

    @Test
    void save() {
    }

    @Test
    void selectAll() {
        List<Stu> list = stuService.selectAll();
        System.out.println("学生人数:"+list.size());
    }

    @Test
    void getToken() {
    }
}