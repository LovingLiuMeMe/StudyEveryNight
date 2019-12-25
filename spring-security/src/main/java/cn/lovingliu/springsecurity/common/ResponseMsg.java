package cn.lovingliu.springsecurity.common;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 响应实体类
 * @Date：Created in 2019-12-25
 */
@Data
public class ResponseMsg<T> {
    private Integer code;
    private T data;
    private String msg;
}
