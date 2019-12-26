package cn.lovingliu.springsecurity.security.browser.service;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-12-26
 */
public interface SmsCodeSenderService {
    void send(String mobile, String code);
}
