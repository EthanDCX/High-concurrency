package com.dcx.concurrency.dcxTest.pollingTest;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * https://my.oschina.net/u/566591/blog/1579950
 * Created by 党楚翔 on 2019/4/17.
 */
public class pollingTest{
    public static void main(String[] args) {
        // 获取线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

//      2000ms后，首次执行，然后每个100ms执行一次
        Future<?> scheduledFuture = executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("testSchedule: " + System.currentTimeMillis());
            }
        }, 2, 5, TimeUnit.SECONDS);

        System.out.println("testMain");
    }
}
