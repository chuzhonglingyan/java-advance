package com.yuntian.advance.leetcode.sort;

import java.util.Arrays;

/**
 * @Auther: yuntian
 * @Date: 2019/12/14 0014 13:03
 * @Description:
 */
public class TestSort {


    public static void main(String[] args) {

//        int[] arr={1,2,3,4};
        int[] arr = {1, 2, 3, 4, 5, 7, 6, 8, 9, 0};
        selectOrderSort1(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 无序 [0,....x]  有序[x+1,....n-1]
     * i=0时候 每层序列交换次数 n-1
     * i=1时候 每层序列交换次数 n-2
     * 通用次数 每层序列交换次数 n-1-i
     * <p>
     * 无序 [0,....n-1-i]  有序[n-i,....n-1]
     *
     * @param arr
     */
    public static void bubbleOrderSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swap = false;
            // i轮交换的次数
            for (int j = 0; j < arr.length - 1 - i; j++) {
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
     * 有序 [0,....x]  无序[x+1,....n-1]
     * 假设 min=i 寻找无序最小min 交换到首部
     * min=0时候 比较次数 n-1
     * min=1时候 比较次数 n-2
     * <p>
     * 有序 [0,....i-1]  无序[i,....n-1]
     * <p>
     * 通用次数 每层序列比较次数 n-(i+1)
     *
     * @param arr
     */
    public static void selectOrderSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }


    public static void quickOrderSort(int[] arr) {


    }

    private static void quickOrderSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(arr, low, high);
        quickOrderSort(arr, low, p - 1);
        quickOrderSort(arr, p + 1, high);

    }

    private static int partition(int[] arr, int low, int high) {
        //设置基准值
        int povite = low;
        int index = povite + 1;
        for (int i = index; i < arr.length; i++) {
            if (arr[i] < arr[povite]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, povite, index - 1);
        return index - 1;
    }


    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    /**
     * 1,2,4,3   1,2  4
     *
     * @param arr
     */
    public static void insertOrderSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //定义排序好的序列 [0,..i]
            int preIndex = i;
            //定义下一个插入元素
            int cur = arr[i + 1];
            //循环序列,将大于cur向右边移动
            for (int j = i; j >= 0 && arr[j] > cur; j--) {
                arr[j + 1] = arr[j];
                preIndex--;
            }
            //插入该元素
            arr[preIndex + 1] = cur;
        }
    }


    /**
     * 4,3,2,1
     * <p>
     * 第一趟  3,2,1,   4
     * 第二趟  2,1, 3  ,4
     *
     * @param arr
     */
    public static void bubbleOrderSort1(int[] arr) {
        //外层控制趟数
        for (int i = 0; i < arr.length - 1; i++) {
            //首部无序   每趟交换的次数  尾部有序
            boolean swap = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
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
     * 4,3,2,1
     * 每次都选择较小的放在前面 首部有序 尾部无序
     * 第一趟  4,3,2,1-> 1,3,2,4
     * 第二趟  1, 3,2,4 ->1,2,3,4
     *
     * @param arr
     */
    public static void selectOrderSort1(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            //寻找最小的数
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }


    /**
     * 3,2,1,5,4
     * <p>
     * 3 为基数 2,1,3,5,4
     * <p>
     * 2为基数  2,1   5为基数 5,4
     *
     * @param arr
     */
    public void quickOrderSort1(int[] arr) {

    }

    public void quickOrderSort1(int[] arr, int low, int high) {
        //递归结束条件
        if (low >= high) {
            return;
        }
        int p = partation(arr, low, high);
        quickOrderSort1(arr, low, p - 1);
        quickOrderSort1(arr, p + 1, high);
    }

    /**
     * 3,2,1,5,4
     * 3 为基数 2,1,3,5,4
     * <p>
     * 2为基数  2,1   5为基数 5,4
     *
     * @param arr
     */
    private int partation(int[] arr, int low, int high) {
        int provite = low;
        int index = provite + 1;
        for (int i = index; i < high; i++) {
            if (arr[i] < arr[provite]) {
                //小于基准值,放到下一位
                swap(arr, i, index);
                //移动一位
                index++;
            }
        }
        //交换基准值到分隔中间位置
        swap(arr, provite, index - 1);
        return index - 1;
    }
}
