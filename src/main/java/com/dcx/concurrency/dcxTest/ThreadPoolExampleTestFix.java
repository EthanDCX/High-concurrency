package com.dcx.concurrency.dcxTest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExampleTestFix {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        List<Callable<String>> callableList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            final int index = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Callable<String> callable = new Callable<String>() {
                public String call() throws Exception {
                    log.info("output:{}", index);
                    return index + "";
                }
            };
            Future<String> submit = executorService.submit(callable);
            callableList.add(callable);
            try {
                String s = submit.get();
                log.info("result:{}", s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        List<String> strings = ThreadUtil.submitAndWait(executorService, callableList, 1, TimeUnit.SECONDS, 1);
        log.info("result:{}", strings);
        executorService.shutdown();
    }
}