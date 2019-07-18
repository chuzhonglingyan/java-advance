package com.yuntian.advance.datastructure.queue;

/**
 * @Auther: yuntian
 * @Date: 2019/7/17 0017 00:20
 * @Description: 队列结构 先进先出  可以将链表的头部当做队头  尾端当做队尾
 *
 * 链表中我们为什么对于链表头部的操作都简单一些呢，因为我们有一个标识的head。那么想让尾部也可以操作简单，设置一个tail变量。
 * 从两端插入元素都是很容易的。
 *
 * tail端前一个节点不容易找，得遍历一遍。此时: head添加删除都容易，tail添加容易，删除不易。
 *
 * 因此队列从head端删除元素，从tail端插入元素。head 队首负责出队，tail队尾负责入队。由于没有dummyHead，要注意链表为空的情况
 */
public class LinkedListQueue<E> implements Queue<E> {


    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }


    /**
     * 记录头结点
     */
    private Node head;
    /**
     * 记录尾节点
     */
    private Node  tail;

    /**
     * 记录元素个数
     */
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * tail队尾负责入队 复杂度O(1)
     * @param e
     */
    @Override
    public void enqueue(E e){
        // 如果队尾为空，说明队列是空的。因为tail一直指向最后一个非空节点。
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        else{
            // 使用tail.next把新Node挂载上来。
            tail.next = new Node(e);
            // tail后挪
            tail = tail.next;
        }
        size ++;
    }


    /**
     * head 队首负责出队 复杂度O(1)
     * @return
     */
    @Override
    public E dequeue(){
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        // head后移
        head = head.next;
        // 元素置空
        retNode.next = null;
        // 如果头结点都没得删了
        if(head == null)
        {
            tail = null;
        }
        size --;
        return retNode.e;
    }

    @Override
    public E getFront(){
        if(isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 5 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
