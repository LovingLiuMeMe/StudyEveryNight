package cn.lovingliu.springsecurity.config.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author：LovingLiu
 * @Description: 配置
 * @Date：Created in 2019-12-25
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() //指明验证的类型 是请求
            .anyRequest() // 对所有请求
            .authenticated() // 需要身份验证
            .and()
            .formLogin(); // 指定表单登录
            //上述基本就是默认配置了（添加了依赖之后）
    }
}
