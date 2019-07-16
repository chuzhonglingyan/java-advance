package com.yuntian.arithmetic.queue;

import com.yuntian.arithmetic.list.ArrayList;

/**
 * @Auther: yuntian
 * @Date: 2019/7/17 0017 00:20
 * @Description: 队列结构 先进先出  可以将数组的首端当做队头  尾端当做队尾
 */
public class ArrayQueue<E> implements Queue<E> {


    private ArrayList<E> list;

    public ArrayQueue() {
        list = new ArrayList<E>();
    }

    public ArrayQueue(int capacity) {
        list = new ArrayList<E>(capacity);
    }


    /**
     * 队尾入队 复杂度O(1)
     *
     * @param e
     */
    public void enqueue(E e) {
        list.add(e);
    }

    /**
     * 队头出队 复杂度O(n)
     */
    public E dequeue() {
        return list.remove(0);
    }

    /**
     * 获得队头 复杂度O(1)
     */
    public E getFront() {
        return list.get(0);
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue ：");
        res.append("front [ ");
        for (int i = 0; i < list.size(); i++) {
            res.append(list.get(i));
            if (i != list.size() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }


}
