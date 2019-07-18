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
        removeElements(listNode1, 6);
        System.out.println(listNode1.toString());

        removeElements(listNode2, 6);
        System.out.println(listNode2.toString());
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

}