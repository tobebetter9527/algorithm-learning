package com.freedom.leetcode.array;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * @author tobebetter9527
 * @create 2022/12/17 10:26
 */
public class Problem34_FindFirstandLastPositionofElementinSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        int n = nums.length;

        // find first element
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    ans[0] = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            }
        }

        // find second element
        low = 0;
        high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                if (mid == n - 1 || nums[mid + 1] != target) {
                    ans[1] = mid;
                    break;
                } else {
                    low = mid + 1;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {};
        int target = 0;
        int[] ints = searchRange(nums, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
