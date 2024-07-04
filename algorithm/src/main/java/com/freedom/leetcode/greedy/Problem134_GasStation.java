package com.freedom.leetcode.greedy;

/**
 * 134. Gas Station
 *
 * @author tobebetter9527
 * @create 2022/11/24 20:31
 */
public class Problem134_GasStation {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        Problem134_GasStation problem = new Problem134_GasStation();
        int i = problem.canCompleteCircuit(gas, cost);
        System.out.println(i);
    }

    /**
     * 贪心策略，寻找最大区间和
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        // 双倍的长度数组，用于模拟循环，简化代码
        int doubleN = n << 1;
        int[] nums = new int[doubleN];
        for (int i = 0; i < n; i++) {
            nums[i] = gas[i] - cost[i];
            nums[i + n] = nums[i];
        }
        // 最大区间和起始索引位置
        int index = 0;
        int sum = 0;
        for (int i = 0; i < doubleN; i++) {
            sum += nums[i];
            // 如果出现区间和小于0，重新开始计算
            if (sum < 0) {
                sum = 0;
                index = i + 1;
            }
        }

        // 起点符合的条件
        if (index + n < doubleN) {
            sum = 0;
            for (int i = index + n - 1; i >= index; i--) {
                sum += nums[i];
            }
            return sum >= 0 ? index : -1;
        }

        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                index = (i + 1) % gas.length;
                curSum = 0;
            }
        }
        if (totalSum < 0) return -1;
        return index;
    }
}
