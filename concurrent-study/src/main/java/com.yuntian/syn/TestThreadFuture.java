package com.yuntian.syn;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yuntian
 * @date 2020/3/15 0015 11:31
 * @description https://www.cnblogs.com/lixin-link/p/10998058.html
 * https://www.cnblogs.com/tong-yuan/p/11768904.html
 */
public class TestThreadFuture {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + "开始运行");
        MyFutureTask<String> myFutureTask = new MyFutureTask<>(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "哈哈";
        });
        new Thread(myFutureTask).start();

        //返回结果前，阻塞主线程
        System.out.println(myFutureTask.get());
    }

    private interface CallBack<V> {
        V get();
    }

    private static class MyFutureTask<V> implements Runnable {

        private CallBack<V> callBack;

        public MyFutureTask(CallBack<V> callBack) {
            this.callBack = callBack;
        }

        private V v;
        private boolean flag;

        /**
         * 回调方法
         */
        @Override
        public void run() {
            CallBack<V> c = callBack;
            try {
                v = c.get();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                flag = true;
            }
        }


        /**
         * 调用方法线程
         *
         * @return
         */
        public V get() {
            //阻塞调用线程中
            while (!flag){
                LockSupport.parkNanos(10);
            }
            return v;
        }


    }
}
