package com.yuntian.advance.datastructure.list;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 22:31
 * @Description:
 */
public interface List<E> {

    void  add(E e);

    E  remove(int index);

    E  get(int index);

    boolean isEmpty();

    int size();

}
