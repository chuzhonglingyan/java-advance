package com.yuntian.jdbc.test;


import com.yuntian.jdbc.dao.ProductStockCrud;
import com.yuntian.jdbc.util.DBManager;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: yuntian
 * @Date: 2019/12/7 0007 22:28
 * @Description: show variables like 'autocommit'; set autocommit = 0;
 * https://www.jianshu.com/p/ed896335b3b4
 * https://www.cnblogs.com/toov5/p/11456555.html
 * https://www.cnblogs.com/ubuntu1/p/8999403.html
 * https://www.cnblogs.com/liulvzhong/articles/9252405.html
 */
public class TestDbLock {

    public static void main(String[] args) {
        final ExecutorService exec = Executors.newFixedThreadPool(20);
        int task=100;
        final CountDownLatch countDownLatch = new CountDownLatch(task);
        AtomicInteger atomicInteger=new AtomicInteger();
        for(int index = 0 ; index < task ; index++) {
            exec.submit(() -> {
                try {
                    Thread.sleep(200+new Random().nextInt(50));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean flag= ProductStockCrud.reduceStockNoLock(1, 1) == 1;
                System.out.println("更新库存："+flag);
                if (flag){
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }
        // 等待所有子线程的业务都处理完成（计数器的count为0时）
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有子线程的处理都完了，主线程继续执行..."+atomicInteger.get());
        DBManager.close();
        exec.shutdown();
    }


}
