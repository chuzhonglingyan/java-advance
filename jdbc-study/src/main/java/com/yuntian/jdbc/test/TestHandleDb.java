package com.yuntian.jdbc.test;


import com.yuntian.jdbc.entity.shop.Order;
import com.yuntian.jdbc.handler.BeanHandler;
import com.yuntian.jdbc.handler.CRUDTemplate;
import com.yuntian.jdbc.util.DBManager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yuntian
 * @Date: 2019/12/7 0007 22:28
 * @Description:
 */
public class TestHandleDb {

    public static void main(String[] args) {
        String sql = "select * from `order` where id=?";
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        int task=20;
        final CountDownLatch countDownLatch = new CountDownLatch(task);
        for(int index = 0 ; index < task ; index++) {
            exec.submit(() -> {
                Order order= CRUDTemplate.executeQuery(sql,new BeanHandler<>(Order.class),1);
                System.out.println(order.toString());
                countDownLatch.countDown();
            });
        }
        // 等待所有子线程的业务都处理完成（计数器的count为0时）
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有子线程的处理都完了，主线程继续执行...");
        DBManager.close();
        exec.shutdown();
    }


}
