package com.dcx.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExampleTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
        executorService.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", "dcx");
                }
        });

        executorService.submit(() -> log.info("task:{}", "dcx2"));

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                log.info("task:{}", "dcx3");
            }
        });


        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "lalalal";
            }
        };
        Future<String> submit = executorService.submit(callable);

        System.out.println(submit.get());

        Future<String> testLambada = executorService.submit(() -> "dcxCall");
//      get会造成线程阻塞,会等到线程执行完毕才返回
        System.out.println(testLambada.isDone()+"\t"+testLambada.get()+"\t"+testLambada.isDone());

        executorService.shutdown(); //等待任务执行完后再关闭线程池
    }
}
