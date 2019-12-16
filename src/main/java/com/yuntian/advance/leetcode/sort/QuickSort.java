package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/14 0014 16:27
 * @Description: 快速排序
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 */
public class QuickSort {


    public static void main(String[] args) {

//        int[] arr={1,2,3,4};
        int[] arr = {4, 3, 8, 5, 2, 1};
        int partition=partition(arr,0,arr.length-1);
//        quickOrderSort(arr);
        System.out.println(partition);
        System.out.println(Arrays.toString(arr));

       // quickOrderSort(arr);
       // System.out.println(Arrays.toString(arr));
    }

    /**
     * 2^k=n
     * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)　
     * @param arr
     */
    public static void quickOrderSort(int[] arr) {
        quickOrderSort(arr, 0, arr.length - 1);
    }

    private static void quickOrderSort(int[] arr, int low, int high) {
        //递归结束条件
        if (low >= high) {
            return;
        }
        int p = partition(arr, low, high);
        quickOrderSort(arr, low, p - 1);
        quickOrderSort(arr, p + 1, high);
    }


    /**
     * 分区操作
     */
    private static int partition(int[] arr, int low, int high) {
        // 设定基准值（pivot）
        int pivot = low;
        int index = pivot + 1;

        // 将小于基准的元素依次放到左边
        for (int i = index; i <= high; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
