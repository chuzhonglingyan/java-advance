package com.yuntian.advance.datastructure.list;

/**
 * @Auther: yuntian
 * @Date: 2019/7/17 0017 00:27
 * @Description:
 */
public class LinkListWithHead<E> implements List<E> {

    /**
     * 设置一个头结点
     */
    private Node head;

    /**
     * 记录节点个数
     */
    private int size;

    public LinkListWithHead(E e) {
        this.head = new Node(e);
        size++;
    }


    /**
     * 节点
     */
    private class Node {

        public Node next;

        public E e;


        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
            this.next = null;
        }

        public Node() {
            this(null, null);
        }


        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", e=" + e +
                    '}';
        }
    }


    /**
     * 新增节点
     * <p>
     * 1.将当前插入节点指针值赋值为其前驱节点原来的后继节点的位置
     * <p>
     * 2.修改前驱节点的指针值为当前插入节点的位置
     *
     * @param e
     */
    @Override
    public void add(E e) {
        add(e, size);
    }

    public void addHead(E e) {
        head = new Node(e, head);
        size++;
    }

    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. index mismatch condition");
        }
        //1寻找index节点的前驱节点preNode
        Node preNode = head;
        for (int i = 0; i < index - 1; i++) {
            preNode = head.next;
        }
        //2构建插入节点nodeInsert,将前驱节点preNode的next赋值给nodeInsert的next
//       Node nodeInsert=new Node(e,preNode.next);
        //3将前驱节点preNode的next指向插入节点
//       preNode.next=nodeInsert;
        preNode.next = new Node(e, preNode.next);
        size++;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. index mismatch condition");
        }

        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public int size() {
        return size;
    }


    /**
     * 打印链表信息及遍历元素。
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkListWithHead: size = %d", size));
        res.append('[');
        Node preNode = head;
        while (preNode!= null) {
            res.append(preNode.e);
            preNode = preNode.next;
            res.append("->");
        }
        res.append(']');
        return res.toString();
    }


}
