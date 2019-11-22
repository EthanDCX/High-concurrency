package com.dcx.concurrency.dcxTest.pollingTest;

/**
 * Created by 党楚翔 on 2019/5/6.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 * @ClassName:FutureTaskDemo
 * @Description:FutureTask实现多线程并发执行任务并取结果归集
 * @author diandian.zhang
 * @date 2017年6月16日上午10:36:05
 */
public class FutureTaskDemo {

    public static void main(String[] args)  {
        Long start = System.currentTimeMillis();
        //开启多线程
        ExecutorService exs = Executors.newFixedThreadPool(10);
        try {
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            List<FutureTask<Integer>> futureList = new ArrayList<FutureTask<Integer>>();
            //启动线程池，10个任务固定线程数为5
            for(int i=0;i<10;i++){
                FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTask(i+1));
                //提交任务，添加返回，Runnable特性
                exs.submit(futureTask);
                //Future特性
                futureList.add(futureTask);
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("结果归集开始时间="+new Date());
            //结果归集
            while(futureList.size()>0){
                Iterator<FutureTask<Integer>> iterable = futureList.iterator();
                //遍历一遍
                while(iterable.hasNext()){
                    Future<Integer> future = iterable.next();
                    if (future.isDone()&& !future.isCancelled()) {
                        //Future特性
                        Integer i = future.get();
                        System.out.println("任务i=" + i + "获取完成，移出任务队列！" + new Date());
                        list.add(i);
                        //任务完成移除任务
                        iterable.remove();
                    }else {
                        //避免CPU高速轮循，可以休息一下。
                        Thread.sleep(1);
                    }
                }
            }

            System.out.println("list="+list);
            System.out.println("总耗时="+(System.currentTimeMillis()-start)+",取结果归集耗时="+(System.currentTimeMillis()-getResultStart));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }
    }

}