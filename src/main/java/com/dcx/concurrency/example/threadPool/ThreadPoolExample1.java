package com.dcx.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());

        for (int i = 0; i < 20; i++) {
            final int index = i; // final nochange var
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }




        executorService.shutdown(); //等待任务执行完后再关闭线程池
    }
}
