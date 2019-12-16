package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/11 0011 20:35
 * @Description:插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，
 * 需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 */
public class InsertionSort {


    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 3, 2, 1, 8};
        insertionOrderSort4(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 6,5,4,3,2,1
     * <p>
     * i=0    有序序列  6    无序序列 5,4,3,2,1     5,6,4,3,2,1
     * i=1    有序序列  5,6  无序序列 4,3,2,1      4,5,6,3,2,1
     * <p>
     * 4,6,7,5 ->  4,5,6,7
     *
     * @param arr
     */
    public static void insertionOrderSort(int[] arr) {
        int i, j, temp;
        for (i = 0; i < arr.length - 1; i++) {
            temp = arr[i + 1];
            for (j = i; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }

    public static void insertionOrderSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i + 1];
            int leftIndex = i;
            //当比到最左边或者遇到比temp小的数据时，结束循环
            while (leftIndex >= 0 && arr[leftIndex] > temp) {
                arr[leftIndex + 1] = arr[leftIndex];
                leftIndex--;
            }
            //把temp放到空位上
            arr[leftIndex + 1] = temp;
        }
    }

    /**
     * 关键点 找到cur元素的左边序列 x  cur  y右边序列
     * curIndex=x+1;
     * y=curIndex+1;
     *
     * @param arr
     */
    public static void insertionOrderSort3(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //arr[0] ..a[i] 排序好的序列
            //有序序列的最后一位
            int preIndex = i;
            //需求操作插入的元素
            int cur = arr[i + 1];
            // 比cur大的右边序列
            for (; preIndex >= 0 && arr[preIndex] > cur; preIndex--) {
                arr[preIndex + 1] = arr[preIndex];
            }
            //左边序列最后的位置preIndex
            // 插入元素到此位置
            arr[preIndex + 1] = cur;
        }
    }


    public static void insertionOrderSort4(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //arr[0] ..a[i] 排序好的序列
            int preIndex = i;
            //需求操作插入的元素
            int cur = arr[i + 1];
            // 比cur大的右边序列
            for (int j=i; j >= 0 && arr[j] > cur; j--) {
                arr[j + 1] = arr[j];
                preIndex--;
            }
            //左边序列最后的位置preIndex
            // 插入元素到此位置
            arr[preIndex+1] = cur;
        }
    }


}
