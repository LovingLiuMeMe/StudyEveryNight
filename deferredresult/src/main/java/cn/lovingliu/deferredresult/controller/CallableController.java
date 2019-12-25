package cn.lovingliu.deferredresult.controller;

import cn.lovingliu.deferredresult.common.ResponseMsg;
import cn.lovingliu.deferredresult.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @Author：LovingLiu
 * @Description: Callable 非阻塞式调用
 * @Date：Created in 2019-12-19
 */
@RestController
public class CallableController {
    private static final Logger log = LoggerFactory.getLogger(CallableController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/callable", method = RequestMethod.GET)
    public Callable<ResponseMsg<String>> getResult(){
        log.info("接收请求，开始处理=========");
        Callable<ResponseMsg<String>> result = new Callable<ResponseMsg<String>>() {
            @Override
            public ResponseMsg<String> call() throws Exception {
                return taskService.getResult();
            }
        };
        log.info("接收任务线程完成并退出========");

        return result;
    }
}
