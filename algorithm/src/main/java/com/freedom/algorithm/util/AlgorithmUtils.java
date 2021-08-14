package com.freedom.algorithm.util;

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

  public static void println(int[] arr) {
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

  /**
   * 对数器，产生长度随机大小，数据随机大小的数组
   *
   * @param maxSize  最长数组值
   * @param maxValue 最大值
   * @return 数组
   */
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    if (maxSize < 0 || maxValue < 0) {
      throw new IllegalArgumentException("args maxSize or maxValue is less than 0");
    }

    int size = (int) (Math.random() * maxSize);
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      // 范围从-maxValue到maxValue
      arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
    }
    return arr;
  }

  public static boolean isEqual(int[] arr1, int[] arr2) {
    if (arr1 == null && arr2 != null) {
      return false;
    }
    if (arr1 != null && arr2 == null) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  public static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int length = arr.length;
    int[] copyArr = new int[length];
    for (int i = 0; i < length; i++) {
      copyArr[i] = arr[i];
    }
    return copyArr;
  }

  public static void main(String[] args) {
    int[] ints = generateRandomArray(100, 100);
    println(ints);
  }
}
