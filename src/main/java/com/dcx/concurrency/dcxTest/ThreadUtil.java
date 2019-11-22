package com.dcx.concurrency.dcxTest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description: 并发工具类
 * Designer: jack
 * Date: 2017/6/22
 * Version: 1.0.0
 */
@Slf4j
public class ThreadUtil {
    public static <T> List<T> submitAndWait(Executor executor, List<Callable<T>> solvers, Integer timeout, TimeUnit unit, Integer sleepTime) {
        long threadStart = System.currentTimeMillis();
        List<T> returnList = new ArrayList<>();//TODO CopyonWriteL
        int n = solvers.size();
        int count = 0;
        long waitMax = unit.toSeconds(timeout);
        long waitCnt = 0;
        CompletionService<T> completionService = new ExecutorCompletionService<>(executor);
        for (Callable<T> callable : solvers) {
            completionService.submit(callable);
        }
        while (count < n && waitCnt < waitMax) {
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                waitCnt += TimeUnit.MILLISECONDS.toSeconds(sleepTime); //count
                for (int i = count; i < n; ++i) {
                    Future<T> poll = completionService.poll();
                    if (poll != null) {
                        T t = poll.get();
                        returnList.add(t);
                        count++;
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        long threadCost = System.currentTimeMillis() - threadStart;
        log.info("thread util Cost: {}", threadCost);
        return returnList;
    }
}