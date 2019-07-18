package com.yuntian.advance.datastructure.question;

/**
 * @author Administrator
 * @Auther: yuntian
 * @Date: 2019/7/18 0018 21:32
 * @Description: 链表节点
 */
public class ListNode1 {

    public ListNode1 next;

    public int val=-1;

    public ListNode1(int val) {
        this.val = val;
    }

    public ListNode1(int[] data) {
        this.val=data[0];
        ListNode1 tailNode=this;
        for (int i =1; i < data.length; i++) {
            tailNode.next= new ListNode1(data[i]);
            tailNode=tailNode.next;
        }
    }


    public ListNode1(ListNode1 next, int val) {
        this.next = next;
        this.val = val;
    }

    public ListNode1 getNext() {
        return next;
    }

    public void setNext(ListNode1 next) {
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
        ListNode1 cur = this;
        while (cur != null){
            res.append(cur.val +"->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }


}
