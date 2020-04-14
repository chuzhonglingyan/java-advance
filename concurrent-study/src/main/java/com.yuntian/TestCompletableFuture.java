package com.yuntian;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yuntian
 * @date 2020/3/14 0014 17:55
 * @description https://www.jianshu.com/p/b3c4dd85901e
 */
public class TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
           testBath();
    }

    /**
     *   A ->
     *        ->C   A,B并行  C需要等A,B完成才执行
     *   B ->
     */
    public static void testCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("f1:" + Thread.currentThread());
            return 100;
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("f2:" + Thread.currentThread());
            return "abc";
        });
        CompletableFuture<String> f3 = f1.thenCombine(f2, (result1, result2) -> {
            System.out.println("f3:" + Thread.currentThread());
            return result2 + "-" + result1;
        });
        System.out.println(f3.get());
    }

    /**
     * thenAccept只对结果执行Action，而不返回新的计算值。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testCustomer() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Void> f =  future.thenAccept(System.out::println);
        System.out.println(f.get());
    }


    public static void testBath() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Void> completableFuture=CompletableFuture.allOf(f1,f2);
        System.out.println(completableFuture.get());
        System.out.println("future1: " + f1.isDone() + " future2: " + f2.isDone());
    }
}
