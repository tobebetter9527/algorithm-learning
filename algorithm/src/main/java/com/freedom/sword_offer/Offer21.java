package com.freedom.sword_offer;

/**
 * Offer 21. 调整数组顺序使奇数位于偶数前面
 *
 * @author tobebetter9527
 * @create 2023/01/30 20:08
 */
public class Offer21 {

    /**
     * time complexity is O(n), space complexity is O(1)
     *
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) {
                left++;
            }
            while (left < right && (nums[right] & 1) == 0) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
