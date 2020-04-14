package com.yuntian;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yuntian
 * @date 2020/3/14 0014 17:25
 * @description
 */
public class TestStream {

    public static void main(String[] args) {
        List<Integer> bigData = Lists.newArrayList();
        //亿级别数据  大概占用900M内存
        for (int i = 0; i < 100000000; i++) {
            bigData.add(i);
        }
        long total = bigData.stream().parallel().reduce((x, y) -> x + y).get();
        System.out.println(total);
    }

}
