package com.freedom.zuo.class41_quadrangle_inequality;

/**
 * 摆放着n堆石子。现要将石子有次序地合并成一堆
 * <p>
 * 规定每次只能选相邻的2堆石子合并成新的一堆，
 * <p>
 * 并将新的一堆石子数记为该次合并的得分
 * <p>
 * 求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
 *
 * @author tobebetter9527
 * @create 2022/08/24 20:45
 */
public class Code03_StoneMerge {

    /**
     * 暴力递归
     *
     * @param arr
     */
    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int[] sums = sum(arr);
        return process1(sums, 0, arr.length - 1);
    }

    /**
     * 求0~0，1~right的累加值，求0~1，2~right的累加值，以此类推；求累加值的最小值
     *
     * @param sums
     * @param left
     * @param right
     */
    private static int process1(int[] sums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = left; leftEnd < right; leftEnd++) {
            next = Math.min(next, process1(sums, left, leftEnd) + process1(sums, left + 1, right));
        }
        // 假设数组{1，2，6，3），假设（1，2），（6，3）分别合并得到两个数，然后再合并，所得的分数最小，
        // 分数计算： 3 + 6 + （3 + 6）,所以这里： substract(sums, left, right)
        return next + substract(sums, left, right);
    }

    private static int[] sum(int[] arr) {
        int[] sums = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            sums[i + 1] = sums[i] + arr[i];
        }
        return sums;
    }

    private static int substract(int[] sums, int left, int right) {
        return sums[right + 1] - sums[left];
    }

    // ------------------------------------------------------ //

    /**
     * 动态规划
     *
     * @param arr
     */
    public static int dp1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int length = arr.length;
        int[] sums = sum(arr);
        int[][] dp = new int[length][length];
        // 隐藏条件left<=right，所以left > right的格子不用，同时left==right的格子值为0
        // 从小到上，左到右填空
        for (int left = length - 2; left >= 0; left--) {
            for (int right = left + 1; right < length; right++) {
                int next = Integer.MAX_VALUE;
                for (int leftEnd = left; leftEnd < right; leftEnd++) {
                    next = Math.min(next, dp[left][leftEnd] + dp[left + 1][right]);
                }
                // 假设数组{1，2，6，3），假设（1，2），（6，3）分别合并得到两个数，然后再合并，所得的分数最小，
                // 分数计算： 3 + 6 + （3 + 6）,所以这里： substract(sums, left, right)
                dp[left][right] = next + substract(sums, left, right);
            }
        }

        return dp[0][length - 1];
    }

    // ------------------------------------------------------ //

    /**
     * 动态规划,不等式优化
     *
     * @param arr
     */
    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int length = arr.length;
        int[] sums = sum(arr);
        int[][] dp = new int[length][length];
        // 分割的最后位置
        int[][] best = new int[length][length];
        // 隐藏条件left<=right，所以left > right的格子不用，同时left==right的格子值为0
        for (int i = 0; i < length - 1; i++) {
            // 对角线不用填，填次对角线
            best[i][i + 1] = i;
            dp[i][i + 1] = substract(sums, i, i + 1);
        }

        for (int left = length - 3; left >= 0; left--) {
            for (int right = left + 2; right < length; right++) {
                int next = Integer.MAX_VALUE;
                int choose = -1;
                // todo 这里还是不是很明白，后续搞懂
                for (int leftEnd = best[left][right - 1]; leftEnd < best[left + 1][right]; leftEnd++) {
                    int cur = Math.min(next, dp[left][leftEnd] + dp[left + 1][right]);
                    if (cur <= next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                best[left][right] = choose;
                dp[left][right] = next + substract(sums, left, right);
            }
        }

        return dp[0][length - 1];
    }

    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 10;
        int maxValue = 10;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min1(arr);
            int ans2 = dp1(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
