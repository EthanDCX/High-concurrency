package com.dcx.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(10000);
            return "Done";
        }
    }

    static class DCXCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            Thread.sleep(10000);
            return "play code";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());  //通过Future接受了其他线程的返回结果
        String s1 = future.get();
        log.info(s1);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        Future<String> dcxExecutor = fixedThreadPool.submit(new DCXCallable());
        String s = dcxExecutor.get();
        log.info(s);


//        DCXCallable dcxCallable = new DCXCallable();
//        String call = dcxCallable.call();
//        log.info(call);

//        Executors.newSingleThreadExecutor()
        log.info("do something in main");
        Thread.sleep(1000);
//        String result = future.get();
//        log.info("result：{}", result);
    }
}