package com.freedom.sword_offer;

public class Offer51 {
    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right) + merge(nums, left, mid, right);
    }

    private static int merge(int[] nums, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int index1 = mid;
        int index2 = right;
        int midplus = mid + 1;
        int pairs = 0;
        boolean flag;
        while (index1 >= left && index2 >= midplus) {
            flag = nums[index1] > nums[index2];
            pairs += flag ? (index2 - midplus + 1) : 0;
            help[i--] = flag ? nums[index1--] : nums[index2--];
        }

        while (index1 >= left) {
            help[i--] = nums[index1--];
        }
        while (index2 >= midplus) {
            help[i--] = nums[index2--];
        }

        for (int j = 0; j < help.length; j++) {
            nums[left + j] = help[j];
        }
        return pairs;
    }

    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4};
        System.out.println(reversePairs(nums));
    }


}
