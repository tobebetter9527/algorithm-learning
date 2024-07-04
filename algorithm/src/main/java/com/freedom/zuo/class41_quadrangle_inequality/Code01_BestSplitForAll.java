package com.freedom.zuo.class41_quadrangle_inequality;

/**
 * 给定一个非负数组arr，长度为N，
 * <p>
 * 那么有N-1种方案可以把arr切成左右两部分
 * <p>
 * 每一种方案都有，min{左部分累加和，右部分累加和}
 * <p>
 * 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
 * <p>
 * 整个过程要求时间复杂度O(N)
 *
 * @author tobebetter9527
 * @create 2022/08/23 21:31
 */
public class Code01_BestSplitForAll {

    /**
     * 暴力法
     *
     * @param arr
     */
    public static int bestSplit1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int ans = 0;
        for (int s = 0; s < arr.length - 1; s++) {
            int sumL = 0;
            for (int i = 0; i <= s; i++) {
                sumL += arr[i];
            }

            int sumR = 0;
            for (int j = s + 1; j < arr.length; j++) {
                sumR += arr[j];
            }

            ans = Math.max(ans, Math.min(sumL, sumR));
        }

        return ans;
    }


    /**
     * 前缀和
     *
     * @param arr
     */
    public static int bestSplit2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int ans = 0;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        int sumL = 0;
        int sumR;
        for (int s = 0; s < arr.length - 1; s++) {
            sumL += arr[s];
            sumR = sum - sumL;
            ans = Math.max(ans, Math.min(sumL, sumR));
        }

        return ans;
    }

    // --------------------------------- //

    public static int[] randomArray(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 20;
        int max = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, max);
            int ans1 = bestSplit1(arr);
            int ans2 = bestSplit2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}
