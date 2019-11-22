package com.dcx.concurrency.dcxTest;

//作者：卡巴拉的树
//        链接：https://juejin.im/post/5a38d2046fb9a045076fcb1f
//        来源：掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//厕所
//种族

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


class Employee implements Runnable {
    private String id;
    private Semaphore semaphore;
    private static Random random = new Random(47);

    public Employee(String id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire();
            System.out.println(this.id + "is using the toilet");
            TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
            semaphore.release();
            System.out.println(this.id + "is leaving");
        } catch (InterruptedException e) {
        }
    }
}

public class ToiletRace {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

//  10个坑位 控制并行度
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Employee(String.valueOf(i), semaphore));
        }

        threadPool.shutdown();
    }
}

