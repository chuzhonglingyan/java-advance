package com.yuntian.arithmetic.stack;

/**
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 23:51
 * @Description:
 */
public interface Stack<E> {

    void  push(E e);

    E  pop();

    E  peek();

    boolean isEmpty();

    int size();

}
