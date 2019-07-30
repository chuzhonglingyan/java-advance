package com.yuntian.advance;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 22:29
 * @Description:
 */
public class TestJava {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            TestJava testJava = new TestJava();
            list.add(testJava.toString());
        }
        System.out.println((System.currentTimeMillis() - start) * 1.0 / 1000 + "s");
        long count = list.stream().distinct().count();
        System.out.println(count < list.size());
    }

}
