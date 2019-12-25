package cn.lovingliu.deferredresult.controller;

import cn.lovingliu.deferredresult.common.ResponseMsg;
import cn.lovingliu.deferredresult.common.TaskQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author：LovingLiu
 * @Description: DeferredResult 实现非阻塞式调用 (一段时间后自动设置DefferdResult的结果)
 * @Date：Created in 2019-12-19
 */
@RestController
public class DeferredResult1Controller {
    private static final Logger log = LoggerFactory.getLogger(DeferredResult1Controller.class);

    //超时结果
    private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1,"超时","out of time");

    //超时时间
    private static final long OUT_OF_TIME = 3000L;

    @Autowired
    private TaskQueue taskQueue;


    @RequestMapping(value = "/dr1",method = RequestMethod.GET)
    public DeferredResult<ResponseMsg<String>> getResult() {

        log.info("接收请求，开始处理=======");

        //建立DeferredResult对象，设置超时时间，以及超时返回超时结果
        DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);
        /**
         * 监听超时事件
         */
        result.onTimeout(() -> {
            log.info("调用超时");
        });
        /**
         * 监听调用完成事件
         */
        result.onCompletion(() -> {
            log.info("调用完成");
        });

        //并发，加锁
        synchronized (taskQueue) {

            taskQueue.put(result);

        }

        log.info("接收任务线程完成并退出========");

        return result;

    }

}
