package com.yuntian.advance.datastructure.question;

/**
 * @Auther: yuntian
 * @Date: 2019/7/18 0018 21:29
 * @Description: <p> 删除链表中的节点
 * 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * </p>
 */
public class Question1 {


    public static void main(String[] args) {
        int[] data = {1, 2, 6, 3, 4, 5, 6};
        ListNode1 listNode1 = new ListNode1(data);
        System.out.println(listNode1.toString());

        ListNode2 listNode2 = new ListNode2(data);
        System.out.println(listNode2.toString());

        // List :1->2->6->3->4->5->6->NULL
//        removeElements(listNode1, 6);
//        System.out.println(listNode1.toString());

        removeElements(listNode2, 6);
        System.out.println(listNode2.toString());

        removeElements2(listNode1, 6);
        System.out.println(listNode1.toString());
    }


    public static ListNode1 removeElements(ListNode1 headNode, int val) {
        while (headNode != null && headNode.val == val) {
//            ListNode1 deleteNode=headNode;
            headNode = headNode.next;
//            deleteNode.next=null;
        }
        if (headNode == null) {
            return null;
        }
        ListNode1 preNode = headNode;
        while (preNode.next != null) {
            if (preNode.next.val == val) {
//                ListNode1 deletNode=preNode.next;
//                deletNode.next=null;
                preNode.next = preNode.next.next;
                // 这里prev不需要后挪，因为删除之后，prev.next节点已经变了，有可能还是val要删除。
            } else {
                preNode = preNode.next;
            }
        }
        return headNode;
    }


    public static ListNode2 removeElements(ListNode2 headNode, int val) {
        ListNode2 preNode = headNode;
        while (preNode.next != null) {
            if (preNode.next.val == val) {
                ListNode2 delNode = preNode.next;
                preNode.next = delNode.next;
                delNode.next = null;
                // 这里prev不需要后挪，因为删除之后，prev.next节点已经变了，有可能还是val要删除。
            } else {
                preNode = preNode.next;
            }
        }
        return headNode;
    }


    /**    headNode 入栈变化              headNode出栈变化  res变化
     *                                    ret          1->2->3->4->5->Null
     *                                    headNode     1->2->6->3->4->5->6->Null
     *                                    ret            2->3->4->5->Null
     *                                    headNode       2->6->3->4->5->6->Null
     *                                    ret               3->4->5->Null
     *                                    headNode          6->3->4->5->6->Null
     *栈底  1->2->6->3->4->5->6->Null      ret               3->4->5->Null
     *     2->6->3->4->5->6->Null         headNode             3->4->5->6->Null
     *       6->3->4->5->6->Null          ret                  4->5->Null
     *          3->4->5->6->Null          headNode                4->5->6->Null
     *            4->5->6->Null           ret                     5->Null
     *              5->6->Null            headNode                  5->6->Null
     * 栈顶               6->Null          ret                        Null
     *                                    headNode                      6->Null
     * @param headNode                    ret                         Null
     * @param val
     * @return
     */
    public static ListNode1 removeElements2(ListNode1 headNode, int val) {
        if (headNode==null){
            return  null;
        }
        // 问题不断小化,头结点分离，对于头结点后面的链表进行删除元素操作
        // 无论head如何，都将head与后面的红色部分连接起来。
        // 将原问题转换为更小问题
        ListNode1 res = removeElements2(headNode.next,val);
        if (headNode.val == val){
            // 继续调用更小问题求解。
            return res;
        }else {
            // 这个head不需要删除，继续连接上链表。
            headNode.next = res;
            return headNode;
        }
    }

}