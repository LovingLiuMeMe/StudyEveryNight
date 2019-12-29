package cn.lovingliu.springsecurity.security.browser.common;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-12-26
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 808522163909731982L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
