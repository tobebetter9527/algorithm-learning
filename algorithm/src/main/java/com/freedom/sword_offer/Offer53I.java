package com.freedom.sword_offer;

public class Offer53I {

    public int search(int[] nums, int target) {
        int firstIdx = -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (low == mid || nums[mid - 1] != target) {
                    firstIdx = mid;
                    break;
                } else {
                    high = mid - 1;
                }
            }
        }

        int secondIdx = -1;
        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (high == mid || nums[mid + 1] != target) {
                    secondIdx = mid;
                    break;
                } else {
                    low = mid + 1;
                }
            }
        }

        if (firstIdx == -1 && secondIdx == -1) {
            return 0;
        } else if (firstIdx == -1 || secondIdx == -1) {
            return 1;
        } else {
            return secondIdx - firstIdx + 1;
        }
    }

}
