package com.freedom.zuo.class08_trie_sort;

import java.util.Arrays;

/**
 * 计数排序，对数据有特殊要求，比如考试分数等
 *
 * @author tobebetter9527
 * @create 2022/06/25 14:55
 */
public class Code03_countSort {

    /**
     * arr的值为分数，从0到100. 时间复杂度O(n),空间复杂度O(m),m为最大值
     * <p>
     * 稳定的排序算法
     *
     * @param arr
     */
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        // 桶的下标，即分数,统计每个分数有多少个
        int[] buckets = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            buckets[arr[i]]++;
        }

        int i = 0;
        for (int j = 0; j < buckets.length; j++) {
            while (buckets[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int score = 100;
        int arrLen = 2000;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(score, arrLen);
            int[] copyArr = copyArray(arr);
            countSort(arr);
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

    private static int[] generateArray(int score, int arrLen) {
        int length = (int) (Math.random() * (arrLen + 1));
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (score + 1));
        }
        return arr;
    }


}
