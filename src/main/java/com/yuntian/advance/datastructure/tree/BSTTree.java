package com.yuntian.advance.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: yuntian
 * @Date: 2019/7/24 0024 22:00
 * @Description: 二分搜索树，binary search tree（限制类型具有可比较性）
 * 特点：二分搜索树的每个节点的值大于其左子树的所有节点的值，小于其右子树的所有节点的值。每一棵子树也是二分搜索树
 */
public class BSTTree<E extends Comparable<E>> {

    private int size;

    private Node root;

    public BSTTree() {
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
        root = add(root, e);
    }


    /**
     * 语义: 向以node为根的二分搜索树中插入元素e，递归算法
     * 2                 2
     * /  \   插入4     /  \
     * 1    3          1    3
     * *                     \
     * *                      4
     * 如果我们在遍历过程中走到了一个node为空的地方，一定是要创建新节点了。此时我们的如下代码并没有递归到底。                           4
     *
     * @param node
     * @param e
     */
    private Node add(Node node, E e) {
        //1书写递归终止条件
        //判断插入e和以node为根判断
        if (node == null) {
            size++;
            return new Node(e);
        }
        //2书写递归方法
        // 既然我们看到了这个e小于node.e ，不管是不是空，都再递归一层。如果递归到的这一层为空，这个位置本身就应该是这个节点。
        // 当e小于 node.e,插入左边
        if (e.compareTo(node.e) < 0) {
            // 如果小于，那么继续往它的左子树添加该节点,这里插入结果可能根发生了变化。
            node.left = add(node.left, e);
        }// 当e大于 node.e,插入右边
        else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
//   但是此时我们就没法把这个节点和我们的树挂接起来了。如何挂？return给函数调用的上层。
        return node;
    }


    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e(递归算法语义)
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else // e.compareTo(node.e) < 0
        {
            return contains(node.right, e);
        }
    }


    /**
     * 前序遍历  根左右
     */
    public void preOrder() {
        preOrder(root);
    }


    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e+ " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历  左根右 可以看到中序遍历的结果就是二分搜索树排序的结果。
     */
    public void inOrder() {
        inOrder(root);
    }


    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    /**
     * 后序遍历  左右根
     * 后序遍历的典型应用: 为二分搜索树释放内存(先释放两个孩子，再删除该节点)
     * <p>
     * 针对一个节点的孩子节点求解出答案，最终再用这些答案组合成这个节点的答案。树形问题: 分治算法，回溯算法，动态规划算法。
     */
    public void postOrder() {
        postOrder(root);
    }


    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    /**
     * 打印二分搜索树的信息
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     *
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    /**
     * 生成树深度的标识。
     *
     * @param depth
     * @return
     */
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    /**
     * 二分搜索树的层序遍历，使用队列实现  广度优先遍历是层序遍历
     * <p>
     * 2
     * /  \       2 1 3
     * 1   3
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.e+" ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }


    private E minimum(){
        if (isEmpty()){
            return null;
        }
        return  minimum(root).e;
    }
    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     * 最小值位于整棵树的最左下角，
     * 左边叶子节点
     *      4
     *    /  \
     *    2   5
     *   / \   \
     *  1   3   6
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if (node.left==null){
            return node ;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素（面向用户）
     *
     * @return
     */
    public E maximum(){
        if(size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     *
     * @param node
     * @return
     */
    private Node maximum(Node node){
        if( node.right == null ) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在节点, 返回最小值
     *
     * @return
     */
    public E removeMin(){
        if(size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return  removeMin(root).e;
    }


    /**
     * 删除掉以node为根的二分搜索树中的最小节点 返回删除节点后新的二分搜索树的根
     *      4
     *    /  \
     *    2   5
     *   / \   \
     *  1   3   6
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 从二分搜索树中删除最大值所在节点, 返回最大值
     *
     * @return
     */
    public E removeMax(){
        if(size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return  removeMax(root).e;
    }


    /**
     * 删除掉以node为根的二分搜索树中的最大节点 返回删除节点后新的二分搜索树的根
     *      4
     *    /  \
     *    2   5
     *   / \   \
     *  1   3   6
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    /**
     * 从二分搜索树中删除元素为e的节点
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     *  删除掉以node为根的二分搜索树中值为e的节点, 递归算法 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e){
        if( node == null ) {
            return null;
        }
        if( e.compareTo(node.e) < 0 ){
            node.left = remove(node.left , e);
            return node;
        }
        else if(e.compareTo(node.e) > 0 ){
            node.right = remove(node.right, e);
            return node;
        }
        else{   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }


}
