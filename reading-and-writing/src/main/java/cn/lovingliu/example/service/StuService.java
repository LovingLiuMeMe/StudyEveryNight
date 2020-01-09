package cn.lovingliu.example.service;

import cn.lovingliu.example.pojo.Stu;

import java.util.List;

public interface StuService {
    int insert(Stu stu);
    int save(Stu stu);
    List<Stu> selectAll();
    String getToken(String appId);
}
