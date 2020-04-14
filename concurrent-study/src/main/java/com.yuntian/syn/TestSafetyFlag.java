package com.yuntian.syn;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yuntian
 * @date 2020/3/15 0015 22:23
 * @description https://www.jianshu.com/p/9e467de97216
 */
public class TestSafetyFlag {

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
    public void testSafeDemoByVolatile() {
        BaseDemo demo = new SafeDemoByVolatile();
        test(demo);
    }

    private void test(BaseDemo demo) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> demo.doWork()).start();
//            executorService.submit(() -> demo.doWork());
        }
        new Thread(() -> demo.shutdown()).start();
    }


    @Data
    @AllArgsConstructor
    private static class ReadTask implements Runnable {

        private CountDownLatch countDownLatch;
        private BaseDemo unSafeDemo;

        @Override
        public void run() {
            unSafeDemo.doWork();
            countDownLatch.countDown();
        }
    }

    @Data
    @AllArgsConstructor
    private static class WriteTask implements Runnable {

        private CountDownLatch countDownLatch;
        private BaseDemo unSafeDemo;

        @Override
        public void run() {
            unSafeDemo.shutdown();
            countDownLatch.countDown();
        }
    }

    private interface BaseDemo {

        void shutdown();

        void doWork();
    }

    @Data
    private static class UnSafeDemo implements BaseDemo {

        private boolean isShutDown;

        @Override
        public void shutdown() {
            isShutDown = true;
            System.out.println(Thread.currentThread() + "结束工作。。");
        }

        @Override
        public void doWork() {
            while (!isShutDown) {
                System.out.println(Thread.currentThread() + "工作进行中。。");
            }
        }
    }


    @Data
    private static class SafeDemoByVolatile implements BaseDemo {

        private volatile boolean isShutDown;

        @Override
        public void shutdown() {
            isShutDown = true;
            System.out.println(Thread.currentThread() + "结束工作。。");
        }

        @Override
        public void doWork() {
            while (!isShutDown) {
                System.out.println(Thread.currentThread() + "工作进行中。。");
            }
        }
    }

}
