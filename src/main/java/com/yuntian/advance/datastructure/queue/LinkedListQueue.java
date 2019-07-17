package com.yuntian.advance.datastructure.queue;

/**
 * @Auther: yuntian
 * @Date: 2019/7/17 0017 00:20
 * @Description: 队列结构 先进先出  可以将链表的头部当做队头  尾端当做队尾
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

    private Node head, tail;
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

    @Override
    public E dequeue(){
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        head = head.next; // head后移
        retNode.next = null; // 元素置空
        if(head == null) // 如果头结点都没得删了
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
