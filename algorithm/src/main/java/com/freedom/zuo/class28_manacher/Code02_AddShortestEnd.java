package com.freedom.zuo.class28_manacher;

/**
 * 在字符串末尾加上最短的字符串，使其成为回文字串 palindromic
 */
public class Code02_AddShortestEnd {

  public static String shortestEnd(String str) {
    if (str == null || str.length() == 0) {
      return null;
    }

    char[] chars = manacharString(str);
    // 当前回文最右边界所在的索引
    int C = -1;
    // 回文最右边界，再过去一位
    int R = -1;
    // 回文半径数组，i位置回文abiba，回文直径是5，回文半径是3
    int[] pArr = new int[chars.length];

    int maxContainsEnd = -1;

    for (int i = 0; i < chars.length; i++) {
      // 当前最少能扩到的范围
      pArr[i] = i >= R ? 1 : Math.min(R - i, pArr[2 * C - i]);
      // i位置向两边扩
      while (i + pArr[i] < chars.length && i - pArr[i] >= 0) {
        if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
          pArr[i]++;
        } else {
          break;
        }
      }

      // 是否需要更新C和R
      if (i + pArr[i] > R) {
        R = i + pArr[i];
        C = i;
      }

      // 是否已经扩到最右边界了
      if (R == chars.length) {
        maxContainsEnd = pArr[i];
        break;
      }
    }
    // 这里的maxContainsEnd可以举例计算，比如abc12321
    char[] ss = new char[str.length() - (maxContainsEnd -1)];
    for (int i = 0; i < ss.length; i++) {
      // 从后面开始填
      ss[ss.length -1 - i] = str.charAt(i);
    }

    return String.valueOf(ss);
  }

  private static char[] manacharString(String str) {
    char[] chars = new char[str.length() * 2 + 1];
    int index = 0;
    for (int i = 0; i < chars.length; i++) {
      chars[i] = (i & 1) == 0 ? '#' : str.charAt(index++);
    }
    return chars;
  }

  public static void main(String[] args) {
    String str = "abc12321";
    System.out.println(shortestEnd(str));
  }
}
