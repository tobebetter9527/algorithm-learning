package com.freedom.zuo.class08_trie_sort;

import java.util.Arrays;

/**
 * 基数排序,比如电话号码的排序，单词排序
 *
 * @author tobebetter9527
 * @create 2022/06/25 15:39
 */
public class Code04_RadixSort {

    /**
     * 时间复杂度O(k*n)，空间复杂度O(n)
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        radixSort(arr, 0, arr.length - 1, getMaxBit(arr));
    }

    private static void radixSort(int[] arr, int left, int right, int maxBit) {
        // 辅助数组
        int[] help = new int[right - left + 1];

        // 本质是计数排序
        for (int digit = 1; digit <= maxBit; digit++) {
            // 计算某个位置数据的个数
            int[] count = new int[10];
            for (int i = left; i <= right; i++) {
                int j = getDigit(arr[i], digit);
                count[j]++;
            }
            // 累加
            for (int i = 1; i < 10; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 第digit个位置由大到小排序
            for (int i = right; i >= left; i--) {
                int j = getDigit(arr[i], digit);
                //        int index = count[j] - 1;
                //        help[index] = arr[i];
                //        count[j]--;
                help[--count[j]] = arr[i];
            }

            for (int i = left, j = 0; i <= right; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    private static int getMaxBit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }

        int maxBit = 0;
        while (max != 0) {
            max /= 10;
            maxBit++;
        }
        return maxBit;
    }

    /**
     * 获取某位置的值，比如x=258,d=2,得到结果5
     *
     * @param x
     * @param d
     * @return
     */
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static void main(String[] args) {
        int maxValue = 100000000;
        int arrLen = 2000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(maxValue, arrLen);
            int[] copyArr = copyArray(arr);
            radixSort(arr);
            Arrays.sort(copyArr);
            if (!isEqual(arr, copyArr)) {
                System.out.println("something wrong!");
            }
        }
        System.out.println("done!");

    }

    private static boolean isEqual(int[] arr, int[] copyArr) {
        if (arr == null && copyArr != null) {
            return false;
        }
        if (arr != null && copyArr == null) {
            return false;
        }
        if (arr == null && copyArr == null) {
            return true;
        }
        if (arr.length != copyArr.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != copyArr[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    private static int[] generateArray(int maxValue, int arrLen) {
        int length = (int) (Math.random() * (arrLen + 1));
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }
}
