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
    int[] arr = new int[]{33, 33, 33, 88, 88, 99, 99, 55, 55, 55, 55, 55};
    printOddTimesNum2(arr);
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
