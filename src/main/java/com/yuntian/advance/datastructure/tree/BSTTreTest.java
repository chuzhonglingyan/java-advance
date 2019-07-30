package com.yuntian.advance.datastructure.tree;

/**
 * @Auther: yuntian
 * @Date: 2019/7/24 0024 22:00
 * @Description: 二分搜索树，binary search tree（限制类型具有可比较性）
 * 特点：二分搜索树的每个节点的值大于其左子树的所有节点的值，小于其右子树的所有节点的值。每一棵子树也是二分搜索树
 */
public class BSTTreTest<E extends Comparable<E>> {

    private int size;

    private Node root;

    public BSTTreTest() {
        size = 0;
        root = null;
    }

    class Node {
        E e;
        /**
         * 左孩子
         */
        Node left;
        /**
         * 右孩子
         */
        Node right;

        public Node(E e) {
            this(e, null, null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 获取搜索树中节点元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 二分搜索树是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 向二分搜索树中添加新的元素e(面向用户使用的)
     *
     * @param e
     */
    public void add(E e) {
        //根节点为空,需要创建
        if (root == null) {
            root = new Node(e);
        } else {
            //向子节点添加叶子节点
            add(root, e);
        }
    }


    /**
     * 语义: 向以node为根的二分搜索树中插入元素e，递归算法
     *         2               2
     *       /  \   插入4     /  \
     *      1    3          1    3
     *                            \
     *                             4
     * @param node
     * @param e
     */
    private void add(Node node, E e) {
        //1书写递归终止条件
        //判断插入e和以node为根判断
        if (e.equals(node.e)){
            return;
        }
        if (e.compareTo(node.e)<0&&node.left==null){
            node.left=new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e)>0&&node.right==null){
            node.right=new Node(e);
            size++;
            return;
        }
        //2书写递归方法
        // 上面条件不满足，说明还得继续往下找左右子树为null可以挂载上的节点
        // 当e小于 node.e,插入左边
        if (e.compareTo(node.e)<0){
            add(node.left,e);
        }// 当e大于 node.e,插入右边
        else if (e.compareTo(node.e)>0){
            add(node.right,e);
        }

    }


}
