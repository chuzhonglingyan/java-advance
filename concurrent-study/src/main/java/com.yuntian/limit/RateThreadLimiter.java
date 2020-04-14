package com.yuntian.limit;

/**
 * @author yuntian
 * @date 2020/3/14 0014 19:37
 * @description
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * Semaphore：从线程个数限流
 **/
public class RateThreadLimiter {


    /**
     * 同时只能有三个线程工作 Java1.5  从同时处理的线程个数来限流
     */
    private final static Semaphore sem=new Semaphore(3);
    private static void testSemaphore(){
        try {
            sem.acquire();
            System.out.println(currentThread().getName()+" is doing work...");
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            sem.release();
            System.out.println(currentThread().getName()+" release the semephore..other thread can get and do job");
        }
    }

    public static void runTestSemaphore(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach((i)->{
            service.submit(RateThreadLimiter::testSemaphore);
        });
        service.shutdown();
    }

    public static void main(String[] args) {
        IntStream.range(0,10).forEach(System.out::println);
        runTestSemaphore();
    }
}