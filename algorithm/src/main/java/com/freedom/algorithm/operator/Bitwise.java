package com.freedom.algorithm.operator;

import static com.freedom.algorithm.util.AlgorithmUtils.*;
import static com.freedom.algorithm.util.AlgorithmUtils.convertToBinary;

/**
 * 异或运算
 *
 * @author tobebetter9527
 * @create 2021/08/14 11:25
 */
public class Bitwise {

  public static void main(String[] args) {
    int[] arr = new int[]{22, 22, 33, 33, 33, 44, 44, 44, 888, 888, 888, 999, 999, 999};
    int i = onlyKTimes(arr, 3);
    System.out.println(i);

  }

  /**
   * 一个数组中有一种数出现K次，其他数都出现了M次， M > 1,  K < M 找到，出现了K次的数， 要求，额外空间复杂度O(1)，时间复杂度O(N)
   *
   * @param arr 数组
   */
  public static int onlyKTimes(int[] arr, int m) {
    // 32位数组
    int[] t = new int[32];
    for (int num : arr) {
      for (int i = 0; i < 32; i++) {
        // 优化写法，每位数的二进制位1的地方加1
        t[i] += (num >> i) & 1;
        //        if (((num >> i) & 1) != 0) {
        //          t[i] += 1;
        //        }
      }
    }

    int a = 0;
    for (int i = 0; i < 32; i++) {
      // 如果该为数不能为m整除，说明该位累加了k次数的二进制1
      if (t[i] % m != 0) {
        a |= (1 << i);
      }
    }

    return a;
  }


  /**
   * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
   *
   * @param arr 数组
   */
  public static void printOddTimesNum2(int[] arr) {
    if (arr == null) {
      return;
    }
    // temp得到结果为a^b
    int temp = 0;
    for (int i = 0; i < arr.length; i++) {
      temp = temp ^ arr[i];
    }

    // 最右边为1的数据
    int rightOne = temp & (~temp + 1);

    int a = 0;
    for (int i = 0; i < arr.length; i++) {
      if ((arr[i] & rightOne) == 0) {
        a = a ^ arr[i];
      }
    }

    int b = temp ^ a;

    System.out.println(a);
    System.out.println(b);
  }


}
