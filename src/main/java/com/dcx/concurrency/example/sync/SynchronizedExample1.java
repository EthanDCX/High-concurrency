package com.dcx.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块  j用来区分是哪个进程调用的
    public void test1(int j) {
        synchronized (SynchronizedExample1.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    public void testNoParam() {
//      作用对象是调用同步代码块的对象
        synchronized (SynchronizedExample1.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {}", i);
            }
        }
    }

    // 修饰一个方法  作用于调用该方法的对象
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
//      两个同样的对象
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        SynchronizedExample1 example3 = new SynchronizedExample1();

//      same object invoke sync code
//      executorService用来创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
//        create a process
//        executorService.execute(() -> {
//            example1.test2(1);
//        });
////      create a process
//        executorService.execute(() -> {
//            example2.test2(2);
//        });

//      //      创建两个进程,让他们异步执行; 一个对象的两个进程同时调用testNoparam的执行情况
//      不同的对象访问同一个sync方法,谁先访问sync就作用于谁,其他对象必须等待
        executorService.execute(() -> {
            example2.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
        executorService.execute(()->{
            example2.test2(3);
        });




    }
}
