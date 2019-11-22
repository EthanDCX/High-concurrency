package com.dcx.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {
//      用futureTask更方便; 这就叫起了个线程;可以监控
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        Callable<Object> stringCallable = () -> {
            Thread.sleep(5000);
            return Integer.MAX_VALUE;
        };


//        futureTask.run();

        new Thread(futureTask).start();

        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result：{}", result);
        Object call = stringCallable.call();
        log.info("MAX_VALUE:"+call);
    }
}
