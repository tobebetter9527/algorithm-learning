package com.freedom.leetcode.bit_manipulation;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * @author tobebetter9527
 * @create 2022/06/03 21:44
 */
public class Problem190_ReverseBits {

  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    StringBuilder sb = new StringBuilder(32);
    for (int i = 0; i < 32; i++) {
      sb.append((n >> i) & 1);
    }
    return Long.valueOf(sb.toString(), 2).intValue();
  }

  /**
   * 官方写法
   *
   * @param n
   * @return
   */
  public int reverseBits2(int n) {
    int rev = 0;
    for (int i = 0; i < 32 && n != 0; i++) {
      rev |= (n & 1) << (31 - i);
      n >>>= 1;
    }
    return rev;
  }

}
