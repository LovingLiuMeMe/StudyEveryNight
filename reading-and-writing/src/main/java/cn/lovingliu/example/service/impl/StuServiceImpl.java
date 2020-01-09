package cn.lovingliu.example.service.impl;

import cn.lovingliu.example.annotation.Master;
import cn.lovingliu.example.mapper.StuMapper;
import cn.lovingliu.example.pojo.Stu;
import cn.lovingliu.example.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Transactional
    @Override
    public int insert(Stu stu) {
        return stuMapper.insert(stu);
    }

    @Master
    @Override
    public int save(Stu stu) {
        return stuMapper.insert(stu);
    }

    @Override
    public List<Stu> selectAll() {

        return stuMapper.selectAll();
    }

    @Master
    @Override
    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return "openid:123456";
    }
}
