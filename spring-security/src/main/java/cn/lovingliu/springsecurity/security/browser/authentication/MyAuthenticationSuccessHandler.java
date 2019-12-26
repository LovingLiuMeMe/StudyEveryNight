package cn.lovingliu.springsecurity.security.browser.authentication;

import cn.lovingliu.springsecurity.security.browser.constant.BrowserConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 自定义登录成功之后的处理逻辑
 * 在不定义登录成功之前 只是对请求简单的放行
 * @Date：Created in 2019-12-26
 */
@Component("myAuthenticationSuccessHandler")
@Slf4j
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${system.login-type}")
    private String systemLoginType;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("【MyAuthenticationSuccessHandler】=>【onAuthenticationSuccess】=>【登录成功】");
        if(systemLoginType.equals(BrowserConstant.systemLoginType.MOBILE)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
