package com.freedom.algorithm;

/**
 * 工具类
 *
 * @author tobebetter9527
 * @create 2021/08/12 23:13
 */
public final class AlgorithmUtils {

  private AlgorithmUtils() {
  }

  public static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }

  public static void printin(int[] arr) {
    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println("");
  }

  public static void convertToBinary(int a) {
    for (int i = 31; i >= 0; i--) {
      System.out.print(((a >> i) & 1) == 0 ? "0" : "1");
    }
    System.out.println("");
  }

  public static void convertToBinary(long a) {
    for (int i = 63; i >= 0; i--) {
      System.out.print(((a >> i) & 1) == 0 ? "0" : "1");
    }
    System.out.println("");
  }


  public static void main(String[] args) {
    long a = Long.MAX_VALUE;
    convertToBinary(a);
    System.out.println(a);
  }
}
