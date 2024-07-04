package com.freedom.zuo.class21_dynamic_programming4;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * <p>
 * 每个值都认为是一种面值，且认为张数是无限的。
 * <p>
 * 返回组成aim的方法数
 * <p>
 * 例如：arr = {1,2}，aim = 4
 * <p>
 * 方法如下：1+1+1+1、1+1+2、2+2
 * <p>
 * 一共就3种方法，所以返回3.  跟上一题不一样的是可以重复取相同的值
 *
 * @author tobebetter9527
 * @create 2022/07/19 21:17
 */
public class Code03_CoinsWayNoLimit {

    /**
     * 暴力递归
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    /**
     * arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少?
     * <p>
     * 递推公式：f(index, restAim)=f(index+1, restAim - 0*arr[index]) + f(index+1, restAim - 1*arr[index]) + ...+f(index+1,
     * restAim - n*arr[index])
     * <p>
     * 其中restAim - n*arr[index]>=0. 终止条件index==arr.length.
     * <p>
     * 本题思路跟Code02_CoinsWayEveryPaperDifferent差不多，区别：这里的每个数可以从0到n <= restAim/arr[index]之间选x个， 后者只能选或不选
     *
     * @param arr
     * @param index
     * @param restAim
     * @return
     */
    private static int process(int[] arr, int index, int restAim) {
        // 终止条件不好理解
        if (index == arr.length) {
            return restAim == 0 ? 1 : 0;
        }
        int ways = 0;
        // 每个index位置的都可以取0到count张，就有count + 1种取法
        for (int count = 0; count * arr[index] <= restAim; count++) {
            ways += process(arr, index + 1, restAim - count * arr[index]);
        }
        return ways;
    }

    // --------------------------------------- //

    /**
     * 动态规划
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;

        // 从下到上，从左到右填
        for (int index = length - 1; index >= 0; index--) {
            for (int restAim = 0; restAim <= aim; restAim++) {
                int ways = 0;
                for (int count = 0; count * arr[index] <= restAim; count++) {
                    ways += dp[index + 1][restAim - count * arr[index]];
                }
                dp[index][restAim] = ways;
            }
        }

        return dp[0][aim];
    }

    // --------------------------------------- //

    /**
     * 动态规划,优化，画图
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWay3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][aim + 1];
        dp[length][0] = 1;

        // 从下到上，从左到右填
        for (int index = length - 1; index >= 0; index--) {
            for (int restAim = 0; restAim <= aim; restAim++) {
                // 底下的一个相加
                dp[index][restAim] = dp[index + 1][restAim];

                // 左边的相加
                if (restAim - arr[index] >= 0) {
                    dp[index][restAim] += dp[index][restAim - arr[index]];
                }
            }
        }

        return dp[0][aim];
    }

    // --------------------------------------- //

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = coinsWay2(arr, aim);
            int ans3 = coinsWay3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
