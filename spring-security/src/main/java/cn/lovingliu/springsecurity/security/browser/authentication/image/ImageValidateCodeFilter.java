package cn.lovingliu.springsecurity.security.browser.authentication.image;


import cn.lovingliu.springsecurity.security.browser.common.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 仅仅对表单登录中的图片验证码进行校
 * @Date：Created in 2019-12-26
 */
@Slf4j
public class ImageValidateCodeFilter extends OncePerRequestFilter {
    private static final String SESSION_KEY = "IMAGE_CODE_KEY";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/authentication/form",request.getRequestURI())&&
            StringUtils.equalsIgnoreCase("POST",request.getMethod())){
            try{
                valicate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                // 调用异常处理
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void valicate(ServletWebRequest request){
        ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request,SESSION_KEY);

        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("imageCode参数缺失");
        }

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request, SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request,SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
