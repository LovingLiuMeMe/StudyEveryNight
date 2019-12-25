package cn.lovingliu.springsecurity.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author：LovingLiu
 * @Description: 配置
 * @Date：Created in 2019-12-25
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() //指明验证的类型 是请求
            .antMatchers("/define_login.html","/authentication/form").permitAll() // 自定义登录页面
            .anyRequest() // 对所有请求
            .authenticated() // 需要身份验证
            .and()
                .formLogin() // 指定表单登录
                //上述基本就是默认配置了(添加了依赖之后)
                    .loginPage("/define_login.html")
                    .loginProcessingUrl("/authentication/form")
            .and()
                .csrf().disable();// 关闭跨站请求保护


    }
}
