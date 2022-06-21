package com.freedom.zuo.class07_enhance_heap_sort;

/**
 * 最大线段重合问题
 *
 * @author tobebetter9527
 * @create 2022/06/21 22:05
 */
public class Code01_CoverMax {

  /**
   * 暴力遍历法
   *
   * @param lines
   * @return
   */
  public static int maxCover1(int[][] lines) {
    int length = lines.length;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < length; i++) {
      min = Math.min(min, lines[i][0]);
      max = Math.max(max, lines[i][1]);
    }

    int cover = 0;
    for (double p = min + 0.5; p < max; p += 1) {
      int cur = 0;
      for (int i = 0; i < length; i++) {
        if (p > lines[i][0] && p < lines[i][1]) {
          cur++;
        }
      }
      cover = Math.max(cover, cur);
    }

    return cover;
  }

}
