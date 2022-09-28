package com.freedom.wangzheng;

/**
 * BM algorithm
 *
 * @author tobebetter9527
 * @create 2022/09/25 11:40
 */
public class Class33_String_BM {

  /**
   * 假设str的字符集在ASCII范围
   *
   * @param str     主串
   * @param pattern 模式串
   * @return 第一个匹配的下标，如果没有匹配返回-1
   */
  public static int bm(String str, String pattern) {
    if (str == null || pattern == null) {
      return -1;
    }
    return bm(str.toCharArray(), str.length(), pattern.toCharArray(), pattern.length());
  }


  /**
   * @param a 主串
   * @param n 主串长度
   * @param b 模式串
   * @param m 模式串长度
   * @return 第一个匹配的下标，如果没有匹配返回-1
   */
  private static int bm(char[] a, int n, char[] b, int m) {
    // 处理坏字符在模式串的索引位置
    int[] bc = gernerateBC(b, m);

    // 处理模式串的后缀子串和前缀子串问题
    int[] suffix = new int[m];
    boolean[] prefix = new boolean[m];
    gernerateGS(b, m, suffix, prefix);

    // i表示主串与模式串匹配的开始位置
    int i = 0;
    while (i <= n - m) {
      int j = 0;
      for (j = m - 1; j >= 0; --j) {
        // 碰到坏字符了
        if (b[j] != a[i + j]) {
          break;
        }
      }

      // 说明模式串已经完成匹配
      if (j < 0) {
        return i;
      }

      int p1 = j - bc[a[i + j]];

      int p2 = 0;
      // 如果有好后缀的话
      if (j < m - 1) {
        p2 = moveByGS(j, m, suffix, prefix);
      }

      // 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
      // bc[(int) a[i+j]] 表示xi位置，j就是si位置
      i = i + Math.max(p1, p2);
    }

    return -1;
  }

  private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
    int k = m - j;
    if (suffix[k] != -1) {
      return j - suffix[k] + 1;
    }

    for (int r = j + 2; r <= m - 1; r++) {
      if (prefix[m - r]) {
        return r;
      }
    }

    return m;
  }

  private static void gernerateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
    for (int i = 0; i < m; i++) {
      suffix[i] = -1;
      prefix[i] = false;
    }

    for (int i = 0; i <= m - 2; i++) {
      int j = i;
      // 公共子串长度
      int k = 0;
      while (j >= 0 && b[j] == b[m - 1 - k]) {
        j--;
        k++;

        suffix[k] = j + 1;
      }

      if (j == -1) {
        prefix[k] = true;
      }
    }
  }

  private static int[] gernerateBC(char[] b, int m) {
    // 假设str的字符集在ASCII范围,
    int[] bc = new int[256];

    // 默认值给-1，表示当前的坏字符在模式串在不存在
    for (int i = 0; i < 256; i++) {
      bc[i] = -1;
    }

    for (int i = 0; i < m; i++) {
      int ascii = b[i];
      bc[ascii] = i;
    }

    return bc;
  }

  // -----------------------------------//


  public static void main(String[] args) {
    //    int testTimes = 100000;
    //    int maxStrLength = 10;
    //    for (int i = 0; i < testTimes; i++) {
    //      String str = generateStr(maxStrLength);
    //      String pattern = generateStr(str.length() - 1);
    //      if (bm(str, pattern) != str.indexOf(pattern)) {
    //        System.out.println("wrong");
    //      }
    //    }
    //    System.out.println("done");

    String str = "aaaaaaaaaaa";
    String pattern = "";
    int bm = bm(str, pattern);
    System.out.println(bm);


  }

  private static String generateStr(int maxStrLength) {
    int length = (int) (Math.random() * maxStrLength) + 1;
    char[] chars = new char[length];
    for (int i = 0; i < length; i++) {
      chars[i] = (char) (Math.random() * 26 + 97);
    }
    return String.valueOf(chars);
  }


}
