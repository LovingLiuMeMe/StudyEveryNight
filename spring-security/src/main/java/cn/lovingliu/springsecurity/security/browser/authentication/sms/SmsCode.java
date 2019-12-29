package cn.lovingliu.springsecurity.security.browser.authentication.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author：LovingLiu
 * @Description:
 * 短信验证码的实体类
 * @Date：Created in 2019-12-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode {
    private String code;
    private String telephone;
    private LocalDateTime expireTime;

    public SmsCode(String code, Integer expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
