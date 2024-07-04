package com.freedom.zuo.class04_merge_sort;

/**
 * 归并排序
 *
 * @author tobebetter9527
 * @create 2022/06/11 15:40
 */
public class Code01_mergeSort {

    /**
     * 时间复杂度O(nlogn),master公式计算
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] nums = new int[right - left + 1];
        int i = 0;
        int index1 = left;
        int index2 = mid + 1;
        while (index1 <= mid && index2 <= right) {
            // 稳定算法
            nums[i++] = arr[index1] <= arr[index2] ? arr[index1++] : arr[index2++];
        }

        // 可能有一个已经处理完了，另外一个没有
        while (index1 <= mid) {
            nums[i++] = arr[index1++];
        }
        while (index2 <= right) {
            nums[i++] = arr[index2++];
        }

        for (int j = 0; j < nums.length; j++) {
            arr[left + j] = nums[j];
        }
    }

}
