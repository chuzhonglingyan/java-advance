package com.yuntian.advance.datastructure.question;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/7/18 0018 21:32
 * @Description: 链表, 自带虚拟头节点
 */
public class ListNode2 {

    public ListNode2 next;

    public int val = -1;

    public ListNode2(int val) {
        this.val = val;
    }

    public ListNode2(int[] data) {
        ListNode2 tailNode = this;
        for (int i = 0; i < data.length; i++) {
            tailNode.next = new ListNode2(data[i]);
            tailNode = tailNode.next;
        }
    }


    public ListNode2(ListNode2 next, int val) {
        this.next = next;
        this.val = val;
    }

    public ListNode2 getNext() {
        return next;
    }

    public void setNext(ListNode2 next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }


    /**
     * 返回以当前节点为头结点的链表信息字符串
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("List :");
        ListNode2 cur = this.next;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }


}
