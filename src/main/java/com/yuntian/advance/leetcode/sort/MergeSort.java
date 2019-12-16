package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/14 0014 22:56
 * @Description:归并排序
 * 是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并
 *
 * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，
 * 因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
 */
public class MergeSort {


    public static void main(String[] args) {

//        int[] arr={1,2,3,4};
        int[] arr = {4, 3, 8, 5, 2, 1};
//        quickOrderSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
         把长度为n的输入序列分成两个长度为n/2的子序列；
         对这两个子序列分别采用归并排序；
        将两个排序好的子序列合并成一个最终的排序序列。
     */
    public static void mergeOrderSort(int[] arr){

    }

    public static void mergeOrderSort(int[] arr,int low ,int high){
        if (low>=high){
            return;
        }
        int mid = (low+high)/2;
        mergeOrderSort(arr,low,mid);
        mergeOrderSort(arr,mid+1,high);
        // 将A[low...mid]和A[mid+1...high]合并为A[low...high]
        merge(arr,low,mid,high);
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];

        int i= low;
        int j = mid+1;
        int k=0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }

}
