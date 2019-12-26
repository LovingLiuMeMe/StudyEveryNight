package cn.lovingliu.springsecurity.security.browser.service.impl;

import cn.lovingliu.springsecurity.security.browser.service.SmsCodeSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-12-26
 */
@Service
@Slf4j
public class SmsCodeSenderServiceImpl implements SmsCodeSenderService {
    @Override
    public void send(String mobile, String code) {
        // 模拟短信服务商 发送短信
        log.info("【SmsCodeSenderServiceImpl】=> 【send】=> 【{}:{}】",mobile,code);
    }
}
