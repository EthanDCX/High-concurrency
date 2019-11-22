package com.dcx.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 1, TimeUnit.SECONDS);



//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
//        executorService.shutdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000); //从当前时间开始执行

        Date date = new Date();
        System.out.println(date);


//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.warn("run");
//            }
//        },);
    }
}
