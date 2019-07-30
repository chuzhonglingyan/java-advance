package com.yuntian.advance.datastructure.queue;

import com.yuntian.advance.datastructure.tree.MaxHeap;

/**
 * @Auther: yuntian
 * @Date: 2019/7/28 0028 20:24
 * @Description:
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>  {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize(){
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty(){
        return maxHeap.isEmpty();
    }

    /**
     * 时间复杂度O(1)
     * @return
     */
    @Override
    public E getFront(){
        return maxHeap.findMax();
    }

    /**
     * 时间复杂度O(log(n))
     * @return
     */
    @Override
    public void enqueue(E e){
        maxHeap.add(e);
    }

    /**
     * 时间复杂度 O(logn)
     * @return
     */
    @Override
    public E dequeue(){
        return maxHeap.extractMax();
    }


}
