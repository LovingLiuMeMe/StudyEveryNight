package cn.lovingliu.springsecurity.security.browser.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 实现UserDetailsService接口,实现用户状态可控
 * @Date：Created in 2019-12-25
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 表单登录,根据用户名获取用户信息,判断用户状态
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1.根据用户名获取用户信息
        log.info("【MyUserDetailsService】=>【loadUserByUsername】=>【username={}】", username);
        // 2.
        boolean isEnabled = isEnabled(null);
        boolean isAccountNonExpired = isAccountNonExpired(null);
        boolean isAccountNonLocked = isAccountNonLocked(null);
        boolean isCredentialsNonExpired =  isCredentialsNonExpired(null);

        return new User(username,
                passwordEncoder.encode("123456"), // 123456 是指数据库查询出来的(原始)密码
                isEnabled,
                isAccountNonExpired,
                isCredentialsNonExpired,
                isAccountNonLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin") // 权限
        );
    }

    /**
     * 是否可用（模拟用户数据库状态 是否可用）
     * @param arg
     * @return
     */
    public boolean isEnabled(Object arg) {
        return true;
    }

    /**
     * 账号是否过期（模拟用户数据库状态 是否过期）
     * @param arg
     * @return
     */
    public boolean isAccountNonExpired(Object arg) {
        return true;
    }

    /**
     * 账号是否被冻结 （模拟用户数据库状态 是否冻结）
     * @param arg
     * @return
     */
    public boolean isAccountNonLocked(Object arg) {
        return true;
    }

    /**
     * 账号密码是否过期（模拟用户数据库状态 密码是否过期）
     * @param arg
     * @return
     */
    public boolean isCredentialsNonExpired(Object arg) {
        return true;
    }
}
