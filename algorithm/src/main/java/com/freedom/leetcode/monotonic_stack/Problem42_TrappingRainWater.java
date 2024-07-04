package com.freedom.leetcode.monotonic_stack;

/**
 * 42. Trapping Rain Water
 *
 * @author tobebetter9527
 * @create 2022/12/14 20:43
 */
public class Problem42_TrappingRainWater {

    public static int trap(int[] height) {
        int n = height.length;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans;
    }

    public static int trap2(int[] height) {
        int n = height.length;
        int[] stack = new int[n];
        int index = -1;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            while (index != -1 && height[i] > height[stack[index]]) {
                int popIdx = stack[index--];
                // 左边没有柱子了
                if (index == -1) {
                    break;
                }

                int leftIdx = stack[index];
                // 多减1是因为不能计算柱子
                int curWidth = i - leftIdx - 1;
                int curHeight = Math.min(height[leftIdx], height[i]) - height[popIdx];
                ans += curWidth * curHeight;
            }
            stack[++index] = i;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap2(height));

    }
}
