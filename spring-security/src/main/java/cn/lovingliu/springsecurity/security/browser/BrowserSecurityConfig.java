package cn.lovingliu.springsecurity.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Author：LovingLiu
 * @Description: 配置
 * @Date：Created in 2019-12-25
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests() //指明验证的类型 是请求
            //.antMatchers("/define_login.html","/authentication/form").permitAll() // 自定义登录页面
            .antMatchers("/authentication/require","/authentication/form","/define_login.html").permitAll()
            .anyRequest() // 对所有请求
            .authenticated() // 需要身份验证
            .and()
                .formLogin() // 指定表单登录
                //上述基本就是默认配置了(添加了依赖之后)
                    //.loginPage("/define_login.html")
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/form")
                    // 自定义登录成功/失败处理
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailureHandler)
            .and()
                .csrf().disable();// 关闭跨站请求保护


    }
}
