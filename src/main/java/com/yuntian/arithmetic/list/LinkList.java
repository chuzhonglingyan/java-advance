package com.yuntian.arithmetic.list;

/**
 * @Auther: yuntian
 * @Date: 2019/7/17 0017 00:27
 * @Description:
 */
public class LinkList<E> implements List<E> {


    /**
     * 节点
     */
    private class  Node{

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
            this(null,null);
        }


        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", e=" + e +
                    '}';
        }
    }



    public void add(E e) {

    }

    public E remove(int index) {
        return null;
    }

    public E get(int index) {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }
}
