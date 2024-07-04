package com.freedom.leetcode.binary_search;

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return
 * the index where it would be if it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * @author tobebetter9527
 * @create 2022/06/05 11:49
 */
public class Problem35_SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int high = nums.length - 1;
        int low = 0;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

}
