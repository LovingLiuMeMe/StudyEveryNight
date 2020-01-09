package cn.lovingliu.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：LovingLiu
 * @Description: mapper扫描设置
 * @Date：Created in 2020-01-09
 */
@Configuration
@MapperScan("cn.lovingliu.example.mapper")
public class MybatisMapperScanConfig {
}
