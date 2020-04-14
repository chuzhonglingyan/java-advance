package com.yuntian.limit;

/**
 * @author yuntian
 * @date 2020/3/14 0014 19:37
 * @description
 */

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * ${DESCRIPTION}
 * RateLimiter：从速率限流  目前常见的算法是漏桶算法和令牌算法，下面会具体介绍
 *
 * @author mengxp
 * @version 1.0
 * @create 2018-01-15 22:44
 **/
public class RateLimiterExample {

    /**
     * Guava  0.5的意思是 1秒中0.5次的操作，2秒1次的操作  从速度来限流，从每秒中能够执行的次数来
     */
    private final static RateLimiter RATE_LIMITER = RateLimiter.create(0.5d);

    private static void testLimiter() {
        System.out.println(currentThread().getName() + " waiting  " + RATE_LIMITER.acquire());
    }

    public static void runTestLimiter() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach((i) -> {
            service.submit(RateLimiterExample::testLimiter);
        });
        service.shutdown();
    }

    public static void main(String[] args) {
        runTestLimiter();
    }
}