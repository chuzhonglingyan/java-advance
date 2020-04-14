package com.yuntian;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yuntian
 * @date 2020/3/14 0014 00:26
 * @description
 */
public class TestJava {


    public static void computerTask1(List<Integer> bigData) {
        long startTime = System.currentTimeMillis();
        long result = 0L;
        for (Integer num : bigData) {
            result = result + num;
        }
        System.out.println("计算结果：" + result);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：158ms
    }

    public static void computerTask2(List<Integer> bigData) {
        long startTime = System.currentTimeMillis();
        long result = 0L;
        for (int i = 0; i < bigData.size(); i++) {
            result = result + bigData.get(i);
        }
        System.out.println("计算结果：" + result);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：135ms
    }


    public static void computerTask3(List<Integer> bigData) {
        long startTime = System.currentTimeMillis();
        long result = bigData.stream().mapToLong(Integer::intValue).sum();
        System.out.println("计算结果：" + result);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：195ms
    }


    /**
     * 流式并行计算
     *
     * @param bigData
     */
    public static void computerTask4(List<Integer> bigData) {
        long startTime = System.currentTimeMillis();
        long result = bigData.parallelStream().mapToLong(Integer::intValue).sum();
        System.out.println("计算结果：" + result);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：182ms
    }

    private final static int CUP_SIZE = Runtime.getRuntime().availableProcessors();
    private final static int CORE_POOL_SIZE = CUP_SIZE + 1;
    private final static int MAX_NUM_SIZE = 20;
    private final static long KEEP_ALIVE_TIME = 60;
    private final static int MAX_CAPACITY = 1000;


    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("computer_async-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_NUM_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(MAX_CAPACITY),
            threadFactory);

    /**
     * 多线程计算
     *
     * @param bigData
     */
    public static void computerTask5(List<Integer> bigData) throws ExecutionException, InterruptedException {
        int partitionSize = bigData.size() / 10 == 0 ? bigData.size() : bigData.size() / 10;
        List<List<Integer>> parts = Lists.partition(bigData, partitionSize);
        long startTime = System.currentTimeMillis();
        long totalResult = 0;
        List<FutureTask<Result<Long>>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            FutureTask<Result<Long>> futureTask = new FutureTask<>(new MyTask1(i + 1, parts.get(i)));
            futureTaskList.add(futureTask);
            executorService.submit(futureTask);
        }
        for (FutureTask<Result<Long>> futureTask : futureTaskList) {
            //此处会阻塞主线程
            Result<Long> result = futureTask.get();
            totalResult = totalResult + result.data;
            System.out.println("任务：" + result.taskId + ",计算完成：" + result.data + ",耗时：" + result.time + "ms");
        }
        System.out.println("计算结果：" + totalResult);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：131ms
    }

    /**
     * CompletableFuture
     * @param bigData
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void computerCompletableFuture(List<Integer> bigData) throws ExecutionException, InterruptedException {
        int partitionSize = bigData.size() / 10 == 0 ? bigData.size() : bigData.size() / 10;
        List<List<Integer>> parts = Lists.partition(bigData, partitionSize);
        long startTime = System.currentTimeMillis();
        long totalResult = 0;
        CompletableFuture<Result<Long>>[] completableFutureArray= new CompletableFuture[parts.size()];
        for (int i = 0; i < parts.size(); i++) {
            CompletableFuture<Result<Long>> completableFuture = CompletableFuture.supplyAsync
                    (new MySupplier1(i + 1, parts.get(i)), executorService);
            completableFutureArray[i]=completableFuture;
        }
        CompletableFuture<Void> allCompletableFuture =CompletableFuture.allOf(completableFutureArray);
        //此处阻塞线程
        allCompletableFuture.join();
        for (CompletableFuture<Result<Long>> completableFuture : completableFutureArray) {
            Result<Long> result = completableFuture.get();
            totalResult = totalResult + result.data;
            System.out.println("任务：" + result.taskId + ",计算完成：" + result.data + ",耗时：" + result.time + "ms");
        }
        System.out.println("计算结果：" + totalResult);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：126ms
    }


    /**
     * 多线程计算
     *
     * @param bigData
     */
    public static void computerTask6(List<Integer> bigData) throws ExecutionException, InterruptedException {
        int partitionSize = bigData.size() / 10 == 0 ? bigData.size() : bigData.size() / 10;
        List<List<Integer>> parts = Lists.partition(bigData, partitionSize);
        long startTime = System.currentTimeMillis();
        long totalResult = 0;
        CompletionService<Result<Long>> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < parts.size(); i++) {
            completionService.submit(new MyTask1(i + 1, parts.get(i)));
        }
        for (int i = 0; i < parts.size(); i++) {
            //此处会阻塞主线程
            Result<Long> result = completionService.take().get();
            totalResult = totalResult + result.data;
            System.out.println("任务：" + result.taskId + ",计算完成：" + result.data + ",耗时：" + result.time + "ms");
        }
        System.out.println("计算结果：" + totalResult);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：135ms
    }


    /**
     * 多线程计算
     *
     * @param bigData
     */
    public static void computerTask7(List<Integer> bigData) throws InterruptedException {
        int partitionSize = bigData.size() / 10 == 0 ? bigData.size() : bigData.size() / 10;
        List<List<Integer>> parts = Lists.partition(bigData, partitionSize);
        //同步器
        CountDownLatch countDownLatch = new CountDownLatch(parts.size());
        long startTime = System.currentTimeMillis();
        AtomicLong totalResult = new AtomicLong();
        for (List<Integer> part : parts) {
            executorService.submit(() -> {
                long startTimePart = System.currentTimeMillis();
                long result = 0L;
                for (Integer num : part) {
                    result = result + num;
                }
                totalResult.addAndGet(result);
                System.out.println(Thread.currentThread() + "计算完成：" + result + ",耗时：" + (System.currentTimeMillis() - startTimePart) + "ms");
                countDownLatch.countDown();
            });
        }
        //此处会阻塞主线程
        countDownLatch.await();
        System.out.println("计算结果：" + totalResult);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：163ms
    }


    /**
     * 多线程计算
     *
     * @param bigData
     */
    public static void computerTask8(List<Integer> bigData) throws InterruptedException {
        int partitionSize = bigData.size() / 10 == 0 ? bigData.size() : bigData.size() / 10;
        List<List<Integer>> parts = Lists.partition(bigData, partitionSize);
        long startTime = System.currentTimeMillis();
        ListeningExecutorService guaExecutorService = MoreExecutors.listeningDecorator(executorService);
        CountDownLatch countDownLatch = new CountDownLatch(parts.size());
        AtomicLong totalResult = new AtomicLong();
        for (int i = 0; i < parts.size(); i++) {
            ListenableFuture<Result<Long>> listenableFuture = guaExecutorService.submit(new MyTask1(i + 1, parts.get(i)));
            Futures.addCallback(listenableFuture, new FutureCallback<Result<Long>>() {
                @Override
                public void onSuccess(@Nullable Result<Long> result) {
                    System.out.println("任务：" + result.taskId + ",计算完成：" + result.data + ",耗时：" + result.time + "ms");
                    totalResult.addAndGet(result.data);
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(@Nonnull Throwable t) {
                    System.out.println("出错,业务回滚或补偿" + t.getMessage());
                    countDownLatch.countDown();
                }
            }, guaExecutorService);
        }
        countDownLatch.await();
        System.out.println("计算结果：" + totalResult);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        //计算结果：4999999950000000
        //耗时：133ms
    }


    @Data
    @AllArgsConstructor
    private static class MySupplier1 implements Supplier<Result<Long>> {

        private int taskId;
        private List<Integer> taskList;

        @Override
        public Result<Long> get() {
            long startTime = System.currentTimeMillis();
            long result = 0L;
            for (Integer num : taskList) {
                result = result + num;
            }
            return new Result<>(taskId, result, (System.currentTimeMillis() - startTime));
        }
    }

    @Data
    @AllArgsConstructor
    private static class MyTask1 implements Callable<Result<Long>> {

        private int taskId;
        private List<Integer> taskList;

        @Override
        public Result<Long> call() {
            long startTime = System.currentTimeMillis();
            long result = 0L;
            for (Integer num : taskList) {
                result = result + num;
            }
            return new Result<>(taskId, result, (System.currentTimeMillis() - startTime));
        }
    }

    @Data
    @AllArgsConstructor
    private static class Result<T> {
        private int taskId;
        private T data;
        private long time;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        List<Integer> bigData = Lists.newArrayList();
        //亿级别数据  大概占用900M内存
        for (int i = 0; i < 100000000; i++) {
            bigData.add(i);
        }
        System.out.println("添加数据耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        computerCompletableFuture(bigData);
        executorService.shutdown();
    }

}
