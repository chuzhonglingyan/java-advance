package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/15 0015 14:30
 * @Description:堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
 * 堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 *
 * 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
 * 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
 * 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，
 * 然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。
 * 不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成
 *
 * 主要思路：第一次保证0~0位置大根堆结构（废话），第二次保证0~1位置大根堆结构，
 * 第三次保证0~2位置大根堆结构...直到保证
 * 0~n-1位置大根堆结构
 * （每次新插入的数据都与其父结点进行比较，如果插入的数比父结点大，则与父结点交换，否
 * 则一直向上交换，直到小于等于父结点，或者来到了顶端）

 */
public class HeapSort {


    public static void main(String[] args) {

//        int[] arr={1,2,3,4};
        int[] arr = {1, 2, 3, 4, 5, 7, 6, 8, 9, 0};
        heapOrderSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     *  4,6,8,5,9  -> 4,5,6,8,9
     *
     *  定无序序列构造成一个大顶堆       （一般升序采用大顶堆，降序采用小顶堆)。
     *       4             4
     *     6   8  ->    5    6
     *   5   9        8  9
     *
     * @param arr
     */
    public static  void  heapOrderSort(int[] arr){

    }





    private static void  swap(int[] arr ,int x,int y){
        int tmp=arr[x];
        arr[x]=arr[y];
        arr[y]=tmp;
    }


}
