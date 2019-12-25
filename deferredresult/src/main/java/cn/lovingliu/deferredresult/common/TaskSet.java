package cn.lovingliu.deferredresult.common;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author：LovingLiu
 * @Description: 任务集合 （存放的是DeferredResult）
 * @Date：Created in 2019-12-19
 */
@Component
@Data
public class TaskSet {
    private Set<DeferredResult<ResponseMsg<String>>> set = new HashSet<>();
}
