package com.freedom.wangzheng;

import com.sun.javafx.robot.FXRobotImage;

/**
 * TODO
 *
 * @author tobebetter9527
 * @create 2022/09/28 20:00
 */
public class BM_algorithm {

  public static int bm(String a, String b) {
    if (a == null || b == null) {
      return -1;
    }
    return bm(a.toCharArray(), a.length(), b.toCharArray(), b.length());
  }

  private static int bm(char[] a, int n, char[] b, int m) {
    int[] bc = generateBC(b, m);

    int[] suffix = new int[m];
    boolean[] prefix = new boolean[m];
    gernerateGS(b, m, suffix, prefix);

    int i = 0;
    while (i <= n - m) {
      int j;
      for (j = m - 1; j >= 0; j--) {
        if (a[i + j] != b[j]) {
          break;
        }
      }

      if (j < 0) {
        return i;
      }

      int p1 = j -  bc[a[i+j]];

      int p2 = 0;
      if (j < m - 1) {
        p2 = moveByGS(j, m, suffix, prefix);
      }

      i = i + Math.max(p1, p2);
    }
    return -1;
  }

  private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
    int k = m- 1 -j;
    if (suffix[k] != -1) {
      return j + 1 - suffix[k];
    }

    for (int r = j + 2; r < m; r++) {
      if (prefix[m - 1 - r + 1]) {
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

    for (int i = 0; i < m - 1; i++) {
      int j = i;
      int k = 0;
      while (j >= 0 && b[j] == b[m - 1 - k]) {
        j--;
        k++;
      }
      suffix[k] = j +1;

      if (j < 0) {
        prefix[k] = true;
      }
    }
  }


  private static int[] generateBC(char[] b, int m) {
    int[] bc = new int[256];
    for (int i = 0; i < 256; i++) {
      bc[i] = -1;
    }
    for (int i = 0; i < m; i++) {
      int ascii = b[i];
      bc[ascii] = i;
    }

    return bc;
  }


  public static String getRandomString(int possibilities, int size) {
    char[] ans = new char[(int) (Math.random() * size) + 1];
    for (int i = 0; i < ans.length; i++) {
      ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
    }
    return String.valueOf(ans);
  }

    public static void main(String[] args) {
      int possibilities = 5;
      int strSize = 20;
      int matchSize = 5;
      int testTimes = 5000000;
      System.out.println("test begin");
      for (int i = 0; i < testTimes; i++) {
        String str = getRandomString(possibilities, strSize);
        String match = getRandomString(possibilities, matchSize);
        if (bm(str, match) != str.indexOf(match)) {
          System.out.println("Oops!");
        }
      }
      System.out.println("test finish");
    }
}
