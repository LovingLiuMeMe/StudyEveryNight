package cn.lovingliu.example.mapper;

import cn.lovingliu.example.pojo.Stu;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2020-01-09
 */
public interface StuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stu record);

    int insertSelective(Stu record);

    Stu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stu record);

    int updateByPrimaryKey(Stu record);

    List<Stu> selectAll();
}
