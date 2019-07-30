package com.yuntian.advance.datastructure.set;

import com.yuntian.advance.datastructure.tree.BSTTree;

/**
 * @Auther: yuntian
 * @Date: 2019/7/27 0027 17:11
 * @Description:
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {


    private BSTTree<E> bstTree;

    public BSTSet() {
        this.bstTree = new BSTTree<>();
    }

    /**
     * 时间复杂度 O(h)
     * @param e
     */
    @Override
    public boolean contains(E e) {
        return bstTree.contains(e);
    }

    @Override
    public int getSize() {
        return bstTree.getSize();
    }

    /**
     * 时间复杂度 O(h)  2^(h-1)个节点   n个节点  h=log2(n)
     * @param e
     */
    @Override
    public void add(E e) {
        bstTree.add(e);
    }

    /**
     * 时间复杂度 O(h)
     * @param e
     */
    @Override
    public void remove(E e) {
         bstTree.remove(e);
    }


    @Override
    public boolean isEmpty() {
        return bstTree.isEmpty();
    }

}
