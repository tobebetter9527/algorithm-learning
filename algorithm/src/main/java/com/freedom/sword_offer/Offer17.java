package com.freedom.sword_offer;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * @author tobebetter9527
 * @create 2023/01/08 16:56
 */
public class Offer17 {

  public static int[] printNumbers(int n) {
    int num = (int) Math.pow(10, n) - 1;
    int[] ans = new int[num];
    for (int i = 0; i < num; i++) {
      ans[i] = i + 1;
    }
    return ans;
  }

  public static void main(String[] args) {
    int n = 1;
    int[] ints = printNumbers(n);
    System.out.println(ints);
    for (int ans : ints) {
      System.out.println(ans);
    }
  }
}
