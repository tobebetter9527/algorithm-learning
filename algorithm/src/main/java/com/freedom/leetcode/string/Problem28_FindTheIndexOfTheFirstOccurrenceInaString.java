package com.freedom.leetcode.string;

/**
 * 28. Find the Index of the First Occurrence in a String
 *
 * @author tobebetter9527
 * @create 2022/10/23 17:37
 */
public class Problem28_FindTheIndexOfTheFirstOccurrenceInaString {


  /**
   * BM算法
   * <p>
   * Constraints:
   * <p>
   * 1 <= haystack.length, needle.length <= 104
   * <p>
   * haystack and needle consist of only lowercase English characters.
   *
   * @param haystack 主串
   * @param needle   模式串
   */
  public static int bm(String haystack, String needle) {
    if (haystack.length() < needle.length()) {
      return -1;
    }
    char[] mainChars = haystack.toCharArray();
    int n = mainChars.length;
    char[] modelChars = needle.toCharArray();
    int m = modelChars.length;
    // 1.坏字符规则，生成模式串的字符索引映射,如果有相同的字符，保存最右边的索引
    int[] badCharMap = generateBadCharMap(modelChars, m);

    // 2. 好后缀规则，
    int[] suffix = new int[m];
    boolean[] prefix = new boolean[m];
    generateSuffixAndPrefix(modelChars, m, suffix, prefix);

    // BM算法大框架
    // i表示主串与模式串匹配的起始索引
    int i = 0;
    while (i <= n - m) {
      // 从模式串后往前匹配
      int j;
      for (j = m - 1; j >= 0; j--) {
        if (mainChars[i + j] != modelChars[j]) {
          break;
        }
      }
      // 已经匹配到了
      if (j < 0) {
        return i;
      }

      int x = j - badCharMap[mainChars[i + j]];
      int y = 0;
      // 如果有好后缀
      if (j < m - 1) {
        y = calculateY(suffix, prefix, j, m);
      }

      i = i + Math.max(x, y);
    }
    return -1;
  }

  private static int calculateY(int[] suffix, boolean[] prefix, int j, int m) {
    int k = m - j - 1;
    if (suffix[k] != -1) {
      return j - suffix[k] + 1;
    }
    for (int r = j + 2; r < m; r++) {
      if (prefix[m - r]) {
        return r;
      }
    }
    return m;
  }

  private static void generateSuffixAndPrefix(char[] modelChars, int m, int[] suffix, boolean[] prefix) {
    for (int i = 0; i < m; i++) {
      suffix[i] = -1;
      prefix[i] = false;
    }
    // i < m - 1，是因为长度不能超过m-1
    for (int i = 0; i < m - 1; i++) {
      int j = i;
      // 公共后缀子串
      int k = 0;
      while (j >= 0 && modelChars[j] == modelChars[m - 1 - k]) {
        j--;
        k++;
        suffix[k] = j + 1;
      }
      if (j < 0) {
        prefix[k] = true;
      }
    }
  }

  private static int[] generateBadCharMap(char[] modelChars, int m) {
    // 用数组比较局限，仅适用英文字母等ASCII码；如果字符集比较大，要用map。
    int[] badCharMap = new int[256];
    // 默认值为-1；
    for (int i = 0; i < 256; i++) {
      badCharMap[i] = -1;
    }
    for (int i = 0; i < m; i++) {
      badCharMap[modelChars[i]] = i;
    }
    return badCharMap;
  }

  // --------------------//

  /**
   * 暴力法
   * <p>
   * time complexity is O(n*m), space complexity is O(1).l
   *
   * @param haystack length >= 1
   * @param needle   length >= 1
   * @return
   */
  public static int strStr(String haystack, String needle) {
    if (haystack.length() < needle.length()) {
      return -1;
    }
    char[] arrN = haystack.toCharArray();
    char[] arrM = needle.toCharArray();
    int n = haystack.length(), m = needle.length();
    for (int i = 0; i < n; i++) {
      int index = i;
      boolean flag = true;
      for (int j = 0; j < m; j++) {
        if (arrN[index++] != arrM[j]) {
          flag = false;
          break;
        }
      }
      if (flag) {
        return i;
      }
    }
    return -1;
  }

  // --------------------//

  public static int kmp(String haystack, String needle) {
    if (needle.length() == 0) {
      return 0;
    }
    if (haystack.length() < needle.length()) {
      return -1;
    }
    char[] mainChar = haystack.toCharArray();
    int n = mainChar.length;
    char[] modelChar = needle.toCharArray();
    int m = modelChar.length;
    int[] nexts = generateNexts(modelChar, m);

    int k = -1;
    for (int i = 0; i < n ; i++) {
      while (k != -1 && mainChar[i] != modelChar[k + 1]) {
        k = nexts[k];
      }

      if (mainChar[i] == modelChar[k + 1]) {
        k++;
      }

      if (k + 1 == m) {
        return i - k;
      }
    }
    return -1;
  }

  private static int[] generateNexts(char[] modelChar, int m) {
    int[] nexts = new int[m];
    // 第一个索引就是为-1
    nexts[0] = -1;
    // k默认是-1；
    int k = -1;
    // 从1开始
    for (int i = 1; i < m; i++) {
      // 如果modelChar[k + 1] != modelChar[i]，往前找到已经匹配的位置
      while (k != -1 && modelChar[k + 1] != modelChar[i]) {
        k = nexts[k];
      }

      if (modelChar[k + 1] == modelChar[i]) {
        k = k + 1;
      }

      nexts[i] = k;
    }
    return nexts;
  }


  public static void main(String[] args) {
    String haystack = "aaa";
    String needle = "aaa";
    int bm = kmp(haystack, needle);
    System.out.println(bm);
  }

}
