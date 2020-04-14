package com.yuntian.syn;

/**
 * @author yuntian
 * @date 2020/3/17 0017 11:21
 * @description
 */
public class VolatileExample1 {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }


    public void reader() {
        if (v == true) {
            //uses x - guaranteed to see 42.
        }
    }
}