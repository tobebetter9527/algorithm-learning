package com.freedom.zuo.class11_binary_tree;

/**
 * 纸张对折打印问题
 *
 * @author tobebetter9527
 * @create 2022/07/02 10:58
 */
public class Code07_PaperFolding {

  public static void printAllFolds(int n) {
    process(1, n, true);
    System.out.println("");
  }

  /**
   * 中序遍历
   *
   * @param i
   * @param n
   * @param b
   */
  private static void process(int i, int n, boolean b) {
    if (i > n) {
      return;
    }
    process(i + 1, n, true);
    System.out.println(b ? i + ":down" : i + ":up");
    process(i + 1, n, false);
  }

  public static void main(String[] args) {
    printAllFolds(4);
  }

}
