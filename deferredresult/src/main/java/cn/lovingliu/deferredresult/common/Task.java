package cn.lovingliu.deferredresult.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author：LovingLiu
 * @Description: 任务实体类
 * @Date：Created in 2019-12-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int taskId;

    private DeferredResult<ResponseMsg<String>> taskResult;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskResult" + "{responseMsg=" + taskResult.getResult() + "}" +
                '}';
    }
}
