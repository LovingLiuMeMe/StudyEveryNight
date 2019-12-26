package cn.lovingliu.springsecurity.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：LovingLiu
 * @Description: 响应实体类
 * @Date：Created in 2019-12-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMsg<T> {
    private Integer code;
    private T data;
    private String msg;
}
