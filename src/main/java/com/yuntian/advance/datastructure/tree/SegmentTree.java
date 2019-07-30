package com.yuntian.advance.datastructure.tree;

/**
 * @Auther: yuntian
 * @Date: 2019/7/29 0029 23:14
 * @Description:
 */
public class SegmentTree<E> {

    private E[] tree;

    // 存储整个线段树数据副本
    private E[] data;

    public SegmentTree(E[] arr) {

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
    }

    /**
     * 获取数组大小
     *
     * @return
     */
    public int getSize() {
        return data.length;
    }

    /**
     * 传入index获取该位置数据
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }


    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

}
