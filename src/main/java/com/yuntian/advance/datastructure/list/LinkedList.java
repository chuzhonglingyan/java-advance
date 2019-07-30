package com.yuntian.advance.datastructure.list;

/**
 * @Auther: yuntian
 * @Date: 2019/7/17 0017 00:27
 * @Description:
 */
public class LinkedList<E> implements List<E> {

    /**
     * 设置一个虚拟头结点
     */
    private Node dummyHead;

    /**
     * 记录节点个数
     */
    private int size;

    public LinkedList() {
        this.dummyHead = new Node();
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
            return "TreeNode{" +
                    "next=" + next +
                    ", e=" + e +
                    '}';
        }
    }


    /**
     * 在链表头添加新元素e 复杂度O(1)
     */
    public void addFirst(E e) {
        add(e, 0);
    }

    /**
     * 在链表尾部元素e 复杂度O(n)
     */
    public void addLast(E e) {
        add(e, size);
    }


    /**
     * 复杂度O(1)
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 复杂度O(n)
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
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


    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. index mismatch condition");
        }
        Node preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = dummyHead.next;
        }
        preNode.next = new Node(e, preNode.next);
        size++;
    }


    /**
     * 找到index处的节点，前驱节点,后继节点
     *
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed. index mismatch condition");
        }
        Node preNode = dummyHead;
        for (int i = 0; i < index; i++) {
            preNode = dummyHead.next;
        }
        Node deleteNode = preNode.next;
        E e = deleteNode.e;
        preNode.next = deleteNode.next;
        deleteNode.next = null;

        size--;
        return e;
    }


    @Override
    public E get(int index) {
        // index不可以取到size，索引从0开始，最多取到size-1
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Illegal index");
        }
        // 从索引为0元素开始
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 修改链表的第index(0-based)个位置的元素为e
     * 在链表中不是一个常用的操作，练习用
     */
    public void set(int index, E e) {
        // index不可以取到size，索引从0开始，最多取到size-1
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index");
        }
        // 从索引为0元素开始
        Node cur = dummyHead.next;
        // 下面与找index-1个节点保持一致。上面执行了一次。所以从index-1个元素变成了找index个元素。
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否有元素e
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public Node removeElements(E e) {
        return removeElements(dummyHead, e);
    }


    private Node removeElements(Node headNode, E val) {
        if (headNode == null) {
            return null;
        }
        // 问题不断小化,头结点分离，对于头结点后面的链表进行删除元素操作
        // 无论head如何，都将head与后面的红色部分连接起来。
        // 将原问题转换为更小问题
        Node res = removeElements(headNode.next, val);
        if (headNode.e.equals(val)) {
            // 继续调用更小问题求解。
            return res;
        } else {
            // 这个head不需要删除，继续连接上链表。
            headNode.next = res;
            return headNode;
        }
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public int size() {
        return size;
    }


}
