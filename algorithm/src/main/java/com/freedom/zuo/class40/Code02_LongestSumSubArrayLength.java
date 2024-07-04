package com.freedom.zuo.class40;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * <p>
 * 给定一个整数值K
 * <p>
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * <p>
 * 返回其长度
 *
 * @author tobebetter9527
 * @create 2022/08/21 16:37
 */
public class Code02_LongestSumSubArrayLength {

    public static int maxLength(int[] arr, int k) {
        // key为累积和，value为最早出现累加的位置
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLen = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                maxLen = Math.max(i - map.get(sum - k), maxLen);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    /**
     * 暴力求解
     *
     * @param arr
     * @param K
     */
    public static int right(int[] arr, int K) {
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isValid(arr, i, j, K)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    private static boolean isValid(int[] arr, int i, int j, int k) {
        int sum = 0;
        for (int l = i; l <= j; l++) {
            sum += arr[l];
        }

        return sum == k;
    }

    // -------------------------------------------------------------//

    public static void main(String[] args) {
        int maxLength = 50;
        int maxValue = 20;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(maxLength, maxValue);
            int K = (int) (Math.random() * (maxValue << 1)) + 1;
            int ans1 = right(arr, K);
            int ans2 = maxLength(arr, K);
            if (ans1 != ans2) {
                System.out.println("wrong");
                System.out.println("k: " + K);
                printArray(arr);
            }
        }
        System.out.println("done!");
    }

    private static int[] generateArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        }
        return arr;
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
