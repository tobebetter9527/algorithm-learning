package com.freedom.zuo.class28_manacher;

/**
 * manachar算法
 *
 * @author tobebetter9527
 * @create 2022/08/01 20:02
 */
public class Code01_Manacher {

  /**
   * 暴力法
   *
   * @param str
   * @return
   */
  public static int right(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }
    int max = 0;
    char[] chars = manacherString(str);
    int length = chars.length;
    for (int i = 0; i < length; i++) {
      int left = i - 1;
      int right = i + 1;
      while (left >= 0 && right < length && chars[left] == chars[right]) {
        left--;
        right++;
      }
      max = Math.max(max, right - left - 1);
    }

    return max / 2;
  }

  private static char[] manacherString(String str) {
    char[] chars = new char[str.length() * 2 + 1];
    int index = 0;
    for (int i = 0; i < chars.length; i++) {
      chars[i] = (i & 1) == 0 ? '#' : str.charAt(index++);
    }
    return chars;
  }

  // --------------------------------------------------------- //

  public static int manacher(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }

    int max = Integer.MIN_VALUE;

    char[] chars = manacherString(str);
    // 回文半径数组，当前i位置的回文abiba,直径是5，半径就是（5+1)/2
    int[] pArr = new int[chars.length];
    // 取得回文半径最右边界时的中心点C
    int C = -1;
    // 回文半径最右边界，这里指边界再过去一位
    int R = -1;

    for (int i = 0; i < chars.length; i++) {
      // i >= R 当前位置超过最大的回文边界，最少能取得的回文大小是1，
      // 或者就是如下，这里画图分析 L…(i`)…C…(i)…R 的结构，以及根据i’回文长度进行的状况划分
      // 2 * C - i 就是i'的位置
      pArr[i] = i >= R ? 1 : Math.min(R - i, pArr[2 * C - i]);
      // 现在向两边扩
      while (i + pArr[i] < chars.length && i - pArr[i] >= 0) {
        if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
          pArr[i]++;
        } else {
          break;
        }
      }

      // 更新R和C
      if (i + pArr[i] > R) {
        R = i + pArr[i];
        C = i;
      }

      max = Math.max(max, pArr[i]);
    }

    return max - 1;
  }

  // --------------------------------------------------------- //


  // for test
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
    int testTimes = 5000000;
    System.out.println("test begin");
    for (int i = 0; i < testTimes; i++) {
      String str = getRandomString(possibilities, strSize);
      if (manacher(str) != right(str)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish");
  }


}
