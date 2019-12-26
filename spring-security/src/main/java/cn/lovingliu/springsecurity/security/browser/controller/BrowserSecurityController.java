package cn.lovingliu.springsecurity.security.browser.controller;

import cn.lovingliu.springsecurity.common.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 实现自定义登录逻辑
 * @Date：Created in 2019-12-25
 */
@RestController
@Slf4j
public class BrowserSecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    /**
     * 自定义登录页面
     * 当用户未登录时,访问任何拦截到的接口时候会拦截到进入到这里
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseMsg requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("【BrowserSecurityController】=>【requireAuthentication】");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("【BrowserSecurityController】=>【requireAuthentication】=> 【重定向地址:{}】",targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,"/define_login.html");
            }
        }
        return new ResponseMsg(1,null,"访问的服务需要身份信息,请引导用户到登录页面");
    }
}
