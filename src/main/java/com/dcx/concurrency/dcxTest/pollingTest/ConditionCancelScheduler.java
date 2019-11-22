package com.dcx.concurrency.dcxTest.pollingTest;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 党楚翔 on 2019/4/17.
 */

@Slf4j
public class ConditionCancelScheduler {
    private static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

//    Executors.newScheduledExcutor
//    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public static void main(String[] args) throws Exception {
        final String jobID = "my_job_1";
        final AtomicInteger count = new AtomicInteger(0);
        final Map<String, Future> futures = new HashMap<>();

        final CountDownLatch countDownLatch = new CountDownLatch(1);
//        Future<String> future =  scheduler.schedule(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.info("testSchedule: " + System.currentTimeMillis());
//                log.info(String.valueOf((count.getAndIncrement())));
//                if (count.get() > 10) {
//                    Future future = futures.get(jobID);
//                    if (future != null) future.cancel(true);
//                    countDownLatch.countDown();
//                }
//                return "testCallable";
//            }
//        }, 5, TimeUnit.SECONDS);

        Future future = scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
//               every execute ,increment 1
                log.info("testSchedule: " + System.currentTimeMillis());
                log.info(String.valueOf(count.getAndIncrement()));
                if (count.get() > 10) {
                    Future future = futures.get(jobID);
                    if (future != null) future.cancel(true);
                    countDownLatch.countDown();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);

        Object o = future.get();
        System.out.println(o);

        futures.put(jobID, future);
        countDownLatch.await();

        scheduler.shutdown();
    }
}
