package com.yuntian.syn;

/**
 * @author yuntian
 * @date 2020/3/15 0015 21:08
 * @description
 */
public class TestThreadYY {


    public static void main(String[] args) {
        Thread threadCustomer=new Thread(new Customer());
        Thread threadProducter=new Thread(new Producter());
        threadCustomer.start();
        threadProducter.start();
    }

    private static class Producter implements Runnable{

        @Override
        public void run() {
            System.out.println("生产线程开启：");
        }
    }

    private static class Customer implements Runnable{

        @Override
        public void run() {
            System.out.println("消费线程开启：");
        }
    }




}
