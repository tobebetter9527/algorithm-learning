package com.freedom.leetcode.sort;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
public class Problem215_KthLargestElementInAnArray {

  public static int findKthLargest(int[] nums, int k) {
    return process(nums, k, 0, nums.length - 1);
  }

  private static int process(int[] nums, int k, int left, int right) {
    if (left == right) {
      return nums[left];
    }
    swap(nums, left + (int) (Math.random() * (right - left + 1)), left);
    int p = partition(nums, left, right);
    if (p + 1 == k) {
      return nums[p];
    } else if (p + 1 < k) {
      return process(nums, k, p + 1, right);
    } else {
      return process(nums, k, left, p - 1);
    }
  }

  private static int partition(int[] nums, int left, int right) {
    int pivot = left;
    int more = right + 1;
    int index = right;
    while (index > left) {
      if (nums[index] < nums[pivot]) {
        swap(nums, index, --more);
      }
      index--;
    }
    swap(nums, pivot, --more);
    return more;
  }

  private static void swap(int[] arr, int index, int i) {
    int temp = arr[index];
    arr[index] = arr[i];
    arr[i] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    int k = 2;
    System.out.println(findKthLargest(nums, k));
  }
}
