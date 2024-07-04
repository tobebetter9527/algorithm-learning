package com.freedom.leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. Sliding Window Maximum
 *
 * @author tobebetter9527
 * @create 2022/11/04 21:02
 */
public class Problem239_SlidingWindowMaximum {

    /**
     * time complexity is O(n),space complexity is O(k).
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        // 头部保存最大值，尾部进出值
        Deque<Integer> deque = new LinkedList<>();
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
                deque.pollLast();
            }
            deque.addLast(right);

            if (right - left + 1 > k) {
                if (left >= deque.peekFirst()) {
                    deque.pollFirst();
                }
                left++;
            }

            if (right - left + 1 == k) {
                ans[index++] = nums[deque.peekFirst()];
            }
        }

        return ans;
    }


    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int index = 0;
        // 头部保存最大值，尾部进出值
        Deque<Integer> deque = new LinkedList<>();
        for (int right = 0; right < n; right++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
                deque.pollLast();
            }
            deque.addLast(right);

            if (deque.peekFirst() < right - k + 1) {
                deque.pollFirst();
            }

            if (right >= k - 1) {
                ans[index++] = nums[deque.peekFirst()];
            }
        }

        return ans;
    }

    /**
     * 这个版本更好，比第一个版本少一个if判断。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;

        // 头部保存最大值，尾部进出值
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        int[] ans = new int[n - k + 1];
        int index = 0;
        ans[index++] = nums[deque.peekFirst()];

        for (int right = k; right < n; right++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
                deque.pollLast();
            }
            deque.addLast(right);

            if (deque.peekFirst() == right - k) {
                deque.pollFirst();
            }

            ans[index++] = nums[deque.peekFirst()];
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] ints = maxSlidingWindow3(nums, k);
        System.out.println(ints);

    }
}
