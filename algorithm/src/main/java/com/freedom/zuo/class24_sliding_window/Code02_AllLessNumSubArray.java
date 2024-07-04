package com.freedom.zuo.class24_sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * <p>
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * <p>
 * sub中最大值 – sub中最小值 <= num，
 * <p>
 * 返回arr中达标子数组的数量
 *
 * @author tobebetter9527
 * @create 2022/07/24 10:49
 */
public class Code02_AllLessNumSubArray {

    /**
     * 暴力对数器
     *
     * @param arr 数组
     * @param sum 最大值
     * @return arr中达标子数组的数量
     */
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int n = arr.length;
        int ans = 0;
        for (int left = 0; left < n; left++) {
            for (int right = left; right < n; right++) {
                int max = arr[left];
                int min = arr[left];
                for (int k = left + 1; k <= right; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                if (max - min <= sum) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // -------------------------------------------------------------------------------------- //

    /**
     * 滑动窗口
     *
     * @param arr 数组
     * @param sum 最大值
     * @return arr中达标子数组的数量
     */
    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }

        int n = arr.length;
        // 保存大的值
        Deque<Integer> maxWin = new LinkedList<>();
        // 保存小的值
        Deque<Integer> minWin = new LinkedList<>();
        int count = 0;
        int right = 0;
        for (int left = 0; left < n; left++) {
            // 找到最右max -min <= sum的right
            while (right < n) {
                // 获取大的值
                while (!maxWin.isEmpty() && arr[maxWin.peekLast()] <= arr[right]) {
                    maxWin.pollLast();
                }
                maxWin.addLast(right);

                // 获取小的值
                while (!minWin.isEmpty() && arr[minWin.peekLast()] >= arr[right]) {
                    minWin.pollLast();
                }
                minWin.addLast(right);

                // 找到不符合条件的right
                if (arr[maxWin.peekFirst()] - arr[minWin.peekFirst()] > sum) {
                    break;
                } else {
                    right++;
                }
            }

            // 这里不用再加1，是因为上面的left到right-1才符合条件。
            // 以现在的left开头，left->0,left->1,...,left->right-1的子数组个数
            count += right - left;

            // 当前的left已经算过了， 需要去掉，从left+1开始重新计算
            if (maxWin.peekFirst() == left) {
                maxWin.pollFirst();
            }
            if (minWin.peekFirst() == left) {
                minWin.pollFirst();
            }
        }

        return count;
    }

    // -------------------------------------------------------------------------------------- //

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
