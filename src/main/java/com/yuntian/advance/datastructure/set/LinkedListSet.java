package com.yuntian.advance.datastructure.set;

import com.yuntian.advance.datastructure.list.LinkedList;

/**
 * @Auther: yuntian
 * @Date: 2019/7/27 0027 17:16
 * @Description:
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet() {
        this.linkedList = new LinkedList<>();
    }

    /**
     * 时间复杂度 O(n)
     * @param e
     */
    @Override
    public void add(E e) {
        if (linkedList.contains(e)) {
            return;
        }
        linkedList.addFirst(e);
    }

    /**
     * 时间复杂度 O(n)
     * @param e
     */
    @Override
    public void remove(E e) {
        linkedList.removeElements(e);
    }

    /**
     * 时间复杂度 O(n)
     * @param e
     */
    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
