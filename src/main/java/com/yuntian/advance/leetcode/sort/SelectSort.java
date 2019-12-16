package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/11 0011 21:58
 * @Description:选择排序(Selection-sort)是一种简单直观的排序算法。
 * 它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。
 * 理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
 */
public class SelectSort {


    public static void main(String[] args) {
//        int[] arr={1,2,3,4};
        int[] arr={4,3,2,1,5};
//        int[] arr={1,2,3,4};
        selectOrderSort(arr);
        System.out.println(Arrays.toString(arr));
    }



    /**
     * 时间复杂度：O(n^2) 不占用额外的内存空间了吧
     * 空间复杂度: O(1)
     * @param arr 有序区和无序区分别为R[1..i-1]和 R(i..n）
     */
    public static void  selectOrderSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            //设定最小的位置
            int min=i;
            //循环找到下一个序列的最小索引
            for (int j = i+1; j < arr.length; j++) {
                //寻找最小的数
                if (arr[min]>arr[j]){
                    min=j;
                }
            }
            swap(arr,min,i);
        }
    }

    public static void  selectOrderSortOptimization(int[] arr){


    }


    private static void  swap(int[] arr ,int x,int y){
        int tmp=arr[x];
        arr[x]=arr[y];
        arr[y]=tmp;
    }

}
