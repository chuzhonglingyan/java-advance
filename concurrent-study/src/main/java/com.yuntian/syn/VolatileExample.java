package com.yuntian.syn;

import java.util.Random;

/**
 * @author yuntian
 * @date 2020/3/16 0016 20:24
 * @description https://www.cnblogs.com/ouyxy/p/7242563.html
 */
public class VolatileExample {

    private volatile boolean isStop;

    private void stop() {
        isStop = true;
        System.out.println(Thread.currentThread() + "停止操作------");
    }

    private void work() {
        while (!isStop) {
            try {
                Thread.sleep(new Random().nextInt(10)+1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "线程正在运行------");
        }
    }

    /**
     * 根据代码顺序规则，线程1的x=5; happens-before b=1;; 线程2的int dummy = b; happens-before while(x!=5);
     * 根据volatile变量规则，线程2的b=1; happens-before int dummy=b;
     * 根据传递性，x=5; happens-before while(x!=5);
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        final VolatileExample example = new VolatileExample();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                example.work();
            }).start();
        }
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                example.stop();
            }
        });
        thread1.start();
    }

}
