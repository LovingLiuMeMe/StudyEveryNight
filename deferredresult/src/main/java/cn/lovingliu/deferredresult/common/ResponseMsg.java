package cn.lovingliu.deferredresult.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：LovingLiu
 * @Description: 响应封装类
 * @Date：Created in 2019-12-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg<T> {
    private int code;

    private String msg;

    private T data;
}
