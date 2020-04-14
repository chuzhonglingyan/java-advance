package com.yuntian.syn;

/**
 * @author yuntian
 * @date 2020/3/15 0015 21:08
 * @description
 */
public class TestThreadJoin {


    public static void main(String[] args) {
        System.out.println("主线程运行");
        Thread thread1=new Thread(()->{
            System.out.println(Thread.currentThread()+"开始运行。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程恢复运行");
    }


}
