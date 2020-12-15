package com.test.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenlong
 * @version 1.0
 * @description
 * @date
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        CyclicBarrier cyclicBarrier=new CyclicBarrier(4);
        System.out.println("爬山开始------------");
        for (int i = 0; i < 4; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 3; i++) {
                        try {
                            System.out.println("选手"+Thread.currentThread().getName()+":"+i+1);
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
