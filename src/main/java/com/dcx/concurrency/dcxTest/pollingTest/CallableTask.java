package com.dcx.concurrency.dcxTest.pollingTest;


import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @Description 回调方法
 * @author denny
 * @date 2018/8/17 下午3:16
 */
public class CallableTask implements Callable<Integer> {
    Integer i;

    public CallableTask(Integer i) {
        super();
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        if (i == 1) {
            Thread.sleep(3000);//任务1耗时3秒
        } else if (i == 5) {
            Thread.sleep(5000);//任务5耗时5秒
        } else {
            Thread.sleep(1000);//其它任务耗时1秒
        }
        System.out.println("task线程：" + Thread.currentThread().getName() + "任务i=" + i + ",完成！"+ new Date());
        return i;
    }
}