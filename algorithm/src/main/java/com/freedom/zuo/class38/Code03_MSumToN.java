package com.freedom.zuo.class38;

/**
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * <p>
 * 比如:
 * <p>
 * 5 = 2+3，5就是这样的数
 * <p>
 * 12 = 3+4+5，12就是这样的数
 * <p>
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * <p>
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 * <p>
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 *
 * @author tobebetter9527
 * @create 2022/08/16 20:44
 */
public class Code03_MSumToN {

  public static boolean isMSum1(int num) {
    for (int start = 1; start < num; start++) {
      int sum = start;
      for (int index = start + 1; index < num; index++) {
        if (sum + index > num) {
          break;
        }

        if (sum + index == num) {
          return true;
        }
        sum += index;
      }
    }
    return false;
  }

  public static boolean isMSum(int num) {
   // return (num & (~num + 1)) == num;
    //  return (num & (-num )) == num;
    return (num & (num - 1)) != 0;
  }


  public static void main(String[] args) {
    for (int i = 0; i < 256; i++) {
      // System.out.println(i + ": " + isMSum1(i));
      if (isMSum1(i) != isMSum(i)) {
        System.out.println("wrong");
      }
    }
  }
}
