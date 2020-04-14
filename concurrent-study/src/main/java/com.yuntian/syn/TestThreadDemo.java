package com.yuntian.syn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yuntian
 * @date 2020/3/15 0015 22:23
 * @description 多线程对共享变量非原子操作
 */
public class TestThreadDemo {

    /**
     * 核心线程数
     */
    private final static int CORE_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    /**
     * 最大线程数
     */
    private final static int MAX_SIZE = 30;
    /**
     * 存活周期
     */
    private final static int KEEP_LIVE_TIME = 60;

    private static ExecutorService executorService;

    private static ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("test_async-%d").build();

    static {
        executorService = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, KEEP_LIVE_TIME,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), factory);
    }


    @Test
    public void testUnSafeDemo() {
        BaseDemo demo = new UnSafeDemo();
        test(demo);
    }

    @Test
    public void testSafeBySynchronized() {
        BaseDemo demo = new SafeBySynchronized();
        test(demo);
    }

    @Test
    public void testSafeByLock() {
        BaseDemo demo = new SafeByLock();
        test(demo);
    }

    @Test
    public void testSafeByCAS() {
        BaseDemo demo = new SafeByCAS();
        test(demo);
    }


    private void test(BaseDemo demo) {
        int taskSize = 20;
        CountDownLatch countDownLatch = new CountDownLatch(taskSize);
        for (int i = 0; i < taskSize; i++) {
            executorService.submit(new Task(countDownLatch, demo));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("多线程累计结果：" + demo.getCount());
        executorService.shutdown();
    }


    @Data
    @AllArgsConstructor
    private static class Task implements Runnable {

        private CountDownLatch countDownLatch;
        private BaseDemo unSafeDemo;

        @Override
        public void run() {
            //增加线程切换几率
            try {
                Thread.sleep(new Random().nextInt(100) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unSafeDemo.add();
            countDownLatch.countDown();
        }
    }

    private interface BaseDemo {

        int getCount();

        void add();

        void reduce();

    }

    @Data
    private static class UnSafeDemo implements BaseDemo {

        private int count;

        @Override
        public void add() {
            count++;
            System.out.println(Thread.currentThread() + ",计算得：" + count);
        }


        @Override
        public void reduce() {
            count--;
        }
    }

    @Data
    private static class SafeBySynchronized implements BaseDemo {

        private int count;


        @Override
        public synchronized void add() {
            count++;
            System.out.println(Thread.currentThread() + ",计算得：" + count);
        }


        @Override
        public synchronized void reduce() {
            count--;
        }
    }

    @Data
    private static class SafeByLock implements BaseDemo {
        private int count;
        private Lock lock = new ReentrantLock();


        @Override
        public void add() {
            lock.lock();
            count++;
            System.out.println(Thread.currentThread() + ",计算得：" + count);
            lock.unlock();
        }


        @Override
        public void reduce() {
            lock.lock();
            count--;
            lock.unlock();
        }
    }


    private static class SafeByCAS implements BaseDemo {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public int getCount() {
            return count.get();
        }

        @Override
        public void add() {
            System.out.println(Thread.currentThread() + ",计算得：" + count.addAndGet(1));
        }

        @Override
        public void reduce() {
            count.addAndGet(-1);
        }
    }

}
