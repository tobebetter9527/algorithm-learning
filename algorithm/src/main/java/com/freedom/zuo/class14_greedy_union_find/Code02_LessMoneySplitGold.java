package com.freedom.zuo.class14_greedy_union_find;

import java.util.PriorityQueue;

/**
 * 切金条问题
 *
 * @author tobebetter9527
 * @create 2022/07/07 19:53
 */
public class Code02_LessMoneySplitGold {

    /**
     * 暴力法
     *
     * @param arr
     * @return
     */
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, 0);
    }

    /**
     * @param arr      剩余的数组
     * @param preCount 之前的代价之和
     * @return
     */
    private static int process(int[] arr, int preCount) {
        // 截止条件,如果等于1，说明已经不需要切分了，就是之前的代价preCount
        if (arr.length == 1) {
            return preCount;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(mergeIntoNewArray(arr, i, j), preCount + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private static int[] mergeIntoNewArray(int[] arr, int i, int j) {
        int[] nextArr = new int[arr.length - 1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                nextArr[index++] = arr[k];
            }
        }

        nextArr[index] = arr[i] + arr[j];
        return nextArr;
    }


    /**
     * 假设数组为10，20，30，40，50。并不是每次切出最大的才最省钱。
     * <p>
     * 先切成90和60，90切成40和50，60切成30和30，其中一个30切成10和20这样最省钱
     *
     * @param arr
     * @return
     */
    public static int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 构建小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : arr) {
            heap.add(i);
        }

        int sum = 0;
        int cur = 0;
        while (heap.size() > 1) {
            // 弹出两个相加
            cur = heap.poll() + heap.poll();
            sum += cur;
            // 重新放入小根堆
            heap.add(cur);
        }

        return sum;
    }


    public static void main(String[] args) {
        int[] arr1 = {10, 20, 30, 40, 50};
        int count = lessMoney2(arr1);
        System.out.println(count);

        int maxLength = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxLength, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("wrong!");
            }
        }
        System.out.println("done!");

    }

    private static int[] generateRandomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }
}
