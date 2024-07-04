package com.freedom.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 *
 * @author tobebetter9527
 * @create 2022/10/20 21:38
 */
public class Problem15_3Sum {

    /**
     * 排序 + 双指针
     * <p>
     * 固定a,然后b,c从两端向中间移动。
     * <p>
     * time complextiy is O(n<sup>2</sup>), space complexity is O(logn).
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        int n = nums.length;
        // 数据从小到大排序，那么三个数必须满足a<=b<=c
        Arrays.sort(nums);

        for (int first = 0; first < n - 2; first++) {
            // 如果第一个数大于0，不可能有三个数相加等于0，优化点
            //      if (first + 2 < n && nums[first] + nums[first + 1] + nums[first + 2] > 0) {
            //        return ans;
            //      }
            if (nums[first] + nums[first + 1] + nums[first + 2] > 0) {
                return triplets;
            }

            // 第一层循环，如果当前和之前的数据相等，就跳过，比如-2，-2，1，1，2，2。index为1和0的都为-2，显然第二个-2可以跳过，
            // 因为已经在第一个-2中枚举过了。
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // 第三层循环的起始位置
            int third = n - 1;
            int target = -nums[first];

            // second肯定是大于first的
            for (int second = first + 1; second < n - 1; second++) {
                // -3,-2,-2,1,1,2. 当idxA=0,idxB=2时，显示idxB=1,已经枚举过了，都是b=-2.
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                // c指针从右向左移动
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                // 如果相等，b指针不能继续向右移动，否则c指针就必须向左移动，这就重复枚举了。
                if (second == third) {
                    break;
                }

                if (nums[second] + nums[third] == target) {
                    triplets.add(Arrays.asList(nums[first], nums[second], nums[third]));
                }
            }
        }

        return triplets;
    }


    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) {
                return ans;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }
}
