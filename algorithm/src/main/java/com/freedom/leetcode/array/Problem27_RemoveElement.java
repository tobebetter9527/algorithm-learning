package com.freedom.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Remove Element
 *
 * @author tobebetter9527
 * @create 2022/10/11 20:27
 */
public class Problem27_RemoveElement {

  public static List<Integer> right(int[] nums, int val) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    List<Integer> list = new ArrayList<>();
    for (int num : nums) {
      if (num != val) {
        list.add(num);
      }
    }

    Collections.sort(list);
    return list;
  }

  /**
   * time complexity is O(n)
   * <p>
   * space complexity is O(1)
   */
  public static int removeElement(int[] nums, int val) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int slow = 0;
    int fast = 0;
    while (fast < nums.length) {
      if (nums[fast] != val) {
        nums[slow++] = nums[fast];
      }
      fast++;
    }
    return slow;
  }



  public static void main(String[] args) {
    int testTimes = 1000000;
    int maxLength = 100;
    int maxValue1 = 50;
    int maxValue2 = 100;

    for (int i = 0; i < testTimes; i++) {
      int[] nums = generateNums(maxLength, maxValue1);
      int val = generateValue(maxValue2);

      List<Integer> list = right(nums, val);
      int k = removeElement(nums, val);
      if (list.size() == k) {
        Arrays.sort(nums, 0, k);
        for (int j = 0; j < k; j++) {
          if (list.get(j) != nums[j]) {
            System.out.println("val=" + val);
            printArray(nums);
            System.out.println("-----------------------");
          }
        }
      } else {
        System.out.println("val=" + val);
        printArray(nums);
        System.out.println("-----------------------");
      }
    }
    System.out.println("done");
  }

  private static int[] generateNums(int maxLength, int maxValue1) {
    int length = generateValue(maxLength);
    int[] nums = new int[length];
    for (int i = 0; i < length; i++) {
      nums[i] = generateValue(maxValue1);
    }
    return nums;
  }


  private static int generateValue(int maxValue2) {
    return (int) (Math.random() * (maxValue2 + 1));
  }

  private static void printArray(int[] nums) {
    if (nums != null) {
      for (int num : nums) {
        System.out.print(num + " ");
      }

    }
  }
}
