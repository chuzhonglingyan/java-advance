package com.yuntian.advance.datastructure.set;

/**
 * @Auther: yuntian
 * @Date: 2019/7/27 0027 17:09
 * @Description:
 */
public interface Set<E> {


    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();

}
