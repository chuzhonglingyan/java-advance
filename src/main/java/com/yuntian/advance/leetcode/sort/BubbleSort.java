package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @link https://blog.csdn.net/weixin_40596016/article/details/79711682
 * @Date: 2019/12/11 0011 20:35
 * @Description:冒泡排序是一种简单的排序算法。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 *
 * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 */
public class BubbleSort {


    public static void main(String[] args) {

//        int[] arr={1,2,3,4};
        int[] arr={1,2,3,4,5,7,6,8,9,0};
        bubbleOrderSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * (n-1)*(n-1-i)
     * 时间复杂度：O(n^2)
     * 空间复杂度: O(1)
     * @param arr
     */
    public static void  bubbleReverseSort(int[] arr){
        // n个元素
        //外层控制冒泡次数 n-1
        for (int i = 0; i < arr.length-1; i++) {
            //内层控制相邻的元素交换,交换次数  n-(i+1)
            for (int j = 0; j < arr.length-(i+1); j++) {
                if (arr[j]<arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 平均时间复杂度：O(n^2)
     * @param arr
     */
    public static void  bubbleOrderSort(int[] arr){
        // n个元素
        //外层控制冒泡次数 n-1
        for (int i = 0; i < arr.length-1; i++) {
            //内层控制相邻的元素交换,交换次数  n-(i+1)
            for (int j = 0; j < arr.length-(i+1); j++) {
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 最大次数：(n-1)(n-1)
     * 时间复杂度：最好,O(n)  最差,O(n^2)
     * @param arr 无序区[0,...n-1-i)和 有序区R[n-1-i,..n-1]
     */
    public static void  bubbleOrderSortOptimize(int[] arr){
        // n个元素
        //外层控制冒泡次数 n-1
        for (int i = 0; i < arr.length-1; i++) {
            //内层控制相邻的元素交换,交换次数  n-(i+1)
            //设置标记,是否发生交换  后半部分为排序好的
            boolean swap=false;
            for (int j = 0; j < arr.length-(i+1); j++) {
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    swap=true;
                }
            }
            if (!swap){
                return;
            }
        }
    }


    private static void  swap(int[] arr ,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
