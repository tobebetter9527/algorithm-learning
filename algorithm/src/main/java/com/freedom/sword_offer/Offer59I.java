package com.freedom.sword_offer;

import java.util.Deque;
import java.util.LinkedList;

public class Offer59I {

  public static int[] maxSlidingWindow(int[] nums, int k) {
    int length = nums.length;
    // 返回值
    int[] res = new int[length - k + 1];
    int idx = 0, left = -1;
    // 构造单调双端队列
    Deque<Integer> deque = new LinkedList<>();
    // k = right - left;
    for (int right = 0; right < length; right++) {
      // 头大尾小，并且尾部用来进出数据，注意这里保存的是索引
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
        deque.pollLast();
      }
      deque.addLast(right);

      // 如果越界，需要把越界的删除
      if (deque.peekFirst() <= left) {
        deque.pollFirst();
      }

      // 窗口大小
      if (k == (right - left)) {
        res[idx++] = nums[deque.peekFirst()];
        left++;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {1, -1};
    int k = 1;
    int[] res = maxSlidingWindow(nums, k);
    for (int i : res) {
      System.out.print(i + " ");
    }
  }

}
