package com.freedom.zuo.class19_dynamic_programming2;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * <p>
 * 那么一个数字字符串比如"111”就可以转化为:
 * <p>
 * "AAA"、"KA"和"AK"
 * <p>
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 *
 * @author tobebetter9527
 * @create 2022/07/16 14:39
 */
public class Code02_ConvertToLetterString {

  /**
   * 暴力迭代
   *
   * @param str
   * @return
   */
  public static int number(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }

    return process(str.toCharArray(), 0);
  }

  /**
   * str[0..i-1]转化无需过问
   * <p>
   * str[i.....]去转化，返回有多少种转化方法
   *
   * @param chars
   * @param i
   */
  private static int process(char[] chars, int i) {
    // 字符最后一个字符索引是length -1, 此时来到length，无论如何都有一种解法
    if (i == chars.length) {
      return 1;
    }
    // 这种情况，未到最后的length位置，此时无解
    if (chars[i] == '0') {
      return 0;
    }

    // 取i位置对应字母
    int ways = process(chars, i + 1);

    // 取i 和 i+1 位置对应字母，
    if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
      ways += process(chars, i + 2);
    }

    return ways;
  }

  // --------------------------------------------------------- //

  /**
   * 动态规划
   *
   * @param str
   * @return
   */
  public static int dp(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }

    char[] chars = str.toCharArray();
    int n = str.length();
    int[] result = new int[n + 1];

    // 字符最后一个字符索引是length -1, 此时来到length，无论如何都有一种解法
    result[n] = 1;

    // 从右往左填数据
    for (int i = n - 1; i >= 0; i--) {
      if (chars[i] != '0') {
        // 取i位置对应字母
        int ways = result[i + 1];
        // 取i 和 i+1 位置对应字母，
        if (i + 1 < n && ((chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27)) {
          ways += result[i + 2];
        }

        result[i] = ways;
      }
    }

    return result[0];
  }


  public static void main(String[] args) {
    int maxLength = 100;
    int testTimes = 100000;
    for (int i = 0; i < testTimes; i++) {
      String str = generateStr(maxLength);
      System.out.println(str);
      if (number(str) != dp(str)) {
        System.out.println("Wrong!");
      }
    }
    System.out.println("done!");
  }

  private static String generateStr(int maxLength) {
    int length = (int) (Math.random() * maxLength);
    char[] chars = new char[length];
    for (int i = 0; i < length; i++) {
      chars[i] = (char) ((int) (Math.random() * 10) + 48);
    }
    return String.valueOf(chars);
  }

}
