package com.yuntian.arithmetic.stack;

import com.yuntian.arithmetic.list.ArrayList;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 23:51
 * @Description: 栈结构 先进后出  可以将数组的首端当做栈底  尾端当做栈顶
 */
public class ArrayStack<E> implements Stack<E> {

    private ArrayList<E> list;

    public ArrayStack() {
        list = new ArrayList<E>();
    }

    public ArrayStack(int capacity) {
        list = new ArrayList<E>(capacity);
    }


    /**
     * 添加最后 复杂度O(1)
     *
     * @param e
     */
    public void push(E e) {
        list.add(e);
    }

    /**
     * 删除最后 复杂度O(1)
     *
     * @return
     */
    public E pop() {
        return list.removeLast();
    }

    public E peek() {
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }


    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack ：");
        res.append("[ ");
        for (int i = 0; i < list.size(); i++) {
            res.append(list.get(i));
            if (i != list.size() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }


}
