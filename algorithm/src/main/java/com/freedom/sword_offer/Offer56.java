package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/03/04 21:57
 */
public class Offer56 {

  public int[] singleNumbers(int[] nums) {
    // a,b未不重复的数，x = a ^ b
    int x = 0;
    for (int num : nums) {
      x = x ^ num;
    }

    // 找最右的1, x & (-x)
    int rightOne = x & (~x + 1);
    int a = 0;
    for (int num : nums) {
      if ((rightOne & num) == rightOne) {
        a ^= num;
      }
    }

    int b = x ^ a;
    return new int[]{a, b};
  }
}
