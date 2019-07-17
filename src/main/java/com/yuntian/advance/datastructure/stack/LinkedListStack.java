package com.yuntian.advance.datastructure.stack;

import com.yuntian.advance.datastructure.list.LinkedList;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 23:51
 * @Description: 栈结构 先进后出  可以将链表的头部当做栈顶  尾端当做栈底
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<E>();
    }


    /**
     * 添加头部 复杂度O(1)
     *
     * @param e
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * 删除头部 复杂度O(1)
     *
     * @return
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    @Override
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
