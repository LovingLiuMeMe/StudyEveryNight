package cn.lovingliu.example.aop;

import cn.lovingliu.example.component.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 通过方法名来区分操作类型
 * @Date：Created in 2020-01-09
 */
@Component
@Aspect
public class DataSourceAop {
    @Pointcut("!@annotation(cn.lovingliu.example.annotation.Master) " +
            "&& (execution(* cn.lovingliu.example.service..*.select*(..)) " +
            "|| execution(* cn.lovingliu.example.service.*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(cn.lovingliu.example.annotation.Master) " +
            "|| execution(* cn.lovingliu.example.service..*.insert*(..)) " +
            "|| execution(* cn.lovingliu.example.service..*.add*(..)) " +
            "|| execution(* cn.lovingliu.example.service..*.update*(..)) " +
            "|| execution(* cn.lovingliu.example.service..*.edit*(..)) " +
            "|| execution(* cn.lovingliu.example.service..*.delete*(..)) " +
            "|| execution(* cn.lovingliu.example.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
