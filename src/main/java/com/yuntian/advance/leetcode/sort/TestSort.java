package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/23 0023 22:45
 * @Description:
 */
public class TestSort {

    public static void main(String[] args) {
//        int[] arr={1,2,3,4};
        int[] arr = {7, 6, 5, 4, 3, 2, 1};
//        int[] arr={3,5,4,2,1 };
        quickOrderSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 第一趟 i=0  6,5,4,3,2,1,7
     * 第二趟 i=1  5,4,3,2,1,6,7
     *
     * @param arr 前面的无序 后面的有序
     */
    private static void bubbleOrderSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swap = true;
                }
            }
            if (!swap) {
                return;
            }
        }
    }

    /**
     * 找最小  前面有序 后面无序
     * 第一次：7，6,5,4,3,2,1-》  1,7,6,5,4,3,2
     * 第二次：1,7,6,5,4,3,2-》  1,2,7，6,5,4,3,,
     *
     * @param arr
     */
    private static void selectOrderSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    /**
     * 3,5,4,2,1  前面有序，后面无序
     * 第一次 插入  3 , 4 ,5  2,1
     *
     * @param arr
     */
    private static void insertOrderSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int preIndex = i;
            int insertIndex = i + 1;
            int insertNode = arr[insertIndex];
            for (int j = i; j >= 0 && arr[j] > insertNode; j--) {
                arr[j + 1] = arr[j];
                preIndex--;
            }
            arr[preIndex + 1] = insertNode;
        }
    }


    private static void quickOrderSort(int[] arr) {
        quickOrderSort(arr, 0, arr.length - 1);
    }

    /**
     * 4,3,2,5,6  4为基数 ——》 3,2, 4, 5,6
     *
     * @param arr
     * @param low
     * @param high
     */
    private static void quickOrderSort(int[] arr, int low, int high) {
        //递归终止条件
        if (low >= high) {
            return;
        }
        int p = paration(arr, low, high);
        quickOrderSort(arr, low, p - 1);
        quickOrderSort(arr, p + 1, high);
    }

    /**
     * 4, 3,2,5,6  4为基数 向右边移动指针 找到基数的位置
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int paration(int[] arr, int low, int high) {
        int provite = low;
        int index = provite + 1;
        for (int i = index; i <=high; i++) {
            if (arr[provite] > arr[i]) {
                swap(arr, index, i);
                index++;
            }
        }
        swap(arr,provite,index-1);
        return  index-1;
    }


    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
