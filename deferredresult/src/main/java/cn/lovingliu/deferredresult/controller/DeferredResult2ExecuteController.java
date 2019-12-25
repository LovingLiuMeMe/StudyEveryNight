package cn.lovingliu.deferredresult.controller;

import cn.lovingliu.deferredresult.common.ResponseMsg;
import cn.lovingliu.deferredresult.common.TaskSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Set;

/**
 * @Author：LovingLiu
 * @Description: DeferredResult 实现非阻塞式调用 （设置DefferdResult的结果）
 * @Date：Created in 2019-12-19
 */
@RestController
public class DeferredResult2ExecuteController {
    private static final Logger log = LoggerFactory.getLogger(DeferredResult2ExecuteController.class);

    @Autowired
    private TaskSet taskSet;

    @RequestMapping(value = "/set/{result}",method = RequestMethod.GET)
    public String setResult(@PathVariable("result") String result){

        ResponseMsg<String> res = new ResponseMsg<>(0,"success",result);

        log.info("结果处理开始，得到输入结果为:{}",res);

        Set<DeferredResult<ResponseMsg<String>>> set = taskSet.getSet();

        synchronized (set){

            set.forEach((deferredResult)->{deferredResult.setResult(res);});

        }

        return "Successfully set result: " + result;
    }

}

