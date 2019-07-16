package com.yuntian.arithmetic.queue;

/**
 * Created by yuntian.
 * Description:
 * Date: 2019-01-15 23:16
 */
public interface Queue<E> {

    /**
     * 入队
     * @param e
     */
    void enqueue(E e); // 入队

    /**
     * 出队
     * @return
     */
    E dequeue();

    /**
     * 取队首元素
     * @return
     */
    E getFront();

    /**
     * 获取队列元素多少
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();
}