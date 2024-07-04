package com.freedom.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum
 *
 * @author tobebetter9527
 * @create 2022/10/22 15:06
 */
public class Problem18_4Sum {

    /**
     * similar with 3 sum,多了一层循环
     * <p>
     * 排序 + 双指针, 注意数据的范围
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }

        int n = nums.length;
        // 先排序
        Arrays.sort(nums);
        long last3Sum = (long) nums[n - 1] + nums[n - 2] + nums[n - 3];
        long last2Sum = (long) nums[n - 1] + nums[n - 2];
        // 至少是四个数相加等于target，所以i移动到n-4即可
        loop1:
        for (int first = 0; first < n - 3; first++) {
            // 剪枝，加速, 连续四个数相加大于target，后面的连续数相加更大，就没有继续了
            if ((long) nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                return quadruplets;
            }
            // 如果当前的数和最后三个数相加小于target，加速
            if ((long) nums[first] + last3Sum < target) {
                continue;
            }

            // 避免重复
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            loop2:
            for (int second = first + 1; second < n - 2; second++) {
                // 中断第二层循环
                if ((long) nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                    break loop2;
                }
                // 剪枝，加速
                if ((long) nums[first] + nums[second] + last2Sum < target) {
                    continue;
                }
                // 去重
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // right右指针
                int right = n - 1;
                int dif = target - nums[first] - nums[second];
                // left左指针
                loop3:
                for (int left = second + 1; left < n - 1; left++) {
                    // 去重
                    if (left > second + 1 && nums[left] == nums[left - 1]) {
                        continue;
                    }

                    while (right > left && nums[left] + nums[right] > dif) {
                        right--;
                    }

                    if (left == right) {
                        break loop3;
                    }
                    if (nums[left] + nums[right] == dif) {
                        quadruplets.add(Arrays.asList(nums[first], nums[second], nums[left], nums[right]));
                    }
                }
            }
        }
        return quadruplets;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000};
        int target = 1000000000;
        List<List<Integer>> lists = fourSum(nums, target);
        System.out.println(lists);
    }
}
