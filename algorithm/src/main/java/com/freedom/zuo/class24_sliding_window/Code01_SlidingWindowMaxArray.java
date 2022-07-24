package com.freedom.zuo.class24_sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * <p>
 * 返回每一次滑出状况的最大值
 * <p>
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3 返回：[5,5,5,4,6,7]
 *
 * @author tobebetter9527
 * @create 2022/07/24 9:19
 */
public class Code01_SlidingWindowMaxArray {

  /**
   * 暴力方法求解
   *
   * @param arr 数组
   * @param w   窗口大小
   * @return 每一次滑出状况的最大值
   */
  public static int[] right(int[] arr, int w) {
    if (arr == null || w < 1 || arr.length < w) {
      return null;
    }
    int n = arr.length;
    int[] ans = new int[n - w + 1];
    int left = 0;
    int right = w - 1;
    int index = 0;
    int max;
    while (right < n) {
      max = arr[left];
      for (int i = left + 1; i <= right; i++) {
        max = Math.max(max, arr[i]);
      }

      ans[index++] = max;
      left++;
      right++;
    }

    return ans;
  }

  // ---------------------------------------------------------------- //

  /**
   * 通过滑动窗口获取最大值
   *
   * @param arr 数组
   * @param w   窗口大小
   * @return 每一次滑出状况的最大值
   */
  public static int[] getMaxWindow(int[] arr, int w) {
    if (arr == null || w < 1 || arr.length < w) {
      return null;
    }
    int n = arr.length;
    int[] ans = new int[n - w + 1];
    int index = 0;
    // 双端队列，保存下标；从头到尾，数值依次变小。从头获取最大值，尾巴进出数值
    Deque<Integer> deque = new LinkedList<>();
    for (int right = 0; right < n; right++) {
      // 如果队列为空，直接从尾巴进入，如果尾巴的值小于等于将进入的数值，弹出后，放入新的值
      while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[right]) {
        deque.pollLast();
      }
      deque.addLast(right);

      // 保证队列的宽度不超过w，left = right - w,如果超过，则从队列头弹出下标
      if (deque.peekFirst() == right - w) {
        deque.pollFirst();
      }

      // 窗口形成了
      if (right >= w - 1) {
        ans[index++] = arr[deque.peekFirst()];
      }
    }
    return ans;
  }

  // ---------------------------------------------------------------- //

  // for test
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * (maxValue + 1));
    }
    return arr;
  }

  // for test
  public static boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int testTime = 100000;
    int maxSize = 100;
    int maxValue = 100;
    System.out.println("test begin");
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      int w = (int) (Math.random() * (arr.length + 1));
      int[] ans1 = getMaxWindow(arr, w);
      int[] ans2 = right(arr, w);
      if (!isEqual(ans1, ans2)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish");
  }
}
