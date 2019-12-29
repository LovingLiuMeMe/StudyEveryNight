package cn.lovingliu.springsecurity.security.browser.authentication.sms;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author：LovingLiu
 * @Description: 3.创建SmsCodeAuthenticationProvider 提供短信验证逻辑
 * @Date：Created in 2019-12-24
 */
@Slf4j
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String SESSION_SMS_CODE_KEY = "SMS_CODE_KEY";
    /**
     * 身份验证逻辑
     * 通过自定义UserDetails 获取用户信息，重新组装AuthenticationToken
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
        /**
         * 此时拿到的是手机号
         */
        UserDetails userDetails = userDetailsService.loadUserByUsername((String)smsCodeAuthenticationToken.getPrincipal());
        log.info("【SmsCodeAuthenticationProvider】=> 【authenticate】=> 【userDetails(用户信息): {}】", userDetails);
        if(userDetails == null){
            throw  new InternalAuthenticationServiceException("无法获取用户信息");
        }
        /**
         * 编写认证逻辑
         */
        String principal = userDetails.getUsername();
        checkSmsCode(principal);
        /**
         * 组装认证结果
         */
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());
        log.info("【SmsCodeAuthenticationProvider】=> 【authenticate】=> 【authenticationResult(验证结果): {}】", authenticationResult);
        return authenticationResult;
    }

    /**
     * 指定 AuthenticationManager 调用具体的Provider执行 (SmsCodeAuthenticationProvider)
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private void checkSmsCode(String mobile){
        // 拿到HTTP请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("smsCode");

        SmsCode smsCode = (SmsCode) request.getSession().getAttribute(SESSION_SMS_CODE_KEY);
        if(smsCode == null) {
            throw new BadCredentialsException("未检测到申请的短信验证码");
        }

        String applyMobile = (String) smsCode.getTelephone();
        String code =  smsCode.getCode();

        if(!applyMobile.equals(mobile)) {
            throw new BadCredentialsException("申请的手机号码与登录手机号码不一致");
        }
        if(!StringUtils.equalsIgnoreCase(code,inputCode)) {
            throw new BadCredentialsException("短信验证码错误");
        }
        if(smsCode.isExpried()){
            throw new BadCredentialsException("短信验证码错误");
        }
    }
}
