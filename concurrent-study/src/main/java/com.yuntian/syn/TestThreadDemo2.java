package com.yuntian.syn;

import java.time.LocalDateTime;

/**
 * @author yuntian
 * @date 2020/3/16 0016 21:44
 * @description
 */
public class TestThreadDemo2 {

    public static void main(String[] args) {
        UnSafeTask unSafeTask = new UnSafeTask();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                unSafeTask.write();
            }).start();
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+",当前状态："+unSafeTask.getStatus()+",时间："+ LocalDateTime.now());
            }
        }).start();
    }


    private static class UnSafeTask {

        private int status = 0;

        public void write() {
            if (status==0){
                status =1;
            }else {
                status =0;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread()+",修改后状态："+status+",时间："+ LocalDateTime.now());
        }

        public int getStatus() {
            return status;
        }
    }

}
