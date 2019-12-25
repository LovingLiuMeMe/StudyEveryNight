package cn.lovingliu.deferredresult.service;

import cn.lovingliu.deferredresult.common.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author：LovingLiu
 * @Description: 建立了一个TaskService，用来为阻塞调用和Callable调用提供实际结果处理的。
 * @Date：Created in 2019-12-19
 */
@Service
public class TaskService {
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    public ResponseMsg<String> getResult(){

        log.info("【任务开始执行，持续等待中】");

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("【任务处理完成】");
        return new ResponseMsg<String>(0,"操作成功","success");
    }
}
