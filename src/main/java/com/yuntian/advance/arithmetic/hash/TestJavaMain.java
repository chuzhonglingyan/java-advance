package com.yuntian.advance.arithmetic.hash;

import com.yuntian.advance.datastructure.tree.BSTTree;

/**
 * @Auther: yuntian
 * @Date: 2019/7/16 0016 22:29
 * @Description:
 */
public class TestJavaMain {

    public static void main(String[] args) {
        BSTTree<Integer> bst = new BSTTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums) {
            bst.add(num);
        }
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        bst.preOrder();
        System.out.println();
        System.out.println(bst);
        bst.levelOrder();

    }


}
