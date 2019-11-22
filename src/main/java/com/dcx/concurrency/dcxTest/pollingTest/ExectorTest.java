package com.dcx.concurrency.dcxTest.pollingTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 党楚翔 on 2019/4/19.
 *
 *    作者：zjhch123
 *         链接：https://hacpai.com/article/1488023925829
 *         来源：黑客派
 *         协议：CC BY-SA 4.0 https://creativecommons.org/licenses/by-sa/4.0/
 */
public class ExectorTest {
    public static void main(String[] args) {
        // Test3
        ExecutorService service = Executors.newFixedThreadPool(1);
        Runnable run = () -> {
            long num = 0;
            boolean flag = true; // by flag control
            while(flag) {
                num += 1;
                if(num == 10) flag = false;
                System.out.println(flag+"\t"+num);
            }
        };
        service.execute(run);
        service.shutdownNow();
    }
}
