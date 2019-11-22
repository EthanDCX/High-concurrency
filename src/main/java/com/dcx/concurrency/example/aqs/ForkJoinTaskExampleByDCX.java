package com.dcx.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTaskExampleByDCX extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExampleByDCX(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) { //
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;  //3
            ForkJoinTaskExampleByDCX leftTask = new ForkJoinTaskExampleByDCX(start, middle); // 1-> 3
            ForkJoinTaskExampleByDCX rightTask = new ForkJoinTaskExampleByDCX(middle + 1, end);//4-5

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
//        ForkJoinPool forkjoinPool = new ForkJoinPool();
        ForkJoinPool forkjoinPool = ForkJoinPool.commonPool();

        //生成一个计算任务，计算1+2+3+4
//        ForkJoinTaskExample task = new ForkJoinTaskExample(1, 100);

        ForkJoinTaskExampleByDCX task = new ForkJoinTaskExampleByDCX(1, 100); //TODO

        //执行一个任务
        Future<Integer> result = forkjoinPool.submit(task);

        try {
            log.info("result:{}", result.get());
        } catch (Exception e) {
            log.error("exception", e);
        }
    }
}
